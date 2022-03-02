package com.health.bdd.helper;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.assertj.core.api.Assertions;

import com.health.bdd.common.page.LoginPage;
import com.health.bdd.utils.HealthJSONReader;
import com.health.bdd.utils.JSONReader;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;

@SuppressWarnings("serial")
public class LoginHelper extends ScenarioSteps{

	LoginPage loginPage;
	
	public LoginHelper(Pages pages) {
		super(pages);
	}
	
	/**.
	 * Open a Browser with specified URL in properties file
	 */
	@Step
	public void openLoginPage() throws Exception {
		loginPage.openPage();
	}
	
	/**.
	 * Description : Method to verify the user name in the Dashboard screen
	 * 
	 */
	@Step
	public void enterUserNameAndPassword(String userName, String password) throws Exception {
		loginPage.enterUserName(userName);
		loginPage.enterPassword(password);
	}
	
	/**.
	 * Description : Method to click Login button in the Dashboard screen
	 * 
	 */
	@Step
	public void clickLogin() throws Exception {
		loginPage.clickLogin();
	}
	
	/**.
	 * Description : Method to verify the user name in the Dashboard screen
	 * 
	 */
	@Step
	public void verifyLoggedInUserName(String userName) throws Exception {
		assert(loginPage.getUserNameDashboard()).equals(userName);
	}
}
