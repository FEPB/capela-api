
package com.vxtech.capela.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vxtech.capela.util.JSR310DateSerializer;
import com.vxtech.capela.util.JSR310DateTimeDeserializer;
import com.vxtech.capela.util.JSR310DateTimeSerializer;
import com.vxtech.capela.util.JSR310LocalDateDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.annotation.PostConstruct;
import java.time.*;
import java.util.TimeZone;

@Configuration
public class JacksonConfiguration{

     @Value("${spring.jackson.time-zone:America/Sao_Paulo}")
     private String timeZone;

     @Bean
     public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {

          JavaTimeModule module = new JavaTimeModule();
          module.addSerializer(OffsetDateTime.class, JSR310DateTimeSerializer.INSTANCE);
          module.addSerializer(ZonedDateTime.class, JSR310DateTimeSerializer.INSTANCE);
          module.addSerializer(LocalDateTime.class, JSR310DateTimeSerializer.INSTANCE);
          module.addSerializer(Instant.class, JSR310DateTimeSerializer.INSTANCE);
          module.addSerializer(LocalDate.class, JSR310DateSerializer.INSTANCE);
          module.addDeserializer(LocalDate.class, JSR310LocalDateDeserializer.INSTANCE);
          module.addDeserializer(LocalDateTime.class, JSR310DateTimeDeserializer.INSTANCE);

          return new Jackson2ObjectMapperBuilder().featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).findModulesViaServiceLoader(true).modulesToInstall(module);
     }

     @PostConstruct
     public void timeZoneDefault() {

          TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
          
     }

}