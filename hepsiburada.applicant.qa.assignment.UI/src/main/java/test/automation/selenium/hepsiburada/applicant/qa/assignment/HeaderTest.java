package test.automation.selenium.hepsiburada.applicant.qa.assignment;

/*
 developed by VolkanTolu
 */
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderTest {

	public WebDriver getBrowser(String browserType) {

		WebDriver driver = null;
		try {
			if (browserType.equals("chrome")) {
				// https://sites.google.com/a/chromium.org/chromedriver/downloads
				System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
				driver = new ChromeDriver();
			} else {
				// generic driver
				// https://sites.google.com/a/chromium.org/chromedriver/downloads
				// "/development/test/automation/selenium/webdrivers/chromeDriver/chromedriver.exe"
				System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
				driver = new ChromeDriver();
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return driver;
	}

	public void navigateToUrl(WebDriver driver, String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public void disablePopUp(WebDriver driver) {

		int timeOutLimit = 5;
		WebDriverWait wait = new WebDriverWait(driver, timeOutLimit);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector(".insider-opt-in-notification-button.insider-opt-in-disallow-button"))).click();

		} catch (Exception e) {
			// e.printStackTrace();
		}

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

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".autocomplete-suggestions div")));

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

	public void signInControl(WebDriver driver) {
		int timeOutLimit = 5;
		WebDriverWait wait = new WebDriverWait(driver, timeOutLimit);
		try {
			System.out.println("Test Case: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			navigateToUrl(driver, "http://www.hepsiburada.com");
			disablePopUp(driver);
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

	public void myBasketControl(WebDriver driver) {

		try {
			System.out.println("Test Case: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			navigateToUrl(driver, "http://www.hepsiburada.com");
			disablePopUp(driver);
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

	public void singleClickShoppingLinkControl(WebDriver driver) {
		try {
			System.out.println("Test Case: " + Thread.currentThread().getStackTrace()[1].getMethodName());

			navigateToUrl(driver, "http://www.hepsiburada.com");
			disablePopUp(driver);
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

	public static void main(String[] args) throws InterruptedException {

		HeaderTest headerTest = new HeaderTest();
		String url = "http://www.hepsiburada.com";
		String browserType = "chrome";
		String searchKeyword = "";
		WebDriver browser = null;

		/*
		 * the developed task consists of 5 test cases covering the assignment
		 */

		try {
			browser = headerTest.getBrowser(browserType);
			headerTest.navigateToUrl(browser, url);
			headerTest.disablePopUp(browser);

			// check suggestionList
			searchKeyword = "telefon";
			headerTest.searchBarSuggestionListControl(browser, searchKeyword);
			searchKeyword = "telefon1";
			headerTest.searchBarSuggestionListControl(browser, searchKeyword);

			// check search functionality
			searchKeyword = "telefon";
			headerTest.searchBarSearchFunctionalityControl(browser, searchKeyword);

			// check signIn
			headerTest.signInControl(browser);

			// check myBasket
			headerTest.myBasketControl(browser);

			// check navigation of links
			headerTest.singleClickShoppingLinkControl(browser);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			browser.close();
			System.exit(0);
		}

	}

}
