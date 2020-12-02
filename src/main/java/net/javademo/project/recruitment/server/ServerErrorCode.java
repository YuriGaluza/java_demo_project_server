package net.javademo.project.recruitment.server;

    public enum ServerErrorCode {
    WRONG_FIRST_NAME("WRONG_FIRST_NAME %s"),
    WRONG_LAST_NAME("WRONG_LAST_NAME %s"),
    WRONG_MIDDLE_NAME("WRONG_MIDDLE_NAME %s"),
    WRONG_LOGIN("WRONG_LOGIN %s"),
    WRONG_PASSWORD("WRONG_PASSWORD"),
    WRONG_PASSWORD_LENGTH("PASSWORD_MUST_BE_LONGER_THAN_6_SYMBOLS %s"),
    WRONG_LOGIN_LENGTH("LOGIN_MUST_BE_LONGER_THAN_4_SYMBOLS %s"),
    DUPLICATE_LOGIN("DUPLICATE_LOGIN"),
    WRONG_LEVEL("THE_LEVEL_MUST_BE_AT_LEAST_1_AND_NOT_EXCEED_5"),
    EMPTY_VALUE("EMPTY_VALUE"),
    LOGIN_IS_EMPTY("LOGIN_IS_EMPTY"),
    NAME_IS_EMPTY("NAME_IS_EMPTY"),
    WRONG_EMPLOYEE("WRONG_EMPLOYEE"),
    INVALID_TOKEN("INVALID_TOKEN"),
    EMPLOYEE_IS_NOT_FOUND("EMPLOYEE_IS_NOT_FOUND"),
    EMPLOYER_IS_NOT_FOUND("EMPLOYER_IS_NOT_FOUND"),
    WRONG_TOKEN("WRONG_TOKEN"),
    ALREADY_LOGOUT("ALREADY_LOGOUT"),
    ALREADY_LOGIN("ALREADY_LOGIN");

    private String message;

    ServerErrorCode(String message) {
        this.message = message;
    }

    public String getServerErrorCode() {
        return message;
    }

    public void setServerErrorCode(String message) {
        this.message = message;
    }
}
