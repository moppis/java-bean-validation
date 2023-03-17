package org.acme.raidingParty;

import org.acme.Result;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/raiding-party")
public class RaidingPartyResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result create(RaidingParty raidingParty) {
        return new Result("Raiding Party valid!");
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result update(RaidingParty raidingParty) {
        return new Result("Raiding Party valid!");
    }
}
