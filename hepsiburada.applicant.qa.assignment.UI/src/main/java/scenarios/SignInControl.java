/**
 * 
 */
package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.automation.selenium.hepsiburada.applicant.qa.assignment.Configuration;


public class SignInControl {
	public SignInControl() {
	}
	
	public void signInControl(WebDriver driver) {
		int timeOutLimit = 5;
		WebDriverWait wait = new WebDriverWait(driver, timeOutLimit);
		try {					
			System.out.println("Test Case: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Configuration config = new Configuration();
			config.navigateToUrl(driver, "http://www.hepsiburada.com");
			config.disablePopUp(driver);
			WebElement signInElement = driver.findElement(By.id("myAccount"));
			signInElement.click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
			String navigationUrl = driver.findElement(By.id("login")).getAttribute("href");
			driver.findElement(By.id("login")).click();

			String currentUrl = driver.getCurrentUrl();

			if (currentUrl.equals(navigationUrl)) {
				System.out.println("SUCCESS");
			} else {
				System.out.println("FAIL");
			}
		} catch (Exception e) {
			System.out.println("TEST CASE FAILED");
			// e.printStackTrace();
		}

	}
	
}
