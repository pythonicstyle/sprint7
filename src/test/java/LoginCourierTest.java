import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

public class LoginCourierTest {

    CourierController courierController = new CourierController();

    @Test
    @DisplayName("Логина курьера")
    public void testLoginCourier() {
        courierController.postCourier(Constants.JSON_STRING);
        courierController.getCourier(Constants.RANDOM_USERNAME, Constants.TEST_PASSWORD)
            .then()
            .statusCode(200)
            .and()
            .body("id", notNullValue());
    }

    @Test
    @DisplayName("Логин курьера с некорректным username")
    public void testLoginCourierWithIncorrectUsername() {
        courierController.postCourier(Constants.JSON_STRING);
        courierController.getCourier(Constants.TEST_INCORRECT_USERNAME, Constants.TEST_PASSWORD)
            .then()
            .statusCode(404)
            .and()
            .body("message", equalTo(Constants.COURIER_NOT_FOUND_ERROR_MESSAGE_404));
    }

    @Test
    @DisplayName("Логина курьера без пароля")
    public void testLoginCourierWithoutPassword() {
        courierController.postCourier(Constants.JSON_STRING);
        courierController.getCourier(Constants.RANDOM_USERNAME, null)
            .then()
            .statusCode(400)
            .and()
            .body("message", equalTo(Constants.LOGIN_ERROR_MESSAGE_400));
    }

    @Test
    @DisplayName("Логина курьера без username")
    public void testLoginCourierWithoutUsername() {
        courierController.postCourier(Constants.JSON_STRING);
        courierController.getCourier(null, Constants.TEST_PASSWORD)
            .then()
            .log().all()
            .statusCode(400)
            .and()
            .body("message", equalTo(Constants.LOGIN_ERROR_MESSAGE_400));
    }

    @After
    public void tearDown() {
        Integer id = courierController.getCourier(Constants.RANDOM_USERNAME, Constants.TEST_PASSWORD)
            .then()
            .extract()
            .jsonPath()
            .get("id");
        if (id != null) {
            courierController.deleteCourier(id).then().statusCode(200);
            System.out.printf("\nПользователь %s удален", Constants.RANDOM_USERNAME);
        }
    }
}
