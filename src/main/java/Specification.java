import static io.restassured.RestAssured.given;

import io.restassured.specification.RequestSpecification;

public class Specification {
    private static final String base_URI = "https://qa-scooter.praktikum-services.ru";

    public static RequestSpecification getRequestSpecification() {
        return given()
            .header("Content-Type", "application/json")
            .baseUri(base_URI)
            .when();
    }
}