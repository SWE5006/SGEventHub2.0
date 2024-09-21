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
public class EventRequest {
    private UUID eventId;
    private String eventTitle;
    private String eventDesc;
    private Date eventCreateDt;
    private Date eventStartDt;
    private Date eventEndDt;
    private String eventPlace;
    private int eventCapacity;
    private String eventOwnerId;
    private String eventStatus;
    private String eventCover;
}
