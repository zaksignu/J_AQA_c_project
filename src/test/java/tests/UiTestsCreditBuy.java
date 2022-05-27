package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CreditBuy;
import pages.IndexPage;
import web.DataWizard;
import web.FellowOneEntity;
import web.Patterns;

import static com.codeborne.selenide.Selenide.open;

public class UiTestsCreditBuy {

    static FellowOneEntity.FellowOne ghost = DataWizard.UserManipulating.generateUser();
    static CreditBuy uiCredit;

    @BeforeAll
    public static void startUp() {

        open("http://localhost:8080");
        var index = new IndexPage();
        uiCredit = index.letMeBuyViaCredit();
        Patterns.fillItCorrect(ghost, uiCredit);
    }

    @Test
    @DisplayName("UI credit test:Non complete card number")
    public void shouldNotWorkWithWrongShortCardNumber(){
        uiCredit.wrongShortCardNumber(ghost);
    }

    @Test
    @DisplayName("UI credit test:Full random card number (neither approved, nor declined)")
    public void shouldNotWorkWithWrongLongCardNumber(){
        uiCredit.wrongLongCardNumber(ghost);
    }

    @Test
    @DisplayName("UI credit test:Wrong month ( without 0)")
    public void shouldNotWorkWithWrongMonthWOZero(){
        uiCredit.wrongMonthNumberWOZero(ghost);
    }

    @Test
    @DisplayName("UI credit test:Wrong month ( >12)")
    public void shouldNotWorkWithWrongMonth(){
        uiCredit.wrongMonth(ghost);
    }

    @Test
    @DisplayName("UI credit test:Wrong year ( < current)")
    public void shouldNotWorkWithWrongYearPast(){
        uiCredit.wrongYearPast(ghost);
    }


    @Test
    @DisplayName("UI credit test:Wrong year ( >current)")
    public void shouldNotWorkWithWrongYearFuture(){
        uiCredit.wrongYearPast(ghost);

    }

    @Test
    @DisplayName("UI credit test:short CVC")
    public void shouldNotWorkWithShortCvc(){
        uiCredit.wrongCvc(ghost);
    }

    @Test
    @DisplayName("UI credit test:Blank card numb")
    public void shouldNotWorkWithBlankCard(){
        uiCredit.blankCardNumb(ghost);
    }

    @Test
    @DisplayName("UI credit test:Blank month")
    public void shouldNotWorkWithBlankMonth(){
        uiCredit.blankCardNumb(ghost);
    }

    @Test
    @DisplayName("UI credit test:Blank year")
    public void shouldNotWorkWithBlankYear(){
        uiCredit.blankYear(ghost);
    }

    @Test
    @DisplayName("UI credit test:Blank CVC")
    public void shouldNotWorkWithBlankCvc(){
        uiCredit.blankCvc(ghost);
    }

    @Test
    @DisplayName("UI credit test:Blank owner")
    public void shouldNotWorkWithBlankOwner(){
        uiCredit.blankOwner(ghost);
    }


}
