package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransactionPage {
    //получить сумму первой карты, получить сумму второй карты?
    //селектор строки для первой карты $$('div[data-test-id="92df3f1c-a033-48e6-8390-206f6b1f56c0"]>button')  $x('//div[contains (text(),"0001")]/button')
    // селектор строки для второй карты $x('//div[text()="**** **** **** 0002"]/..') $x('//div[contains (text(),"0002")]/button')
    //ввести нужную сумму, селектор для ввода $("[data-test-id=amount] input")
    //выбрать нужную карту, этот выбор у меня есть
    //селектор кнопки для первой карты
    //селектор кнопки для второй карты
    //выбрать первую карту, нажать на кнопку "Пополнить", селектор
    //или выбрать вторую карту, нажать на кнопку пополнить
    //убедиться что после нажатия на кнопку "Пополнить", выполнен переход на соответствующую страницу
    //ввести сумму пополнения
    //нажать на кнопку "Пополнить"
    //убедиться что открыта соответствующая страница - страница со списком карт

    //private SelenideElement topUpFirstCard = $x("//div[text()="0001"]/..");
    //private SelenideElement topUpSecondCard = $x("//div[text()="0002"]/..");

    //вот здесь сначала надо найти баланс по первой и второй карте (перед кликом, а потом уже кликнуть) - ок

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
        //метод кликает на кнопку продолжить первой карты и должен получить страницу
        clickOnButtonFirstCard.click();
        return new DashboardPage(); //но это же не та страница транзакции, это промежуточная страница
    }

    public DashboardPage clickOnButtonSecond() {
        //метод кликает на кнопку продолжить первой карты и должен получить страницу

        clickOnButtonSecondCard.click();
        return new DashboardPage(); //но это же не та страница транзакции, это промежуточная страница
    }

///перешли на страницу транзакции после клика на карту
    //ввести сумму в поле суммы (вот здесь все же не понятно как вводить, желательно сумму формировать из теста)
    //ввести карту (тут по-хорошему карта должна вставляться автоматически, при клике на по кнопке Продолжить, но тогда не протестируешь если ввести ту же карту)
    //Нажать кнопку "Пополнить"
    //Создать отдельный метод на кнопку "Отмена", чтобы протестировать?
    //После нажатия на кнопку переход на DashboardPage()


    public DashboardPage transactionFirstToSecondCard(DataHelper.TransactionDateForSecondCard secondCard) {
        //метод кликает на кнопку продолжить первой карты и должен получить страницу
        clickOnButtonFirst();
        String stAmount = Integer.toString(amount);
        amountField.sendKeys(Keys.CONTROL + "A");
        amountField.sendKeys(Keys.DELETE);
        amountField.setValue(stAmount);
        cardNumberField.sendKeys(Keys.CONTROL + "A");
        cardNumberField.sendKeys(Keys.DELETE);
        cardNumberField.setValue(secondCard.getNumberSecondCard());
        buttonTransfer.click();
        return new DashboardPage();
    }
    public DashboardPage transactionSecondToFirstCard(DataHelper.TransactionDateForFirstCard firstCard) {
        //метод кликает на кнопку продолжить первой карты и должен получить страницу
        clickOnButtonSecond();
        String stAmount = Integer.toString(amount);
        amountField.sendKeys(Keys.CONTROL + "A");
        amountField.sendKeys(Keys.BACK_SPACE);
        amountField.setValue(stAmount);
        cardNumberField.sendKeys(Keys.CONTROL + "A");
        cardNumberField.sendKeys(Keys.DELETE);
        cardNumberField.setValue(firstCard.getNumberFirstCard());
        buttonTransfer.click();
        return new DashboardPage();
    }

    public DashboardPage alignmentAmount(DataHelper.TransactionDateForFirstCard firstCard, DataHelper.TransactionDateForSecondCard secondCard) {
         var getBalancePage = new GetBalancePage();
        int initialAmount = 10000;
        int initialBalanceFirstCard = getBalancePage.getCardBalance("0001");
        int initialBalanceSecondCard = getBalancePage.getCardBalance("0002");
        if (initialBalanceFirstCard == initialAmount) {
            return new DashboardPage();
        }
        if (initialBalanceFirstCard > initialAmount) {
            clickOnButtonSecond();
            int difference = initialBalanceFirstCard - initialAmount;
            String stAmount = Integer.toString(difference);
            amountField.sendKeys(Keys.CONTROL + "A");
            amountField.sendKeys(Keys.BACK_SPACE);
            amountField.setValue(stAmount);
            cardNumberField.sendKeys(Keys.CONTROL + "A");
            cardNumberField.sendKeys(Keys.DELETE);
            cardNumberField.setValue(firstCard.getNumberFirstCard());
            buttonTransfer.click();
            return new DashboardPage();
        }
        if (initialBalanceFirstCard < initialAmount) {
            clickOnButtonFirst();
            String stAmount = Integer.toString(initialBalanceSecondCard - initialAmount);
            amountField.sendKeys(Keys.CONTROL + "A");
            amountField.sendKeys(Keys.BACK_SPACE);
            amountField.setValue(stAmount);
            cardNumberField.sendKeys(Keys.CONTROL + "A");
            cardNumberField.sendKeys(Keys.DELETE);
            cardNumberField.setValue(secondCard.getNumberSecondCard());
            buttonTransfer.click();
            return new DashboardPage();
        }
        return new DashboardPage();
        }


}
