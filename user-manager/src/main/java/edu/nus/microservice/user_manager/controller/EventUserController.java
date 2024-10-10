package edu.nus.microservice.user_manager.controller;

import edu.nus.microservice.user_manager.dto.*;
import edu.nus.microservice.user_manager.model.EventUser;
import edu.nus.microservice.user_manager.service.EventUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import edu.nus.microservice.user_manager.config.WebConfig;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class EventUserController {

    private final EventUserService eventUserService;


    @PostMapping (path="/signup")
    public EventUserResponse createEventUser(@RequestBody EventUserRequest eventUserRequest) {

        // 检查用户是否已经存在
        boolean found = eventUserService.CheckUserExist(eventUserRequest.getEmailAddress());
        if (found) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email address is already registered."
            );
        }


        // 调用 service 层创建用户
        return eventUserService.createEventUser(eventUserRequest);

    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody EventUserRequest eventUserRequest) {

        // 检查邮箱是否已注册
        boolean found = eventUserService.CheckUserExist(eventUserRequest.getEmailAddress());

        if (found){
            throw new ResponseStatusException(
                    HttpStatus.NOT_MODIFIED,
                    "User Already Exist"
            );}
        else eventUserService.createEventUser(eventUserRequest);

    }



    @PostMapping(path="/chpassword")
    public String ChangePassword(@RequestParam String EmailAddress,
                                             @RequestParam String Password) {
        boolean updated = eventUserService.ChangePassword(EmailAddress,Password);
        if (updated)
        {
            return "Password Changed";
        }
        else {throw new ResponseStatusException(
                HttpStatus.NOT_MODIFIED,
                "Update Error"
        );}
    }


    @PostMapping(path="/update")
    @ResponseStatus(HttpStatus.OK)
    public void UpdateEventUser(@RequestBody EventUserRequest eventUserRequest) {
        try {
             eventUserService.updateEventUser(eventUserRequest.getUserId()
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
    public List<EventUserResponse> getAllEventUsers() {
        return eventUserService.getAllEventUsers();
    }

    @GetMapping (path="/allwrole")
    public List<UserDetailResponse> getAllEventUsersWithRole() {
        return eventUserService.getAllEventUsersRole();
    }

    @DeleteMapping(path="/delete/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEventUser(@PathVariable("userId") UUID userId)throws ResourceNotFoundException {
       eventUserService.deleteEventUser(userId);
    }

    @GetMapping(path="/search/{userId}")
    public UserDetailResponse searchEventUser(@PathVariable("userId") UUID userId) throws ResourceNotFoundException{

            return eventUserService.getEventUserById(userId);

    }

    @PostMapping(path = "/login")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) throws ResourceNotFoundException {
        LoginResponse eventUser = eventUserService.CheckUserLogin(loginRequest.getEmailAddress(), loginRequest.getPassword());

        // 返回不包含密码的 LoginResponse
        return new LoginResponse(
                eventUser.getUserId(),
                eventUser.getUserName(),
                eventUser.getEmailAddress(),
                eventUser.getActiveStatus(),
                eventUser.getRoleId(),
                eventUser.getCreateDt()
        );
    }


    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource Not Found")
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }



}
