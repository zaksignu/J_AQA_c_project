package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
        open("http://localhost:8080");
        var index = new IndexPage();
        dbCredit = index.letMeBuyViaCredit();
        Patterns.fillItCorrect(ghost, dbCredit);
    }

    @Test
    @DisplayName("DB credit test:Happy path with APPROVED card and DB inspection")
    public void happyPathWithApprovedCard(){
        String actual = dbCredit.happyPathWithApprovedCardAndDbCredit(ghost);
        String expected = "APPROVED";
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("DB credit test:Happy path with DECLINED card and DB inspection")
    public void happyPathWithDeclined(){
        String actual = dbCredit.happyPathWithDeclinedCardAndDbCredit(ghost);
        String expected = "DECLINED";
        Assertions.assertEquals(expected,actual);
    }

}
