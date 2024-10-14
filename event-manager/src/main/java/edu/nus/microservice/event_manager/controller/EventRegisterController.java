package edu.nus.microservice.event_manager.controller;

import edu.nus.microservice.event_manager.dto.EventRegisterResponse;

import edu.nus.microservice.event_manager.service.EventRegisterationService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/event-manager/eventregister")
@RequiredArgsConstructor
public class EventRegisterController {

    private final EventRegisterationService registrationService;
    private final Logger log = LoggerFactory.getLogger(EventRegisterController.class);
    @GetMapping(path="/all")
    public List<EventRegisterResponse> getAllEventRegister() {
        log.info("Getting All the Event Registration");
        return registrationService.getAllRegistration();
    }


    @GetMapping(path = "/register/{eventId}/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public EventRegisterResponse RegisterEvent(
            @PathVariable("eventId") UUID eventId,
            @PathVariable("userId") UUID userId
    ) {
        log.info("Getting event register information:{}|{}", eventId, userId);
    return registrationService.registerEvent(userId,eventId);

    }

    @GetMapping(path = "/unregister/{eventId}/{userId}")
    public EventRegisterResponse UnRegisterEvent(
            @PathVariable("eventId") UUID eventId,
            @PathVariable("userId") UUID userId
    ) {
        log.info("Getting event un register information:{}|{}", eventId, userId);
        return registrationService.unregisterEvent(userId,eventId);

    }



}
