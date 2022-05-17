package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.TravelOfTheDay;
import web.DataWizard;

import static com.codeborne.selenide.Selenide.open;

public class DbTests {
    static DataWizard.FellowOne ghost = DataWizard.UserManipulating.generateUser();
    static TravelOfTheDay travelPage = new TravelOfTheDay();

    @BeforeAll
    public static void startUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
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

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
}
