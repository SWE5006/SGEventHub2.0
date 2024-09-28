package edu.nus.microservice.user_manager.controller;

import edu.nus.microservice.user_manager.dto.EventUserRequest;
import edu.nus.microservice.user_manager.dto.EventUserResponse;
import edu.nus.microservice.user_manager.dto.UserDetailResponse;
import edu.nus.microservice.user_manager.service.EventUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-manager/user")
@RequiredArgsConstructor
public class EventUserController {

    private final EventUserService eventUserService;


    @PostMapping (path="/signup")
    @ResponseStatus(value=HttpStatus.CREATED,reason = "Successfully Created")
    public EventUserResponse createEventUser(@RequestBody EventUserRequest eventUserRequest) {

        boolean found = eventUserService.CheckUserExist(eventUserRequest.getEmailAddress());
        if (found) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email address is already registered."
            );
        }

        eventUserService.createEventUser(eventUserRequest);
        return eventUserService.createEventUser(eventUserRequest);
    }

    @PostMapping(path="/chpassword")
    @ResponseStatus(value = HttpStatus.OK)
    public String ChangePassword(@RequestParam String EmailAddress,
                                             @RequestParam String Password) {
        boolean updated = eventUserService.ChangePassword(EmailAddress,Password);
        if (updated)
        {
            return "Updated";
        }
        else {throw new ResponseStatusException(
                HttpStatus.NOT_MODIFIED,
                "Update Error"
        );}
    }


    @PostMapping(path="/update")
    @ResponseStatus(value = HttpStatus.OK, reason = "Password Successfully Updated")
    public EventUserResponse UpdateEventUser(@RequestBody EventUserRequest eventUserRequest) {
        try {
            return eventUserService.updateEventUser(eventUserRequest.getUserId()
                    , eventUserRequest.getPassword(), eventUserRequest.getUserName(),
                    eventUserRequest.getEmailAddress(), eventUserRequest.getRoleId());
        }
        catch(Exception exception)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_MODIFIED,
                    "Update Error:" + exception.getMessage()
            );
        }
    }

    @GetMapping (path="/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EventUserResponse> getAllEventUsers() {
        return eventUserService.getAllEventUsers();
    }

    @GetMapping (path="/allwrole")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDetailResponse> getAllEventUsersWithRole() {
        return eventUserService.getAllEventUsersRole();
    }

    @DeleteMapping(path="/delete/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEventUser(@PathVariable("id") int userId)throws ResourceNotFoundException {
       eventUserService.deleteEventUser(userId);
    }

    @GetMapping(path="/search/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public UserDetailResponse searchEventUser(@PathVariable("id") UUID userId) throws ResourceNotFoundException{

            return eventUserService.getEventUserById(userId);

    }

    @GetMapping(path="/login")
    @ResponseStatus(HttpStatus.OK)
    public UserDetailResponse LoginUser(@PathVariable("email") String EmailAddress,
                                        @PathVariable("password") String Password)
            throws ResourceNotFoundException{

        return eventUserService.CheckUserLogin(EmailAddress,Password);

    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource Not Found")
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }



}
