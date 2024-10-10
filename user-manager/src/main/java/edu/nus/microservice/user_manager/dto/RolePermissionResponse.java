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
public class RolePermissionResponse {
    private UUID PermissionId;
    private String RoleName;
    private String Permissions;
}
