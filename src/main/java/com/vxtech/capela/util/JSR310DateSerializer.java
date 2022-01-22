
package com.vxtech.capela.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Ignore;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

@Ignore
public final class JSR310DateSerializer extends JsonSerializer<TemporalAccessor>{

     private static final DateTimeFormatter ISOFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());

     public static final JSR310DateSerializer INSTANCE = new JSR310DateSerializer();

     JSR310DateSerializer(){

     }

     @Override
     public void serialize(TemporalAccessor value, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {

          generator.writeString(ISOFormatter.format(value));

     }

}
