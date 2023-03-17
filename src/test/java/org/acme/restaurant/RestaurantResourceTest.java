package org.acme.restaurant;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

@QuarkusTest
class RestaurantResourceTest {

    private static final String UNDER_EIGHTEEN = "202002012389";
    private static final String EIGHTEEN = "200501012397";
    private static final String OVER_EIGHTEEN = "200001062397";

    @Test
    void beginCannotBeInThePast() {
        var begin = LocalDateTime.now().minusHours(1);
        var end = LocalDateTime.now().plusHours(1);

        var request = new Request(begin, end, EIGHTEEN);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(400);
    }

    @Test
    void endCannotBeInThePast() {
        var end = LocalDateTime.now().minusHours(1);
        var begin = LocalDateTime.now().plusHours(1);

        var request = new Request(begin, end, EIGHTEEN);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(400);
    }

    @Test
    void beginCannotBeNull() {
        LocalDateTime begin = null;
        var end = LocalDateTime.now().plusHours(1);

        var request = new Request(begin, end, EIGHTEEN);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(400);
    }

    @Test
    void endCannotBeNull() {
        var begin = LocalDateTime.now().minusHours(1);
        LocalDateTime end = null;

        var request = new Request(begin, end, EIGHTEEN);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(400);
    }

    @Test
    void beginCannotLaterThanEnd() {
        var begin = LocalDateTime.now().plusHours(3);
        var end = LocalDateTime.now().plusHours(1);

        var request = new Request(begin, end, EIGHTEEN);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(400);
    }

    @Test
    void customerCannotBeNull() {
        var begin = LocalDateTime.now().plusHours(1);
        var end = LocalDateTime.now().plusHours(3);

        var request = new Request(begin, end, null);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(400);
    }

    @Test
    void customerCanBeEighteen() {
        var begin = LocalDateTime.now().plusHours(1);
        var end = LocalDateTime.now().plusHours(3);

        var request = new Request(begin, end, EIGHTEEN);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(200);
    }

    @Test
    void customerCanBeOverEighteen() {
        var begin = LocalDateTime.now().plusHours(1);
        var end = LocalDateTime.now().plusHours(3);

        var request = new Request(begin, end, OVER_EIGHTEEN);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(200);
    }

    @Test
    void customerCannotBeUnderEighteen() {
        var begin = LocalDateTime.now().plusHours(1);
        var end = LocalDateTime.now().plusHours(3);

        var request = new Request(begin, end, UNDER_EIGHTEEN);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(400);
    }
}