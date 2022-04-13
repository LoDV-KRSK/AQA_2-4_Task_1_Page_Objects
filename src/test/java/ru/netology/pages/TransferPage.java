package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.*;

public class TransferPage {
    private SelenideElement amount = $("[data-test-id='amount'] .input__control");
    private SelenideElement fromCard = $("[data-test-id='from'] .input__control");
    private SelenideElement topUpButton = $("[data-test-id='action-transfer']");
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private SelenideElement errorMessage = $(byText("На ваше счете недостаточно средств"));

    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    public void shouldShowErrorTransfer() {
        errorMessage.shouldBe(visible);
    }

    public DashboardPage makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amount.setValue(amountToTransfer);
        fromCard.setValue(cardInfo.getCardNumber());
        topUpButton.click();
        return new DashboardPage();
    }

    public DashboardPage makeTransferOriginal(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amount.sendKeys(chord(SHIFT, HOME, BACK_SPACE));
        amount.setValue(amountToTransfer).sendKeys(TAB);
        fromCard.sendKeys(Keys.BACK_SPACE);
        fromCard.setValue(cardInfo.getCardNumber());
        topUpButton.click();
        return new DashboardPage();
    }
}
