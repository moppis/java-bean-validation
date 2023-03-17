package org.acme;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

public record Result(boolean success, String message) {
    public Result(String message) {
        this(true, message);
    }

    public Result(Set<? extends ConstraintViolation<?>> violations) {
        this(false, violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", ")));
    }

    public Result(ConstraintViolationException exception) {
        this(exception.getConstraintViolations());
    }
}
