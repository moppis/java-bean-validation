package org.acme.restaurant;

import dev.personnummer.Personnummer;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class RestaurantService {
    public Reservation createReservation(LocalDateTime begin, LocalDateTime end, Personnummer customer) {
        return new Reservation(begin, end, customer);
    }
}
