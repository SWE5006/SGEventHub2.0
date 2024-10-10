package edu.nus.microservice.event_manager.controller;

import edu.nus.microservice.event_manager.dto.EventRequest;
import edu.nus.microservice.event_manager.dto.EventResponse;
import edu.nus.microservice.event_manager.dto.EventReviewResponse;
import edu.nus.microservice.event_manager.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final Logger log = LoggerFactory.getLogger(EventController.class);
    @GetMapping("/{title}")
    public EventResponse searchEventUser(@PathVariable("title") String Title) {
        log.info("Get Event by Title: {}", Title);
        return eventService.searchEventByTitle(Title);
    }

    @GetMapping("/{eventid}")
    public EventResponse searchEventUser(@PathVariable("eventid") UUID eventid) {
        log.info("Returning Event:{}", eventid);
        return eventService.searchEventById(eventid);
    }

    @PostMapping (path="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse createEvent(@RequestBody EventRequest eventRequest) {
        log.info("Event is Created:{}", eventRequest.getEventId());
       return eventService.createEvent(eventRequest);
    }

    @GetMapping (path="/all")
    public List<EventResponse> getAllEvents() {
        log.info("All Event Listing is Called");
        return eventService.getAllEvents();
    }

    @DeleteMapping(path="/delete/{eventid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEventUser(@PathVariable("eventid") UUID eventid) {
        log.info("Delete Event by Id:{}", eventid);
        eventService.deleteEventbyId(eventid);
    }

    @PostMapping(path = "/update")
    @ResponseStatus(HttpStatus.OK) // Map ONLY POST Requests
    public void UpdateStatus(
            @RequestBody EventRequest eventRequest
    ) {

        int updatestatus = eventService.UpdateEvent(eventRequest.getEventId(),
                eventRequest.getEventTitle(),
                eventRequest.getEventDesc(),
                eventRequest.getEventCover(),
                eventRequest.getEventPlace(),
                eventRequest.getEventStartDt(),
                eventRequest.getEventEndDt(),
                eventRequest.getEventCapacity());

        if (updatestatus != 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_MODIFIED,
                    "Update Error"
            );
        }
    }

}
