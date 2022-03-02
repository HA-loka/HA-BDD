package com.health.bdd.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum ApplicationTestProperties {
	Instance;

	private static Properties testProperties;

	public ApplicationTestProperties getInstanceMethod() {
		return Instance;
	}

	ApplicationTestProperties() {
		loadTestProperties();
	}
	
	private static void loadTestProperties() {
		InputStream inputStream = null;
		try {
			inputStream = ApplicationTestProperties.class.getResourceAsStream("/properties/application.properties");
			if (inputStream != null) {
				testProperties = new Properties();
				testProperties.load(inputStream);
			}
		} catch (IOException e) {
			System.out.println("Error loading Constant Properties file - "+ e.getMessage() );
		}finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					System.out.println("Error Closing Constant Properties file - "+ e.getMessage());
				}
			}
		}
	}
	
	/**.
	 * Description : Method to get properties from application.properties file
	 * @param propertyName
	 * @return String
	 */
	public String getTestProperty(String propertyName) {
		return getTestProperty(propertyName, "Unknown");
	}
	
	/**.
	 * Description : Method to get properties from application.properties file
	 * @param propertyName
	 * @param defaultName
	 * @return String
	 */
	public String getTestProperty(String propertyName, String defaultValue) {
		if (testProperties == null) {
			return "NOT LOADED";
		}
		return testProperties.getProperty(propertyName, defaultValue);
	}
	
}
