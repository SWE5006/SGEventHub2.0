
package edu.nus.microservice.event_manager.repository;
import java.util.Date;
import java.util.List;


import edu.nus.microservice.event_manager.model.EventReview;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Transactional
public interface EventReviewRepository extends CrudRepository<EventReview, UUID> {
    @Modifying
    @Query(
            value = "select event_review.* from event_review where review_id=?1",
            nativeQuery = true
    )
    EventReview QueryEventReviewById(UUID review_id);

    @Query(
            value = "select event_review.* from event_review where event_id=?1",
            nativeQuery = true
    )
    List<EventReview> QueryEventReviewByEventId(UUID event_id);

}
