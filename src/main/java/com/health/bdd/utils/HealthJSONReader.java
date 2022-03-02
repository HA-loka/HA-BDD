package com.health.bdd.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public enum HealthJSONReader {
Instance;
	
	private static JSONParser parser;
	private static JSONObject jsonObject;
	
	public HealthJSONReader getInstance() {
		return Instance;
	}
	
	HealthJSONReader() {
		readJsonFile();
	}
	
	/**.
	 * Method to Read data from JSON File
	 */
	public static void readJsonFile() {
		parser = new JSONParser();
		String path = null;
		
		path = System.getProperty("user.dir");
		path = path + ApplicationTestProperties.Instance.getTestProperty(ApplicationConstants.JSONFILEPATH);
		
		try {
				Object obj = parser.parse(new FileReader(path));
				jsonObject = (JSONObject) obj;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}
	
	/**.
	 * Method to Read value pair from JSON file
	 */
	public JSONObject readJsonValue(String... valuePair) {
		
		JSONObject keyValuePair = null;
		JSONArray content = null;
		
		String element = valuePair[0];
		content = (JSONArray) jsonObject.get(element);
		for(int i=1; i<valuePair.length; i++) {
			element = valuePair[i];
			keyValuePair = (JSONObject) content.get(0);
			content = (JSONArray) keyValuePair.get(element);
		}
		
		try {
			keyValuePair = (JSONObject) content.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return keyValuePair;
	}
}
