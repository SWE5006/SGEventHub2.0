package edu.nus.microservice.user_manager.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolePermission {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int PermissionId;
    private String RoleName;
    private String Permissions;
}
