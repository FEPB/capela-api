
package com.vxtech.capela.config.liquibase;

import com.vxtech.capela.config.Properties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class LiquibaseConfig{

     public final Properties properties;
     
     @Bean
     public LiquibaseMultiTenant liquibaseMultiTenant(DataSource dataSource) {
          
          Properties.Capela.Liquibase liquibaseProperties = properties.getCapela().getLiquibase();
          Boolean liquibaseMainEnabled = liquibaseProperties.getMain().getEnabled();
          String liquibaseMainChangeLog = liquibaseProperties.getMain().getChangeLog();
          
          Boolean liquibaseClientsEnabled = liquibaseProperties.getClients().getEnabled();
          String liquibaseClientsChangeLog = liquibaseProperties.getClients().getChangeLog();
          
          Boolean liquibaseClearLocks = liquibaseProperties.getClearLocks();
          Boolean liquibaseClearSums = liquibaseProperties.getClearSums();
          
          LiquibaseMultiTenant liquibase = new LiquibaseMultiTenant(liquibaseMainChangeLog, liquibaseMainEnabled, liquibaseClientsChangeLog, liquibaseClientsEnabled, liquibaseClearLocks, liquibaseClearSums, dataSource);
          return liquibase;
          
     }

}
