package org.acme.raidingParty;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void mustHaveName() {
        var player = new Player(null, PlayerClass.WARRIOR, 100, 0);

        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        assertFalse(violations.isEmpty());
    }

    @Test
    void nameCannotBeEmpty() {
        var player = new Player("", PlayerClass.WARRIOR, 100, 0);

        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        assertFalse(violations.isEmpty());
    }

    @Test
    void cannotHaveNameLongerThan10() {
        var player = new Player("a".repeat(11), PlayerClass.WARRIOR, 100, 0);

        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        assertFalse(violations.isEmpty());
    }

    @Test
    void mustHaveAClass() {
        var player = new Player("player", null, 100, 0);

        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        assertFalse(violations.isEmpty());
    }

    @Test
    void healthCannotBeNegative() {
        var player = new Player("player", PlayerClass.WARRIOR, -1, 0);

        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        assertFalse(violations.isEmpty());
    }

    @Test
    void healthCanBeZero() {
        var player = new Player("player", PlayerClass.WARRIOR, 0, 0);

        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        assertTrue(violations.isEmpty());
    }

    @Test
    void healthCanBe200() {
        var player = new Player("player", PlayerClass.WARRIOR, 200, 0);

        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        assertTrue(violations.isEmpty());
    }

    @Test
    void healthCannotBeOver200() {
        var player = new Player("player", PlayerClass.WARRIOR, 201, 0);

        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        assertFalse(violations.isEmpty());
    }

    @Test
    void healthCanBeUpTo400ForWarriors() {
        var player = new Player("player", PlayerClass.WARRIOR, 400, 0);

        Set<ConstraintViolation<Player>> violations = validator.validate(player, ValidationGroups.Warrior.class);
        assertFalse(violations.isEmpty());
    }

    @Test
    void healthCannotBeOver400ForWarriors() {
        var player = new Player("player", PlayerClass.WARRIOR, 401, 0);

        Set<ConstraintViolation<Player>> violations = validator.validate(player, ValidationGroups.Warrior.class);
        assertFalse(violations.isEmpty());
    }

    @Test
    void manaCannotBeNegative() {
        var player = new Player("player", PlayerClass.WARRIOR, 100, -1);

        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        assertFalse(violations.isEmpty());
    }

    @Test
    void manaCanBeZero() {
        var player = new Player("player", PlayerClass.WARRIOR, 100, 0);

        Set<ConstraintViolation<Player>> violations = validator.validate(player);
        assertTrue(violations.isEmpty());
    }

    @Test
    void manaMustBeZeroForWarriors() {
        var player = new Player("player", PlayerClass.WARRIOR, 100, 1);

        Set<ConstraintViolation<Player>> violations = validator.validate(player, ValidationGroups.Warrior.class);
        assertFalse(violations.isEmpty());
    }

    @Test
    void manaCanBeUpTo400ForWizards() {
        var player = new Player("player", PlayerClass.WIZARD, 100, 400);

        Set<ConstraintViolation<Player>> violations = validator.validate(player, ValidationGroups.Wizard.class);
        assertTrue(violations.isEmpty());
    }

    @Test
    void manaCannotBeOver400ForWizards() {
        var player = new Player("player", PlayerClass.WIZARD, 100, 410);

        Set<ConstraintViolation<Player>> violations = validator.validate(player, ValidationGroups.Wizard.class);
        assertFalse(violations.isEmpty());
    }
}