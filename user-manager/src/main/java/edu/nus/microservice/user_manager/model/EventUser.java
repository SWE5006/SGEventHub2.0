package edu.nus.microservice.user_manager.model;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EventUser {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int UserId;
	private String UserName;
	private String Password;
	private String EmailAddress;
	private int ActiveStatus;
	private int RoleId;
	private Date CreateDt;

}
