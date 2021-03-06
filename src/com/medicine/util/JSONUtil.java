package com.medicine.util;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class JSONUtil {
	/**
	 * 将json转化为实体POJO
	 * 
	 * @param jsonStr
	 * @param obj
	 * @return
	 */
	public static <T> Object JSONToObj(String jsonStr, Class<T> obj) {
		T t = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			t = objectMapper.readValue(jsonStr, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将实体POJO转化为JSON
	 * 
	 * @param obj
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 */
	public static <T> JSONObject objectToJson(T obj) throws JSONException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		// Convert object to JSON string
		String jsonStr = "";
		try {
			jsonStr = mapper.writeValueAsString(obj);
		} catch (IOException e) {
			throw e;
		}
		return new JSONObject();
	}

	public static String serializeObject(Object o) throws Exception {
		return mapper.writeValueAsString(o);
	}

	static ObjectMapper mapper = new ObjectMapper();

	public static Object deserializeObject(String s, Class<?> clazz) throws Exception {
		return mapper.readValue(s, clazz);
	}

	public static Object deserializeObject(String s, TypeReference<?> typeReference) throws Exception {
		return mapper.readValue(s, typeReference);
	}
}
