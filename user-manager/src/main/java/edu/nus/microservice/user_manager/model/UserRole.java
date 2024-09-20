package edu.nus.microservice.user_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private UUID RoleId;
	private String RoleName;
	private String Permission;
}
