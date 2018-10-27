public class RGSTravelAbroadSteps extends CommonMethodsToSimplify {
    //Переменные xpath необходимых для теста Web элементов
    //BT - Button - кнопка
    //CB - CheckBox - переключатель
    //F  - Field - поле для заполнения
    //INS - Insurance - страхование
    //HP - Home Page - главная страница
    final static String HOME_PAGE_LINK = "https://www.rgs.ru/";
    final static String BT_INS_HP = "//*[@id='main-navbar-collapse']//a[contains(text(),'Страхование')]";
    final static String BT_INS_HP_GOING_ABROAD = "//a[contains(text(),'Выезжающим за рубеж')]";
    final static String BT_CALC_PAGE_INS_GOING_ABROAD = "//*[@class='btn btn-attention btn-sm btn-block text-uppercase text-semibold' and contains(text(), 'Рассчитать')]";
    final static String HEADLINE_PAGE_INS_GOING_ABROAD = "//*[@class='h1' and contains(text(), 'Страхование выезжающих')]";
    final static String BT_FEW_TRIPS_PAGE_ONLINE_CALC = "//*[@data-test-value='Multiple']";
    final static String CB_ACCEPT_PAGE_ONLINE_CALC = "//*[@class='adaptive-checkbox-label' and contains(text(),'Я согласен на обработку моих персональных данных в целях расчета страховой премии')]";
    final static String BT_CALCULATE_PAGE_ONLINE_CALC = "//*[@type='submit' and contains(text(), 'Рассчитать')]";
    final static String F_WHERE_WE_GO_PAGE_ONLINE_CALC = "//label[text()=' Куда едем? ']/following::input[1]";
    final static String F_COUNTRU_SELECT_PAGE_ONLINE_CALC = "//*[@id='ArrivalCountryList']";
  //final static String F_DATE_FIRST_TRAVEL_PAGE_ONLINE_CALC = "//*[@class='form-control width-xs-9rem width-sm-9rem validation-control-has-error']";
  //final static String F_DATE_FIRST_TRAVEL_PAGE_ONLINE_CALC = "//*[@class='form-control width-xs-9rem width-sm-9rem validation-control-has-error collapsing-in collapsing-out']";
  //final static String F_DATE_FIRST_TRAVEL_PAGE_ONLINE_CALC = "//*[@class='form-control width-xs-9rem width-sm-9rem validation-control-has-error collapsing-in']";
    final static String F_DATE_FIRST_TRAVEL_PAGE_ONLINE_CALC = "//label[contains(text(),' Дата первой поездки ')]/following::input[1]";
    final static String BT_SUM_DAYS_ABROAD_PAGE_ONLINE_CALC = "//*[contains(text(),' Не более 90 дней ')]";
    final static String F_FIO_PAGE_ONLINE_CALC = "//span[contains(text(),' Фамилия')]/parent::*/following::input[1]";
    final static String F_BIRTHDAY_PAGE_ONLINE_CALC = "//span[contains(text(),' Фамилия')]/parent::*/following::input[4]";
    final static String BT_LEISURE_PAGE_ONLINE_CALC = "//*[@class='toggle off toggle-rgs']";
    final static String GET_RESULT_INSURANCE_TERMS = "//*[text()=' Условия страхования ']/parent::*/*/span";
    final static String GET_RESULT_TERRITOTY = "//div[@class='summary']/div[@class='summary-row']/span/span/strong";
    final static String GET_RESULT_INSURED_FIO = "//div[@class='summary']/*/div[@class='summary-row']/span/span/strong";
    final static String GET_RESULT_BIRTHDAY = "//div[@class='summary']/*/div[@class='summary-row']/span/span/span/strong";
    final static String GET_RESULT_LEISURYE = "//span[@class='text-bold' and contains(text(),'Включен')]";

    //Переменные для заполнения форм
    static String wereWeGo = "Шенген";
    static String country = "Испания";
    static String dateFirstTravel = "01.11.2018";
    static String fio = "IVANOV IVAN";
    static String birthday = "15.02.1997";

    //Переменные для проверки заголовков и ожидаемого/полученного результатов
    final static String EXPECTED_HEADLINE_INS_GOING_ABROAD = "Страхование выезжающих за рубеж";
    final static String EXPECTED_RESULT_INSURANCE_TERMS = "Многократные поездки в течение года";
    final static String EXPECTED_RESULT_TERRITOTY = wereWeGo;
    final static String EXPECTED_RESULT_INSURED_FIO = fio;
    final static String EXPECTED_RESULT_BIRTHDAY = birthday;
    final static String EXPECTED_RESULT_LEISURYE = "Включен";



    RGSTravelAbroadSteps(){

    };

    //клик на кнопку страхование на главной странице
    public RGSTravelAbroadSteps insuranceHomePageClick (){
        waitVisibleAndScrollAndClick(BT_INS_HP);
        return this;
    }
    //на главной странице в Меню страхование переход по кнопке "Выезжающим за рубеж'
    public RGSTravelAbroadSteps clickButtonGoingAbroadMenuInsuranceHP(){
        waitVisibleAndScrollAndClick(BT_INS_HP_GOING_ABROAD);
        return this;
    }
    //клик по кнопке "Рассчитать" на странице "Страхование выезжающих за рубеж"
    public RGSTravelAbroadSteps clickCalculatePageInsuranceGoingAbroad(){
        waitVisibleAndScrollAndClick(BT_CALC_PAGE_INS_GOING_ABROAD);
        return this;
    }
    //проверка заголовка "Страхование выезжающих за рубеж" в онлайн калькуляторе
    public RGSTravelAbroadSteps checkEqualsHeadCalcGoingAbroad(){
        checkEquals(EXPECTED_HEADLINE_INS_GOING_ABROAD, HEADLINE_PAGE_INS_GOING_ABROAD);
        return this;
    }
    //Заполнение формы онлайн калькулятора: 1. Несколько поездок в течении года. 2.Я согласен на обработку данных  - выбрать чекбокс
    public RGSTravelAbroadSteps clickFewTipsAndAcceptPersInfo(){
            waitVisibleAndScrollAndClick(BT_FEW_TRIPS_PAGE_ONLINE_CALC);
            waitVisibleAndScrollAndClick(CB_ACCEPT_PAGE_ONLINE_CALC);
        return this;
    }
    //Клик по кнопке рассчитать на странице онлайн калькулятора вызжающих за границу
    public RGSTravelAbroadSteps clickCalculatePageOnlineCalcGoingAbroad (){
        waitVisibleAndScrollAndClick(BT_CALCULATE_PAGE_ONLINE_CALC);
        return this;
    }
    //8. Заполнить поля: 1.Куда едем – Шенген 2. Страна въезда – Испания 3.Дата первой поездки – 1 ноября
                        //4. Сколько дней планируете пробыть за рубежом за год – не более 90 5. ФИО
                        // 6. Дата рождения 7. Планируется активный отдыхac
    public RGSTravelAbroadSteps fillingFieldOnlineCalc() {
        insertTextIntoWebElement(F_WHERE_WE_GO_PAGE_ONLINE_CALC, wereWeGo, "DOWN", "ENTER");
        chooseCountry(F_COUNTRU_SELECT_PAGE_ONLINE_CALC, country);
        //insertTextIntoWebElement(F_DATE_FIRST_TRAVEL_PAGE_ONLINE_CALC, dateFirstTravel, "TAB");
        insertDate(F_DATE_FIRST_TRAVEL_PAGE_ONLINE_CALC, dateFirstTravel);
        waitVisibleAndScrollAndClick(BT_SUM_DAYS_ABROAD_PAGE_ONLINE_CALC);
        insertTextIntoWebElement(F_FIO_PAGE_ONLINE_CALC, fio);
        //insertTextIntoWebElement(F_BIRTHDAY_PAGE_ONLINE_CALC, birthday);
        insertDate(F_BIRTHDAY_PAGE_ONLINE_CALC, birthday);
        waitVisibleAndScrollAndClick(BT_LEISURE_PAGE_ONLINE_CALC);
        return this;
    }
    //10. Проверить значения: Условия страхования. Многократные поездки в течении года. Территория – Шенген. Застрахованный. Дата рождения. Активный отдых - включен
    public void checkResult (){
        checkEquals(EXPECTED_RESULT_INSURANCE_TERMS, GET_RESULT_INSURANCE_TERMS);
        checkEquals(EXPECTED_RESULT_TERRITOTY, GET_RESULT_TERRITOTY);
        checkEquals(EXPECTED_RESULT_INSURED_FIO, GET_RESULT_INSURED_FIO);
        checkEquals(EXPECTED_RESULT_BIRTHDAY, GET_RESULT_BIRTHDAY);
        checkEquals(EXPECTED_RESULT_LEISURYE, GET_RESULT_LEISURYE);
    }

}
