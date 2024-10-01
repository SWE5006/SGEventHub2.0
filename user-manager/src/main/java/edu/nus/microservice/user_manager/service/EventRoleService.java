package edu.nus.microservice.user_manager.service;


import edu.nus.microservice.user_manager.dto.RolePermissionRequest;
import edu.nus.microservice.user_manager.dto.RolePermissionResponse;
import edu.nus.microservice.user_manager.dto.UserRoleRequest;
import edu.nus.microservice.user_manager.dto.UserRoleResponse;

import edu.nus.microservice.user_manager.model.RolePermission;
import edu.nus.microservice.user_manager.model.UserRole;
import edu.nus.microservice.user_manager.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class EventRoleService {
    private final RoleRepository roleRepository;

    public void createUserRole(UserRoleRequest userRoleRequest) {
        UserRole userRole = UserRole.builder()
                .RoleId(userRoleRequest.getRoleId())
                .RoleName(userRoleRequest.getRoleName())
                .Permission(userRoleRequest.getPermission())
                .build();
        roleRepository.save(userRole);

        new UserRoleResponse(userRole.getRoleId(),userRole.getRoleName(),userRole.getPermission());
    }

    public List<UserRoleResponse> getAllUserRoles() {
        List<UserRole> userRoleList = (List<UserRole>) roleRepository.findAll();

        return userRoleList.stream().map(this::maptoEventRole).toList();
    }


    private UserRoleResponse maptoEventRole(UserRole userRole) {
        return UserRoleResponse.builder()
                .RoleId (userRole.getRoleId())
                .RoleName(userRole.getRoleName())
                .Permission(userRole.getPermission())
                .build();
    }


}
