package edu.nus.microservice.event_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventReviewResponse {
    private UUID reviewId;

    private UUID eventId;
    private UUID userId;
    private int rating;
    private String comment;
}
