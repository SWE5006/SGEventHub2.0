package edu.nus.microservice.event_manager.service;
import edu.nus.microservice.event_manager.dto.EventRegisterResponse;
import edu.nus.microservice.event_manager.model.EventRegistration;
import edu.nus.microservice.event_manager.repository.EventRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
                .eventId(eventRegistration.getEventId())
                .registerDt(eventRegistration.getRegisterDt())
                .userId(eventRegistration.getUserId())
                .build();
    }

    public EventRegisterResponse registerEvent(String userId,String eventId)
    {
        EventRegistration EReg = new EventRegistration();
        EReg.setRegisterId(UUID.randomUUID());
        EReg.setEventId(UUID.fromString(eventId));
        EReg.setRegisterDt(new Date());
        EReg.setRegisterStatus("Registered");
        EReg.setUserId(UUID.fromString(userId));
        eventRegisterRepository.save(EReg);

        return maptoRegisterResponse(EReg);

    }


    public EventRegisterResponse unregisterEvent(UUID eventId, UUID userId)
    {
        eventRegisterRepository.deleteByIds(eventId,userId);

        return new EventRegisterResponse();
    }

}
