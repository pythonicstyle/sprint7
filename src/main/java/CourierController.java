import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.HashMap;


public class CourierController {

    @Step("Создание курьера")
    public Response postCourier(String file) {
        return Specification.getRequestSpecification().body(file).post("/api/v1/courier");
    }

    @Step("Удаление курьера по id")
    public Response deleteCourier(Integer id) {
        return Specification.getRequestSpecification().delete(String.format("/api/v1/courier/%d", id));
    }

    @Step("Получение id курьера")
    public Response getCourier(String login, String pass) {
        HashMap<String, String> data = new HashMap<>();
        data.put("login", login);
        data.put("password", pass);
        return Specification.getRequestSpecification().body(data).post("/api/v1/courier/login");
    }
}
