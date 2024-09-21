package edu.nus.microservice.event_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;

import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventReview {
    @Id
    @GeneratedValue
    private UUID reviewId;
    private UUID eventId;
    private UUID userId;
    private int rating;
    private String comment;
}
