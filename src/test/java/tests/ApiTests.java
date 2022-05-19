package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.DataWizard;

public class ApiTests {
    static DataWizard.FellowOne ghost = DataWizard.UserManipulating.generateUser();
    static DataWizard.ApiPaths aPath  = new DataWizard.ApiPaths();

 @Test
 @DisplayName("Response with approved card for straight payment ")
public void approvedCardWithStraightPAyment() {
     String expected = "APPROVED";
     String actual = DataWizard.Patterns.postIt(ghost, aPath.getStraightPath(),ghost.getCards().getApprovedCardLong(),"status");
     Assertions.assertEquals(expected, actual);
 }

    @Test
    @DisplayName("Response with declined card for straight payment ")
    public void declinedCardWithStraightPAyment() {
        String expected = "DECLINED";
        String actual = DataWizard.Patterns.postIt(ghost, aPath.getStraightPath(),ghost.getCards().getDeclinedCardLong(),"status");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Response with approved card for credit payment ")
    public void approvedCardWithCReditPAyment() {
        String expected = "APPROVED";
        String actual = DataWizard.Patterns.postIt(ghost, aPath.getCreditPath(),ghost.getCards().getApprovedCardLong(),"status");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Response with declined card for credit payment ")
    public void declinedCardWithCReditPAyment() {
        String expected = "DECLINED";
        String actual = DataWizard.Patterns.postIt(ghost, aPath.getCreditPath(),ghost.getCards().getDeclinedCardLong(),"status");
        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Response with random card for straight payment ")
    public void randomCardWithStraightPayment() {
        String expected = "Server error";
        String actual = DataWizard.Patterns.postIt(ghost, aPath.getStraightPath(),ghost.getCards().getRandomCardLong(),"error");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Response with random card for credit payment ")
    public void randomCardWithCreditPayment() {
        String expected = "Server error";
        String actual = DataWizard.Patterns.postIt(ghost, aPath.getCreditPath(),ghost.getCards().getRandomCardLong(),"error");
        Assertions.assertEquals(expected, actual);
    }
}

