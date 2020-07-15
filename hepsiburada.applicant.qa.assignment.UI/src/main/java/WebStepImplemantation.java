import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStore;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import scenarios.SignInControl;
import test.automation.selenium.hepsiburada.applicant.qa.assignment.HeaderTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebStepImplemantation {

    SignInControl signInControl = new SignInControl();

    HeaderTest headerTest = new HeaderTest();

    //initial value
    String browserType = "chrome";

    String searchKeyword = "";
    WebDriver browser = null;

    DataStore specsStore = new DataStore();
    Integer index = 1;

    @BeforeScenario
    public void setUp() {
        //initialize basics
        browser = headerTest.getBrowser(browserType);
    }

    @Step("<url> adresine gidilir")
    public void adresineGidilir(String url) {

        /*
         * the developed task consists of 5 test cases covering the assignment
         */

        try {
            headerTest.navigateToUrl(browser, url);
            browser.manage().window().maximize();
            headerTest.disablePopUp(browser);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Step("<obje> menusune tıklanır")
    public void menusuneTiklanir(String obje) {

        ((ChromeDriver) browser).findElementByXPath("//li[@id='kitap-muzik-film-hobi']/a").click();

    }


    @Step("<obje> alt menusune tıklanır")
    public void altMenusuneTiklanir(String obje) {

        ((ChromeDriver) browser).findElementByXPath("//*[@data-title=\"Uzaktan Kumandalı Araçlar\"]").click();

    }

    @Step("<obje> alanina tiklanir")
    public void alaninaTiklanir(String obje) {

        ((ChromeDriver) browser).findElementByXPath("//*[@class=\"moreCategories\"]").click();

    }

    @Step("<obje> alaninin altina tiklanir")
    public void alanininnAltinaTiklanir(String obje) {

        ((ChromeDriver) browser).findElementByXPath("//li[@title=\"Drone Yedek Parçaları\"]/a").click();

    }

    @Step("<obje> butonuna tiklanir")
    public void sepeteEkle(String obje) {

        ((ChromeDriver) browser).findElementByXPath("(//span[text()='Sepete Ekle']//ancestor::button)[1]").click();

    }

    @AfterScenario
    public void onExit() {
        browser.quit();
    }

    @Step({"<saniye> saniye beklenir"})
    public void waitForSeconds(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep((long) seconds);
    }

    @Step({"<saniye> saniye beklenir debug"})
    public void waitForSecondsDebug(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep((long) seconds);
    }

    @Step("<xpath> xpathi calistirilir")
    public void xpathCalistir(String xpathExpression) {

        WebElement webElement = ((ChromeDriver) browser).findElementByXPath(xpathExpression);

        String hrefSuffix = webElement.getAttribute("href");
        specsStore.put("hrefSuffix", "https://www.hepsiburada.com" + hrefSuffix);

        String valueOfAttribute = webElement.getAttribute("content");

        if (valueOfAttribute != null) {
            specsStore.put("product" + index, valueOfAttribute);
            index++;
        } else {

            //((ChromeDriver) browser).findElementByXPath(xpathExpression).click();
            webElement.click();
            //validate page redirections are correct
            browser.getCurrentUrl().equals(specsStore.get("hrefSuffix"));
        }
    }

    @Step("<xpath> xpathi ile validasyon saglanir")
    public void xpathCalistirForValidation(String xpathExpression) {

        List<WebElement> webElements = ((ChromeDriver) browser).findElementsByXPath(xpathExpression);

        Integer size = webElements.size();
        index = 1;
        String singleValue;

        for (int i = 0; i < size; i++) {
            String valueOfAttribute = webElements.get(i).getAttribute("data-hbus");

            if (valueOfAttribute != null) {

                for (int y = 1; y < specsStore.entrySet().size() + 1; y++) {
                    singleValue = specsStore.get("product" + y).toString();

                    if (valueOfAttribute.contains(singleValue)) {
                        //index=0;
                        break;
                    } else {
                        //index++;
                    }
                }

            }


        }


    }

    @Step("<xpath> javascripti calistirilir")
    public void jsCalistir(String xpathExpression) {
        JavascriptExecutor js = (JavascriptExecutor) browser;

        WebElement element = ((ChromeDriver) browser).findElementByXPath(xpathExpression);

        js.executeScript("arguments[0].click();", element);
    }

    @Step("<obje> objesine <deger> degeri yazılır")
    public void objesineYazilir(String xpathExpression, String deger) {

        ((ChromeDriver) browser).findElementByXPath(xpathExpression).sendKeys(deger);

    }

    @Step("Onceki sayfaya don")
    public void oncekiSayfayaDon() {
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("window.history.go(-1)");
    }

    @Step("sayfayi kaydir")
    public void sayfaKaydir() throws InterruptedException {

        Actions action = new Actions(browser);
        Thread.sleep(3000);
        //SCROLL DOWN
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        WebElement element = ((ChromeDriver) browser).findElementByXPath("//*[contains(@class,'box-container satici')]");

        action.moveToElement(element).perform();
    }

    @Step("<browserName> browser secilir")
    public void setBrowser(String browserName) {
        browserType = browserName;

    }
}
