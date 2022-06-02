package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import lombok.val;

import static com.codeborne.selenide.Selenide.$$;

public class GetBalancePage {
    //селектор для поля первой карты
    //селектор для поля второй карты
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public GetBalancePage() {

    }
    //public int getFirstCardBalance() {
       // val text = cards.first().text();
      //  return extractBalance(text);
   // }

    public int getCardBalance(String id) {
        //найти карту с нужным ID
        //bpdktxm lfyyst
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
