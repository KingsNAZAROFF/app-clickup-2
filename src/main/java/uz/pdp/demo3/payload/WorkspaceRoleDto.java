package uz.pdp.demo3.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.demo3.entity.enums.WorkspacePermissionName;
import uz.pdp.demo3.entity.enums.WorkspaceRoleName;


import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceRoleDto {

    private UUID id;

    private String name;

    private WorkspaceRoleName extendsRole;

    private WorkspacePermissionName permissionName;

}
