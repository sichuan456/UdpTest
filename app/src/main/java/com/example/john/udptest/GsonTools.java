package com.example.john.udptest;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonTools {
	
	private static Gson gson;

	private static Gson getGson() {
		if (null == gson) {
			gson = new Gson();
		}
		return gson;
	}
	
	
	public static String getJson(final Object pObject) throws Exception {
		return getGson().toJson(pObject);
	}

	public static String getJson(final Object pObject, final Type type) throws Exception {
		return getGson().toJson(pObject, type);
	}

	public static <T> T getObject(final String pJson, Type type)  throws Exception{
		return getGson().fromJson(pJson, type);
	}
	
	public static <T> T getObject(final String pJson, final Class<T> pValueType) throws Exception {
		return getGson().fromJson(pJson, pValueType);
	}
	
	
}
