package tests;

//import com.codeborne.selenide.logevents.SelenideLogger;
//import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.IndexPage;
import pages.StraightBuy;
import web.DataWizard;
import web.FellowOneEntity;
import web.Patterns;

import static com.codeborne.selenide.Selenide.open;

public class DbTestsStraightBuy {
    static FellowOneEntity.FellowOne ghost = DataWizard.UserManipulating.generateUser();
    static StraightBuy dbStraight;

    @BeforeAll
    public static void startUp() {
//        SelenideLogger.addListener("allure", new AllureSelenide());
        open("http://localhost:8080");
        var index = new IndexPage();
        dbStraight = index.letMeBuyStraight();
        Patterns.fillItCorrect(ghost, dbStraight);
    }


//     @AfterAll
//    public static void tearDown() {
//        SelenideLogger.removeListener("allure");
//    }

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
