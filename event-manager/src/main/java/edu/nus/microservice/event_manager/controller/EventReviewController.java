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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/event-manager/review")
@RequiredArgsConstructor
public class EventReviewController {

    private final EventReviewService reviewService;

    @GetMapping(path="/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EventReviewResponse> getAllEventReview() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{eventid}")
    @ResponseStatus(HttpStatus.OK)
    public EventReviewResponse searchEventUser(@PathVariable("eventid") UUID eventid) {
        return reviewService.searchReviewByEventId(eventid);
    }

    @PostMapping (path="/add")
    @ResponseStatus(HttpStatus.CREATED)
    public EventReviewResponse addEventReview(@RequestBody EventReview reviewRequest) {
        return reviewService.createEventReview(reviewRequest);
    }
}
