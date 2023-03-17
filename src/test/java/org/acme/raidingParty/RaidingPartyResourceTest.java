package org.acme.raidingParty;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;

@QuarkusTest
class RaidingPartyResourceTest {

    @Test
    void titleCannotBeNull() {
        var raidingParty = new RaidingParty(UUID.randomUUID(), null, List.of());

        given()
                .contentType("application/json")
                .body(raidingParty)
                .when().put("/raiding-party")
                .then().statusCode(400);
    }

    @Test
    void titleCannotBeEmpty() {
        var raidingParty = new RaidingParty(UUID.randomUUID(), "", List.of());

        given()
                .contentType("application/json")
                .body(raidingParty)
                .when().put("/raiding-party")
                .then().statusCode(400);
    }

    @Test
    void titleCannotBeBlank() {
        var raidingParty = new RaidingParty(UUID.randomUUID(), "\t  \n  \t", List.of());

        given()
                .contentType("application/json")
                .body(raidingParty)
                .when().put("/raiding-party")
                .then().statusCode(400);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    void titleLengthCannotBeLessThan8(int count) {
        var raidingParty = new RaidingParty(UUID.randomUUID(), "a".repeat(count), List.of());

        given()
                .contentType("application/json")
                .body(raidingParty)
                .when().put("/raiding-party")
                .then().statusCode(400);
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 9, 10, 11, 12, 13, 14, 15, 16})
    void titleLengthCanBeBetween8And16(int count) {
        var raidingParty = new RaidingParty(UUID.randomUUID(), "a".repeat(count), List.of());

        given()
                .contentType("application/json")
                .body(raidingParty)
                .when().put("/raiding-party")
                .then().statusCode(200);
    }

    @ParameterizedTest
    @ValueSource(ints = {17, 18, 19, 20})
    void titleLengthCannotBeMoreThan16(int count) {
        var raidingParty = new RaidingParty(UUID.randomUUID(), "a".repeat(count), List.of());

        given()
                .contentType("application/json")
                .body(raidingParty)
                .when().put("/raiding-party")
                .then().statusCode(400);
    }

    @Test
    void mustHaveIdWhenUpdating() {

        var raidingParty = new RaidingParty(null, "Awesome Name", List.of());

        given()
                .contentType("application/json")
                .body(raidingParty)
                .when().put("/raiding-party")
                .then().statusCode(400);
    }

    @Test
    void cannotHaveIdWhenPosting() {
        var raidingParty = new RaidingParty(UUID.randomUUID(), "Awesome Name", List.of());

        given()
                .contentType("application/json")
                .body(raidingParty)
                .when().post("/raiding-party")
                .then().statusCode(400);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    void canHaveUpTo4Members(int count) {
        var raidingParty = new RaidingParty(UUID.randomUUID(), "Awesome Name", getPlayers(count));

        given()
                .contentType("application/json")
                .body(raidingParty)
                .when().put("/raiding-party")
                .then().statusCode(200);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7})
    void cannotHaveMoreThan4Members(int count) {
        var raidingParty = new RaidingParty(UUID.randomUUID(), "Awesome Name", getPlayers(count));

        given()
                .contentType("application/json")
                .body(raidingParty)
                .when().put("/raiding-party")
                .then().statusCode(400);
    }

    @Test
    void onlyValidPlayersAllowed() {
        var badPlayer = new Player("", null, Integer.MAX_VALUE, Integer.MAX_VALUE);

        var raidingParty = new RaidingParty(UUID.randomUUID(), "Awesome Name", List.of(badPlayer));

        given()
                .contentType("application/json")
                .body(raidingParty)
                .when().put("/raiding-party")
                .then().statusCode(400);
    }

    List<Player> getPlayers(int count) {
        return IntStream.iterate(0, i -> i + 1).limit(count)
                .mapToObj(i -> new Player("Play" + i, PlayerClass.WIZARD, 0, 0)).toList();
    }
}