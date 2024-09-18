package edu.nus.microservice.user_manager.model;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RolePermission {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String PermissionId;
    private String RoleName;
    private String Permissions;
}
