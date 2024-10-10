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
    public ResponseEntity<EventUserResponse> addUser(@RequestBody EventUserRequest eventUserRequest) {

        // 检查邮箱是否已注册
        boolean found = eventUserService.CheckUserExist(eventUserRequest.getEmailAddress());
        if (found) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null); // 返回空或一个错误响应
        }

        // 添加用户并返回 EventUserResponse
        EventUserResponse eventUserResponse = eventUserService.addUser(
                eventUserRequest.getUserName(),
                eventUserRequest.getEmailAddress(),
                eventUserRequest.getPassword(),
                eventUserRequest.getActiveStatus(),
                eventUserRequest.getRoleId()
        );

        // 返回 ResponseEntity，包含 HTTP 状态和用户信息（不包含密码）
        return new ResponseEntity<>(eventUserResponse, HttpStatus.CREATED);
    }



    @PostMapping(path="/chpassword")
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
    public List<EventUserResponse> getAllEventUsers() {
        return eventUserService.getAllEventUsers();
    }

    @GetMapping (path="/allwrole")
    public List<UserDetailResponse> getAllEventUsersWithRole() {
        return eventUserService.getAllEventUsersRole();
    }

    @DeleteMapping(path="/delete/{userId}")
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
