package ch.mobi.gluon.base.extension.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class GluonBaseExtensionResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/gluon-base-extension")
                .then()
                .statusCode(200)
                .body(is("Hello gluon-base-extension"));
    }
}
