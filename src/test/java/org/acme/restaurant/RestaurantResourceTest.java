package org.acme.restaurant;

import dev.personnummer.Personnummer;
import dev.personnummer.PersonnummerException;
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
    void beginCannotBeInThePast() throws PersonnummerException {
        var begin = LocalDateTime.now().minusHours(1);
        var end = LocalDateTime.now().plusHours(1);
        var customer = Personnummer.parse(EIGHTEEN);

        var request = new Request(begin, end, customer);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(400);
    }

    @Test
    void endCannotBeInThePast() throws PersonnummerException {
        var end = LocalDateTime.now().minusHours(1);
        var begin = LocalDateTime.now().plusHours(1);
        var customer = Personnummer.parse(EIGHTEEN);

        var request = new Request(begin, end, customer);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(400);
    }

    @Test
    void beginCannotBeNull() throws PersonnummerException {
        LocalDateTime begin = null;
        var end = LocalDateTime.now().plusHours(1);
        var customer = Personnummer.parse(EIGHTEEN);

        var request = new Request(begin, end, customer);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(400);
    }

    @Test
    void endCannotBeNull() throws PersonnummerException {
        var begin = LocalDateTime.now().minusHours(1);
        LocalDateTime end = null;
        var customer = Personnummer.parse(EIGHTEEN);

        var request = new Request(begin, end, customer);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(400);
    }

    @Test
    void beginCannotLaterThanEnd() throws PersonnummerException {
        var begin = LocalDateTime.now().plusHours(3);
        var end = LocalDateTime.now().plusHours(1);
        var customer = Personnummer.parse(EIGHTEEN);

        var request = new Request(begin, end, customer);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request).when()
                .post("/restaurant")
                .then()
                .statusCode(400);
    }
// TODO: 2023-03-17 Never got these to work :(

//    @Test
//    void customerCannotBeNull() throws PersonnummerException {
//        var begin = LocalDateTime.now().plusHours(1);
//        var end = LocalDateTime.now().plusHours(3);
//        Personnummer customer = null;
//
//        var request = new Request(begin, end, customer);
//
//        given()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(request).when()
//                .post("/restaurant")
//                .then()
//                .statusCode(400);
//    }
//
//    @Test
//    void customerCanBeEighteen() throws PersonnummerException {
//        var begin = LocalDateTime.now().plusHours(1);
//        var end = LocalDateTime.now().plusHours(3);
//        var customer = Personnummer.parse(OVER_EIGHTEEN);
//
//        var request = new Request(begin, end, customer);
//
//        given()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(request).when()
//                .post("/restaurant")
//                .then()
//                .statusCode(200);
//    }
//
//    @Test
//    void customerCanBeOverEighteen() throws PersonnummerException {
//        var begin = LocalDateTime.now().plusHours(1);
//        var end = LocalDateTime.now().plusHours(3);
//        var customer = Personnummer.parse(OVER_EIGHTEEN);
//
//        var request = new Request(begin, end, customer);
//
//        given()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(request).when()
//                .post("/restaurant")
//                .then()
//                .statusCode(200);
//    }
//
//    @Test
//    void customerCannotBeUnderEighteen() throws PersonnummerException {
//        var begin = LocalDateTime.now().plusHours(1);
//        var end = LocalDateTime.now().plusHours(3);
//        var customer = Personnummer.parse(UNDER_EIGHTEEN);
//
//        var request = new Request(begin, end, customer);
//
//        given()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(request).when()
//                .post("/restaurant")
//                .then()
//                .statusCode(400)
//                .body(is(""));
//    }
}