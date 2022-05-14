package tests;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TravelOfTheDay;
import web.DataWizard;

import static com.codeborne.selenide.Selenide.open;

public class UiTests {
    static DataWizard.FellowOne ghost = DataWizard.UserManipulating.generateUser();
    static TravelOfTheDay travelPage = new TravelOfTheDay();

    @BeforeAll
    public static void startUp() {

        open("http://localhost:8080");
//        var travelPage = new TravelOfTheDay();
        travelPage.makeItStraightBuy();
        travelPage.fillItCorrect(ghost);

     //   travelPage.click();
    }

    @Test
    @DisplayName("UI test:Happy path with APPROVED card")
    public void happyPathWithApprovedCard(){
        //var travelPage = new TravelOfTheDay();
        travelPage.happyPathWithAprovedCard();
    }

    @Test
    @DisplayName("UI test:Happy path with DECLINED card")
    public void happyPathWithDeclinedCard(){
        //var travelPage = new TravelOfTheDay();
        travelPage.happyPathWithDeclinedCard(ghost);
    }

    @Test
    @DisplayName("UI test:Non complete card number")
    public void shouldNotWorkWithWrongCardNumber(){
        //var travelPage = new TravelOfTheDay();
        travelPage.wrongCardNumber(ghost);
    }

    @Test
    @DisplayName("UI test:Wrong month ( without 0)")
    public void shouldNotWorkWithWrongMonthWOZero(){
       // var travelPage = new TravelOfTheDay();
        travelPage.wrongMonthNumberWOZero(ghost);
    }

    @Test
    @DisplayName("UI test:Wrong month ( >12)")
    public void shouldNotWorkWithWrongMonth(){
      //  var travelPage = new TravelOfTheDay();
        travelPage.wrongMonth(ghost);
    }

    @Test
    @DisplayName("UI test:Wrong year ( < current)")
    public void shouldNotWorkWithWrongYearPast(){
      //  var travelPage = new TravelOfTheDay();
        travelPage.wrongYearPast(ghost);
    }


    @Test
    @DisplayName("UI test:Wrong year ( >current)")
    public void shouldNotWorkWithWrongYearFuture(){
     //   var travelPage = new TravelOfTheDay();
        travelPage.wrongYearFuture(ghost);
    }

    @Test
    @DisplayName("UI test:short CVC")
    public void shouldNotWorkWithShortCvc(){
     //   var travelPage = new TravelOfTheDay();
        travelPage.wrongCvc(ghost);
    }



    @Test
    @DisplayName("UI test:Blank card numb")
    public void shouldNotWorkWithBlankCard(){
      //  var travelPage = new TravelOfTheDay();
        travelPage.blankCardNumb(ghost);
    }

    @Test
    @DisplayName("UI test:Blank month")
    public void shouldNotWorkWithBlankMonth(){
      //  var travelPage = new TravelOfTheDay();
        travelPage.blankMonth(ghost);
    }

    @Test
    @DisplayName("UI test:Blank year")
    public void shouldNotWorkWithBlankYear(){
      //  var travelPage = new TravelOfTheDay();
        travelPage.blankYear(ghost);
    }

    @Test
    @DisplayName("UI test:Blank CVC")
    public void shouldNotWorkWithBlankCvc(){
      //  var travelPage = new TravelOfTheDay();
        travelPage.blankCvc(ghost);
    }

    @Test
    @DisplayName("UI test:Blank owner")
    public void shouldNotWorkWithBlankOwner(){
       // var travelPage = new TravelOfTheDay();
        travelPage.blankOwner(ghost);
    }


}
