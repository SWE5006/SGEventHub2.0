
package edu.nus.microservice.event_manager.repository;
import java.util.Date;
import java.util.List;

import edu.nus.microservice.event_manager.model.Event;
import edu.nus.microservice.event_manager.model.EventReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface EventReviewRepository extends JpaRepository<EventReview, UUID> {



    @Query(
            value = "select event_review.* from event_review where review_id=?1",
            nativeQuery = true
    )
    EventReview QueryEventReviewById(UUID review_id);

}
