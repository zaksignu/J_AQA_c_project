package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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
    private SelenideElement yearBlankError = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[contains(text(),'Неверный формат')]");
    private SelenideElement cvcBlankError = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[contains(text(),'Неверный формат')]");
    private SelenideElement ownerBlankError = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[contains(text(),'Поле обязательно для заполнения')]");

    //sucess pop-ups
    private SelenideElement succesWithApprovedCard = $x("//*[@id=\"root\"]/div/div[2]/div[contains(text(),'Операция одобрена Банком.')]");

    //*[@id="root"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[3]
    ////span[@class='input-group__input-case']//span[contains(text(),'Месяц')]//input[@class='input__control']
/// //*[@id="root"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]
//  //*[@id="root"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3][contains(text(),'Неверный формат')]

    public void makeItStraightBuy(){
        toBuyButton.click();

    }

    public void makeItCreditBuy(){
        toBuyWithCreditButton.click();
    }

    private void clearIt(SelenideElement entity) {
        entity.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE );
    }

    public void fillItCorrect(DataWizard.FellowOne user){
      //  cardNumberField.click();
        cardNumberField.setValue(user.getCards().getApprovedCard());
      //  cardMonthField.click();
       cardMonthField.setValue(user.getDates().getValidMonth());
     //  cardYearField.click();
        cardYearField.setValue(user.getDates().getValidYear());
     //  cardOwnerField.click();
        cardOwnerField.setValue(user.getName().getValidName());
        cardCvcField.setValue(user.getCvc().getValidCvc());
    }

    public void happyPathWithAprovedCard(){
        proceedButton.click();
        succesWithApprovedCard.shouldBe(Condition.visible);
    }

    public void happyPathWithDeclinedCard(DataWizard.FellowOne user){
        patternForPageTests(cardNumberField, proceedButton, succesWithApprovedCard,user.getCards().getDeclinedCard(), user.getCards().getApprovedCard() );
    }

    /**
     * Шаблон для проверки тестовых полей
     * @param testingElement Поле, которое будет тестироваться
     * @param buttonToClick  Кнопка, для отправки формы
     * @param errorMessage  Ожидаемое сообщение об ошибке
     * @param testValue     Значение, передаваевое в текстовое поле
     * @param validValue    Значение, устанавливаемое после прохождения теста
     */
    private void patternForPageTests( SelenideElement testingElement,SelenideElement buttonToClick, SelenideElement errorMessage, String testValue, String validValue ){
        clearIt(testingElement);
        testingElement.setValue(testValue);
        buttonToClick.click();
        errorMessage.shouldBe(Condition.visible);
        clearIt(testingElement);
        testingElement.setValue(validValue);
    }


    public void wrongCardNumber(DataWizard.FellowOne user){
        patternForPageTests(cardNumberField, proceedButton, cardFieldError,user.getCards().getWrongCard(), user.getCards().getApprovedCard() );

    }

    public void wrongMonthNumberWOZero(DataWizard.FellowOne user){
        patternForPageTests(cardMonthField, proceedButton, monthWrongFormatError,user.getDates().getInvalidMonthWOZero(), user.getDates().getValidMonth() );

    }

    public void wrongMonth(DataWizard.FellowOne user){
        patternForPageTests(cardMonthField, proceedButton, monthValidityError,user.getDates().getInvalidMonth(), user.getDates().getValidMonth() );

    }

    public void wrongYearPast(DataWizard.FellowOne user){
        patternForPageTests(cardYearField, proceedButton, yearPastDateError,user.getDates().getInvalidYearPast(), user.getDates().getValidYear() );

    }

    public void wrongYearFuture(DataWizard.FellowOne user){
        patternForPageTests(cardYearField, proceedButton, yearFutureDateError,user.getDates().getInvalidYearFuture(), user.getDates().getValidYear() );

    }

    public void wrongCvc(DataWizard.FellowOne user){
        patternForPageTests(cardCvcField, proceedButton, cvcBlankError,user.getCvc().getInvalidCvc(), user.getCvc().getValidCvc() );

    }
//    public void wrongName(DataWizard.FellowOne user){
//        patternForPageTests(cardOwnerField, proceedButton, yearFutureDateError,user.getDates().getInvalidYearFuture(), user.getDates().getValidYear() );
//
//        clearIt(cardOwnerField);
//        cardOwnerField.setValue(user.getName().getInvalidName());
//        proceedButton.click();
//        yearFutureDateError.shouldBe(Condition.visible);
//        clearIt(cardOwnerField);
//        cardOwnerField.setValue(user.getName().getValidName());
//    }

    public void blankCardNumb(DataWizard.FellowOne user){
        patternForPageTests(cardNumberField, proceedButton, cardFieldError,"", user.getCards().getApprovedCard() );

    }

    public void blankMonth(DataWizard.FellowOne user){
        patternForPageTests(cardMonthField, proceedButton, monthWrongFormatError,"", user.getDates().getValidMonth() );

    }

    public void blankYear(DataWizard.FellowOne user){
        patternForPageTests(cardYearField, proceedButton, yearBlankError,"", user.getDates().getValidYear() );

    }

    public void blankCvc(DataWizard.FellowOne user){
        patternForPageTests(cardCvcField, proceedButton, cvcBlankError,"", user.getCvc().getValidCvc() );

    }

    public void blankOwner(DataWizard.FellowOne user){
        patternForPageTests(cardOwnerField, proceedButton, ownerBlankError,"", user.getName().getValidName() );

    }


}
