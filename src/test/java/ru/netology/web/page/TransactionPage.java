package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransactionPage {
    //получить сумму первой карты, получить сумму второй карты?
    //селектор строки для первой карты $x('//div[text()="**** **** **** 0001"]/..')
    // селектор строки для второй карты $x('//div[text()="**** **** **** 0002"]/..')
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

    private SelenideElement clickOnButtonFirstCard = $("[data-test-id=login] input");
    private SelenideElement clickOnButtonSecondCard = $("[data-test-id=login] input");

    public TransactionPage clickOnButtonFirst() {
        //метод кликает на кнопку продолжить первой карты и должен получить страницу
        clickOnButtonFirstCard.click();
        return new TransactionPage(); //но это же не та страница транзакции, это промежуточная страница
    }



}
