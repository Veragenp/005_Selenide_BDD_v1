package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransactionPage {
    private SelenideElement clickOnButtonFirstCard = $x("//div[contains (text(),'0001')]/button");
    private SelenideElement clickOnButtonSecondCard = $x("//div[contains (text(),'0002')]/button");
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement cardNumberField = $("[data-test-id=from] input");
    private SelenideElement buttonTransfer = $("[data-test-id=action-transfer]");

    private int amount;

    public TransactionPage(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }


    public DashboardPage clickOnButtonFirst() {
        clickOnButtonFirstCard.click();
        return new DashboardPage();
    }

    public DashboardPage clickOnButtonSecond() {
        clickOnButtonSecondCard.click();
        return new DashboardPage();
    }


    public DashboardPage transactionSecondToFirstCard(String number) {
        clickOnButtonFirst();
        String stAmount = Integer.toString(amount);
        amountField.sendKeys(Keys.CONTROL + "A");
        amountField.sendKeys(Keys.DELETE);
        amountField.setValue(stAmount);
        cardNumberField.sendKeys(Keys.CONTROL + "A");
        cardNumberField.sendKeys(Keys.DELETE);
        cardNumberField.setValue(DataHelper.getSecondCard().getNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }

    public DashboardPage transactionFirstToSecondCard(String number) {
        clickOnButtonSecond();
        String stAmount = Integer.toString(amount);
        amountField.sendKeys(Keys.CONTROL + "A");
        amountField.sendKeys(Keys.BACK_SPACE);
        amountField.setValue(stAmount);
        cardNumberField.sendKeys(Keys.CONTROL + "A");
        cardNumberField.sendKeys(Keys.DELETE);
        cardNumberField.setValue(DataHelper.getFirstCard().getNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }
}
