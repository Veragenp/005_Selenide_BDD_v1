package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.GetBalancePage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransactionPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
//    var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }
    @Test
    void shouldGetBalanceFirstCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
//    var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var getBalancePage = new GetBalancePage();
        //getBalancePage.getFirstCardBalance();
        int balanceFirstCard = getBalancePage.getCardBalance("0001");
        int balanceSecondCard = getBalancePage.getCardBalance("0002");

        assertEquals(20500, getBalancePage.getCardBalance("0002"));

            }
    @Test
    void shouldСLickOnSecondCard() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var transactionPage = new TransactionPage();
       //    var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        transactionPage.clickOnButtonFirst();
        transactionPage.transactionFirstToSecondCard(DataHelper.getNumberFirstCard());





    }

}