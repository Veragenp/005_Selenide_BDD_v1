package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransactionPage {
    //получить сумму первой карты, получить сумму второй карты?
    //селектор строки для первой карты $$('div[data-test-id="92df3f1c-a033-48e6-8390-206f6b1f56c0"]>button')  $x('//div[contains (text(),"0001")]/button')
    // селектор строки для второй карты $x('//div[text()="**** **** **** 0002"]/..') $x('//div[contains (text(),"0002")]/button')
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


    public DashboardPage clickOnButtonFirst() {
        //метод кликает на кнопку продолжить первой карты и должен получить страницу
        clickOnButtonFirstCard.click();
        return new DashboardPage(); //но это же не та страница транзакции, это промежуточная страница
    }



}
