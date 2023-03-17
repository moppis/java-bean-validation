package org.acme.restaurant;

import java.time.LocalDateTime;

public record Request(LocalDateTime begin, LocalDateTime end, String customer) {}
