package tests;

import org.junit.jupiter.api.BeforeAll;
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
}
