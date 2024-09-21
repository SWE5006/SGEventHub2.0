package edu.nus.microservice.event_manager.service;
import edu.nus.microservice.event_manager.dto.EventRegisterResponse;
import edu.nus.microservice.event_manager.dto.EventResponse;
import edu.nus.microservice.event_manager.model.Event;
import edu.nus.microservice.event_manager.model.EventRegistration;
import edu.nus.microservice.event_manager.repository.EventRegisterRepository;
import edu.nus.microservice.event_manager.repository.EventReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Transactional
public class EventRegisterationService {

    private final EventRegisterRepository eventRegisterRepository;

    public List<EventRegisterResponse> getAllRegistration() {
        List<EventRegistration> registerlist = (List<EventRegistration>) eventRegisterRepository.findAll();

        return registerlist.stream().map(this::maptoRegisterResponse).toList();
    }

    private EventRegisterResponse maptoRegisterResponse(EventRegistration eventRegistration) {
        return EventRegisterResponse.builder()
                .registerId(eventRegistration.getRegisterId())

                .build();
    }

}
