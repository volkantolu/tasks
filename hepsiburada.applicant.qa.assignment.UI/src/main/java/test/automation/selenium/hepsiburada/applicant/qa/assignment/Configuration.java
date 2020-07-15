/**
 * 
 */
package test.automation.selenium.hepsiburada.applicant.qa.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author km10188
 *
 */
public class Configuration {

	public Configuration() {
	}

	public void navigateToUrl(WebDriver driver, String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disablePopUp(WebDriver driver) {

		int timeOutLimit = 5;
		WebDriverWait wait = new WebDriverWait(driver, timeOutLimit);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector(".insider-opt-in-notification-button.insider-opt-in-disallow-button"))).click();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
