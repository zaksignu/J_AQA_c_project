package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import pages.IndexPage;
import pages.StraightBuy;
import web.DataWizard;
import web.FellowOneEntity;
import web.Patterns;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;

public class DbTestsStraightBuy {
    static FellowOneEntity.FellowOne ghost = DataWizard.UserManipulating.generateUser();
    static StraightBuy dbStraight;

    @BeforeAll
    public static void startUp() {
        WebDriverManager.chromedriver().driverVersion("85").setup();
        open("http://localhost:8080");
        var index = new IndexPage();
        dbStraight = index.letMeBuyStraight();
        Patterns.fillItCorrect(ghost, dbStraight);
    }
    @AfterAll
    public static void killIt(){
        closeWindow();
    }
    @Test
    @DisplayName("DB straight test:Happy path with APPROVED card and DB inspection")
    public void happyPathWithApprovedCard() {
        String actual = dbStraight.happyPathWithApprovedCardAndDbStraight(ghost);
        String expected = "APPROVED";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("DB straight test:Happy path with DECLINED card and DB inspection")
    public void happyPathWithDeclined() {
        String actual = dbStraight.happyPathWithDeclinedCardAndDbStraight(ghost);
        String expected = "DECLINED";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("DB straight:Price inspection for happyPath")
    public void priceForTourCheck(){
        String actual = dbStraight.lookingForPriceWithApprovedCard(ghost);
        String expected = "4500000";
        Assertions.assertEquals(expected,actual);
    }

}
