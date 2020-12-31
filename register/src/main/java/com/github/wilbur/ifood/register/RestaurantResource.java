package com.github.wilbur.ifood.register;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/restaurant")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantResource
{
    @GET
    public List<Restaurant> search() {
        return Restaurant.listAll();
    }

    @POST
    @Transactional
    public void persisted(Restaurant dto) {
        dto.persist();
    }
}
