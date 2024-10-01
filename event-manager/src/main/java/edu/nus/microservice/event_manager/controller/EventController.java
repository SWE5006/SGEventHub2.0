package edu.nus.microservice.event_manager.controller;

import edu.nus.microservice.event_manager.dto.EventRequest;
import edu.nus.microservice.event_manager.dto.EventResponse;
import edu.nus.microservice.event_manager.dto.EventReviewResponse;
import edu.nus.microservice.event_manager.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/event-manager/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/{title}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponse searchEventUser(@PathVariable("title") String Title) {
        return eventService.searchEventByTitle(Title);
    }

    @GetMapping("/{eventid}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponse searchEventUser(@PathVariable("eventid") UUID eventid) {
        return eventService.searchEventById(eventid);
    }

    @PostMapping (path="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse createEvent(@RequestBody EventRequest eventRequest) {
       return eventService.createEvent(eventRequest);
    }

    @GetMapping (path="/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EventResponse> getAllEvents() {
        return eventService.getAllEvents();
    }

    @DeleteMapping(path="/delete/{eventid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEventUser(@PathVariable("eventid") UUID eventid) {
        eventService.deleteEventbyId(eventid);
    }


}
