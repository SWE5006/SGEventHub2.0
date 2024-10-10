package edu.nus.microservice.event_manager.service;

import edu.nus.microservice.event_manager.dto.EventRequest;
import edu.nus.microservice.event_manager.dto.EventResponse;
import edu.nus.microservice.event_manager.model.Event;
import edu.nus.microservice.event_manager.repository.EventRepository;
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


    public List<EventResponse> getAllEvents() {
        List<Event> eventlist = (List<Event>) eventRepository.findAll();

        return eventlist.stream().map(this::maptoEventResponse).toList();
    }

    private EventResponse maptoEventResponse(Event event) {
        return EventResponse.builder()
                .eventDesc(event.getEventDesc())

                .build();
    }

    public EventResponse createEvent(EventRequest eventRequest) {
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
        return new EventResponse(eventRequest.getEventId(),eventRequest.getEventTitle(),eventRequest.getEventDesc(),
        eventRequest.getEventCreateDt(),eventRequest.getEventStartDt(),eventRequest.getEventEndDt(),eventRequest.getEventPlace(),
                eventRequest.getEventCapacity(),eventRequest.getEventOwnerId(),eventRequest.getEventStatus(),eventRequest.getEventCover());

    }

    public void deleteEventbyId(UUID eventid)
    {
        eventRepository.deleteById(eventid);

    }

    public EventResponse searchEventByTitle(String Title)
    {
        Event sgevent = eventRepository.SearchEventByTitle(Title);


        return new EventResponse(sgevent.getEventId(),sgevent.getEventTitle(),
                sgevent.getEventDesc(),sgevent.getEventCreateDt(),sgevent.getEventStartDt(),
                sgevent.getEventEndDt(),sgevent.getEventPlace(),sgevent.getEventCapacity(),
                sgevent.getEventOwnerId(),sgevent.getEventStatus(),sgevent.getEventCover());

    }

    public EventResponse searchEventById(UUID eventId)
    {
        Event sgevent = eventRepository.QueryEventById(eventId);


        return new EventResponse(sgevent.getEventId(),sgevent.getEventTitle(),
                sgevent.getEventDesc(),sgevent.getEventCreateDt(),sgevent.getEventStartDt(),
                sgevent.getEventEndDt(),sgevent.getEventPlace(),sgevent.getEventCapacity(),
                sgevent.getEventOwnerId(),sgevent.getEventStatus(),sgevent.getEventCover());

    }

    public  int UpdateEvent( UUID eventId, String eventTitle, String eventDesc, String eventCover, String eventPlace,
                             Date eventStartDt, Date eventEndDt, int eventCapacity)
    {
        return eventRepository.UpdateEvent( eventId, eventTitle, eventDesc, eventCover, eventPlace, eventStartDt, eventEndDt,eventCapacity);
    }
}
