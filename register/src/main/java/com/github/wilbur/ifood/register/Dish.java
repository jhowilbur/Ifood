package com.github.wilbur.ifood.register;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.persistence.*;
import javax.xml.namespace.QName;
import java.math.BigDecimal;

@Entity
@Table(name = "dish")
@JsonFilter("filter")
public class Dish extends PanacheEntityBase
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private Restaurant restaurant;

    private BigDecimal price;

    public Dish()
    {
    }

    public Dish(Long id, String name, String description, Restaurant restaurant, BigDecimal price)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.restaurant = restaurant;
        this.price = price;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Restaurant getRestaurant()
    {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant)
    {
        this.restaurant = restaurant;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
}
