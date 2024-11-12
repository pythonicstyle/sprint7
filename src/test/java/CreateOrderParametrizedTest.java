import static org.hamcrest.CoreMatchers.notNullValue;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import java.io.File;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateOrderParametrizedTest {
    private final String path;
    private final Integer statusCode;

    public CreateOrderParametrizedTest(String path, Integer statusCode) {
        this.path = path;
        this.statusCode = statusCode;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
            {"src/test/resources/OrderBlackScooter.json", 201},
            {"src/test/resources/OrderGreyScooter.json", 201},
            {"src/test/resources/OrderBlackAndGreyScooter.json", 201},
            {"src/test/resources/OrderScooterWithoutColor.json", 201}
        };
    }

    OrdersController ordersController = new OrdersController();

    @Test
    @DisplayName("Создание заказа")
    @Description("Параметризированный тест для создания заказов с разными цветами")
    public void testCreateOrder() {
        File json = new File(path);
        ordersController
            .postOrder(json)
            .then()
            .assertThat()
            .statusCode(statusCode)
            .and()
            .body("track", notNullValue());
    }
}
