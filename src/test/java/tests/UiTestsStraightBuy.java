package tests;

//import com.codeborne.selenide.logevents.SelenideLogger;
//import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TravelOfTheDay;
import web.DataWizard;

import static com.codeborne.selenide.Selenide.open;

public class UiTestsStraightBuy {
    static DataWizard.FellowOne ghost = DataWizard.UserManipulating.generateUser();
    static TravelOfTheDay travelPage = new TravelOfTheDay();

    @BeforeAll
    public static void startUp() {
//        SelenideLogger.addListener("allure", new AllureSelenide());
        open("http://localhost:8080");
        travelPage.makeItStraightBuy();
        travelPage.fillItCorrect(ghost);
    }

//    @Test
//    @DisplayName("UI test:Happy path with APPROVED card")
//    public void happyPathWithApprovedCard(){
//        travelPage.happyPathWithAprovedCard(ghost);
//    }
//
//    @Test
//    @DisplayName("UI test:Happy path with DECLINED card")
//    public void happyPathWithDeclinedCard(){
//        travelPage.happyPathWithDeclinedCard(ghost);
//    }

    @Test
    @DisplayName("UI straight test:Non complete card number")
    public void shouldNotWorkWithWrongShortCardNumber(){
        travelPage.wrongShortCardNumber(ghost);
    }

    @Test
    @DisplayName("UI straight test:Full random card number (neither approved, nor declined)")
    public void shouldNotWorkWithWrongLongCardNumber(){
        travelPage.wrongLongCardNumber(ghost);
    }

    @Test
    @DisplayName("UI straight test:Wrong month ( without 0)")
    public void shouldNotWorkWithWrongMonthWOZero(){
        travelPage.wrongMonthNumberWOZero(ghost);
    }

    @Test
    @DisplayName("UI straight test:Wrong month ( >12)")
    public void shouldNotWorkWithWrongMonth(){
        travelPage.wrongMonth(ghost);
    }

    @Test
    @DisplayName("UI straight test:Wrong year ( < current)")
    public void shouldNotWorkWithWrongYearPast(){
        travelPage.wrongYearPast(ghost);
    }


    @Test
    @DisplayName("UI straight test:Wrong year ( >current)")
    public void shouldNotWorkWithWrongYearFuture(){
        travelPage.wrongYearFuture(ghost);
    }

    @Test
    @DisplayName("UI straight test:short CVC")
    public void shouldNotWorkWithShortCvc(){
        travelPage.wrongCvc(ghost);
    }



    @Test
    @DisplayName("UI straight test:Blank card numb")
    public void shouldNotWorkWithBlankCard(){
        travelPage.blankCardNumb(ghost);
    }

    @Test
    @DisplayName("UI straight test:Blank month")
    public void shouldNotWorkWithBlankMonth(){
        travelPage.blankMonth(ghost);
    }

    @Test
    @DisplayName("UI straight test:Blank year")
    public void shouldNotWorkWithBlankYear(){
        travelPage.blankYear(ghost);
    }

    @Test
    @DisplayName("UI straight test:Blank CVC")
    public void shouldNotWorkWithBlankCvc(){
        travelPage.blankCvc(ghost);
    }

    @Test
    @DisplayName("UI straight test:Blank owner")
    public void shouldNotWorkWithBlankOwner(){
        travelPage.blankOwner(ghost);
    }

//    @AfterAll
//    static void tearDownAll() {
//        SelenideLogger.removeListener("allure");
//    }


}
