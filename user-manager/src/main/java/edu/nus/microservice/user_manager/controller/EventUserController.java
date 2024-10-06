package edu.nus.microservice.user_manager.controller;

import edu.nus.microservice.user_manager.dto.*;
import edu.nus.microservice.user_manager.model.EventUser;
import edu.nus.microservice.user_manager.service.EventUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user-manager/user")
@RequiredArgsConstructor
public class EventUserController {

    private final EventUserService eventUserService;

    private final Logger log = LoggerFactory.getLogger(EventUserController.class);
    @PostMapping (path="/signup")
    @ResponseStatus(value=HttpStatus.CREATED,reason = "Successfully Created")
    public EventUserResponse createEventUser(@RequestBody EventUserRequest eventUserRequest) {

        // 检查用户是否已经存在
        boolean found = eventUserService.CheckUserExist(eventUserRequest.getEmailAddress());
        if (found) {
            log.info("User is Already Exist{}", eventUserRequest.getEmailAddress());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email address is already registered."
            );
        }


        // 调用 service 层创建用户
        return eventUserService.createEventUser(eventUserRequest);

    }

    @PostMapping(path = "/add")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Successfully Created")
    public ResponseEntity<EventUserResponse> addUser(@RequestBody EventUserRequest eventUserRequest) {

        // 检查邮箱是否已注册
        boolean found = eventUserService.CheckUserExist(eventUserRequest.getEmailAddress());
        if (found) {
            log.info("Event User is Already Exist{}", eventUserRequest.getEmailAddress());
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
    @ResponseStatus(value = HttpStatus.OK)
    public String ChangePassword(@RequestParam String EmailAddress,
                                             @RequestParam String Password) {
        boolean updated = eventUserService.ChangePassword(EmailAddress,Password);
        if (updated)
        {
            log.info("Password has been changed for:{}", EmailAddress);
            return "Updated";
        }
        else {
            log.error("Error in updating password for:{}", EmailAddress);
            throw new ResponseStatusException(

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
        log.info("Get List of All the Event User");
        return eventUserService.getAllEventUsers();
    }

    @GetMapping (path="/allwrole")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDetailResponse> getAllEventUsersWithRole() {
        return eventUserService.getAllEventUsersRole();
    }

    @DeleteMapping(path="/delete/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEventUser(@PathVariable("id") UUID userId)throws ResourceNotFoundException {
        log.info("Event User is Deleted:{}", userId);
       eventUserService.deleteEventUser(userId);
    }

    @GetMapping(path="/search/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public UserDetailResponse searchEventUser(@PathVariable("id") UUID userId) throws ResourceNotFoundException{
        log.info("User Found:{}", userId);
            return eventUserService.getEventUserById(userId);

    }

    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.OK)
    public EventUserResponse loginUser(@RequestBody EventUserRequest loginRequest) throws ResourceNotFoundException {
        EventUserResponse eventUser = eventUserService.CheckUserLogin(loginRequest.getEmailAddress(), loginRequest.getPassword());
        log.info("User Login Successfully:{}|{}", loginRequest.getEmailAddress(), loginRequest.getUserName());
        // 返回不包含密码的 LoginResponse
        return new EventUserResponse(
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
