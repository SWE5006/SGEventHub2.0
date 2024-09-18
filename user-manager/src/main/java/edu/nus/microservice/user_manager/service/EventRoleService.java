package edu.nus.microservice.user_manager.service;

import edu.nus.microservice.user_manager.dto.EventUserRequest;
import edu.nus.microservice.user_manager.dto.EventUserResponse;
import edu.nus.microservice.user_manager.dto.UserRoleRequest;
import edu.nus.microservice.user_manager.dto.UserRoleResponse;
import edu.nus.microservice.user_manager.model.EventUser;
import edu.nus.microservice.user_manager.model.UserRole;
import edu.nus.microservice.user_manager.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class EventRoleService {
    private final RoleRepository roleRepository;

    public EventRoleService(RoleRepository roleRepository) {
        this.roleRepository =  roleRepository;
    }

    public void createUserRole(UserRoleRequest userRoleRequest) {
        UserRole userRole = UserRole.builder()
                .RoleId(userRoleRequest.getRoleId())
                .RoleName(userRoleRequest.getRoleName())
                .Permission(userRoleRequest.getPermission())
                .build();
        roleRepository.save(userRole);

        new UserRoleResponse(userRole.getRoleId(),userRole.getRoleName(),userRole.getPermission());
    }
}
