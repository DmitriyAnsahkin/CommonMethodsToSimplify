import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

class CommonMethodsToSimplify {
    WebDriver driver;
    WebDriverWait wait;


    public WebDriver getDriver() {
        return driver;
    }

    String xpByClassName = "//%s[@%s='%s']%s";
    String xpByClassNameAndText = "//%s[@%s='%s'and text()='%s']";
    String xpByClassNameAndTextCont = "//%s[@%s='%s'and contains(text(),'%s')]";
    String xpByTextCont = "//%s[contains(text(),'%s')]";


    public void begin(boolean maximaze) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        if (maximaze) driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    public void end(boolean closeBrowser) {
        if (closeBrowser) driver.close();
    }

    public void openSite(String link) {
        driver.get(link);
    }

    public void waitVisibilityOfElementLocated(String xpath) {
        wait.pollingEvery(Duration.ofMillis(300))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public WebElement getWebElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public void clickWebElement(WebElement webElement) {
        webElement.click();
    }

    public void scrollToElement(String xpath) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , getWebElementByXpath(xpath));
    }

    public void waitVisibleAndScrollAndClick(String xpath) {
        waitVisibilityOfElementLocated(xpath);
        scrollToElement(xpath);
        clickWebElement(getWebElementByXpath(xpath));
    }

    public void checkEquals(String expected, String xpath) {
        waitVisibilityOfElementLocated(xpath);
        Assert.assertEquals("Ожидаемый и полученный результаты не совподают", expected, getWebElementByXpath(xpath).getText());
    }

    public void insertTextIntoWebElement(String xpath, String wereWeGo, String... sendText) {
        WebElement temp = driver.findElement(By.xpath(xpath));
        temp.sendKeys(wereWeGo);
        for (String s : sendText) {
            temp.sendKeys(Keys.valueOf(s));
        }
    }

    public void chooseCountry (String xpath, String country){
        waitVisibilityOfElementLocated(xpath);
        WebElement temp = getWebElementByXpath(xpath);
        Select chooseCountry = new Select(temp);
        chooseCountry.selectByVisibleText(country);
    }

    public boolean availabilityElement (String xpath){
        try {
            driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void insertDate (String xpath, String date){
        waitVisibleAndScrollAndClick(xpath);
        String s = date;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar != '.'){
                getWebElementByXpath(xpath).sendKeys(Keys.valueOf("NUMPAD" + String.valueOf(aChar)));
            }
        }
        getWebElementByXpath(xpath).sendKeys(Keys.TAB);
    }


}
