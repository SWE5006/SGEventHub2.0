package edu.nus.microservice.user_manager.controller;


import edu.nus.microservice.user_manager.dto.RolePermissionResponse;
import edu.nus.microservice.user_manager.dto.UserRoleRequest;
import edu.nus.microservice.user_manager.dto.UserRoleResponse;
import edu.nus.microservice.user_manager.model.UserRole;
import edu.nus.microservice.user_manager.service.EventRoleService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user-manager/role")
@RequiredArgsConstructor
public class UserRoleController {

    private final EventRoleService eventRoleService;
    private final Logger log = LoggerFactory.getLogger(UserRoleController.class);
    @PostMapping(path="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEventRole(@RequestBody UserRoleRequest userRoleRequest) {
        log.info("User Role is created!");
        eventRoleService.createUserRole(userRoleRequest);
    }

    @GetMapping(path="/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserRoleResponse> getAllUserRoles() {

        log.info("List All of User Role");
        return eventRoleService.getAllUserRoles();
    }
}
