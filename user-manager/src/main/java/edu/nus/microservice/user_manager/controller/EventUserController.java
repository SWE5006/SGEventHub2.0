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


    @PostMapping (path="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEventUser(@RequestBody EventUserRequest eventUserRequest) {
        eventUserService.createEventUser(eventUserRequest);
    }

    @PostMapping(path="/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateEventUser(@RequestParam String UserId,@RequestParam String Password,
                                @RequestParam String UserName,@RequestParam String EmailAddress,
                                @RequestParam int UserRole) {
        eventUserService.updateEventUser(UserId,Password, UserName, EmailAddress,UserRole);
    }

    @GetMapping (path="/all")

    @ResponseStatus(HttpStatus.OK)
    public List<EventUserResponse> getAllEventUsers() {
        return eventUserService.getAllEventUsers();
    }


}
