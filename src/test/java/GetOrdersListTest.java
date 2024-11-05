import static org.hamcrest.CoreMatchers.notNullValue;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import java.io.File;
import org.junit.Test;

public class GetOrdersListTest {

    OrdersController ordersController = new OrdersController();
    CourierController courierController = new CourierController();

    @Test
    @DisplayName("Получение списка заказов курьера по ID")
    @Description("Проверка, что в тело ответа возвращается список заказов")
    public void testGetOrdersList() {

        String json = "{\"login\": \"" + Constants.RANDOM_USERNAME + "\", \"password\": \"" + Constants.TEST_PASSWORD +
            "\", \"firstName\": \"" + Constants.TEST_NAME + "\"}";

        courierController.postCourier(json); // Создание курьера
        Integer courierId = courierController.getCourier(Constants.RANDOM_USERNAME, Constants.TEST_PASSWORD)
            .then().extract().jsonPath().get("id"); // Получени ID курьера
        Integer track = ordersController
            .postOrder(new File("src/test/resources/OrderBlackScooter.json"))
            .then()
            .extract()
            .jsonPath()
            .get("track"); // Создание заказа и получение его номера
        ordersController.acceptOrder(track, courierId); // Назначение заказа курьеру
        ordersController.getOrdersList(courierId) // Получение списка заказов по ID курьера
            .then()
            .statusCode(200)
            .and()
            .assertThat()
            .body("orders", notNullValue());
        if (courierId != null) {
            courierController.deleteCourier(courierId).then().statusCode(200);
            System.out.printf("\nПользователь %d удален%n", courierId);
        }
    }
}