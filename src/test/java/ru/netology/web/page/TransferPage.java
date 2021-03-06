package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading = $(byText("Пополнение карты"));
    private SelenideElement amount = $("[data-test-id=amount] [class=input__control]");
    private SelenideElement from = $("[data-test-id=from] [class=input__control]");
    private SelenideElement actionTransfer = $("[data-test-id=action-transfer]");
    private SelenideElement overLimitError = $(byText("Сумма транзакции превышает сумму текущего баланса"));

    public TransferPage() { heading.shouldBe(visible); }

    public DashboardPage transfer(int value,DataHelper.Cards cards) {
        amount.setValue(String.valueOf(value));
        from.setValue(cards.getCardNum());
        actionTransfer.click();
        return new DashboardPage();
    }

    public DashboardPage overLimit(int value,DataHelper.Cards cards) {
        amount.setValue(String.valueOf(value));
        from.setValue(cards.getCardNum());
        actionTransfer.click();
        overLimitError.shouldBe(visible);
        return new DashboardPage();
    }
}
