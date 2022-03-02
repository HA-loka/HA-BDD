package com.health.bdd.pages;

import java.time.Duration;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.health.bdd.utils.ApplicationConstants;
import com.health.bdd.utils.ApplicationTestProperties;
import com.health.bdd.utils.Log;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.WebDriverFacade;

public class BasePage extends PageObject{
	
	/** Max No of Retries */
	static int maxNoOfRetries = Integer.parseInt(ApplicationTestProperties.Instance.getTestProperty(ApplicationConstants.MAX_NO_OF_RETRIES));
	
	/** Wait Value */
	static int waitTimeInMS = Integer.parseInt(ApplicationTestProperties.Instance.getTestProperty(ApplicationConstants.WAIT_TIME_IN_MS));
	
	public BasePage(WebDriver driver) {
		super(driver);
	}
	
	/**.
	 * Check if an element is present. If not wait for a specified amount of time and retry again until click element is exist
	 */
	public void waitUntilElementIsStable(WebElementFacade element) {
		int counter = 0;
		while(maxNoOfRetries > 0 && counter != maxNoOfRetries) {
			waitABit(waitTimeInMS);
			try {
				waitForPageToCompleteState();
				element.isPresent();
				break;
			} catch (Exception e) {
				waitABit(waitTimeInMS);
			}
		}
	}
	
