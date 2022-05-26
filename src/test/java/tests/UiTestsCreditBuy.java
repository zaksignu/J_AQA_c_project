package tests;

//import com.codeborne.selenide.logevents.SelenideLogger;
//import io.qameta.allure.selenide.AllureSelenide;
//import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CreditBuy;
import pages.IndexPage;
import web.DataWizard;
import web.FellowOneEntity;
import web.Patterns;

import static com.codeborne.selenide.Selenide.open;

public class UiTestsCreditBuy {

    static FellowOneEntity.FellowOne ghost = DataWizard.UserManipulating.generateUser();
  //  static TravelOfTheDay travelPage = new TravelOfTheDay();
    static CreditBuy uiCredit;

    @BeforeAll
    public static void startUp() {
//        SelenideLogger.addListener("allure", new AllureSelenide());
//        SelenideLogger.addListener("allure", new AllureSelenide());
        open("http://localhost:8080");
        var index = new IndexPage();
        uiCredit = index.letMeBuyViaCredit();
        Patterns.fillItCorrect(ghost, uiCredit);
      //  travelPage.makeItCreditBuy();
     //   travelPage.fillItCorrect(ghost);
    }

//    @AfterAll
//    public static void tearDown() {
//        SelenideLogger.removeListener("allure");
//    }
//    @Test
//    @DisplayName("UI credit test:Happy path with APPROVED card")
//    public void happyPathWithApprovedCard(){
//        travelPage.happyPathWithAprovedCard(ghost);
//    }
//
//    @Test
//    @DisplayName("UI credit test:Happy path with DECLINED card")
//    public void happyPathWithDeclinedCard(){
//        travelPage.happyPathWithDeclinedCard(ghost);
//    }

    @Test
    @DisplayName("UI credit test:Non complete card number")
    public void shouldNotWorkWithWrongShortCardNumber(){

       // travelPage.wrongShortCardNumber(ghost);
        uiCredit.wrongShortCardNumber(ghost);
    }

    @Test
    @DisplayName("UI credit test:Full random card number (neither approved, nor declined)")
    public void shouldNotWorkWithWrongLongCardNumber(){
      //  travelPage.wrongLongCardNumber(ghost);
        uiCredit.wrongLongCardNumber(ghost);
    }

    @Test
    @DisplayName("UI credit test:Wrong month ( without 0)")
    public void shouldNotWorkWithWrongMonthWOZero(){
      //  travelPage.wrongMonthNumberWOZero(ghost);
        uiCredit.wrongMonthNumberWOZero(ghost);
    }

    @Test
    @DisplayName("UI credit test:Wrong month ( >12)")
    public void shouldNotWorkWithWrongMonth(){
    //    travelPage.wrongMonth(ghost);
        uiCredit.wrongMonth(ghost);
    }

    @Test
    @DisplayName("UI credit test:Wrong year ( < current)")
    public void shouldNotWorkWithWrongYearPast(){
       // travelPage.wrongYearPast(ghost);
        uiCredit.wrongYearPast(ghost);
    }


    @Test
    @DisplayName("UI credit test:Wrong year ( >current)")
    public void shouldNotWorkWithWrongYearFuture(){
       // travelPage.wrongYearFuture(ghost);
        uiCredit.wrongYearPast(ghost);

    }

    @Test
    @DisplayName("UI credit test:short CVC")
    public void shouldNotWorkWithShortCvc(){
    //    travelPage.wrongCvc(ghost);
        uiCredit.wrongCvc(ghost);
    }



    @Test
    @DisplayName("UI credit test:Blank card numb")
    public void shouldNotWorkWithBlankCard(){
      //  travelPage.blankCardNumb(ghost);
        uiCredit.blankCardNumb(ghost);
    }

    @Test
    @DisplayName("UI credit test:Blank month")
    public void shouldNotWorkWithBlankMonth(){
    //    travelPage.blankMonth(ghost);
        uiCredit.blankCardNumb(ghost);
    }

    @Test
    @DisplayName("UI credit test:Blank year")
    public void shouldNotWorkWithBlankYear(){
       // travelPage.blankYear(ghost);
        uiCredit.blankYear(ghost);
    }

    @Test
    @DisplayName("UI credit test:Blank CVC")
    public void shouldNotWorkWithBlankCvc(){
       // travelPage.blankCvc(ghost);
        uiCredit.blankCvc(ghost);
    }

    @Test
    @DisplayName("UI credit test:Blank owner")
    public void shouldNotWorkWithBlankOwner(){

        //travelPage.blankOwner(ghost);
        uiCredit.blankOwner(ghost);
    }

//    @AfterAll
//    static void tearDownAll() {
//        SelenideLogger.removeListener("allure");
//    }

}
