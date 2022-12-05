package com.khekrn.superheroes.villain;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class VillainResourceTest {

    @Test
    public void testHelloEndpoint() {
        RestAssured.given()
                .when()
                .get("/api/villains/hello")
                .then()
                .statusCode(200)
                .body(Is.is("Hello from Quarkus !!"));
    }
}
