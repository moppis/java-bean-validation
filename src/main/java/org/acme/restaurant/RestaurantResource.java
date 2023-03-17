package org.acme.restaurant;

import org.acme.Result;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/restaurant")
public class RestaurantResource {

    @Inject
    RestaurantService restaurantService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result makeReservation(Request request) {

        try {
            restaurantService.createReservation(request.begin(), request.end(), request.customer());

            return new Result("Successfully made an reservation");
        } catch (ConstraintViolationException exception) {
            throw new BadRequestException(Response.status(400).entity(new Result(exception)).build());
        }
    }
}
