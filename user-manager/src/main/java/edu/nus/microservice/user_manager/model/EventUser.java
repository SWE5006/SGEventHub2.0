package edu.nus.microservice.user_manager.model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Id;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventUser {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private UUID UserId;
	private String UserName;
	private String Password;
	private String EmailAddress;
	private int ActiveStatus;
	private int RoleId;
	private Date CreateDt;

}
