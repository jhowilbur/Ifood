package com.wilbur;

import javax.persistence.*;

@Entity
@Table(name = "localization")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;

}
