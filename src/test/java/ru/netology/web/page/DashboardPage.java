package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement clickOnButtonFirstCard = $x("//div[contains (text(),'0001')]/button");
    private SelenideElement clickOnButtonSecondCard = $x("//div[contains (text(),'0002')]/button");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage clickOnButtonFirst() {
        clickOnButtonFirstCard.click();
        return new DashboardPage();
    }

    public DashboardPage clickOnButtonSecond() {
        clickOnButtonSecondCard.click();
        return new DashboardPage();
    }

    public int getCardBalance(String id) {
        val card = cards.filterBy(Condition.text(id));
        val text = card.first().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}