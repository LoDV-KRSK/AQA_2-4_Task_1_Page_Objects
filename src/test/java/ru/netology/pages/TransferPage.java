package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amount = $("[data-test-id='amount'] .input__control");
    private SelenideElement fromCard = $("[data-test-id='from'] .input__control");
    private SelenideElement topUpButton = $("[data-test-id='action-transfer']");
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private SelenideElement errorMessage = $(byText("На вашем счете недостаточно средств"));

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
}
