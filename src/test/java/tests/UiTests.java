package tests;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TravelOfTheDay;
import web.DataWizard;

import static com.codeborne.selenide.Selenide.open;

public class UiTests {
    static DataWizard.FellowOne ghost = DataWizard.UserManipulating.generateUser();


    @BeforeAll
    public static void startUp() {

        open("http://localhost:8080");
        var travelPage = new TravelOfTheDay();
        travelPage.MakeItStraigntBuy();
        travelPage.FillItCorrect(ghost);

     //   travelPage.click();
    }

    @Test
    @DisplayName("UI test:Non complete card number")
    public void ShouldNotWorkWithWrongCardNumber(){
        var travelPage = new TravelOfTheDay();
        travelPage.WrongCardNumber(ghost);
    }

    @Test
    @DisplayName("UI test:Wrong month ( without 0)")
    public void ShouldNotWorkWithWrongMonthWOZero(){
        var travelPage = new TravelOfTheDay();
        travelPage.WrongMonthNumberWOZero(ghost);
    }

    @Test
    @DisplayName("UI test:Wrong month ( >12)")
    public void ShouldNotWorkWithWrongMonth(){
        var travelPage = new TravelOfTheDay();
        travelPage.WrongMonth(ghost);
    }

    @Test
    @DisplayName("UI test:Wrong year ( < current)")
    public void ShouldNotWorkWithWrongYearPast(){
        var travelPage = new TravelOfTheDay();
        travelPage.WrongYearPast(ghost);
    }


    @Test
    @DisplayName("UI test:Wrong year ( < current)")
    public void ShouldNotWorkWithWrongYearFuture(){
        var travelPage = new TravelOfTheDay();
        travelPage.WrongYearFuture(ghost);
    }
}
