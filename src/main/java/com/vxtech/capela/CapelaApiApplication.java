package com.vxtech.capela;

import com.vxtech.capela.config.Properties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(value = Properties.class)
@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
public class CapelaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapelaApiApplication.class, args);
	}

}
