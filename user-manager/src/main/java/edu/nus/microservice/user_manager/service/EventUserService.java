package edu.nus.microservice.user_manager.service;


import edu.nus.microservice.user_manager.dto.UserDetailResponse;
import edu.nus.microservice.user_manager.dto.UserRoleResponse;
import edu.nus.microservice.user_manager.model.UserRole;
import edu.nus.microservice.user_manager.repository.RoleRepository;
import edu.nus.microservice.user_manager.repository.UserRepository;
import edu.nus.microservice.user_manager.model.EventUser;
import edu.nus.microservice.user_manager.dto.EventUserRequest;
import edu.nus.microservice.user_manager.dto.EventUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class EventUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public EventUserResponse createEventUser(EventUserRequest eventUserRequest) {
        EventUser eventUser = EventUser.builder()
                .UserId(eventUserRequest.getUserId())
                .UserName(eventUserRequest.getUserName())
                .EmailAddress(eventUserRequest.getEmailAddress())
                .CreateDt(new Date())
                .Password(eventUserRequest.getPassword())
                .ActiveStatus(1)
                .RoleId(1)
                .build();
        userRepository.save(eventUser);
        return new EventUserResponse(eventUser.getUserId(), eventUser.getUserName(), eventUser.getPassword()
                , eventUser.getEmailAddress(), 1,
                1, new Date());
    }

    public EventUserResponse SignUpUser(String UserName,String EmailAddress,String Password) {
        EventUser eventUser = EventUser.builder()
                .UserName(UserName)
                .EmailAddress(EmailAddress)
                .CreateDt(new Date())
                .Password(Password)
                .ActiveStatus(1)
                .RoleId(1)
                .build();
        userRepository.save(eventUser);
        return new EventUserResponse(eventUser.getUserId(), eventUser.getUserName(), eventUser.getPassword()
                , eventUser.getEmailAddress(), 1,
                1, new Date());
    }

    public EventUserResponse AddUser(String UserName,String EmailAddress,String Password,int ActiveStatus,int RoleId) {
        EventUser eventUser = EventUser.builder()
                .UserName(UserName)
                .EmailAddress(EmailAddress)
                .CreateDt(new Date())
                .Password(Password)
                .ActiveStatus(ActiveStatus)
                .RoleId(RoleId)
                .build();
        userRepository.save(eventUser);
        return new EventUserResponse(eventUser.getUserId(), eventUser.getUserName(), eventUser.getPassword()
                , eventUser.getEmailAddress(), 1,
                1, new Date());
    }

    public void deleteEventUser(int userId)
    {
        userRepository.deleteById(userId);

    }

    public EventUserResponse updateEventUser(UUID UserId, String Password, String UserName, String EmailAddress,
                                             int UserRole) {

        userRepository.UpdateUser(UserId,Password, UserName, EmailAddress,UserRole);

        return new EventUserResponse(UserId, UserName, Password
                , EmailAddress, 1,
                UserRole, new Date());


    }

    public boolean ChangePassword(String EmailAddress,
                                             String Password) {

        try {
            userRepository.ChangePassword(Password, EmailAddress);

        }catch (Exception exception)
        {
            return false;
        }
        return true;


    }

    public UserDetailResponse getEventUserById(UUID userId)
    {
        EventUser eventUser = userRepository.SearchEventUser(userId);
        UserRole userRole = roleRepository.SearchUserRole(eventUser.getRoleId());
        return new UserDetailResponse(eventUser.getUserId(), eventUser.getUserName(), eventUser.getPassword()
                , eventUser.getEmailAddress(), eventUser.getActiveStatus(), eventUser.getCreateDt(),
                eventUser.getRoleId(),userRole.getRoleName(),userRole.getPermission());
    }

    public UserDetailResponse CheckUserLogin(String EmailAddress,String Password)
    {
        EventUser eventUser = userRepository.UserLogin(EmailAddress,Password);
        UserRole userRole = roleRepository.SearchUserRole(eventUser.getRoleId());
        return new UserDetailResponse(eventUser.getUserId(), eventUser.getUserName(), eventUser.getPassword()
                , eventUser.getEmailAddress(), eventUser.getActiveStatus(), eventUser.getCreateDt(),
                eventUser.getRoleId(),userRole.getRoleName(),userRole.getPermission());
    }


    public List<EventUserResponse> getAllEventUsers() {
        List<EventUser> eventUserlist = (List<EventUser>) userRepository.findAll();

        return eventUserlist.stream().map(this::maptoEventUserResponse).toList();
    }

    public List<UserDetailResponse> getAllEventUsersRole() {
        List<EventUser> eventUserlist = (List<EventUser>) userRepository.findAll();

        return eventUserlist.stream().map(this::maptoUserDetailResponse).toList();
    }

    private EventUserResponse maptoEventUserResponse(EventUser eventUser) {


        return EventUserResponse.builder()
                .UserId(eventUser.getUserId())
                .UserName(eventUser.getUserName())
                .EmailAddress(eventUser.getEmailAddress())
                .ActiveStatus(eventUser.getActiveStatus())
                .RoleId(eventUser.getRoleId())
                .CreateDt(eventUser.getCreateDt())
                .build();
    }

    private UserDetailResponse maptoUserDetailResponse(EventUser eventUser) {
        UserRole userRoleResponse = roleRepository.SearchUserRole(eventUser.getRoleId());
        return UserDetailResponse.builder()
                .UserId(eventUser.getUserId())
                .UserName(eventUser.getUserName())
                .EmailAddress(eventUser.getEmailAddress())
                .ActiveStatus(eventUser.getActiveStatus())
                .RoleId(eventUser.getRoleId())
                .RoleName(userRoleResponse.getRoleName())
                .Permission(userRoleResponse.getPermission())
                .CreateDt(eventUser.getCreateDt())
                .build();
    }

    public boolean CheckUserExist(String EmailAddress) {
        boolean found = false;
        EventUser evuser = null;
        try {
            evuser = userRepository.SearchEventbyEmail(EmailAddress);
            if (evuser != null) {
                found = true;
            }
        } catch (NullPointerException ex) {
            found = false;
        }
        return found;
    }

}
