package edu.nus.microservice.event_manager.service;

import edu.nus.microservice.event_manager.model.Event;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public class EventReviewService {

    @Query(
            value = "select event.* from event where event_id=?1",
            nativeQuery = true
    )
    Event QueryEventReviewById(UUID event_id);
}
