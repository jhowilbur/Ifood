package com.github.wilbur.ifood.register;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.hibernate.engine.spi.Status;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/{idRestaurant}/dish")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Dish")
public class DishResource
{

    @GET
    public List<Restaurant> consult(@PathParam("idRestaurant") Long idRestaurant) {
        Optional<Restaurant> restaurantOp = Restaurant.findByIdOptional(idRestaurant);

        if(restaurantOp.isEmpty()){
            throw new NotFoundException();
        }

        return Dish.list("restaurant", restaurantOp.get());
    }

    @POST
    @Transactional
    public Response insert(Dish dishDto) {
        dishDto.persist();
        return Response.status(Response.Status.CREATED).entity(dishDto).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Dish dishReceiver) {
        Optional<Dish> dishOp = Dish.findByIdOptional(id);

        if (dishOp.isEmpty()) {
            throw new NotFoundException();
        }

        Dish dishDto = dishOp.get();
        dishDto.setName(dishReceiver.getName());
        dishDto.setDescription(dishReceiver.getDescription());
        dishDto.setPrice(dishReceiver.getPrice());
        dishDto.persist();

        return Response.status(200).entity(dishDto).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Optional<Dish> dishOp = Dish.findByIdOptional(id);
        dishOp.ifPresentOrElse(Dish::delete, () -> {throw new NotFoundException();});
        return Response.status(200).entity(dishOp).build();
    }

}
