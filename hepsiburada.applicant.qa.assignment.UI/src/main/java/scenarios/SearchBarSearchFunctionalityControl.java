/**
 * 
 */
package scenarios;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author km10188
 *
 */
public class SearchBarSearchFunctionalityControl implements Scenario {

	public SearchBarSearchFunctionalityControl() {
	}
	
	public void searchBarSearchFunctionalityControl(WebDriver driver, String searchKeyword) {
		try {
			System.out.println("Test Case: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			int timeOutLimit = 5;
			WebDriverWait wait = new WebDriverWait(driver, timeOutLimit);

			WebElement productSearch = driver.findElement(By.id("productSearch"));
			productSearch.clear();
			productSearch.sendKeys(searchKeyword);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonProductSearch"))).click();

			List<WebElement> productSearchResultContainer = driver
					.findElements(By.cssSelector(".filter-box-container div"));
			int containerGroupCount = productSearchResultContainer.size();
			// must greater than 0
			System.out.println("Container Group Count:" + containerGroupCount);

			productSearch = driver.findElement(By.id("productSearch"));
			String searchBarValueAfterSearch = productSearch.getText();

			if ((searchBarValueAfterSearch != searchKeyword) && (containerGroupCount > 0)) {
				System.out.println("SUCCESS");
			} else {
				System.out.println("FAIL");
			}
		} catch (Exception e) {
			System.out.println("TEST CASE FAILED");
			// e.printStackTrace();

		}

	}

	/* (non-Javadoc)
	 * @see scenarios.Scenario#run(java.util.Map)
	 */
	public void run(Map<String, Object> params) {
		
	}

	/* (non-Javadoc)
	 * @see scenarios.Scenario#beforeRun()
	 */
	public Map<String, Object> beforeRun() {
		return null;
	}
}
