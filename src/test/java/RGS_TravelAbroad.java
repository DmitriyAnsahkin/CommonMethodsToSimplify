import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RGS_TravelAbroad {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void startDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        //driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        //step 1. Перейти по ссылке http://www.rgs.ru
        driver.get("http://www.rgs.ru");
    }

//    @After
//    public void closeDriver(){
//        driver.close();
//    }

    @Test
    public void testRGS_TravelAbroad() {
        //Переменные для подстановки
        String territory = "Шенген";
        String sfio = "IVANOV IVAN";
        String date = "15.07.1955";
        String active = "Включен";

        //список используемых в тестах элементов
        String insurance = "//*[@id='main-navbar-collapse']//a[contains(text(),'Страхование')]";    //Кнопка меню "страхование" на главной странице
        String bInsuranceGoingAbroad = "//a[contains(text(),'Выезжающим за рубеж')]";               //Кнопка "Путешествия – Страхование выезжающих за рубеж" в меню "Страхование на главной странице
        String bCalculateOnline = "//*[@class='btn btn-attention btn-sm btn-block " +               //Кнопка "Рассчитать онлайн" в ""Путешествия – Страхование выезжающих за рубеж"
                "text-uppercase text-semibold' and contains(text(), 'Рассчитать')]";
        String hInsuranceGoingAbroad = "//*[@class='h1' and contains(text(), " +                    //Заголовок страницы "Калькулятор страхования путешественников онлайн"
                "'Страхование выезжающих')]";
        String bMultiple = "//*[@data-test-value='Multiple']";                                      //Кнопка "Несколько в течении года" на странице "Калькулятор страхования путешественников онлайн"
        String acceptPersonalInfo = "//*[@class='adaptive-checkbox-label' " +                       //Чекер на согласе обработки персональных данных окна "Калькулятор страхования путешественников онлайн"
                "and contains(text(),'Я согласен на обработку моих персональных " +
                "данных в целях расчета страховой премии')]";
        String bCalculate = "//*[@type='submit' and contains(text(), 'Рассчитать')]";               //Кнопка рассчитатать на странице "Калькулятор страхования путешественников онлайн"
        String whereWeGo = "//label[text()=' Куда едем? ']/following::input[1]";                    //Поле ввода направления на странице "Калькулятор страхования путешественников онлайн"
        String countrySelect = "//*[@id='ArrivalCountryList']";                                     //Поле ввода страны при выборе направления "Шеншен" на странице "Калькулятор страхования путешественников онлайн"
        String dateFirstTravel = "//*[@class='form-control width-xs-9rem width-" +                  //Поле выбора даты первой поездки на странице "Калькулятор страхования путешественников онлайн"
                "sm-9rem validation-control-has-error']";
        String day90 = "//*[contains(text(),' Не более 90 дней ')]";                                //Кнопка количества дней планируемых пробыть за рубежем на странице "Калькулятор страхования путешественников онлайн"
        String fio = "//span[contains(text(),' Фамилия')]/parent::*/following::input[1]";           //Поле ввода Фамилия / имя на латинице на странице "Калькулятор страхования путешественников онлайн"
        String dateBir = "//span[contains(text(),' Фамилия')]/parent::*/following::input[4]";       //Поле ввода даты рождения на странице "Калькулятор страхования путешественников онлайн"
        String leisure = "//*[@class='toggle off toggle-rgs']";                                     //Кнопка планирования активного отдыха на странице "Калькулятор страхования путешественников онлайн"


        //тело теста
        click(insurance);                                                                           //step 2. Выбрать пункт меню – Страхование
        click(bInsuranceGoingAbroad);                                                               //step 3. Путешествия – Страхование выезжающих за рубеж
        click(bCalculateOnline);                                                                    //step 4. Нажать рассчитать – Онлайн
        checkEquals("Страхование выезжающих за рубеж",
                waitVisibilityOfElementLocatedAndReturnThis(hInsuranceGoingAbroad));                //step 5. Проверить наличие заголовка – Страхование выезжающих за рубеж
        click(bMultiple);                                                                           //step 6. Заполнить форму //6.1 Несколько поездок в течении года
        click(acceptPersonalInfo);                                                                  //step 6. Заполнить форму //6.2 согласен на обработку данных  - выбрать чекбокс
        click(bCalculate);                                                                          //step 7. Нажать рассчитать
        insertTextAndDownEnter(whereWeGo, territory);                                    //step 8. Заполнить поля: //Куда едем – Шенген
        chooseCountry(countrySelect, "Испания");                                            //step 8. Заполнить поля: //Страна въезда – Испания

        insertDate(dateFirstTravel, getTomorrowDate());                                             //step 8. Заполнить поля: //Дата первой поездки – 1 ноября *P.S. отступил от задания, сделал дату завтращний день
        click(day90);                                                                               //step 8. Заполнить поля: //Сколько дней планируете пробыть за рубежом за год – не более 90
        sendKeys(fio, sfio);                                                      //step 8. Заполнить поля: //Фио
        sendKeys(dateBir, date);                                                     //step 8. Заполнить поля: //Дата рождения
        click(leisure);                                                                             //step 8. Заполнить поля: // Планируется активный отдых
        click(bCalculate);                                                                          //step 9. Нажать рассчитать
        checkEquals(territory, waitVisibilityOfElementLocatedAndReturnThis("//div[@class='summary']/div[@class='summary-row']/span/span/strong"));
        checkEquals(sfio, waitVisibilityOfElementLocatedAndReturnThis("//div[@class='summary']/*/div[@class='summary-row']/span/span/strong"));
        checkEquals(date, waitVisibilityOfElementLocatedAndReturnThis("//div[@class='summary']/*/div[@class='summary-row']/span/span/span/strong"));
        checkEquals(active, waitVisibilityOfElementLocatedAndReturnThis("//span[@class='text-bold' and contains(text(),'Включен')]"));


    }


    public void click(String xpath) {
        WebElement temp = waitVisibilityOfElementLocatedAndReturnThis(xpath);
        scrollToElement(temp);
        temp.click();
    }

    public void sendKeys(String xpath, String sendText) {
        WebElement temp = waitVisibilityOfElementLocatedAndReturnThis(xpath);
        scrollToElement(temp);
        temp.sendKeys(sendText);
    }

    public void checkEquals(String expected, WebElement webElement) {
        Assert.assertEquals("Ошибка", expected, webElement.getText());
    }


    public WebElement waitVisibilityOfElementLocatedAndReturnThis(String xpath) {
        wait.pollingEvery(Duration.ofMillis(300))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , element);
    }

    public void insertTextAndDownEnter (String xpath, String whereWeGo){
        WebElement temp = waitVisibilityOfElementLocatedAndReturnThis(xpath);
        temp.sendKeys(whereWeGo);
        temp.sendKeys(Keys.DOWN, Keys.ENTER);
    }

    public void chooseCountry (String xpath, String country){
        WebElement temp = waitVisibilityOfElementLocatedAndReturnThis(xpath);
        Select chooseCountry = new Select(temp);
        chooseCountry.selectByVisibleText(country);
    }

    public String getTomorrowDate (){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("ddMMyyyy");
        return formatForDateNow.format(tomorrow);
    }

    public void insertDate (String xpath, String date){
        WebElement temp = waitVisibilityOfElementLocatedAndReturnThis(xpath);
        temp.click();

        temp.sendKeys(date);
    }




}
