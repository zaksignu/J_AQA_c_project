package web;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import lombok.Value;

import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import static io.restassured.RestAssured.given;

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
    ///////////
    // User methods
    //////////

    public static class GenerateMe {
        private GenerateMe() {
        }
        public static ApiEntity generateEntity (FellowOne userOne, String card){
            ApiEntity entity = new ApiEntity(
                    card,
                    userOne.getName().validName,
                    userOne.getCvc().validCvc,
                    userOne.getDates().getValidMonth(),
                    userOne.getDates().getValidYear());
            return entity;
        }
        public static int GenerateShortTime(){
            return shrtTime;
        }
        public static int GenerateLongTime(){
            return lngTime;
        }
        public static Cards GenerateCards(){
            Cards cards = new Cards(
                    approvedCard,
                    declinedCard,
                    ghostOne.regexify("[0-9]{12}").toString(),
                    ghostOne.regexify("[0-9]{16}").toString(),
                    approvedCardLong,
                    declinedCardLong,
                    ghostOne.regexify("[0-9]{4}").toString()+" "+ghostOne.regexify("[0-9]{4}").toString()+" "+ghostOne.regexify("[0-9]{4}").toString()+" "+ghostOne.regexify("[0-9]{4}").toString()
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
        public static Calendar generateDate(){
            return Calendar.getInstance();
        }
        public static DatesForCard GenerateDates(){
            int gap  = 3;
            SimpleDateFormat formateYear = new SimpleDateFormat("yy");
            Calendar cal = generateDate();
            int currentYear = Integer.valueOf(formateYear.format(cal.getTime()));

            DatesForCard date = new DatesForCard(
                    GenerateValidYearDate(currentYear, gap),
                    GenerateInvalidYearDatePast(currentYear,gap),
                    GenerateInvalidYearDateFuture(currentYear,gap),
                    GenerateValidMonth(),
                    GenerateInvalidMonth(),
                    GenerateInvalidMonthWOZero());
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
            return DataWizard.Patterns.GenerateComplexDate(gap+currYear,currYear+1,1,1);
        }
        public static String GenerateInvalidYearDatePast(int currYear, int gap) {
            return DataWizard.Patterns.GenerateComplexDate(gap+currYear,currYear-2,-1,1);
        }
        public static String GenerateInvalidYearDateFuture(int currYear, int gap) {
            return DataWizard.Patterns.GenerateComplexDate(gap+currYear+7,currYear+7,1,1);
        }
        public static String GenerateValidMonth() {
            return DataWizard.Patterns.GenerateComplexDate(12,0,1,-1);
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
           return (String) currentYear.subSequence(0,17);
        }

    }

    public static class DbRoutines {
        private DbRoutines() {
        }

        @SneakyThrows
        public static String getPaymentStatusForCreditBuy(Calendar cal) {
            return DataWizard.Patterns.paymentStatusFromService(cal,"credit_request_entity");

        }

        @SneakyThrows
        public static String getPaymentStatusForStraightBuy(Calendar cal) {
            return DataWizard.Patterns.paymentStatusFromService(cal,"payment_entity");
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
                       GenerateMe.GenerateCvc(),
                       GenerateMe.GenerateShortTime(),
                       GenerateMe.GenerateLongTime());
            return user;
        }

    }

    public static class Patterns{
        private Patterns() {
        }


        /**
         * Шаблон для выполнения запроса к БД. Из экземпляра cal получаем текущую дату в формате "ГГГГ-ММ-ДД ЧЧ:ММ:С%",
         * формируем упорядоченный по дате список и возвращаем первую строку, откуда забираем статус платежа.
         * @param cal экземпляр Calendar с датой нажатия кнопки
         * @param dbPage таблица, откуда забирается первая строка ( credit_request_entity или payment_entity)
         * @return
         */
        @SneakyThrows
        public static String paymentStatusFromService(Calendar cal, String dbPage) {
            String date = GenerateMe.generateDateForDbQuerry(cal);  //
            try (
                    var conn = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/app",
                            "app",
                            "pass"
                    );
                    var paymentStatus = conn.createStatement();
            ) {

                try (var rs = paymentStatus.executeQuery(" SELECT  status from "+dbPage+ " credit_request_entity  where created like '" +date  + "%' order by created desc;")) {
                    if (rs.next()) {
                        return rs.getString(1);
                    }
                }
            }
            return "";
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
            } else {
                res = rand.nextInt(max - min) - min;
                res = Math.abs(res);
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
        private static RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://185.119.57.197")
                .setPort(9999)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        /**
         * Шаблон для генерации POST запроса и получения результата
         * @param newUser - экземпляр пользователя FellowOne, нужен для формирования тела запроса
         * @param path адрес запроса
         * @param card карта для передачи запроса
         * @param gsonString - имя поля, по которому извлекается ответ сервера
         * @return
         */
        public static String postIt(FellowOne newUser,String path,String card, String gsonString) {
            Gson gson = new Gson();
            Response response = given()
                    .spec(requestSpec)
                    .body(DataWizard.GenerateMe.generateEntity(newUser,card))
                    .when()
                    .post(path);
            String responseBody = response.getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);
            String token = jsonPath.getString(gsonString);
            return token;
        }

    }

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

}
