package uz.pdp.demo3.service;


import uz.pdp.demo3.entity.User;
import uz.pdp.demo3.payload.ApiResponse;
import uz.pdp.demo3.payload.MemberDTO;
import uz.pdp.demo3.payload.WorkspaceDTO;
import uz.pdp.demo3.payload.WorkspaceRoleDto;

import java.util.List;
import java.util.UUID;


public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user);

    ApiResponse editWorkspace(WorkspaceDTO workspaceDTO);

    ApiResponse changeOwnerWorkspace(Long id, UUID ownerId);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse addOrEditOrRemoveMember(Long id, MemberDTO memberDTO);

    ApiResponse joinToWorkspace(Long id, User user);

    List<MemberDTO> getMembersAndGuests(Long id);

    List<WorkspaceDTO> getWorkspaces(User user);

    ApiResponse addPermissionToRole(WorkspaceRoleDto workspaceRoleDto);

    ApiResponse removePermissionFromRole(WorkspaceRoleDto workspaceRoleDto);

    ApiResponse addRole(Long workspaceId, WorkspaceRoleDto workspaceRoleDto, User user);

}
