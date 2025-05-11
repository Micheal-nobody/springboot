package com.example.demo.Serializer;

import com.example.demo.pojo.Form.Form;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Collections;

public class FormSerializer extends JsonSerializer<Form>{

    @Override
    public void serialize(Form form, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        // List 字段默认空列表
        jsonGenerator.writeObjectField("questions", form.getQuestions() != null ? form.getQuestions() : Collections.emptyList());
    }
}
