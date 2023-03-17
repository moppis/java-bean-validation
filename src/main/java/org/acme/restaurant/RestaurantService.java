package org.acme.restaurant;

import dev.personnummer.Personnummer;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApplicationScoped
public class RestaurantService {
    @ConsistentDateParameters
    public Reservation createReservation(@NotNull LocalDateTime begin, @NotNull LocalDateTime end, @Min(18) Personnummer customer) {
        return new Reservation(begin, end, customer);
    }
}
