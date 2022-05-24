package pages;

import web.DataBaseWizard;
import web.FellowOneEntity;
import web.Patterns;

import java.util.Calendar;

public class CreditBuy extends GenericPage {
    public CreditBuy() {
    }

    public String happyPathWithApprovedCardAndDbCredit(FellowOneEntity.FellowOne user) {
        Calendar cal = Patterns.patternForPageTests(cardNumberField, proceedButton, succesWithApprovedCard, user.getCards().getApprovedCard(), user.getCards().getApprovedCard(), user.getLongTime(), new CreditBuy());
        String act = DataBaseWizard.getPaymentStatusForCreditBuy(cal);
        return act;
    }

    public String happyPathWithDeclinedCardAndDbCredit(FellowOneEntity.FellowOne user) {
        Calendar cal = Patterns.patternForPageTests(cardNumberField, proceedButton, succesWithApprovedCard, user.getCards().getDeclinedCard(), user.getCards().getDeclinedCard(), user.getLongTime(), new CreditBuy());
        String act = DataBaseWizard.getPaymentStatusForCreditBuy(cal);
        return act;
    }

    public void wrongShortCardNumber(FellowOneEntity.FellowOne user) {
        Patterns.patternForPageTests(cardNumberField, proceedButton, cardFieldError, user.getCards().getWrongShortCard(), user.getCards().getApprovedCard(), user.getShortTime(), new CreditBuy());
    }

    public void wrongLongCardNumber(FellowOneEntity.FellowOne user) {
        Patterns.patternForPageTests(cardNumberField, proceedButton, cardReject, user.getCards().getWrongLongCard(), user.getCards().getApprovedCard(), user.getLongTime(), new CreditBuy());
    }

    public void wrongMonthNumberWOZero(FellowOneEntity.FellowOne user) {
        Patterns.patternForPageTests(cardMonthField, proceedButton, monthWrongFormatError, user.getDates().getInvalidMonthWOZero(), user.getDates().getValidMonth(), user.getShortTime(), new CreditBuy());
    }

    public void wrongMonth(FellowOneEntity.FellowOne user) {
        Patterns.patternForPageTests(cardMonthField, proceedButton, monthValidityError, user.getDates().getInvalidMonth(), user.getDates().getValidMonth(), user.getShortTime(), new CreditBuy());
    }

    public void wrongYearPast(FellowOneEntity.FellowOne user) {
        Patterns.patternForPageTests(cardYearField, proceedButton, yearPastDateError, user.getDates().getInvalidYearPast(), user.getDates().getValidYear(), user.getShortTime(), new CreditBuy());
    }

    public void wrongYearFuture(FellowOneEntity.FellowOne user) {
        Patterns.patternForPageTests(cardYearField, proceedButton, yearFutureDateError, user.getDates().getInvalidYearFuture(), user.getDates().getValidYear(), user.getShortTime(), new CreditBuy());
    }

    public void wrongCvc(FellowOneEntity.FellowOne user) {
        Patterns.patternForPageTests(cardCvcField, proceedButton, cvcBlankError, user.getCvc().getInvalidCvc(), user.getCvc().getValidCvc(), user.getShortTime(), new CreditBuy());
    }

    public void blankCardNumb(FellowOneEntity.FellowOne user) {
        Patterns.patternForPageTests(cardNumberField, proceedButton, cardFieldError, "", user.getCards().getApprovedCard(), user.getShortTime(), new CreditBuy());
    }

    public void blankMonth(FellowOneEntity.FellowOne user) {
        Patterns.patternForPageTests(cardMonthField, proceedButton, monthWrongFormatError, "", user.getDates().getValidMonth(), user.getShortTime(), new CreditBuy());
    }

    public void blankYear(FellowOneEntity.FellowOne user) {
        Patterns.patternForPageTests(cardYearField, proceedButton, yearBlankError, "", user.getDates().getValidYear(), user.getShortTime(), new CreditBuy());
    }

    public void blankCvc(FellowOneEntity.FellowOne user) {
        Patterns.patternForPageTests(cardCvcField, proceedButton, cvcBlankError, "", user.getCvc().getValidCvc(), user.getShortTime(), new CreditBuy());
    }

    public void blankOwner(FellowOneEntity.FellowOne user) {
        Patterns.patternForPageTests(cardOwnerField, proceedButton, ownerBlankError, "", user.getName().getValidName(), user.getShortTime(), new CreditBuy());
    }

}
