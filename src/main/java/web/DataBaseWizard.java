package web;

import lombok.SneakyThrows;

import java.sql.DriverManager;
import java.util.Calendar;

public class DataBaseWizard {
    private DataBaseWizard() {
    }

    @SneakyThrows
    public static String getPaymentStatusForCreditBuy(Calendar cal, String status) {
        return DataBaseWizard.paymentStatusFromService(cal, "credit_request_entity", status);
    }

    @SneakyThrows
    public static String getPaymentStatusForStraightBuy(Calendar cal, String status) {
        return DataBaseWizard.paymentStatusFromService(cal, "payment_entity", status);
    }

    @SneakyThrows
    public static String getPriceOfTourWithStraight(Calendar cal, String status) {
        return DataBaseWizard.paymentValue(cal, "payment_entity", status);
    }


    @SneakyThrows
    public static String paymentValue(Calendar cal, String dbPage, String status) {
        String date = DataWizard.GenerateMe.generateDateForDbQuerry(cal);  //
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app",
                        "app",
                        "pass"
                );
                var paymentStatus = conn.createStatement();
        ) {
            String p = " SELECT  amount from " + dbPage + "  where created like '" + date + "%' and status like '" + status + "' order by created desc;";
            try (var rs = paymentStatus.executeQuery(" SELECT  amount from " + dbPage + " where created like '" + date + "%' and status like '" + status + "' order by created desc;")) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        }
        return "OOPS! Look like we have nothing for your querry. Try again, it can stuck.";

    }

    /**
     * Шаблон для выполнения запроса к БД. Из экземпляра cal получаем текущую дату в формате "ГГГГ-ММ-ДД ЧЧ:ММ:С%",
     * формируем упорядоченный по дате список и возвращаем первую строку, откуда забираем статус платежа.
     *
     * @param cal    экземпляр Calendar с датой нажатия кнопки
     * @param dbPage таблица, откуда забирается первая строка ( credit_request_entity или payment_entity)
     * @return
     */
    @SneakyThrows
    public static String paymentStatusFromService(Calendar cal, String dbPage, String status) {
        String date = DataWizard.GenerateMe.generateDateForDbQuerry(cal);  //
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app",
                        "app",
                        "pass"
                );
                var paymentStatus = conn.createStatement();
        ) {
            String p = " SELECT  status from " + dbPage + "  where created like '" + date + "%' and status like '" + status + "' order by created desc;";
            try (var rs = paymentStatus.executeQuery(" SELECT  status from " + dbPage + " where created like '" + date + "%' and status like '" + status + "' order by created desc;")) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        }
        return "OOPS! Look like we have nothing for your querry. Try again, it can stuck.";
    }
}
