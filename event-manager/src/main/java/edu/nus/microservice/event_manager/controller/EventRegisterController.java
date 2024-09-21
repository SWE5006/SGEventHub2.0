package edu.nus.microservice.event_manager.controller;

import edu.nus.microservice.event_manager.dto.EventRegisterResponse;

import edu.nus.microservice.event_manager.service.EventRegisterationService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/event-manager/register")
@RequiredArgsConstructor
public class EventRegisterController {

    private final EventRegisterationService registrationService;

    @GetMapping(path="/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EventRegisterResponse> getAllEventRegister() {
        return registrationService.getAllRegistration();
    }
}
