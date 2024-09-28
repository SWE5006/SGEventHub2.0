package edu.nus.microservice.event_manager.controller;


import edu.nus.microservice.event_manager.dto.EventReviewResponse;
import edu.nus.microservice.event_manager.service.EventReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event-manager/review")
@RequiredArgsConstructor
public class EventReviewController {

    private final EventReviewService reviewService;

    @GetMapping(path="/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EventReviewResponse> getAllEventReview() {
        return reviewService.getAllReviews();
    }
}
