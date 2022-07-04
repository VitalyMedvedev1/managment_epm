package ru.homework.andry.soap.constant;

public class AppValues {

    public static final int ERROR_CODE = 99;
    public static final String SALARY_ERROR_TEXT_MESSAGE = "This salary is not suitable for position: {0}. ";
    public static final String REQUIRED_FIELD_ERROR_TEXT_MESSAGE = "For position: {0}, required fields are not filled!";
    public static final String KAFKA_UPSERT_TOPIC_NAME = "employeesToUpsert";
    public static final String KAFKA_DELETE_TOPIC_NAME = "employeesToDelete";
}
