package tests;

import org.junit.jupiter.api.*;
import pages.TravelOfTheDay;
import web.DataWizard;

import static com.codeborne.selenide.Selenide.open;

public class DbTestsCreditBuy {
    static DataWizard.FellowOne ghost = DataWizard.UserManipulating.generateUser();
    static TravelOfTheDay travelPage = new TravelOfTheDay();

    @BeforeAll
    public static void startUp() {

        open("http://localhost:8080");
        travelPage.makeItCreditBuy();
        travelPage.fillItCorrect(ghost);
    }

    @Test
    @DisplayName("DB credit test:Happy path with APPROVED card and DB inspection")
    public void happyPathWithApprovedCard(){
        String actual = travelPage.happyPathWithApprovedCardAndDbCredit(ghost);
        String expected = "APPROVED";
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("DB credit test:Happy path with DECLINED card and DB inspection")
    public void happyPathWithDeclined(){
        String actual = travelPage.happyPathWithDeclinedCardAndDbCredit(ghost);
        String expected = "DECLINED";
        Assertions.assertEquals(expected,actual);
    }

}
