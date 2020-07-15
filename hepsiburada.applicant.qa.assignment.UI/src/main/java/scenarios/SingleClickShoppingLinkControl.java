/**
 * 
 */
package scenarios;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import test.automation.selenium.hepsiburada.applicant.qa.assignment.Configuration;

/**
 * @author km10188
 *
 */
public class SingleClickShoppingLinkControl {
	public SingleClickShoppingLinkControl() {
	}
	
	public void singleClickShoppingLinkControl(WebDriver driver) {
		try {
			System.out.println("Test Case: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			Configuration config = new Configuration();
			config.navigateToUrl(driver, "http://www.hepsiburada.com");
			config.disablePopUp(driver);
			List<WebElement> hrefElements = driver.findElements(By.tagName("a"));
			String navigationUrl = "";

			for (WebElement we : hrefElements) {
				if (we.getText().equals("Tek Tıkla Alışveriş")) {
					// wait.until(ExpectedConditions.elementToBeClickable(we));
					navigationUrl = we.getAttribute("href");
					we.click();
					break;
				}
			}

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
