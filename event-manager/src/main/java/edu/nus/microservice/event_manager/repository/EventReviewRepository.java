
package edu.nus.microservice.event_manager.repository;
import java.util.List;

import edu.nus.microservice.event_manager.model.EventReview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EventReviewRepository extends JpaRepository<EventReview, UUID> {

    List<EventReview> findByEventId(UUID eventId);
}
