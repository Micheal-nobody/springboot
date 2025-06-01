package com.example.demo.Serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;


//自定有SimpleGrantedAuthority反序列化器
public class SimpleGrantedAuthorityDeserializer extends JsonDeserializer<SimpleGrantedAuthority> {

    @Override
    public SimpleGrantedAuthority deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        JsonToken jsonToken = jsonParser.currentToken();

        if ( jsonToken == JsonToken.START_OBJECT){
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            String authority = node.get("authority").asText();
            return new SimpleGrantedAuthority(authority);

        }else if( jsonToken == JsonToken.VALUE_STRING) {
             String authority = jsonParser.getText();
             return  new SimpleGrantedAuthority(authority);
        }

        throw new RuntimeException("Invalid Json format");
    }

}
