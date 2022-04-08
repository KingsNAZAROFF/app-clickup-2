package uz.pdp.demo3.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo3.entity.WorkspacePermission;
import uz.pdp.demo3.entity.enums.WorkspacePermissionName;
import uz.pdp.demo3.entity.enums.WorkspaceRoleName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {

    Optional<WorkspacePermission> findByWorkspaceRoleIdAndPermission(UUID workspaceRole_id, WorkspacePermissionName permission);
    List<WorkspacePermission> findAllByWorkspaceRole_ExtendsRoleAndWorkspaceRole_Workspace_id(WorkspaceRoleName workspaceRole_extendsRole, Long workspaceRole_workspace_id);

}
