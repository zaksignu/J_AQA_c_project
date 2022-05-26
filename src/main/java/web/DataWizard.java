package web;


import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DataWizard {
    private DataWizard() {
    }

    final static String approvedCard = "1111222233334444";
    final static String declinedCard = "5555666677778888";
    final static String approvedCardLong = "1111 2222 3333 4444";
    final static String declinedCardLong = "5555 6666 7777 8888";
    final static int shrtTime = 4;
    final static int lngTime = 20;

    static Faker ghostOne = new Faker(new Locale("EN"));
    static Faker ghostTwo = new Faker(new Locale("RU"));

    public static class GenerateMe {
        private GenerateMe() {
        }

        public static FellowOneEntity.ApiEntity generateEntity(FellowOneEntity.FellowOne userOne, String card) {
            FellowOneEntity.ApiEntity entity = new FellowOneEntity.ApiEntity(
                    card,
                    userOne.getName().getValidName(),
                    userOne.getCvc().getValidCvc(),
                    userOne.getDates().getValidMonth(),
                    userOne.getDates().getValidYear());
            return entity;
        }

        public static int GenerateShortTime() {
            return shrtTime;
        }

        public static int GenerateLongTime() {
            return lngTime;
        }

        public static FellowOneEntity.Cards GenerateCards() {
            FellowOneEntity.Cards cards = new FellowOneEntity.Cards(
                    approvedCard,
                    declinedCard,
                    ghostOne.regexify("[0-9]{12}").toString(),
                    ghostOne.regexify("[0-9]{16}").toString(),
                    approvedCardLong,
                    declinedCardLong,
                    ghostOne.regexify("[0-9]{4}").toString() + " " + ghostOne.regexify("[0-9]{4}").toString() + " " + ghostOne.regexify("[0-9]{4}").toString() + " " + ghostOne.regexify("[0-9]{4}").toString()
            );
            return cards;
        }

        public static FellowOneEntity.Names GenerateNames() {
            FellowOneEntity.Names names = new FellowOneEntity.Names(
                    GenerateValidName(),
                    GenerateInValidNameRu(),
                    GenerateInvalidNameSmall(),
                    GenerateInvalidName());
            return names;
        }

        public static Calendar generateDate() {
            return Calendar.getInstance();
        }

        public static FellowOneEntity.DatesForCard GenerateDates() {
            int gap = 3;
            SimpleDateFormat formateYear = new SimpleDateFormat("yy");
            Calendar cal = generateDate();
            int currentYear = Integer.valueOf(formateYear.format(cal.getTime()));

            FellowOneEntity.DatesForCard date = new FellowOneEntity.DatesForCard(
                    GenerateValidYearDate(currentYear, gap),
                    GenerateInvalidYearDatePast(currentYear, gap),
                    GenerateInvalidYearDateFuture(currentYear, gap),
                    GenerateValidMonth(),
                    GenerateInvalidMonth(),
                    GenerateInvalidMonthWOZero());
            return date;
        }

        public static FellowOneEntity.Cvc GenerateCvc() {
            FellowOneEntity.Cvc cvc = new FellowOneEntity.Cvc(
                    generateValidCvc(),
                    generateInValidCvc());
            return cvc;
        }

        private static String GenerateValidName() {
            return ghostOne.name().firstName() + " " + ghostOne.name().lastName();
        }

        private static String GenerateInValidNameRu() {
            return ghostTwo.name().firstName() + " " + ghostTwo.name().lastName();
        }

        private static String GenerateInvalidNameSmall() {
            return ghostOne.name().firstName().substring(0, 2);
        }

        private static String GenerateInvalidName() {
            return ghostOne.name().firstName() + ghostOne.name().firstName() + ghostOne.name().firstName() + ghostOne.name().firstName() + " " + ghostOne.name().lastName() + ghostOne.name().lastName() + ghostOne.name().lastName() + ghostOne.name().lastName();
        }

        public static String GenerateValidYearDate(int currYear, int gap) {
            return Patterns.GenerateComplexDate(gap + currYear, currYear + 1, 1, 1);
        }

        public static String GenerateInvalidYearDatePast(int currYear, int gap) {
            return Patterns.GenerateComplexDate(gap + currYear, currYear - 2, -1, 1);
        }

        public static String GenerateInvalidYearDateFuture(int currYear, int gap) {
            return Patterns.GenerateComplexDate(gap + currYear + 7, currYear + 7, 1, 1);
        }

        public static String GenerateValidMonth() {
            return Patterns.GenerateComplexDate(12, 0, 1, -1);
        }

        public static String GenerateInvalidMonthWOZero() {
            return ghostOne.regexify("[0-9]{1}").toString();
        }

        public static String GenerateInvalidMonth() {
            return ghostOne.regexify("[1-9]{2}").toString();
        }

        public static String generateValidCvc() {
            return ghostOne.regexify("[0-9]{3}").toString();
        }

        public static String generateInValidCvc() {
            return ghostOne.regexify("[0-9]{2}").toString();
        }

        public static String generateDateForDbQuerry(Calendar cal) {
            TimeZone utcTimezone = TimeZone.getTimeZone("UTC");
            SimpleDateFormat formateDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formateDate.setTimeZone(utcTimezone);
            String currentYear = formateDate.format(cal.getTime());
            return (String) currentYear.subSequence(0, 17);
        }

    }

    public static class UserManipulating {
        private UserManipulating() {
        }

        public static FellowOneEntity.FellowOne generateUser() {
            FellowOneEntity.FellowOne user = new FellowOneEntity.FellowOne(
                    GenerateMe.GenerateCards(),
                    GenerateMe.GenerateNames(),
                    GenerateMe.GenerateDates(),
                    GenerateMe.GenerateCvc(),
                    GenerateMe.GenerateShortTime(),
                    GenerateMe.GenerateLongTime());
            return user;
        }

    }

}
