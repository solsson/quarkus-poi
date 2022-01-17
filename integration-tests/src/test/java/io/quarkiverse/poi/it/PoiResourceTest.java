package io.quarkiverse.poi.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PoiResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/poi")
                .then()
                .statusCode(200)
                .body(is("Hello poi"));
    }

    @Test
    public void testExport() {
        given()
                .when().get("/poi/export")
                .then()
                .statusCode(200);
    }

}
