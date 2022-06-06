package ru.netology.web.test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.GetBalancePage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransactionPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

    }

    @Test
    void shouldGetBalanceFirstCard() {
        var getBalancePage = new GetBalancePage();
        //getBalancePage.getFirstCardBalance();
        assertEquals(10000, getBalancePage.getCardBalance("0002"));

    }
    @Test
    void shouldTransactionAmountLessThanBalance() {
        var transactionPage = new TransactionPage(100);
        var getBalancePage = new GetBalancePage();
        int expectedBalanceFirstCard = getBalancePage.getCardBalance("0001") + transactionPage.getAmount();
        int expectedBalanceSecondCard = getBalancePage.getCardBalance("0002") - transactionPage.getAmount();
        transactionPage.transactionFirstToSecondCard(DataHelper.getNumberSecondCard());
        assertEquals(expectedBalanceFirstCard, getBalancePage.getCardBalance("0001"));
        assertEquals(expectedBalanceSecondCard, getBalancePage.getCardBalance("0002"));
    }

    @Test
    void shouldТNotTransactionAmountAboveThanBalance() {
        var transactionPage = new TransactionPage(100000);
        var getBalancePage = new GetBalancePage();
        int expectedBalanceFirstCard = getBalancePage.getCardBalance("0001") + transactionPage.getAmount();
        int expectedBalanceSecondCard = getBalancePage.getCardBalance("0002") - transactionPage.getAmount();
        transactionPage.transactionFirstToSecondCard(DataHelper.getNumberSecondCard());
        assertEquals(expectedBalanceFirstCard, getBalancePage.getCardBalance("0001"));
        assertEquals(expectedBalanceSecondCard, getBalancePage.getCardBalance("0002"));
    }

}