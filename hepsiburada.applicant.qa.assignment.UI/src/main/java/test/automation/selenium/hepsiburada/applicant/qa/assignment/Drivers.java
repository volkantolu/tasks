/**
 * 
 */
package test.automation.selenium.hepsiburada.applicant.qa.assignment;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author km10188
 *
 */
public class Drivers {
	public Drivers() {
	}
	
	public List<WebDriver> getBrowsers (String ...strings) {
		return null;
	}
	public WebDriver getBrowser(String browserType) {

		WebDriver driver = null;
		try {
			if (browserType.equals("chrome")) {
				// https://sites.google.com/a/chromium.org/chromedriver/downloads
                System.setProperty("webdriver.chrome.driver", "C:/app/dev/tools/automation/selenium/drivers/32Bit/chrome/chromedriver-v0.2.34-x86/chromedriver_32Bit_ver_2.34.exe");
				driver = new ChromeDriver();
			}
			else if (browserType.equals("firefox")){
                driver = new FirefoxDriver();
            }
			else {
				// generic driver
				// https://sites.google.com/a/chromium.org/chromedriver/downloads
				// "/development/test/automation/selenium/webdrivers/chromeDriver/chromedriver.exe"
                System.setProperty("webdriver.chrome.driver", "C:/app/dev/tools/automation/selenium/drivers/32Bit/chrome/chromedriver-v0.2.34-x86/chromedriver_32Bit_ver_2.34.exe");
				driver = new ChromeDriver();
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return driver;
	}
	
}
