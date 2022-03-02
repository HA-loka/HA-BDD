package com.health.bdd.pages;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;

public class PageObjectFactory {

	public PageObjectFactory() {
		
	}
	
	/**.
	 * @param driver : current Web Driver
	 * @param type : new page object type
	 * @return
	 */
	public static <T extends BasePage> T getPage(WebDriver driver, Class<T> type) {
		T page = null;
		
		try {
			page = type.getDeclaredConstructor(WebDriver.class).newInstance(driver);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		return page;
	}
}
