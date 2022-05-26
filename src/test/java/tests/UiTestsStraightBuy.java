package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.IndexPage;
import pages.StraightBuy;
import web.DataWizard;
import web.FellowOneEntity;
import web.Patterns;

import static com.codeborne.selenide.Selenide.open;

public class UiTestsStraightBuy {
    static FellowOneEntity.FellowOne ghost = DataWizard.UserManipulating.generateUser();
    static StraightBuy uiStraight;

    @BeforeAll
    public static void startUp() {
        open("http://localhost:8080");
        var index = new IndexPage();
        uiStraight = index.letMeBuyStraight();
        Patterns.fillItCorrect(ghost, uiStraight);
    }

    @Test
    @DisplayName("UI straight test:Non complete card number")
    public void shouldNotWorkWithWrongShortCardNumber() {
        uiStraight.wrongShortCardNumber(ghost);
    }

    @Test
    @DisplayName("UI straight test:Full random card number (neither approved, nor declined)")
    public void shouldNotWorkWithWrongLongCardNumber() {
        uiStraight.wrongLongCardNumber(ghost);
    }

    @Test
    @DisplayName("UI straight test:Wrong month ( without 0)")
    public void shouldNotWorkWithWrongMonthWOZero() {
        uiStraight.wrongMonthNumberWOZero(ghost);
    }

    @Test
    @DisplayName("UI straight test:Wrong month ( >12)")
    public void shouldNotWorkWithWrongMonth() {
        uiStraight.wrongMonth(ghost);
    }

    @Test
    @DisplayName("UI straight test:Wrong year ( < current)")
    public void shouldNotWorkWithWrongYearPast() {
        uiStraight.wrongYearPast(ghost);
    }


    @Test
    @DisplayName("UI straight test:Wrong year ( >current)")
    public void shouldNotWorkWithWrongYearFuture() {
        uiStraight.wrongYearFuture(ghost);
    }

    @Test
    @DisplayName("UI straight test:short CVC")
    public void shouldNotWorkWithShortCvc() {
        uiStraight.wrongCvc(ghost);
    }

    @Test
    @DisplayName("UI straight test:Blank card numb")
    public void shouldNotWorkWithBlankCard() {
        uiStraight.blankCardNumb(ghost);
    }

    @Test
    @DisplayName("UI straight test:Blank month")
    public void shouldNotWorkWithBlankMonth() {
        uiStraight.blankMonth(ghost);
    }

    @Test
    @DisplayName("UI straight test:Blank year")
    public void shouldNotWorkWithBlankYear() {
        uiStraight.blankYear(ghost);
    }

    @Test
    @DisplayName("UI straight test:Blank CVC")
    public void shouldNotWorkWithBlankCvc() {
        uiStraight.blankCvc(ghost);
    }

    @Test
    @DisplayName("UI straight test:Blank owner")
    public void shouldNotWorkWithBlankOwner() {
        uiStraight.blankOwner(ghost);
    }

}
