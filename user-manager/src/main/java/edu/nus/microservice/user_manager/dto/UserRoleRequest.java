package edu.nus.microservice.user_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleRequest {
    private UUID RoleId;
    private String RoleName;
    private String Permission;
}