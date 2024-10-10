package edu.nus.microservice.event_manager.controller;


import edu.nus.microservice.event_manager.dto.EventRequest;
import edu.nus.microservice.event_manager.dto.EventResponse;
import edu.nus.microservice.event_manager.dto.EventReviewRequest;
import edu.nus.microservice.event_manager.dto.EventReviewResponse;
import edu.nus.microservice.event_manager.model.EventReview;
import edu.nus.microservice.event_manager.service.EventReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/event-manager/review")
@RequiredArgsConstructor

public class EventReviewController {

    private final EventReviewService reviewService;
    private final Logger log = LoggerFactory.getLogger(EventReviewController.class);
    @GetMapping(path="/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EventReviewResponse> getAllEventReview() {

        log.info("Getting All the Event Review");
        return reviewService.getAllReviews();
    }

    @GetMapping("/{eventid}")
    @ResponseStatus(HttpStatus.OK)
    public EventReviewResponse searchEventUser(@PathVariable("eventid") UUID eventid) {
        log.info("Event Review by Id:{}", eventid);
        return reviewService.searchReviewByEventId(eventid);
    }

    @PostMapping (path="/add")
    @ResponseStatus(HttpStatus.CREATED)
    public EventReviewResponse addEventReview(@RequestBody EventReview reviewRequest) {
        log.info("Event Review is created!");
        return reviewService.createEventReview(reviewRequest);
    }
}
