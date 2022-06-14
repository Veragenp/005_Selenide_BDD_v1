package ru.netology.web.test;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;

import static com.codeborne.selenide.Selenide.$;

public class AlignmentAmount {

    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement cardNumberField = $("[data-test-id=from] input");
    private SelenideElement buttonTransfer = $("[data-test-id=action-transfer]");

    public DashboardPage alignmentAmount() {
        var dashboardPage = new DashboardPage();
        int initialAmount = 10000;
        int initialBalanceFirstCard = dashboardPage.getCardBalance(DataHelper.getFirstCard().getNumber().substring(15, 19));
        int initialBalanceSecondCard = dashboardPage.getCardBalance(DataHelper.getSecondCard().getNumber().substring(15, 19));
        if (initialBalanceFirstCard == initialAmount) {
            return new DashboardPage();
        }
        if (initialBalanceFirstCard > initialAmount) {
            dashboardPage.clickOnButtonSecond();
            int difference = initialBalanceFirstCard - initialAmount;
            String stAmount = Integer.toString(difference);
            amountField.sendKeys(Keys.CONTROL + "A");
            amountField.sendKeys(Keys.BACK_SPACE);
            amountField.setValue(stAmount);
            cardNumberField.sendKeys(Keys.CONTROL + "A");
            cardNumberField.sendKeys(Keys.DELETE);
            cardNumberField.setValue(DataHelper.getFirstCard().getNumber());
            buttonTransfer.click();
            return new DashboardPage();
        }
        if (initialBalanceFirstCard < initialAmount) {
            dashboardPage.clickOnButtonFirst();
            String stAmount = Integer.toString(initialBalanceSecondCard - initialAmount);
            amountField.sendKeys(Keys.CONTROL + "A");
            amountField.sendKeys(Keys.BACK_SPACE);
            amountField.setValue(stAmount);
            cardNumberField.sendKeys(Keys.CONTROL + "A");
            cardNumberField.sendKeys(Keys.DELETE);
            cardNumberField.setValue(DataHelper.getSecondCard().getNumber());
            buttonTransfer.click();
            return new DashboardPage();
        }
        return new DashboardPage();
    }
}
