package edu.nus.microservice.event_manager.dto;

import edu.nus.microservice.event_manager.model.EventRegistration;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class EventDetailResponse extends EventResponse {
    private List<EventRegistration> userList;
}
