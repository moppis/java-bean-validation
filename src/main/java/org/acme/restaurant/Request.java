package org.acme.restaurant;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record Request(LocalDateTime begin, LocalDateTime end, @NotBlank String customer) {}
