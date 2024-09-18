package edu.nus.microservice.user_manager.service;


import edu.nus.microservice.user_manager.repository.UserRepository;
import edu.nus.microservice.user_manager.model.EventUser;
import edu.nus.microservice.user_manager.dto.EventUserRequest;
import edu.nus.microservice.user_manager.dto.EventUserResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventUserService {

    private final UserRepository userRepository;

    public EventUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createEventUser(EventUserRequest eventUserRequest) {
        EventUser eventUser = EventUser.builder()
                .UserId(eventUserRequest.getUserId())
                .UserName(eventUserRequest.getUserName())
                .EmailAddress(eventUserRequest.getEmailAddress())
                .CreateDt(eventUserRequest.getCreateDt())
                .Password(eventUserRequest.getPassword())
                .ActiveStatus(eventUserRequest.getActiveStatus())
                .RoleId(eventUserRequest.getRoleId())
                .build();
        userRepository.save(eventUser);

        new EventUserResponse(eventUser.getUserId(), eventUser.getUserName(), eventUser.getPassword()
                , eventUser.getEmailAddress(), eventUser.getActiveStatus(),
                eventUser.getRoleId(), eventUser.getCreateDt());
    }

    public void updateEventUser(String UserId,String Password, String UserName, String EmailAddress, int UserRole) {

        userRepository.UpdateUser(UserId,Password, UserName, EmailAddress,UserRole);

        new EventUserResponse(UserId, UserName, Password
                , EmailAddress, 1,
                UserRole, new Date());
    }

    public List<EventUserResponse> getAllEventUsers() {
        List<EventUser> eventUserlist = (List<EventUser>) userRepository.findAll();

        return eventUserlist.stream().map(this::maptoEventUserResponse).toList();
    }

    private EventUserResponse maptoEventUserResponse(EventUser eventUser) {
        return EventUserResponse.builder()
                .UserId(eventUser.getUserId())
                .UserName(eventUser.getUserName())
                .EmailAddress(eventUser.getEmailAddress())
                .ActiveStatus(eventUser.getActiveStatus())
                .CreateDt(eventUser.getCreateDt())
                .build();
    }


}
