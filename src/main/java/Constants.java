public class Constants {
    public static final String TEST_USERNAME = "YuriXXL";
    public static final String RANDOM_USERNAME = Constants.TEST_USERNAME + (int)(Math.random() * 1000);
    public static final String TEST_PASSWORD = "XXL";
    public static final String TEST_NAME = "Yuri";
    public static final String TEST_INCORRECT_USERNAME = "Нгкш";
    public static final String JSON_STRING = "{\"login\": \"" + Constants.RANDOM_USERNAME + "\", \"password\": \"" + Constants.TEST_PASSWORD +
        "\", \"firstName\": \"" + Constants.TEST_NAME + "\"}";

    public static final String CREATE_COURIER_ERROR_MESSAGE_400 = "Недостаточно данных для создания учетной записи";
    public static final String DOUBLE_LOGIN_ERROR_MESSAGE_409 = "Этот логин уже используется";
    public static final String COURIER_NOT_FOUND_ERROR_MESSAGE_404 = "Учетная запись не найдена";
    public static final String LOGIN_ERROR_MESSAGE_400 = "Недостаточно данных для входа";
}
