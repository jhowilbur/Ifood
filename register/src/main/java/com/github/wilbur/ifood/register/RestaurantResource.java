package com.github.wilbur.ifood.register;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/restaurant")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Restaurant")
public class RestaurantResource
{
    @GET
    public List<Restaurant> consult() {
        return Restaurant.listAll();
    }

    @POST
    @Transactional
    public Response insert(Restaurant dto) {
        dto.persist();
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Restaurant receiverRestaurant) {
        Optional<Restaurant> restaurantOp = Restaurant.findByIdOptional(id);

        if(restaurantOp.isEmpty()) {
            Response.serverError();
            throw new NotFoundException();
        }

        Restaurant restaurant = restaurantOp.get();
        restaurant.setName(receiverRestaurant.getName());
        restaurant.setDateUpdated(receiverRestaurant.getDateUpdated());
        restaurant.persist();

        return Response.status(200).entity(restaurant).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Optional<Restaurant> restaurantOp = Restaurant.findByIdOptional(id);
        restaurantOp.ifPresentOrElse(Restaurant::delete, () -> {throw new NotFoundException();});
    }

}
