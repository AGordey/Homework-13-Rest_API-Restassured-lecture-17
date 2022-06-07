package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class reqres_test extends reqresEndpoints {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    @DisplayName("First API test - GET method")
    void checkApiEndpoint1() {

        given()
                .log().uri()
                .when()
                .get(getSingleResourseAPI)
                .then()
                .log().status()
                .statusCode(200)
                .body("data.name", is("fuchsia rose"))
                .body("support.url", is("https://reqres.in/#support-heading"));
    }

    @Test
    @DisplayName("Second API test - POST method")
    void checkApiEndpoint2() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        given()
                .log().uri()
                .when()
                .body(body)
                .contentType(JSON)
                .post(postRegisterSuccessfulAPI)
                .then()
                .log().status()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is(notNullValue()));
    }

    @Test
    @DisplayName("Third API test - PUT method")
    void checkApiEndpoint3() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given()
                .log().uri()
                .when()
                .body(body)
                .contentType(JSON)
                .put(putUpdateApi)
                .then()
                .log().status()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", is(notNullValue()));

    }

    @Test
    @DisplayName("Fourth API test - PATCH method")
    void checkApiEndpoint4() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given()
                .log().uri()
                .when()
                .body(body)
                .contentType(JSON)
                .patch(putUpdateApi)
                .then()
                .log().status()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", is(notNullValue()));

    }

    @Test
    @DisplayName("Fifth API test - DELETE method")
    void checkApiEndpoint5() {

        given()
                .log().uri()
                .when()
                .delete(putUpdateApi)
                .then()
                .log().status()
                .statusCode(204);

    }
}
