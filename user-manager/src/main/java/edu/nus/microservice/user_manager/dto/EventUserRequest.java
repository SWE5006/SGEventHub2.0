package edu.nus.microservice.user_manager.dto;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventUserRequest
{
    private UUID UserId;
    private String UserName;
    private String Password;
    private String EmailAddress;
    private int ActiveStatus;
    private int RoleId;
    private Date CreateDt;

}
