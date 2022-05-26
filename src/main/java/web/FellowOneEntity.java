package web;

import lombok.Value;

public class FellowOneEntity {

    @Value
    public static class FellowOne {
        private Cards cards;
        private Names name;
        private DatesForCard dates;
        private Cvc cvc;
        private int shortTime;
        private int longTime;
    }

    @Value
    public static class ApiEntity {
        private String number;
        private String holder;
        private String cvc;
        private String month;
        private String year;
    }

    @Value
    public static class Cards {
        private String approvedCard;
        private String declinedCard;
        private String wrongShortCard;
        private String wrongLongCard;
        private String approvedCardLong;
        private String declinedCardLong;
        private String randomCardLong;
    }

    @Value
    public static class Names {
        private String validName;
        private String invalidNameRu;
        private String invalidNameSmall;
        private String invalidName;
    }

    @Value
    public static class DatesForCard {
        private String validYear;
        private String invalidYearPast;
        private String invalidYearFuture;
        private String validMonth;
        private String invalidMonth;
        private String invalidMonthWOZero;
    }

    @Value
    public static class Cvc {
        private String validCvc;
        private String invalidCvc;
    }

    @Value
    public static class ApiPaths {
        final public String straightPath = "/payment";
        final public String creditPath = "/credit";
    }

    @Value
    public static class Status {
        final public String approved = "APPROVED";
        final public String declined = "DECLINED";
    }

}
