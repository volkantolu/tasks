/**
 * 
 */
package test.automation.selenium.hepsiburada.applicant.qa.assignment;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import scenarios.Scenario;

public class HeaderControl_PageModel {
	public HeaderControl_PageModel() {
	}
	
	public static void main(String[] args) {
		WebDriver driver = null;
		String url = "http://www.hepsiburada.com";
		String browserType = "firefox";
		String searchKeyword = "";
		try {
			Drivers drivers = new Drivers();
			driver = drivers.getBrowser(browserType);
			Configuration config = new Configuration();
			config.navigateToUrl(driver, url);
			config.disablePopUp(driver);
			searchKeyword = "telefon";
			// ekrandan secilen senaryolar
			ArrayList<String> selectedScenarios = new ArrayList<String>();
			selectedScenarios.add("SearchBarSuggestionListControl");
			selectedScenarios.add("SearchBarSearchFunctionalityControl");
			// ekrandan secilen senaryoların hangi metodlarının tetikleneceği db
			// den cekilir
			ArrayList<String> selectedScenariosMethods = new ArrayList<String>();
			selectedScenariosMethods.add("searchBarSuggestionListControl");
			selectedScenariosMethods.add("searchBarSearchFunctionalityControl");
			// reflection
			for (int i = 0; i < selectedScenarios.size(); i++) {
				String scenarioName = "scenarios." + selectedScenarios.get(i);
				Class cls = Class.forName(scenarioName);
				Scenario obj = (Scenario) cls.newInstance();
				Map<String, Object> beforeRun = obj.beforeRun();
				obj.run(beforeRun);
				//alttakini kullanmamak ıcın ınterface dusunuldu - yarı reflectıon gıbı
				Method method = cls.getDeclaredMethod(selectedScenariosMethods.get(i), WebDriver.class, String.class);
				method.invoke(obj, driver, searchKeyword);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
			System.exit(0);
		}
	}
}
