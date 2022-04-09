package uz.pdp.demo3.payload;

import lombok.Data;
import uz.pdp.demo3.entity.enums.WorkspacePermissionName;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class WorkspacePermissionDto {
    @NotNull
    private UUID workspaceRoleId;

    @NotNull
    private WorkspacePermissionName permission;

}
