package edu.nus.microservice.user_manager.service;

import edu.nus.microservice.user_manager.controller.EventUserController;
import edu.nus.microservice.user_manager.dto.*;
import edu.nus.microservice.user_manager.model.UserRole;
import edu.nus.microservice.user_manager.repository.RoleRepository;
import edu.nus.microservice.user_manager.repository.UserRepository;
import edu.nus.microservice.user_manager.model.EventUser;
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
        return saveAndBuildResponse(
                eventUserRequest.getUserName(),
                eventUserRequest.getEmailAddress(),
                eventUserRequest.getPassword(),
                1,  // 默认 ActiveStatus
                1   // 默认 RoleId
        );
    }

    public EventUserResponse addUser(String emailAddress, String userName, String password, int activeStatus, int roleId) {
        return saveAndBuildResponse(emailAddress, userName, password, activeStatus, roleId);
    }

    private EventUserResponse saveAndBuildResponse(String emailAddress, String userName,  String password, int activeStatus, int roleId) {
        // 创建 EventUser 实体对象
        EventUser eventUser = EventUser.builder()
                .UserName(userName)
                .EmailAddress(emailAddress)
                .Password(password)
                .ActiveStatus(activeStatus)
                .RoleId(roleId)
                .CreateDt(new Date())
                .build();

        // 保存到数据库
        userRepository.save(eventUser);

        // 返回不包含密码的 EventUserResponse
        return EventUserResponse.builder()
                .UserId(eventUser.getUserId())
                .UserName(eventUser.getUserName())
                .EmailAddress(eventUser.getEmailAddress())
                .ActiveStatus(eventUser.getActiveStatus())
                .RoleId(eventUser.getRoleId())
                .CreateDt(eventUser.getCreateDt())
                .build();
    }


    public void deleteEventUser(UUID userId)
    {
        userRepository.deleteById(userId);

    }

    public EventUserResponse updateEventUser(UUID UserId, String Password, String UserName, String EmailAddress,
                                             int RoleId) {

        userRepository.UpdateUser(UserId,Password, UserName, EmailAddress, RoleId);

        return new EventUserResponse(UserId, UserName, Password
                , EmailAddress, 1,
                RoleId, new Date());


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

    public UserDetailResponse getEventUserById(UUID userId) throws EventUserController.ResourceNotFoundException {
        // 查找用户
        EventUser eventUser = userRepository.SearchEventUser(userId);
        if (eventUser == null) {
            throw new EventUserController.ResourceNotFoundException("User not found with id: " + userId);
        }

        // 查找角色
        UserRole userRole = roleRepository.SearchUserRole(eventUser.getRoleId());
        if (userRole == null) {

                throw new RuntimeException("Role not found for user with id: " + userId);
            
        }

        // 返回包含用户和角色信息的 UserDetailResponse 对象
        return new UserDetailResponse(
                eventUser.getUserId(),
                eventUser.getUserName(),
                eventUser.getPassword(),
                eventUser.getEmailAddress(),
                eventUser.getActiveStatus(),
                eventUser.getCreateDt(),
                eventUser.getRoleId(),
                userRole.getRoleName(),
                userRole.getPermission()
        );
    }


    public LoginResponse CheckUserLogin(String EmailAddress, String Password) {
        EventUser eventUser = userRepository.UserLogin(EmailAddress, Password);

        // 检查 eventUser 是否为 null
        if (eventUser == null) {

                throw new RuntimeException("User not found or invalid credentials");


        }

        // 返回 LoginResponse，不包含密码等敏感信息
        return new LoginResponse(
                eventUser.getUserId(),
                eventUser.getUserName(),
                eventUser.getEmailAddress(),
                eventUser.getActiveStatus(),
                eventUser.getRoleId(),
                eventUser.getCreateDt()
        );
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