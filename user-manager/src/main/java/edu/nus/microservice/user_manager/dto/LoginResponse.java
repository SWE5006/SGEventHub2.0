package edu.nus.microservice.user_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private UUID UserId;
    private String UserName;
    private String EmailAddress;
    private int ActiveStatus;
    private int RoleId;
    private Date CreateDt;

}
