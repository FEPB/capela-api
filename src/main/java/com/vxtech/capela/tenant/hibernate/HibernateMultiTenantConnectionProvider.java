
package com.vxtech.capela.tenant.hibernate;

import com.vxtech.capela.tenant.TenantContext;
import com.vxtech.capela.tenant.TenantNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
@Component
public class HibernateMultiTenantConnectionProvider implements MultiTenantConnectionProvider{

     private static final long serialVersionUID = 7824425137774902943L;

     private final DataSource dataSource;

     public HibernateMultiTenantConnectionProvider(DataSource dataSource){

          this.dataSource = dataSource;
     }

     @Override
     public Connection getAnyConnection() throws SQLException {

          return dataSource.getConnection();
     }

     @Override
     public void releaseAnyConnection(Connection connection) throws SQLException {

          connection.close();
     }

     @Override
     public Connection getConnection(String tenantIdentifier) throws SQLException {

          final Connection connection = getAnyConnection();
          try {

               log.debug("Performing SET search_path TO {} on the database", tenantIdentifier);
               connection.createStatement().execute("SET search_path TO " + tenantIdentifier);
               ResultSet result = connection.createStatement().executeQuery("select current_schema()");
               if (result.next()) {
                    if (result.getString("current_schema") == null) {
                         log.warn("Tenant {} does not exist", tenantIdentifier);
                         throw new TenantNotFoundException("Tenant " + tenantIdentifier + " Not Found");
                    }
               }

          } catch (SQLException e) {

               throw new HibernateException("It was not possible to connect to [" + tenantIdentifier + "]", e);

          }
          return connection;
     }

     @Override
     public void releaseConnection(String tenantIdentifier, Connection connection) throws HibernateException {

          try (connection) {

               log.debug("Performing SET search_path TO {} on the database", TenantContext.DEFAULT_TENANT);
               connection.createStatement().execute("SET search_path TO " + TenantContext.DEFAULT_TENANT);

          } catch (SQLException e) {

               throw new HibernateException("It was not possible to connect to [" + TenantContext.DEFAULT_TENANT + "] when releasing a connection", e);

          }
     }

     @Override
     public boolean isUnwrappableAs(Class unwrapType) {

          return false;
     }

     @Override
     public <T> T unwrap(Class<T> unwrapType) {

          return null;
     }

     @Override
     public boolean supportsAggressiveRelease() {

          return true;
     }

}
