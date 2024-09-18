package edu.nus.microservice.user_manager.service;



import org.springframework.stereotype.Service;
import edu.nus.microservice.user_manager.repository.PermissionRepository;
import edu.nus.microservice.user_manager.model.RolePermission;
import edu.nus.microservice.user_manager.dto.RolePermissionRequest;
import edu.nus.microservice.user_manager.dto.RolePermissionResponse;



import java.util.List;

@Service
public class RolePermissionService {

    private final PermissionRepository permissionRepository;

    public RolePermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository =  permissionRepository;
    }

    public List<RolePermissionResponse> getAllRolePermission() {
        List<RolePermission> rolePermissionList = (List<RolePermission>) permissionRepository.findAll();

        return rolePermissionList.stream().map(this::maptoRolePermissionResponse).toList();
    }


    private RolePermissionResponse maptoRolePermissionResponse(RolePermission rolePermission) {
        return RolePermissionResponse.builder()
                .PermissionId(rolePermission.getPermissionId())
                .RoleName(rolePermission.getRoleName())
                .Permissions(rolePermission.getPermissions())
                .build();
    }

    public void createRolePermission(RolePermissionRequest rolePermissionRequest) {
        RolePermission rolePermission = RolePermission.builder()
                .PermissionId(rolePermissionRequest.getPermissionId())
                .RoleName(rolePermissionRequest.getRoleName())
                .Permissions(rolePermissionRequest.getPermissions())


                .build();
        permissionRepository.save(rolePermission);

        new RolePermissionResponse(rolePermission.getPermissionId(),rolePermission.getRoleName(),rolePermission.getPermissions());
    }
}
