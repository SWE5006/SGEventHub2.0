package edu.nus.microservice.user_manager.controller;

import edu.nus.microservice.user_manager.dto.RolePermissionResponse;
import edu.nus.microservice.user_manager.dto.UserRoleRequest;
import edu.nus.microservice.user_manager.dto.UserRoleResponse;
import edu.nus.microservice.user_manager.model.UserRole;
import edu.nus.microservice.user_manager.service.EventRoleService;
import edu.nus.microservice.user_manager.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-manager/eventrole")
@RequiredArgsConstructor
public class UserRoleController {

    private final EventRoleService eventRoleService;

    @PostMapping(path="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEventRole(@RequestBody UserRoleRequest userRoleRequest) {
        eventRoleService.createUserRole(userRoleRequest);
    }

    @Autowired
    private RoleRepository roleRepository;
    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<Object> getAllRoles() {
        // This returns a JSON or XML with the roles
        Iterable<UserRole> roleList = roleRepository.findAll();
        return new ResponseEntity<Object>(roleList, HttpStatus.OK);
    }
}
