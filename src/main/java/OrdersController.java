import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.io.File;
import java.util.HashMap;


public class OrdersController {

    @Step("Создание заказа")
    public Response postOrder(File file) {
        return Specification.getRequestSpecification().body(file).post("/api/v1/orders");
    }

    @Step("Получение списка заказов по id")
    public Response getOrdersList(Integer id) {
        HashMap<String, Integer> data = new HashMap<>();
        data.put("id", id);
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        return Specification.getRequestSpecification().get(String.format("/api/v1/orders?id=%d", id));
    }

    @Step("Назначение заказа курьеру")
    public Response acceptOrder(Integer track, Integer courierId) {
        HashMap<String, Integer> data = new HashMap<>();
        data.put("track", track);
        data.put("courierId", courierId);

        return Specification.getRequestSpecification().put(String.format(
            "/v1/orders/accept/%d?courierId=%d",
            track,
            courierId
        ));
    }
}