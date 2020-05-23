package com.slq.production.config;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ListSerializer extends JsonDeserializer<List<String>>{
//JsonSerializer
//	@Override
//	public void serialize(List<String> value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
//		// TODO Auto-generated method stub
//		if(value.isEmpty()) {
//			jsonGenerator.writeArrayFieldStart(null);
//		}else {
//			System.out.println("1");
//			jsonGenerator.writeStartArray(value);
//			//jsonGenerator.isEnabled(JsonGenerator.Feature.a)
//		}
//	}

	@Override
	public List<String> deserialize(JsonParser parser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		System.out.println(JSON.toJSONString(parser));	
		System.out.println("parser.getText()"+parser.getText());
		if(parser!=null&&parser.getTextLength()>0) {
			return JSON.parseArray(parser.getText(), String.class);
		}else {
			return null;
		}
	}

	
}
