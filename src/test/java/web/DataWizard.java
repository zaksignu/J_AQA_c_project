package web;

import com.github.javafaker.Faker;
import lombok.Value;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class DataWizard {
    private DataWizard() {
    }
    final static String approvedCard = "1111222233334444";
    final static String declinedCard = "5555666677778888";

    static Faker ghostOne = new Faker(new Locale("EN"));
    static Faker ghostTwo = new Faker(new Locale("RU"));
    ///////////
    // User methods
    //////////

    public static class GenerateMe {
        private GenerateMe() {
        }

        public static Cards GenerateCards(){
            Cards cards = new Cards(
                    approvedCard,
                    declinedCard,
                    ghostOne.regexify("[0-9]{12}").toString()
            );
            return cards;
        }

        public static Names GenerateNames(){
            Names names = new Names(
                    GenerateValidName(),
                    GenerateInValidNameRu(),
                    GenerateInvalidNameSmall(),
                    GenerateInvalidName());
            return names;
        }

        public static DatesForCard GenerateDates(){
            int gap  = 3;
            SimpleDateFormat formateYear = new SimpleDateFormat("yy");
            SimpleDateFormat formateMonth = new SimpleDateFormat("MM");
            Calendar cal = Calendar.getInstance();
            int currentYear = Integer.valueOf(formateYear.format(cal.getTime()));
            int currentMonth = Integer.valueOf(formateMonth.format(cal.getTime()));

            DatesForCard date = new DatesForCard(
                    GenerateValidYearDate(currentYear, gap),
                    GenerateInvalidYearDatePast(currentYear,gap),
                    GenerateInvalidYearDateFuture(currentYear,gap),
                    GenerateValidMonth(),
                    GenerateInvalidMonth(),
                    GenerateinvalidMonthWOZero());
            return date;
        }

        public static Cvc GenerateCvc(){
            Cvc cvc = new Cvc(
                    generateValidCvc(),
                    generateInValidCvc());
            return cvc;
        }

        private static String GenerateValidName() {
            return ghostOne.name().firstName() + " " + ghostOne.name().lastName();
        }

        private static String GenerateInValidNameRu() {
            //TODO:хз есть ли смысл
            return ghostTwo.name().firstName() + " " + ghostTwo.name().lastName();
        }

        private static String GenerateInvalidNameSmall() {
            // 2 симбола без пробела
            //TODO:хз есть ли смысл
            return ghostOne.name().firstName().substring(0,2);
        }

        private static String GenerateInvalidName() {
            //TODO:хз есть ли смысл
            return ghostOne.name().firstName()+ghostOne.name().firstName()+ghostOne.name().firstName()+ghostOne.name().firstName() + " " + ghostOne.name().lastName()+ ghostOne.name().lastName()+ ghostOne.name().lastName()+ ghostOne.name().lastName();
        }


        public static String GenerateValidYearDate(int currYear, int gap) {
            return GenerateComplexDate(gap+currYear,currYear+1,1,1);
        }

        public static String GenerateInvalidYearDatePast(int currYear, int gap) {
            return GenerateComplexDate(gap+currYear,currYear-2,-1,1);
        }

        public static String GenerateInvalidYearDateFuture(int currYear, int gap) {
            return GenerateComplexDate(gap+currYear+7,currYear+7,1,1);
        }

        public static String GenerateValidMonth() {

            return GenerateComplexDate(12,0,1,-1);


        }

        public static String GenerateinvalidMonthWOZero() {
            return ghostOne.regexify("[0-9]{1}").toString();
//            Random rand = new Random();
//            int res = rand.nextInt(12);
//            return Integer.toString(res);

        }

        public static String GenerateInvalidMonth() {
             return ghostOne.regexify("[1-9]{2}").toString();
        }

        /**
         * Шаблон для работы с генератором года и месяца. Генерирует корректный/некорректный год либо месяц
         * Принимает на вход максимальный, минимальный предел генерирования, порядок и тип выходного значения
         * @param max максимум случайного значения
         * @param min минимум
         * @param order порядок генерации - >=0  прямой ( промежуточный выход после рандомизатора>0) , <0  - обратный ( нужен для получения "старого" года)
         * @param dateType режим выхода. >=0  - прямой выход ( для получения года ), <0 - выход с учетом величины  для получения  валидного месяца ( 08 )
         * @return
         */
        public static String GenerateComplexDate(int max, int min, int order,int dateType) {
            int res;
            Random rand = new Random();
            if (order>=0){
                 res = rand.nextInt(max - min) + min;
               // return Integer.toString(res);
            } else {
                 res = rand.nextInt(max - min) - min;
                res = Math.abs(res);
               // return Integer.toString(res);
            }
            //datetype >=0  - работа с годами
            //datetype <0  - работа с месяцами

            if (dateType>=0){

                return Integer.toString(res);

            } else{
                if (res>=10) {
                    return Integer.toString(res);
                } else {
                    String ret = "0"+Integer.toString(res);
                    return ret;
                }

            }

        }


        public static String generateValidCvc() {
            return ghostOne.regexify("[0-9]{3}").toString();
        }

        public static String generateInValidCvc() {
            return ghostOne.regexify("[0-9]{2}").toString();
        }

    }

    public static class UserManipulating {
        private UserManipulating() {
        }

        public static FellowOne generateUser() {
            FellowOne user = new FellowOne(
                       GenerateMe.GenerateCards(),
                    GenerateMe.GenerateNames(),
                    GenerateMe.GenerateDates(),
                    GenerateMe.GenerateCvc());
            return user;
        }

    }
    //////
    //  FellowOneStructure
    /////

    @Value
    public static class FellowOne {
        private Cards cards;
        private Names name;
        private DatesForCard dates;
        private Cvc cvc;
//        private String bearToken;
//        private String cardOne;
    }


    @Value
    public static class Cards {
        private String approvedCard;
        private String declinedCard;
        private String wrongCard;
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

}
