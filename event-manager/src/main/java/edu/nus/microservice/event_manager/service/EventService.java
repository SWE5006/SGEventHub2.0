package edu.nus.microservice.event_manager.service;

import edu.nus.microservice.event_manager.dto.EventRequest;
import edu.nus.microservice.event_manager.dto.EventResponse;
import edu.nus.microservice.event_manager.model.Event;
import edu.nus.microservice.event_manager.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final EventRepository eventRepository;


    public List<EventResponse> getAllEvents() {
        List<Event> eventlist = (List<Event>) eventRepository.findAll();

        return eventlist.stream().map(this::maptoEventResponse).toList();
    }

    private EventResponse maptoEventResponse(Event event) {
        return EventResponse.builder()
                .eventDesc(event.getEventDesc())

                .build();
    }

    public void createEvent(EventRequest eventRequest) {
        Event sgevent = Event.builder()
                .eventId(eventRequest.getEventId())
                .eventCreateDt(eventRequest.getEventCreateDt())
                .eventCover(eventRequest.getEventCover())
                .eventCapacity(eventRequest.getEventCapacity())
                .eventOwnerId(eventRequest.getEventOwnerId())
                .eventPlace(eventRequest.getEventPlace())
                .eventStartDt(eventRequest.getEventStartDt())
                .eventStatus(eventRequest.getEventStatus())
                .eventTitle(eventRequest.getEventTitle())
                .eventEndDt(eventRequest.getEventEndDt())
                .eventDesc(eventRequest.getEventDesc())

                .build();
        eventRepository.save(sgevent);

    }

    public EventResponse searchEventByTitle(String Title)
    {
        Event sgevent = eventRepository.SearchEventByTitle(Title);


        return new EventResponse(sgevent.getEventId(),sgevent.getEventTitle(),
                sgevent.getEventDesc(),sgevent.getEventCreateDt(),sgevent.getEventStartDt(),
                sgevent.getEventEndDt(),sgevent.getEventPlace(),sgevent.getEventCapacity(),
                sgevent.getEventOwnerId(),sgevent.getEventStatus(),sgevent.getEventCover());

    }
}
