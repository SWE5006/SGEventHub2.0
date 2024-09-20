package edu.nus.microservice.event_manager.service;

import edu.nus.microservice.event_manager.dto.EventResponse;
import edu.nus.microservice.event_manager.model.Event;
import edu.nus.microservice.event_manager.repository.EventRepository;
import edu.nus.microservice.event_manager.repository.EventReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final EventRepository eventRepository;


    public List<EventResponse> getAllEventUsers() {
        List<Event> eventlist = (List<Event>) eventRepository.findAll();

        return eventlist.stream().map(this::maptoEventUserResponse).toList();
    }

    private EventResponse maptoEventUserResponse(Event event) {
        return EventResponse.builder()
                .eventDesc(event.getEventDesc())

                .build();
    }
}
