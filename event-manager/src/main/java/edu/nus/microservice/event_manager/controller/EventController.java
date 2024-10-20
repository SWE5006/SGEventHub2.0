package edu.nus.microservice.event_manager.controller;

import edu.nus.microservice.event_manager.dto.EventDetailResponse;
import edu.nus.microservice.event_manager.dto.EventRequest;
import edu.nus.microservice.event_manager.dto.EventResponse;
import edu.nus.microservice.event_manager.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/event-manager/event/")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final Logger log = LoggerFactory.getLogger(EventController.class);
    @GetMapping(path="/{title}")
    public EventResponse searchEventUser(@PathVariable("title") String Title) {
        log.info("Get Event by Title: {}", Title);
        return eventService.searchEventByTitle(Title);
    }


    @GetMapping("/details")
    public EventDetailResponse searchEventById(@RequestParam("eventid") UUID eventId) {
        if (eventId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event ID cannot be null");
        }
        log.info("Returning Event: {}", eventId);
        return eventService.searchEventById(eventId);
    }


    //10-14修改


    @PostMapping (path="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse createEvent(@RequestBody EventRequest eventRequest) {
        log.info("Event is Created:{}", eventRequest.getEventId());
       return eventService.createEvent(eventRequest);
    }

    @GetMapping (path="/all")
    public List<EventResponse> getAllEvents( @RequestHeader Map<String, String> header) {
        log.info("All Event Listing is Called");
        UUID userId = UUID.fromString(header.get("userid"));
        return eventService.getAllEvents(userId);

    }

    //修改后端路径定义和匹配问题：
    // 在 EventController 中，/details/{eventId} 使用了 UUID 作为路径参数，
    // 但是在前端调用时，你使用了 query string 参数（如 ?id=${eventId}），而不是路径参数。

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEventUser(@PathVariable("id") UUID eventId) {
        if (eventId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event ID cannot be null");
        }
        log.info("Delete Event by Id: {}", eventId);
        eventService.deleteEventbyId(eventId);
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
