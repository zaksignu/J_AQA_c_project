package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class GenericPage {
    public GenericPage(){
    }
    //buttons
    public SelenideElement proceedButton = $x("//*[@id=\"root\"]/div/form/fieldset/div[4]/button");

    //fields
    public SelenideElement cardNumberField = $x("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[2]/input");
    public SelenideElement cardMonthField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[2]/input");
    public SelenideElement cardOwnerField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    public SelenideElement cardYearField = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[2]/input");
    public SelenideElement cardCvcField = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[2]/input");

    //error pop-ups
    public SelenideElement cardFieldError = $x("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[3]");
    public SelenideElement monthWrongFormatError = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[contains(text(),'Неверный формат')]");
    public SelenideElement monthValidityError = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[contains(text(),'Неверно указан срок действия карты')]");
    public SelenideElement yearPastDateError = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[contains(text(),'Истёк срок действия карты')]");
    public SelenideElement yearFutureDateError = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[contains(text(),'Неверно указан срок действия карты')]");
    public SelenideElement yearBlankError = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[contains(text(),'Неверный формат')]");
    public SelenideElement cvcBlankError = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[contains(text(),'Неверный формат')]");
    public SelenideElement ownerBlankError = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[contains(text(),'Поле обязательно для заполнения')]");
    public SelenideElement cardReject = $x("//*[@id=\"root\"]/div/div[3]/div[contains(text(),'Ошибка! Банк отказал в проведении операции.')]");

    //suсcess pop-ups
    public SelenideElement succesWithApprovedCard = $x("//*[@id=\"root\"]/div/div[2]/div[contains(text(),'Операция одобрена Банком.')]");
    public SelenideElement closeSuccessPopUp = $x("//*[@id=\"root\"]/div/div[2]/button");
    public SelenideElement closeDeniedPopUp = $x("//*[@id=\"root\"]/div/div[3]/button");

}
