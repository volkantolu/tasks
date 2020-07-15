/**
 * 
 */
package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import test.automation.selenium.hepsiburada.applicant.qa.assignment.Configuration;

/**
 * @author km10188
 *
 */
public class MyBasketControl {

	public MyBasketControl() {
	}
	
	public void myBasketControl(WebDriver driver) {

		try {			
			System.out.println("Test Case: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Configuration config = new Configuration();
			config.navigateToUrl(driver, "http://www.hepsiburada.com");
			config.disablePopUp(driver);
			WebElement myBasketElement = driver.findElement(By.id("shoppingCart"));
			String navigationUrl = myBasketElement.getAttribute("href");
			myBasketElement.click();

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
