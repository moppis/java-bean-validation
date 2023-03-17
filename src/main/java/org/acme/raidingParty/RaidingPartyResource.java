package org.acme.raidingParty;

import org.acme.Result;

import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/raiding-party")
public class RaidingPartyResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result create(@Valid @ConvertGroup(to = ValidationGroups.Post.class) RaidingParty raidingParty) {
        return new Result("Raiding Party valid!");
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result update(@Valid @ConvertGroup(to = ValidationGroups.Put.class) RaidingParty raidingParty) {
        return new Result("Raiding Party valid!");
    }
}
