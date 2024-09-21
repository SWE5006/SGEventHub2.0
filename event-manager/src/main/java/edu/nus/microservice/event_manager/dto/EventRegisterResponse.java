package edu.nus.microservice.event_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRegisterResponse {
    private UUID registerId;
    private UUID userId;
    private UUID eventId;
    private Date registerDt;
    private String registerStatus;
}
