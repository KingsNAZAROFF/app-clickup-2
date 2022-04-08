package uz.pdp.demo3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import uz.pdp.demo3.entity.*;
import uz.pdp.demo3.entity.enums.AddType;
import uz.pdp.demo3.entity.enums.WorkspacePermissionName;
import uz.pdp.demo3.entity.enums.WorkspaceRoleName;
import uz.pdp.demo3.payload.ApiResponse;
import uz.pdp.demo3.payload.MemberDTO;
import uz.pdp.demo3.payload.WorkspaceDTO;
import uz.pdp.demo3.payload.WorkspaceRoleDto;
import uz.pdp.demo3.repository.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;
    @Autowired
    WorkspaceRoleRepository workspaceRoleRepository;
    @Autowired
    WorkspacePermissionRepository workspacePermissionRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user) {
        //WORKSPACE OCHDIK
        if (workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDTO.getName()))
            return new ApiResponse("Sizda bunday nomli ishxona mavjud", false);
        Workspace workspace = new Workspace(
                workspaceDTO.getName(),
                workspaceDTO.getColor(),
                user,
                workspaceDTO.getAvatarId() == null ? null : attachmentRepository.findById(workspaceDTO.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("attachment"))
        );
        workspaceRepository.save(workspace);

        //WORKSPACE ROLE OCHDIK
        WorkspaceRole ownerRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_OWNER.name(), null));
        WorkspaceRole adminRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_ADMIN.name(), null));
        WorkspaceRole memberRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_MEMBER.name(), null));
        WorkspaceRole guestRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_GUEST.name(), null));


        //OWNERGA HUQUQLARNI BERYAPAMIZ
        WorkspacePermissionName[] workspacePermissionNames = WorkspacePermissionName.values();
        List<WorkspacePermission> workspacePermissions = new ArrayList<>();

        for (WorkspacePermissionName workspacePermissionName : workspacePermissionNames) {
            WorkspacePermission workspacePermission = new WorkspacePermission(
                    ownerRole,
                    workspacePermissionName);
            workspacePermissions.add(workspacePermission);
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
                workspacePermissions.add(new WorkspacePermission(
                        adminRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_MEMBER)) {
                workspacePermissions.add(new WorkspacePermission(
                        memberRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                workspacePermissions.add(new WorkspacePermission(
                        guestRole,
                        workspacePermissionName));
            }

        }
        workspacePermissionRepository.saveAll(workspacePermissions);

        //WORKSPACE USER OCHDIK
        workspaceUserRepository.save(new WorkspaceUser(
                workspace,
                user,
                ownerRole,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())

        ));
        return new ApiResponse("Ishxona saqlandi", true);
    }

    @Override
    public ApiResponse editWorkspace(WorkspaceDTO workspaceDTO) {
        Workspace workspace = workspaceRepository.findById(workspaceDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("workspace not found"));
        workspace.setName(workspaceDTO.getName());
        workspace.setColor(workspaceDTO.getColor());
        workspace.setAvatar(workspaceDTO.getAvatarId() == null ? null : attachmentRepository.findById(workspaceDTO.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("avatar not found")));
        workspaceRepository.save(workspace);
        return new ApiResponse("Workspace edited",true);
    }

    @Override
    public ApiResponse addRole(Long workspaceId, WorkspaceRoleDto workspaceRoleDto, User user) {
        Workspace workspace = workspaceRepository.findById(workspaceId).orElseThrow(() -> new ResourceNotFoundException("workspace not found"));

        if (workspaceRoleRepository.existsByWorkspaceIdAndName(workspaceId, workspaceRoleDto.getName()))
            return new ApiResponse("The workspace and role together are unique", false);

        WorkspaceRole workspaceRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                workspaceRoleDto.getName(),
                workspaceRoleDto.getExtendsRole()
        ));

        List<WorkspacePermission> workspacePermissions = workspacePermissionRepository.findAllByWorkspaceRole_ExtendsRoleAndWorkspaceRole_Workspace_id(workspaceRole.getExtendsRole(), workspaceId);

        List<WorkspacePermission> workspacePermissionList = new ArrayList<>();
        for (WorkspacePermission workspacePermission : workspacePermissions) {
            WorkspacePermission newWorkspacePermission = new WorkspacePermission(
                    workspaceRole,
                    workspacePermission.getPermission()
            );
            workspacePermissionList.add(newWorkspacePermission);
        }
        workspacePermissionRepository.saveAll(workspacePermissionList);
        return new ApiResponse("Success", true);
    }

    @Override
    public ApiResponse changeOwnerWorkspace(Long id, UUID ownerId) {
        return null;
    }

    @Override
    public ApiResponse deleteWorkspace(Long id) {
        try {
            workspaceRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik", false);
        }
    }

    @Override
    public ApiResponse addOrEditOrRemoveMember(Long id, MemberDTO memberDTO) {
        if (memberDTO.getAddType().equals(AddType.ADD)) {
            WorkspaceUser workspaceUser = new WorkspaceUser(
                    workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id")),
                    userRepository.findById(memberDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    new Timestamp(System.currentTimeMillis()),
                    null
            );
            workspaceUserRepository.save(workspaceUser);

            //TODO EMAILGA INVITE XABAR YUBORISH
        } else if (memberDTO.getAddType().equals(AddType.EDIT)) {
            WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, memberDTO.getId()).orElseGet(WorkspaceUser::new);
            workspaceUser.setWorkspaceRole(workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")));
            workspaceUserRepository.save(workspaceUser);
        } else if (memberDTO.getAddType().equals(AddType.REMOVE)) {
            workspaceUserRepository.deleteByWorkspaceIdAndUserId(id, memberDTO.getId());
        }
        return new ApiResponse("Muvaffaqiyatli", true);
    }

    @Override
    public ApiResponse joinToWorkspace(Long id, User user) {
        Optional<WorkspaceUser> optionalWorkspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, user.getId());
        if (optionalWorkspaceUser.isPresent()) {
            WorkspaceUser workspaceUser = optionalWorkspaceUser.get();
            workspaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
            workspaceUserRepository.save(workspaceUser);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Error", false);
    }


    @Override
    public List<MemberDTO> getMembersAndGuests(Long id) {
        List<WorkspaceUser> workspaceUsers = workspaceUserRepository.findAllByWorkspaceId(id);
        return workspaceUsers.stream().map(this::getAllMembersMethod).collect(Collectors.toList());
    }

    @Override
    public List<WorkspaceDTO> getWorkspaces(User user) {
        List<WorkspaceUser> workspaces = workspaceUserRepository.findAllByUserId(user.getId());
        return workspaces.stream().map(this::getAllWorkspacesMethod).collect(Collectors.toList());
    }

    @Override
    public ApiResponse addPermissionToRole(WorkspaceRoleDto workspaceRoleDto) {
        WorkspaceRole workspaceRole = workspaceRoleRepository.findById(workspaceRoleDto.getId()).orElseThrow(() -> new ResourceNotFoundException("not found"));
        Optional<WorkspacePermission> permission = workspacePermissionRepository.findByWorkspaceRoleIdAndPermission(workspaceRole.getId(), workspaceRoleDto.getPermissionName());
        if (permission.isPresent())
            return new ApiResponse("Permission allaqachon qo`shilgan", false);
        WorkspacePermission workspacePermission = new WorkspacePermission(
                workspaceRole, workspaceRoleDto.getPermissionName()
        );
        workspacePermissionRepository.save(workspacePermission);
        return new ApiResponse("Muvoffaqiyatli qo`shildi", true);
    }

    @Override
    public ApiResponse removePermissionFromRole(WorkspaceRoleDto workspaceRoleDto) {
        WorkspaceRole workspaceRole = workspaceRoleRepository.findById(workspaceRoleDto.getId()).orElseThrow(() -> new ResourceNotFoundException("not found"));
        Optional<WorkspacePermission> permission = workspacePermissionRepository.findByWorkspaceRoleIdAndPermission(workspaceRole.getId(), workspaceRoleDto.getPermissionName());

        if (permission.isPresent()) {
            workspacePermissionRepository.delete(permission.get());
            return new ApiResponse("Muvoffaqiyatli o`chirildi", true);
        } else {
            return new ApiResponse("Bunday permission topilmadi", false);
        }
    }

    //METHODS
    public MemberDTO getAllMembersMethod(WorkspaceUser workspaceUser) {
        return new MemberDTO(
                workspaceUser.getUser().getId(),
                workspaceUser.getUser().getFullName(),
                workspaceUser.getUser().getEmail(),
                workspaceUser.getWorkspaceRole().getName(),
                workspaceUser.getUser().getLastActive()
        );
    }

    public WorkspaceDTO getAllWorkspacesMethod(WorkspaceUser workspaceUser) {
        return new WorkspaceDTO(
                workspaceUser.getWorkspace().getId(),
                workspaceUser.getWorkspace().getName(),
                workspaceUser.getWorkspace().getColor(),
                workspaceUser.getWorkspace().getInitialLetter(),
                workspaceUser.getWorkspace().getAvatar() == null ? null : workspaceUser.getWorkspace().getAvatar().getId()
        );
    }

}
