/**
 * 
 */
package com.health.bdd.steps;

import java.util.List;

import com.health.bdd.helper.LoginHelper;
import com.health.bdd.utils.ApplicationConstants;
import com.health.bdd.utils.ApplicationTestProperties;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;

/**
 * @author Sanjeev Nandivada
 *
 */
public class LoginSteps {
	
	@Steps
	LoginHelper loginHelper;

	/**.
	 * Description: Method to Open Browser with specified URL
	 * Author By : Sanjeev N
	 * Created On : Jan 2022
	 * @throws Exception
	 */
	@Given("^I open 'dashBoardCheckinAsyst' url$")
	public void openBrowserWithApplicationUrl() throws Exception {
		loginHelper.openLoginPage();
	}
	
	/**.
	 * Description: Method to login into Dashboard CA application
	 * Author By : Sanjeev N
	 * Created On : Jan 2022
	 * @throws Exception
	 */
	@When("^I login with \"([^\"*]*)\" and \"([^\"]*)\"$")
	public void loginWithDashboardUsernameAndPassword(String username, String password) throws Exception {
		loginHelper.enterUserNameAndPassword(username, password);
	}
	
	/**.
	 * Description: Method to login into Dashboard CA application
	 * Author By : Sanjeev N
	 * Created On : Jan 2022
	 * @throws Exception
	 */
	@And("^I click on login button$")
	public void clickOnLoginButton() throws Exception {
		loginHelper.clickLogin();
	}
	
	/**.
	 * Description: Method to verify respective module is opened
	 * Author By : Sanjeev N
	 * Created On : Jan 2022
	 * @throws Exception
	 */
	@Then("^I verify respective \"(.*?)\" is logged in$")
	public void verifyRespectiveModleIsOpened(String userName) throws Exception {
		loginHelper.verifyLoggedInUserName(userName);
	}
}