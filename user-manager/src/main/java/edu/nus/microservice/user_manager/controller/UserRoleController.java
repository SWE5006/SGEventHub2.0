package edu.nus.microservice.user_manager.controller;

import edu.nus.microservice.user_manager.dto.RolePermissionRequest;
import edu.nus.microservice.user_manager.dto.UserRoleRequest;
import edu.nus.microservice.user_manager.service.EventRoleService;
import edu.nus.microservice.user_manager.service.RolePermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserRoleController {

    private EventRoleService eventRoleService;

    @PostMapping(path="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEventRole(@RequestBody UserRoleRequest userRoleRequest) {
        eventRoleService.createUserRole(userRoleRequest);
    }
}
