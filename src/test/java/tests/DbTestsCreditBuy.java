package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.CreditBuy;
import pages.IndexPage;
import web.DataWizard;
import web.FellowOneEntity;
import web.Patterns;

import static com.codeborne.selenide.Selenide.open;

public class DbTestsCreditBuy {
    static FellowOneEntity.FellowOne ghost = DataWizard.UserManipulating.generateUser();
    static CreditBuy dbCredit;

    @BeforeAll
    public static void startUp() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        open("http://localhost:8080");
        var index = new IndexPage();
        dbCredit = index.letMeBuyViaCredit();
        Patterns.fillItCorrect(ghost, dbCredit);
    }

    @AfterAll
    public static void tearDown() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    @DisplayName("DB credit test:Happy path with APPROVED card and DB inspection")
    public void happyPathWithApprovedCard() {
        String actual = dbCredit.happyPathWithApprovedCardAndDbCredit(ghost);
        String expected = "APPROVED";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("DB credit test:Happy path with DECLINED card and DB inspection")
    public void happyPathWithDeclined() {
        String actual = dbCredit.happyPathWithDeclinedCardAndDbCredit(ghost);
        String expected = "DECLINED";
        Assertions.assertEquals(expected, actual);
    }


}
