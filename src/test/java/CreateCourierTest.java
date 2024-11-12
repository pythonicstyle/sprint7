import static org.hamcrest.CoreMatchers.equalTo;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

public class CreateCourierTest {

    CourierController courierController = new CourierController();

    @Test
    @DisplayName("Создание курьера")
    public void testCreateCourier() {
        courierController.postCourier(Constants.JSON_STRING)
            .then()
            .statusCode(201)
            .and()
            .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    public void testCreateCourierWithoutPassword() {
        String json = "{\"login\": \"" + Constants.RANDOM_USERNAME + "\"}";
        courierController.postCourier(json)
            .then()
            .statusCode(400)
            .and()
            .body("message", equalTo(Constants.CREATE_COURIER_ERROR_MESSAGE_400));
    }

    @Test
    @DisplayName("Создание курьера с одним и тем же логином")
    public void testCreateDoubleCourier() {
        courierController.postCourier(Constants.JSON_STRING);
        courierController.postCourier(Constants.JSON_STRING)
            .then()
            .assertThat()
            .statusCode(409)
            .and()
            .body("message", equalTo(Constants.DOUBLE_LOGIN_ERROR_MESSAGE_409));
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
