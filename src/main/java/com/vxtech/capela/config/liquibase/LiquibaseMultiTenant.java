
package com.vxtech.capela.config.liquibase;

import br.com.twsoftware.alfred.object.Objeto;
import com.vxtech.capela.tenant.TenantContext;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@AllArgsConstructor
@Log4j2
public class LiquibaseMultiTenant implements InitializingBean{

     String liquibaseMainChangeLog;

     Boolean liquibaseMainEnabled;

     String liquibaseClientsChangeLog;

     Boolean liquibaseClientsEnabled;

     Boolean liquibaseClearLocks;

     Boolean liquibaseClearSums;

     DataSource dataSource;

     @Override
     public void afterPropertiesSet() throws Exception {
          
          runLiquibaseMain();
          runLiquibaseClients();
          
     }

     public void runLiquibaseMain() throws LiquibaseException {

          SpringLiquibase liquibase = genLiquibase(liquibaseMainEnabled, dataSource, liquibaseMainChangeLog, TenantContext.DEFAULT_TENANT);

          clearLocksAndSums(TenantContext.DEFAULT_TENANT, dataSource);
          
          liquibase.afterPropertiesSet();

     }
     
     public void runLiquibaseClients() throws SQLException, LiquibaseException {
          
          
          @Cleanup
          Connection con = dataSource.getConnection();
          con.setSchema(TenantContext.DEFAULT_TENANT);

          @Cleanup
          Statement st = con.createStatement();
          
          @Cleanup
          ResultSet rs = st.executeQuery("SELECT SCHEMA_NAME FROM CLIENTS"); 
          
          while(rs.next()) {
               
               String schemaName = rs.getString(1);
               runLiquibaseClient(schemaName);
               
          }
          
     }

     public void runLiquibaseClient(String schemaName) {

          if(Objeto.notBlank(schemaName)) {
               
               try {
                    
                    createSchema(schemaName, dataSource);
                    
                    SpringLiquibase liquibase = genLiquibase(liquibaseClientsEnabled, dataSource, liquibaseClientsChangeLog, schemaName);
                    
                    clearLocksAndSums(schemaName, dataSource);
                    
                    liquibase.afterPropertiesSet();
                    
               } catch (Exception e) {
                    throw new RuntimeException("[TENANT] - Problem when trying to run liquibase for client: " + schemaName, e);
               }
               
          }
     }     

     public SpringLiquibase genLiquibase(boolean enabled, DataSource dataSource, String changeLog, String schema) {

          SpringLiquibase liquibase = new SpringLiquibase();
          liquibase.setDataSource(dataSource);
          liquibase.setChangeLog(changeLog);
          liquibase.setDefaultSchema(schema);
          liquibase.setShouldRun(enabled);

          return liquibase;

     }

     public void clearLocksAndSums(String schema, DataSource dataSource) {

          try {

               @Cleanup
               Connection con = dataSource.getConnection();
               con.setSchema(schema);

               @Cleanup
               Statement st = con.createStatement();

               if (liquibaseClearLocks) {

                    log.info("[TENANT] - Clear liquibase locks");
                    st.executeUpdate("DELETE FROM DATABASECHANGELOGLOCK");
               }

               if (liquibaseClearSums) {

                    log.info("[TENANT] - Clear liquibase checksums");
                    st.executeUpdate("UPDATE DATABASECHANGELOG SET MD5SUM=NULL");
               }

               con.commit();

          } catch (Exception e) {
               log.error("[TENANT] - Problem while trying to release locks.");
          }

     }

     public void createSchema(String schema, DataSource dataSource) {

          try {

               @Cleanup
               Connection con = dataSource.getConnection();
               
               @Cleanup
               Statement st = con.createStatement();
               st.executeUpdate("CREATE SCHEMA IF NOT EXISTS \"" + schema + "\";");
               
               con.commit();
               
               log.info("[TENANT] - Schema created successful");

          } catch (Exception e) {
               throw new RuntimeException("[TENANT] - Problem when trying to create schema " + schema, e);
          }

     }

}
