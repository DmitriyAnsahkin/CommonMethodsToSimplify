import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestItselftRGSTravelAbroad {
    RGSTravelAbroadSteps imitationUser = new RGSTravelAbroadSteps();
    boolean mazimazeBrowserAtStart = false;
    boolean closeBrowserInTheEnd = false;

    @Before
    public void begin() {
        imitationUser.begin(mazimazeBrowserAtStart);
    }

    @After
    public void end() {
        imitationUser.end(closeBrowserInTheEnd);
    }

    @Test
    public void testItself() {

        imitationUser.openSite(RGSTravelAbroadSteps.HOME_PAGE_LINK);                        //1. Перейти по ссылке http://www.rgs.ru
        imitationUser.insuranceHomePageClick().                                             //2. Выбрать пункт меню – Страхование
                clickButtonGoingAbroadMenuInsuranceHP().                                    //3. Путешествия – Страхование выезжающих за рубеж
                clickCalculatePageInsuranceGoingAbroad().                                   //4. Нажать рассчитать – Онлайн
                checkEqualsHeadCalcGoingAbroad().                                           //5. Проверить наличие заголовка – Страхование выезжающих за руюеж
                clickFewTipsAndAcceptPersInfo().                                            //6. Заполнить форму: Несколько поездок в течении года, Я согласен на обработку данных
                clickCalculatePageOnlineCalcGoingAbroad().                                  //7. Нажать рассчитать
                fillingFieldOnlineCalc().                                                   //8. Заполнить поля: Куда едем. Страна въезда. Дата первой поездки. Сколько дней планируете пробыть за рубежом за год
                                                                                            //ФИО. Дата рождения. Планируется активный отдыхac
                        clickCalculatePageOnlineCalcGoingAbroad().                          //9. Нажать рассчитать
                checkResult();                                                              //10. Проверить значения:



    }

}
