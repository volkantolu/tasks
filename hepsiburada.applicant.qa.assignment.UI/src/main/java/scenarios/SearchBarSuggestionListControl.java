/**
 * 
 */
package scenarios;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author km10188
 *
 */
public class SearchBarSuggestionListControl {

	public SearchBarSuggestionListControl() {
	}
	
	public void searchBarSuggestionListControl(WebDriver driver, String searchKeyword) {
		System.out.println("Test Case: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		int timeOutLimit = 5;
		WebDriverWait wait = new WebDriverWait(driver, timeOutLimit);
		try {
			WebElement productSearch = driver.findElement(By.id("productSearch"));

			List<WebElement> autocompleteSuggestions = driver
					.findElements(By.cssSelector(".autocomplete-suggestions div"));
			int before = autocompleteSuggestions.size();
			System.out.println("Before Search: " + before);
			productSearch.clear();
			productSearch.sendKeys(searchKeyword);

			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".autocomplete-suggestions div")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".rfk_search_container.rfk-sb.rfk_focus.rfk_visible")));			

			autocompleteSuggestions = driver.findElements(By.cssSelector(".autocomplete-suggestions div"));
			int after = autocompleteSuggestions.size();
			System.out.println("After Search: " + after);

			if (before != after) {
				System.out.println("SUCCESS");
			} else {
				System.out.println("FAIL/NOTHING FOUND");
			}
		} catch (Exception e) {
			System.out.println("TEST CASE FAILED");
			// e.printStackTrace();

		}

	}
	
}
