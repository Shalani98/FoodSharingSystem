package com.example.foodsharing.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="food")

public class Food{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int quantity;
    private String location;
    private Double location_lat;
    private Double location_lng;

    @JsonFormat(pattern= "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime availableFrom;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime availableUntil;

    private boolean claimed = false;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name="donor_id")
    private User donor;
}
