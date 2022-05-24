package pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class IndexPage {
    private SelenideElement toBuyButton = $(byText("Купить"));
    private SelenideElement toBuyWithCreditButton = $x("//*[@id=\"root\"]/div/button[2]");

    public IndexPage() {
        toBuyButton.shouldBe(Condition.visible);
        toBuyWithCreditButton.shouldBe(Condition.visible);
    }

    public StraightBuy letMeBuyStraight() {
        toBuyButton.click();
        return new StraightBuy();
    }

    public CreditBuy letMeBuyViaCredit(){
        toBuyWithCreditButton.click();
        return new CreditBuy();
    }
}
