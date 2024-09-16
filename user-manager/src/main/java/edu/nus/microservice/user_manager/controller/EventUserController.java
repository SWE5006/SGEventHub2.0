package edu.nus.microservice.user_manager.controller;

import edu.nus.microservice.user_manager.dto.EventUserRequest;
import edu.nus.microservice.user_manager.dto.EventUserResponse;
import edu.nus.microservice.user_manager.service.EventUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-manager")
@RequiredArgsConstructor
public class EventUserController {

    private final EventUserService eventUserService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEventUser(@RequestBody EventUserRequest eventUserRequest) {
        eventUserService.createEventUser(eventUserRequest);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void updateEventUser(@RequestBody String UserId,String Password, String UserName, String EmailAddress, int UserRole) {
        eventUserService.updateEventUser(UserId,Password, UserName, EmailAddress,UserRole);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventUserResponse> getAllEventUsers() {
        return eventUserService.getAllEventUsers();
    }


}
