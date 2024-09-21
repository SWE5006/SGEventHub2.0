package edu.nus.microservice.event_manager.service;

import edu.nus.microservice.event_manager.dto.EventResponse;
import edu.nus.microservice.event_manager.model.Event;
import edu.nus.microservice.event_manager.repository.EventRepository;
import edu.nus.microservice.event_manager.repository.EventReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    public EventResponse getEventbyTitle(String Title)
    {
        Event sgevent = eventRepository.SearchEventByTitle(Title);


        return new EventResponse(sgevent.getEventId(),sgevent.getEventTitle(),
                sgevent.getEventDesc(),sgevent.getEventCreateDt(),sgevent.getEventStartDt(),
                sgevent.getEventEndDt(),sgevent.getEventPlace(),sgevent.getEventCapacity(),
                sgevent.getEventOwnerId(),sgevent.getEventStatus(),sgevent.getEventCover());

    }
}
