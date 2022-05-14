package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;
import org.openqa.selenium.Keys;
import web.DataWizard;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TravelOfTheDay {
    //buttons
    private SelenideElement toBuyButton = $(byText("Купить"));
    private SelenideElement toBuyWithCreditButton = $(byText("Купить в кредит"));
    private SelenideElement proceedButton = $x("//*[@id=\"root\"]/div/form/fieldset/div[4]/button");

    //fields
    private SelenideElement cardNumberField = $x("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[2]/input");
    private SelenideElement cardMonthField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[2]/input");
    private SelenideElement cardOwnerField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private SelenideElement cardYearField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[2]/input");
    private SelenideElement cardCvcField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[2]/input");

    //error pop-ups
    private SelenideElement cardFieldError = $x("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[3]");
    private SelenideElement monthWrongFormatError = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[contains(text(),'Неверный формат')]");
    private SelenideElement monthValidityError = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[contains(text(),'Неверно указан срок действия карты')]");
    private SelenideElement yearPastDateError = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[contains(text(),'Истёк срок действия карты')]");
    private SelenideElement yearFutureDateError = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[contains(text(),'Неверно указан срок действия карты')]");

    ////span[@class='input-group__input-case']//span[contains(text(),'Месяц')]//input[@class='input__control']
/// //*[@id="root"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]
//  //*[@id="root"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3][contains(text(),'Неверный формат')]
    public void click() {
        toBuyButton.click();
        toBuyWithCreditButton.click();
        System.out.println("");
    }
    public void MakeItStraigntBuy(){
        toBuyButton.click();
    }

    public void MakeItCreditBuy(){
        toBuyWithCreditButton.click();
    }

    private void ClearIt(SelenideElement entity) {
        entity.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE );
    }

    public void FillItCorrect(DataWizard.FellowOne user){
    //    cardNumberField.click();
        cardNumberField.setValue(user.getCards().getApprovedCard());
     //   cardMonthField.click();
       cardMonthField.setValue(user.getDates().getValidMonth());
      ///  cardYearField.click();
        cardYearField.setValue(user.getDates().getValidYear());
      //  cardOwnerField.click();
        cardOwnerField.setValue(user.getName().getValidName());
        cardCvcField.setValue(user.getCvc().getValidCvc());
    }

    public void WrongCardNumber(DataWizard.FellowOne user){
        ClearIt(cardNumberField);
        cardNumberField.setValue(user.getCards().getWrongCard());
        proceedButton.click();
        cardFieldError.shouldBe(Condition.visible);
        ClearIt(cardNumberField);
        cardNumberField.setValue(user.getCards().getApprovedCard());
    }

    public void WrongMonthNumberWOZero(DataWizard.FellowOne user){
        ClearIt(cardMonthField);
        cardMonthField.setValue(user.getDates().getInvalidMonthWOZero());
        proceedButton.click();
        monthWrongFormatError.shouldBe(Condition.visible);
        ClearIt(cardMonthField);
        cardMonthField.setValue(user.getDates().getValidMonth());
    }

    public void WrongMonth(DataWizard.FellowOne user){
        ClearIt(cardMonthField);
        cardMonthField.setValue(user.getDates().getInvalidMonth());
        proceedButton.click();
        monthValidityError.shouldBe(Condition.visible);
        ClearIt(cardMonthField);
        cardMonthField.setValue(user.getDates().getValidMonth());
    }

    public void WrongYearPast(DataWizard.FellowOne user){
        ClearIt(cardYearField);
        cardYearField.setValue(user.getDates().getInvalidYearPast());
        proceedButton.click();
        yearPastDateError.shouldBe(Condition.visible);
        ClearIt(cardYearField);
        cardYearField.setValue(user.getDates().getValidMonth());
    }

    public void WrongYearFuture(DataWizard.FellowOne user){
        ClearIt(cardYearField);
        cardYearField.setValue(user.getDates().getInvalidYearFuture());
        proceedButton.click();
        yearFutureDateError.shouldBe(Condition.visible);
        ClearIt(cardYearField);
        cardYearField.setValue(user.getDates().getValidMonth());
    }
}
