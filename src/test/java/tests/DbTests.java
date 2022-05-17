package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TravelOfTheDay;
import web.DataWizard;

import static com.codeborne.selenide.Selenide.open;

public class DbTests {
    static DataWizard.FellowOne ghost = DataWizard.UserManipulating.generateUser();
    static TravelOfTheDay travelPage = new TravelOfTheDay();

    @BeforeAll
    public static void startUp() {

        open("http://localhost:8080");
        travelPage.makeItStraightBuy();
        travelPage.fillItCorrect(ghost);
    }

    @Test
    @DisplayName("DB test:Happy path with APPROVED card and DB inspection")
    public void happyPathWithApprovedCard(){
        //var travelPage = new TravelOfTheDay();
        String actual = travelPage.happyPathWithApprovedCardAndDb(ghost);
        String expected = "APPROVED";
        Assertions.assertEquals(expected,actual);
      //  System.out.println("");
    }

    @Test
    @DisplayName("DB test:Happy path with DECLINED card and DB inspection")
    public void happyPathWithDeclined(){
        //var travelPage = new TravelOfTheDay();
        String actual = travelPage.happyPathWithDeclinedCardAndDb(ghost);
        String expected = "DECLINED";
        Assertions.assertEquals(expected,actual);
        //  System.out.println("");
    }
}
