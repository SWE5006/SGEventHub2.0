package edu.nus.microservice.user_manager.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EventUserResponse {
    private UUID UserId;
    private String UserName;
    private String EmailAddress;
    private int ActiveStatus;
    private int RoleId;
    private Date CreateDt;

    public EventUserResponse(UUID UserId, String userName, String password, String emailAddress, int i, int RoleId, Date date) {
    }
}
