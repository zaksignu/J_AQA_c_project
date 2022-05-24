package web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.Keys;
import pages.GenericPage;

import java.time.Duration;
import java.util.Calendar;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class Patterns {
    private Patterns() {
    }

    public static void fillItCorrect(FellowOneEntity.FellowOne user, GenericPage page) {
        page.cardNumberField.setValue(user.getCards().getApprovedCard());
        page.cardMonthField.setValue(user.getDates().getValidMonth());
        page.cardYearField.setValue(user.getDates().getValidYear());
        page.cardOwnerField.setValue(user.getName().getValidName());
        page.cardCvcField.setValue(user.getCvc().getValidCvc());
    }

    public static void clearIt(SelenideElement entity) {
        entity.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
    }

    /**
     * Шаблон для проверки тестовых полей. Возвращает момент нажатия кнопки отправки формы в формате Calendar.
     *
     * @param testingElement Поле, которое будет тестироваться
     * @param buttonToClick  Кнопка, для отправки формы
     * @param errorMessage   Ожидаемое сообщение об ошибке
     * @param testValue      Значение, передаваемое в текстовое поле
     * @param validValue     Значение, устанавливаемое после прохождения теста
     * @param time           Время ожидания теста
     */
    public static Calendar patternForPageTests(SelenideElement testingElement, SelenideElement buttonToClick, SelenideElement errorMessage, String testValue, String validValue, int time, GenericPage page) {
        Patterns.clearIt(testingElement);
        testingElement.setValue(testValue);
        Calendar cal = DataWizard.GenerateMe.generateDate();
        buttonToClick.click();
        errorMessage.shouldBe(Condition.visible, Duration.ofSeconds(time));
        if (errorMessage == page.succesWithApprovedCard) {
            page.closeSuccessPopUp.click();
        }
        if (errorMessage == page.cardReject) {
            page.closeDeniedPopUp.click();
        }
        clearIt(testingElement);
        testingElement.setValue(validValue);
        return cal;
    }

    /**
     * Шаблон для работы с генератором года и месяца. Генерирует корректный/некорректный год либо месяц
     * Принимает на вход максимальный, минимальный предел генерирования, порядок и тип выходного значения
     *
     * @param max      максимум случайного значения
     * @param min      минимум
     * @param order    порядок генерации - >=0  прямой ( промежуточный выход после рандомизатора>0) , <0  - обратный ( нужен для получения "старого" года)
     * @param dateType режим выхода. >=0  - прямой выход ( для получения года ), <0 - выход с учетом величины  для получения  валидного месяца ( 08 )
     * @return
     */
    public static String GenerateComplexDate(int max, int min, int order, int dateType) {
        int res;
        Random rand = new Random();
        if (order >= 0) {
            res = rand.nextInt(max - min) + min;
        } else {
            res = rand.nextInt(max - min) - min;
            res = Math.abs(res);
        }
        //datetype >=0  - работа с годами
        //datetype <0  - работа с месяцами

        if (dateType >= 0) {

            return Integer.toString(res);

        } else {
            if (res >= 10) {
                return Integer.toString(res);
            } else {
                String ret = "0" + Integer.toString(res);
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
     *
     * @param newUser    - экземпляр пользователя FellowOne, нужен для формирования тела запроса
     * @param path       адрес запроса
     * @param card       карта для передачи запроса
     * @param gsonString - имя поля, по которому извлекается ответ сервера
     * @return
     */
    public static String postIt(FellowOneEntity.FellowOne newUser, String path, String card, String gsonString) {
        Gson gson = new Gson();
        Response response = given()
                .spec(requestSpec)
                .body(DataWizard.GenerateMe.generateEntity(newUser, card))
                .when()
                .post(path);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        String token = jsonPath.getString(gsonString);
        return token;
    }

}



