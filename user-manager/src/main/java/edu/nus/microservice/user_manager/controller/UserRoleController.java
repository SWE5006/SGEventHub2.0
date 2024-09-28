package edu.nus.microservice.user_manager.controller;


import edu.nus.microservice.user_manager.dto.UserRoleRequest;
import edu.nus.microservice.user_manager.service.EventRoleService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-manager/role")
@RequiredArgsConstructor
public class UserRoleController {

    private final EventRoleService eventRoleService;

    @PostMapping(path="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEventRole(@RequestBody UserRoleRequest userRoleRequest) {
        eventRoleService.createUserRole(userRoleRequest);
    }
}
