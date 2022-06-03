package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
//
//    @Value
//    public static class TransactionDate {
//        private String numberFirstCard;
//        private String numberSecondCard;
//        private int amount;
//            }
//
//    public static TransactionDate getNumberFirstCard() {
//        return new TransactionDate("5559 0000 0000 0001");
//    }
//    public static TransactionDate getNumberSecondCard() {
//        return new TransactionDate("5559 0000 0000 0002");
//    }

    @Value
    public static class TransactionDateForFirstCard {
        private String numberFirstCard;
            }

    public static TransactionDateForFirstCard  getNumberFirstCard() {
        return new TransactionDateForFirstCard ("5559 0000 0000 0001");
    }
    @Value
    public static class TransactionDateForSecondCard {
        private String numberFirstCard;
        private int amountTransaction;
    }

    public static TransactionDateForSecondCard  getNumberSecondCard(int amountTransaction) {
        return new TransactionDateForSecondCard ("5559 0000 0000 0002", amountTransaction);
    }




}

