package edu.nus.microservice.event_manager.controller;

import edu.nus.microservice.event_manager.dto.EventRegisterResponse;

import edu.nus.microservice.event_manager.service.EventRegisterationService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/event-manager/register")
@RequiredArgsConstructor
public class EventRegisterController {

    private final EventRegisterationService registrationService;

    @GetMapping(path="/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EventRegisterResponse> getAllEventRegister() {
        return registrationService.getAllRegistration();
    }


    @GetMapping(path = "/register/{eventid}/{userid}")
    @ResponseStatus(HttpStatus.CREATED)
    public EventRegisterResponse RegisterEvent(
            @PathVariable("eventid") UUID eventid,
            @PathVariable("userid") UUID userid
    ) {

    return registrationService.registerEvent(userid,eventid);

    }

    @GetMapping(path = "/unregister/{eventid}/{userid}")
    @ResponseStatus(HttpStatus.CREATED)
    public EventRegisterResponse UnRegisterEvent(
            @PathVariable("eventid") UUID eventid,
            @PathVariable("userid") UUID userid
    ) {

        return registrationService.unregisterEvent(userid,eventid);

    }



}
