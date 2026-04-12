package com.example.foodsharing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

// Lombok @Data generates getters, setters, toString, etc.
@Data
public class foodDTO {
    private String title;
    private String description;
    private int quantity;
    private String location;
    private Double location_lat;
    private Double location_lng;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private String availableFrom;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private String availableUntil;

    private Long donorId;
}