package com.health.bdd.common.page;

import org.openqa.selenium.WebDriver;

import com.health.bdd.pages.BasePage;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends BasePage {
	
	@FindBy(id = "txtUserName")
	private WebElementFacade userNameInputField;
	
	@FindBy(id = "txtPassword")
	private WebElementFacade passwordInputField;
	
	@FindBy(id = "btnLogin")
	private WebElementFacade loginButton;
	
	@FindBy(xpath = "//div[@style = 'display: inline-block; vertical-align: top;']/span[contains(text(), 'ReceptionHA100@healthasyst.com')]")
	private WebElementFacade dashBoardUserName;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	/**.
	 * Method to Open a Browser with a specified URL in serenity properties file
	 */
	public void openPage() throws Exception {
		open();
		getDriver().manage().window().maximize();
	}
	
	/**.
	 * Method to enter user name in Dashboard login screen
	 * @param userName
	 * @throws Exception
	 */
	public void enterUserName(String userName) throws Exception {
		waitUntilElementIsStable(userNameInputField);
		enterTextFieldValue(userNameInputField, userName);
	}
	
	/**.
	 * Method to enter password in Dashboard login screen
	 * @param password
	 * @throws Exception
	 */
	public void enterPassword(String password) throws Exception {
		waitUntilElementIsStable(passwordInputField);
		enterTextField(passwordInputField, password);
	}
	
	/**.
	 * Method to click on Login button on Dashboard login screen
	 * @param password
	 * @throws Exception
	 */
	public void clickLogin() throws Exception {
		waitUntilElementIsStable(loginButton);
		clickOn(loginButton);
	}
	
	/**.
	 * Method to get user name from Dashboard
	 * @param userName
	 * @throws Exception
	 */
	public String getUserNameDashboard() throws Exception {
		waitUntilElementIsStable(dashBoardUserName);
		String userName = dashBoardUserName.getText();
		return userName;
	}
}
