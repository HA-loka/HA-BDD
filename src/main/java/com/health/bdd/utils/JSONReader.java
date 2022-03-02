package com.health.bdd.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {
	
	private static Map<String, JSONObject> jsonMap = new HashMap<String, JSONObject>();
	private static JSONReader singletonInstance = null;
	
	/**.
	 * Description : Method to Read Data from JSON files
	 * Author By :
	 * Created On :
	 */
	public static void loadJsonFiles() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
		String jsonDirectory = null;
		String jsonFileName = null;
		
		jsonDirectory = System.getProperty("user.dir") + ApplicationTestProperties.Instance.getTestProperty(ApplicationConstants.JSONDIRECTORY);
		
		try {
			ArrayList<File> jsonFileList = listf(jsonDirectory);
			
			for (File jsonFile: jsonFileList) {
				jsonObject = (JSONObject) parser.parse(new FileReader(jsonFile));
				jsonFileName = jsonFile.getName();
				jsonMap.put(jsonFileName.substring(0,jsonFileName.lastIndexOf('.')), jsonObject);
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * A private Constructor prevents any other class from instantiating.
	 */
	private  JSONReader() {}
	
	/* Static 'instant' method */
	public static JSONReader getInstance() {
		if(singletonInstance == null) {
			singletonInstance = new JSONReader();
			loadJsonFiles();
		}
		return singletonInstance;
	}
	
	
	/**.
	 * Method to get all json files within the data files directory
	 * @param directoryName
	 * @return
	 */
	public static ArrayList<File> listf(String directoryName){
		File directory = new File(directoryName);
		ArrayList<File> files = new ArrayList<File>();
		
		//get all the files from directory
		File[] fList = directory.listFiles();
		for(File file: fList) {
			if(file.isFile() && file.getName().endsWith(".json")) {
				files.add(file);
			}else if (file.isDirectory()) {
				listf(file.getAbsolutePath());
			}
		}
		return files;
	}
	
	/**.
	 * Method to Read node from JSON File
	 * @param valuePair
	 * @return String
	 */
	public JSONObject readJsonNode(String jsonFileName,String... valuePair) {
		
		JSONObject keyValuePair = null;
		JSONArray content = null;
		
		String element = valuePair[0];
		content = (JSONArray) jsonMap.get(jsonFileName).get(element);
		for(int i=1; i<valuePair.length; i++) {
			element = valuePair[i];
			keyValuePair = (JSONObject) content.get(0);
			content = (JSONArray) keyValuePair.get(element);
		}
		
		keyValuePair = (JSONObject) content.get(0);
		return keyValuePair;
	}
}
