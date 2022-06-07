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
    void shouldTransactionAmountLessThanBalanceFromSecondToFirstCard() {
        var transactionPage = new TransactionPage(100);
        var getBalancePage = new GetBalancePage();
        transactionPage.alignmentAmount(DataHelper.getNumberFirstCard(), DataHelper.getNumberSecondCard());
        int expectedBalanceFirstCard = getBalancePage.getCardBalance("0001") + transactionPage.getAmount();
        int expectedBalanceSecondCard = getBalancePage.getCardBalance("0002") - transactionPage.getAmount();
        transactionPage.transactionFirstToSecondCard(DataHelper.getNumberSecondCard());
        assertEquals(expectedBalanceFirstCard, getBalancePage.getCardBalance("0001"));
        assertEquals(expectedBalanceSecondCard, getBalancePage.getCardBalance("0002"));
        }

    @Test
     void shouldTransactionAmountLessThanBalanceFromFirstToSecondCard() {
        var transactionPage = new TransactionPage(100);
        var getBalancePage = new GetBalancePage();
        transactionPage.alignmentAmount(DataHelper.getNumberFirstCard(), DataHelper.getNumberSecondCard());
        int expectedBalanceFirstCard = getBalancePage.getCardBalance("0001") - transactionPage.getAmount();
        int expectedBalanceSecondCard = getBalancePage.getCardBalance("0002") + transactionPage.getAmount();
        transactionPage.transactionSecondToFirstCard(DataHelper.getNumberFirstCard());
        assertEquals(expectedBalanceFirstCard, getBalancePage.getCardBalance("0001"));
        assertEquals(expectedBalanceSecondCard, getBalancePage.getCardBalance("0002"));
    }

    @Test
    void shouldNotTransactionAmountAboveThanBalanceFromSecondToFirstCard() {
        var transactionPage = new TransactionPage(15000);
        var getBalancePage = new GetBalancePage();
        transactionPage.alignmentAmount(DataHelper.getNumberFirstCard(), DataHelper.getNumberSecondCard());
        int expectedBalanceFirstCard = getBalancePage.getCardBalance("0001");
        int expectedBalanceSecondCard = getBalancePage.getCardBalance("0002");
        transactionPage.transactionFirstToSecondCard(DataHelper.getNumberSecondCard());
        assertEquals(expectedBalanceSecondCard, getBalancePage.getCardBalance("0002"));
    }
    @Test
    void shouldTransactionAmountAboveThanBalanceFromFirstToSecondCard() {
        var transactionPage = new TransactionPage(15000);
        var getBalancePage = new GetBalancePage();
        transactionPage.alignmentAmount(DataHelper.getNumberFirstCard(), DataHelper.getNumberSecondCard());
        int expectedBalanceFirstCard = getBalancePage.getCardBalance("0001");
        int expectedBalanceSecondCard = getBalancePage.getCardBalance("0002");
        transactionPage.transactionSecondToFirstCard(DataHelper.getNumberFirstCard());
        assertEquals(expectedBalanceFirstCard, getBalancePage.getCardBalance("0001"));
       }

   @Test
    void shouldNotTransactionZeroAmountBalanceFromSecondToFirstCard() {
        var transactionPage = new TransactionPage(0);
        var getBalancePage = new GetBalancePage();
        transactionPage.alignmentAmount(DataHelper.getNumberFirstCard(), DataHelper.getNumberSecondCard());
        int expectedBalanceFirstCard = getBalancePage.getCardBalance("0001");
        int expectedBalanceSecondCard = getBalancePage.getCardBalance("0002");
        transactionPage.transactionFirstToSecondCard(DataHelper.getNumberSecondCard());
        assertEquals(expectedBalanceFirstCard, getBalancePage.getCardBalance("0001"));
        assertEquals(expectedBalanceSecondCard, getBalancePage.getCardBalance("0002"));
    }
    @Test
    void shouldNotTransactionZeroAmountBalanceFromFirstToSecondCard() {
        var transactionPage = new TransactionPage(0);
        var getBalancePage = new GetBalancePage();
        transactionPage.alignmentAmount(DataHelper.getNumberFirstCard(), DataHelper.getNumberSecondCard());
        int expectedBalanceFirstCard = getBalancePage.getCardBalance("0001");
        int expectedBalanceSecondCard = getBalancePage.getCardBalance("0002");
        transactionPage.transactionSecondToFirstCard(DataHelper.getNumberFirstCard());
        assertEquals(expectedBalanceFirstCard, getBalancePage.getCardBalance("0001"));
    }
}