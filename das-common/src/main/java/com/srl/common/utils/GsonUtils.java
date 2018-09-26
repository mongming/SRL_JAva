package com.srl.common.utils;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class GsonUtils {

	/**
	 * 注册自定义的Strin适配器,防止空值时显示"null"--改为-->""
	 */
	private static final TypeAdapter<String> STRING = new TypeAdapter<String>() {
		public String read(JsonReader reader) throws IOException {
			if (reader.peek() == JsonToken.NULL) {
				reader.nextNull();
				return "";
			}
			return reader.nextString();
		}
		
		@Override
		public void write(JsonWriter writer, String value) throws IOException {
			if (value == null) {
				// 在这里处理null改为空字符串
				writer.value("");
				return;
			}
			writer.value(value);
		}

	};

	public static final String DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 自定义转对象为json字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		GsonBuilder  gsonBuilder = new GsonBuilder();
		//注册自定义String的适配器
		gsonBuilder.registerTypeAdapter(String.class, STRING);
		//设置输出的时间格式
		gsonBuilder.setDateFormat(DATE_FORMATE);
		Gson gson = gsonBuilder.create();
		return gson.toJson(obj);
	}
}
