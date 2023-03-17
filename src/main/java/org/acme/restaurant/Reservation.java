package org.acme.restaurant;

import dev.personnummer.Personnummer;

import java.time.LocalDateTime;

public record Reservation(LocalDateTime begin, LocalDateTime end, Personnummer customer) {
}
