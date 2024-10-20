package edu.nus.microservice.event_manager.service;

import edu.nus.microservice.event_manager.dto.EventRequest;
import edu.nus.microservice.event_manager.dto.EventResponse;
import edu.nus.microservice.event_manager.dto.EventReviewRequest;
import edu.nus.microservice.event_manager.dto.EventReviewResponse;
import edu.nus.microservice.event_manager.model.Event;
import edu.nus.microservice.event_manager.model.EventReview;
import edu.nus.microservice.event_manager.repository.EventRepository;
import edu.nus.microservice.event_manager.repository.EventReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Transactional
public class EventReviewService {

    private final EventReviewRepository eventReviewRepository;

    public List<EventReviewResponse> getAllReviews() {
        List<EventReview> reviewList = (List<EventReview>) eventReviewRepository.findAll();

        return reviewList.stream().map(this::maptoReviewResponse).toList();
    }

    private EventReviewResponse maptoReviewResponse(EventReview eventReview) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(eventReview, EventReviewResponse.class);
    }

    public List<EventReviewResponse> searchReviewByEventId(UUID eventId)
    {
        List<EventReview> eventReviewList = eventReviewRepository.QueryEventReviewByEventId(eventId);

        return eventReviewList.stream().map(this::maptoReviewResponse).toList();

    }

    public EventReviewResponse createEventReview(EventReview reviewRequest) {
        EventReview eventReview = EventReview.builder()
                .eventId(reviewRequest.getEventId())
                .reviewId(UUID.randomUUID())
                .userId(reviewRequest.getUserId())
                .rating(reviewRequest.getRating())
                .comment(reviewRequest.getComment())
                .build();
         eventReviewRepository.save(eventReview);
        return maptoReviewResponse(eventReview);
    }
}