	/**.
	 * Fluent Wait
	 */
	@SuppressWarnings("unchecked")
	public void fluentWait(WebElementFacade wef, int timeout) throws Exception {
		new FluentWait<WebElementFacade>(wef).withTimeout(Duration.ofSeconds(timeout))
		.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class)
		.until(waitUntilElementIsVisible(wef));
	}
	
	@SuppressWarnings("rawtypes")
	public ExpectedCondition waitUntilElementIsVisible(final WebElementFacade wef) {
		return new ExpectedCondition() {
			public Boolean apply(Object object) {
				return (wef.isVisible());
			}
		};
	}

	/**.
	 * Scroll element into view
	 */
	public void scrollElementIntoView(WebElementFacade element) {
		((JavascriptExecutor) getDriver()).executeScript("argument[0].scrollIntoView(true);",element);
	}
	
	/**.
	 * Click an element if not visible which ever occurs first
	 */
	public void clickElementAfterVisible(WebElementFacade element) {
		int counter = 0;
		while(maxNoOfRetries > 0 && counter != maxNoOfRetries) {
			waitABit(waitTimeInMS);
			try {
				waitUntilElementIsStable(element);
				scrollElementIntoView(element);
				element.click();
				break;
			} catch (Exception e) {
				Log.warn(String.format("clickElementAfterVisible: Element not found",element.toString()));
			}
			counter++;
		}
	}
	
	/**.
	 * Wait for page to load Using the document.readystate attribute
	 */
	public void waitForPageToCompleteState() throws Exception {
		int counter = 0;
		while(maxNoOfRetries > 0 && counter != maxNoOfRetries) {
			waitABit(waitTimeInMS);
			try {
				JavascriptExecutor js = (JavascriptExecutor) getDriver();
				if(js.executeScript("return document.readyState").toString().equals("complete")) {
					break;
				}
			} catch (Exception e) {
				Log.error("Page is still loading");
			}
		}
	}
	
	
	
	/**.
	 * Click on Element (Button) if an element is responds. If not, wait for a specified amount of time
	 * and retry again until the click succeeds or the maximum no of retires occurs, which ever occurs first.
	 * @param element
	 */
	public void clickAfterElementIsStable(WebElementFacade element) throws Exception {
		int counter = 0;
		while(maxNoOfRetries > 0 && counter != maxNoOfRetries) {
			waitABit(waitTimeInMS);
			try {
				waitForPageToCompleteState();
				element.click();
				break;
			} catch (Exception e) {
				if (counter >= 2) {
					clickElementAfterVisible(element);
					break;
				}
				Log.warn(String.format("clickAfterElementIsStable: Element Not Found", element.toString()));
			}
			counter++;
		}
	}
	
	/**.
	 * Method to enter Text in - TYPE 
	 * @param element, txt
	 */
	public void enterTextField(WebElementFacade element, String txt) throws Exception {
		clickAfterElementIsStable(element);
		waitForPageToCompleteState();
		element.clear();
		element.type(txt);
	}
	
	/**.
	 * Method to enter Text in - TYPE and TAB
	 * @param element, txt
	 */
	public void enterTextFieldValue(WebElementFacade element, String txt) throws Exception {
		clickAfterElementIsStable(element);
		waitForPageToCompleteState();
		element.clear();
		element.type(txt);
		element.sendKeys(Keys.RIGHT);
		element.sendKeys(Keys.TAB);
		waitForPageToCompleteState();
	}

	/**.
	 * Press TAB on Text field
	 */
	public void pressTabOnTextField(WebElementFacade element) throws Exception {
		clickAfterElementIsStable(element);
		waitForPageToCompleteState();
		element.sendKeys(Keys.RIGHT);
		element.sendKeys(Keys.TAB);
		waitForPageToCompleteState();
	}
	
	/**.
	 * Press Page Down
	 */
	public void pressPageDown(WebElementFacade element) throws Exception {
		clickAfterElementIsStable(element);
		getDriver().switchTo().activeElement().sendKeys(Keys.PAGE_DOWN);
	}
	
	
	/**.
	 * Press Keyboard Keys based on Input
	 */
	public void nativeKeyPress(String key) throws Exception {
		switch (key) {
		case "pagedown":
			getDriver().switchTo().activeElement().sendKeys(Keys.PAGE_DOWN);
			break;
		case "tab":
			getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
			break;
		default:
			break;
		}
	}
	
	/**.
	 * Get Browser Type
	 */
	public String getBrowserType() {
		Capabilities cap = ((RemoteWebDriver) ((WebDriverFacade) this.getDriver()).getProxiedDriver()).getCapabilities();
		System.out.println(cap.getBrowserName().toString());
		String browserCap = cap.getBrowserName().toString();
		return browserCap;
	}
	
	/**.
	 * To Move control to Previous Field
	 */
	public void pressShiftTabOnField(WebElementFacade element) throws Exception {
		String press = Keys.chord(Keys.SHIFT, Keys.TAB);
		element.sendKeys(press);
	}
	
	/**.
	 * Shortcuts Keys
	 */
	public void pressShortcutKeys(String shortcutKeys) throws Exception {
		switch (shortcutKeys) {
		case "ALT-C":
			if (getBrowserType().equals("firefox")) {
				getDriver().switchTo().activeElement().sendKeys(Keys.chord(Keys.ALT + "C"));
				waitABit(500);
				getDriver().switchTo().activeElement().sendKeys(Keys.chord(Keys.ENTER));
			}else if (getBrowserType().equals("chrome")) {
				getDriver().switchTo().activeElement().sendKeys(Keys.chord(Keys.ALT + "C"));
			}
			break;

		default:
			break;
		}
	}
	
	/**.
	 * Is Element Present
	 */
	public boolean isElementPresent(WebElementFacade element) throws Exception {
		try {
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			try {
				WebDriverWait wait = new WebDriverWait(getDriver(), 2);
				wait.until(ExpectedConditions.invisibilityOf(element));
				return false;
			} catch (Exception e2) {
				return false;
			}
		}
	}
	
	/**.
	 * click until expected button to load
	 */
	public void clickUntilExpectedElementLoads(WebElementFacade element, WebElementFacade expectedElement) throws Exception {
		int counter = 0;
		while(counter != maxNoOfRetries) {
			try {
				scrollElementIntoView(element);
				element.click();
				waitABit(waitTimeInMS);
				if (expectedElement.isVisible()) {
					break;
				}
				
			} catch (Exception e) {
				Log.error("Element not Found");
			}
			counter++;
		}
	}
	
	
}
