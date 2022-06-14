package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransactionPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var alignmentAmount = new AlignmentAmount();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        alignmentAmount.alignmentAmount();
    }

    @Test
    void shouldGetBalanceFirstCard() {
        var dashboardPage = new DashboardPage();
        assertEquals(10000, dashboardPage.getCardBalance(DataHelper.getFirstCard().getNumber().substring(15, 19)));
    }

    @Test
    void shouldGetBalanceSecondCard() {
        var dashboardPage = new DashboardPage();
        assertEquals(10000, dashboardPage.getCardBalance(DataHelper.getSecondCard().getNumber().substring(15, 19)));
    }

    @Test
    void shouldTransactionAmountLessThanBalanceFromSecondToFirstCard() {
        var transactionPage = new TransactionPage(100);
        var dashboardPage = new DashboardPage();
        var alignmentAmount = new AlignmentAmount();
        int expectedBalanceFirstCard = dashboardPage.getCardBalance(DataHelper.getFirstCard().getNumber().substring(15, 19)) + transactionPage.getAmount();
        int expectedBalanceSecondCard = dashboardPage.getCardBalance(DataHelper.getSecondCard().getNumber().substring(15, 19)) - transactionPage.getAmount();
        transactionPage.transactionSecondToFirstCard(DataHelper.getSecondCard().getNumber());
        assertEquals(expectedBalanceFirstCard, dashboardPage.getCardBalance(DataHelper.getFirstCard().getNumber().substring(15, 19)));
        assertEquals(expectedBalanceSecondCard, dashboardPage.getCardBalance(DataHelper.getSecondCard().getNumber().substring(15, 19)));
    }

    @Test
    void shouldTransactionAmountLessThanBalanceFromFirstToSecondCard() {
        var transactionPage = new TransactionPage(100);
        var dashboardPage = new DashboardPage();
        int expectedBalanceFirstCard = dashboardPage.getCardBalance(DataHelper.getFirstCard().getNumber().substring(15, 19)) - transactionPage.getAmount();
        int expectedBalanceSecondCard = dashboardPage.getCardBalance(DataHelper.getSecondCard().getNumber().substring(15, 19)) + transactionPage.getAmount();
        transactionPage.transactionFirstToSecondCard(DataHelper.getFirstCard().getNumber());
        assertEquals(expectedBalanceFirstCard, dashboardPage.getCardBalance(DataHelper.getFirstCard().getNumber().substring(15, 19)));
        assertEquals(expectedBalanceSecondCard, dashboardPage.getCardBalance(DataHelper.getSecondCard().getNumber().substring(15, 19)));
    }

    @Test
    void shouldNotTransactionAmountAboveThanBalanceFromSecondToFirstCard() {
        var transactionPage = new TransactionPage(15000);
        var dashboardPage = new DashboardPage();
        int expectedBalanceSecondCard = dashboardPage.getCardBalance(DataHelper.getSecondCard().getNumber().substring(15, 19));
        transactionPage.transactionSecondToFirstCard(DataHelper.getSecondCard().getNumber());
        assertEquals(expectedBalanceSecondCard, dashboardPage.getCardBalance(DataHelper.getSecondCard().getNumber().substring(15, 19)));
    }

    @Test
    void shouldNotTransactionAmountAboveThanBalanceFromFirstToSecondCard() {
        var transactionPage = new TransactionPage(15000);
        var dashboardPage = new DashboardPage();
        int expectedBalanceFirstCard = dashboardPage.getCardBalance(DataHelper.getFirstCard().getNumber().substring(15, 19));
        transactionPage.transactionFirstToSecondCard(DataHelper.getFirstCard().getNumber());
        assertEquals(expectedBalanceFirstCard, dashboardPage.getCardBalance(DataHelper.getFirstCard().getNumber().substring(15, 19)));
    }

    @Test
    void shouldNotTransactionZeroAmountBalanceFromSecondToFirstCard() {
        var transactionPage = new TransactionPage(0);
        var dashboardPage = new DashboardPage();
        int expectedBalanceFirstCard = dashboardPage.getCardBalance(DataHelper.getFirstCard().getNumber().substring(15, 19));
        int expectedBalanceSecondCard = dashboardPage.getCardBalance(DataHelper.getSecondCard().getNumber().substring(15, 19));
        transactionPage.transactionFirstToSecondCard(DataHelper.getFirstCard().getNumber());
        assertEquals(expectedBalanceFirstCard, dashboardPage.getCardBalance(DataHelper.getFirstCard().getNumber().substring(15, 19)));
        assertEquals(expectedBalanceSecondCard, dashboardPage.getCardBalance(DataHelper.getSecondCard().getNumber().substring(15, 19)));
    }

    @Test
    void shouldNotTransactionZeroAmountBalanceFromFirstToSecondCard() {
        var transactionPage = new TransactionPage(0);
        var dashboardPage = new DashboardPage();
        int expectedBalanceFirstCard = dashboardPage.getCardBalance(DataHelper.getFirstCard().getNumber().substring(15, 19));
        int expectedBalanceSecondCard = dashboardPage.getCardBalance(DataHelper.getSecondCard().getNumber().substring(15, 19));
        transactionPage.transactionFirstToSecondCard(DataHelper.getFirstCard().getNumber());
        assertEquals(expectedBalanceFirstCard, dashboardPage.getCardBalance(DataHelper.getFirstCard().getNumber().substring(15, 19)));
        assertEquals(expectedBalanceSecondCard, dashboardPage.getCardBalance(DataHelper.getSecondCard().getNumber().substring(15, 19)));
    }
}