package edu.nus.microservice.event_manager.controller;

import edu.nus.microservice.event_manager.dto.EventRequest;
import edu.nus.microservice.event_manager.dto.EventResponse;
import edu.nus.microservice.event_manager.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-manager/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/{title}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponse searchEventUser(@PathVariable("id") String Title) {
        return eventService.searchEventByTitle(Title);
    }

    @PostMapping (path="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody EventRequest eventRequest) {
        eventService.createEvent(eventRequest);
    }

    @GetMapping (path="/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EventResponse> getAllEventUsers() {
        return eventService.getAllEvents();
    }

}
