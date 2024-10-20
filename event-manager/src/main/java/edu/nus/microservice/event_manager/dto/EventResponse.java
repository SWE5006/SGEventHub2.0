package edu.nus.microservice.event_manager.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
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
    private long registrationCount;
    private boolean isRegistered;
}
