package edu.nus.microservice.user_manager.controller;


import edu.nus.microservice.user_manager.dto.RolePermissionRequest;
import edu.nus.microservice.user_manager.dto.RolePermissionResponse;

import edu.nus.microservice.user_manager.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-manager/permission")
@RequiredArgsConstructor
public class RolePermissionController {
    private final RolePermissionService rolePermissionService;

    @PostMapping(path="/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPermission(@RequestBody RolePermissionRequest rolePermissionRequest) {
        rolePermissionService.createRolePermission(rolePermissionRequest);
    }


    @GetMapping(path="/all")
    @ResponseStatus(HttpStatus.OK)
    public List<RolePermissionResponse> getAllPermission() {
        return rolePermissionService.getAllRolePermission();
    }
}
