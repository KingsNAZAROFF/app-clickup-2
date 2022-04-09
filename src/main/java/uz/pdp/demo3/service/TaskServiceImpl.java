package uz.pdp.demo3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.demo3.entity.*;
import uz.pdp.demo3.entity.enums.AddType;
import uz.pdp.demo3.entity.enums.WorkspaceRoleName;
import uz.pdp.demo3.payload.ApiResponse;
import uz.pdp.demo3.payload.TaskAttachmentDTO;
import uz.pdp.demo3.payload.TaskDTO;
import uz.pdp.demo3.payload.TaskUserDTO;
import uz.pdp.demo3.repository.*;


import java.util.Optional;
import java.util.UUID;

import static uz.pdp.demo3.utils.CommonUtils.isExistsAuthority;


@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskUserRepository taskUserRepository;
    @Autowired
    TaskAttachmentRepository taskAttachmentRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    PriorityRepository priorityRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;
    @Autowired
    SpaceRepository spaceRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ApiResponse addOrEditTask(TaskDTO taskDTO, User user) {
        checkAuthority(taskDTO.getWorkspaceId(), user.getId());

        Task task = null;
        Status status = statusRepository.findById(taskDTO.getStatusId()).orElseThrow(() -> new ResourceNotFoundException("status id"));
        Category category = categoryRepository.findById(taskDTO.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("category id"));
        Priority priority = priorityRepository.findById(taskDTO.getPriorityId()).orElseThrow(() -> new ResourceNotFoundException("priority id"));
        Task parentTask = taskRepository.findById(taskDTO.getParentTaskId()).orElse(null);

        if (taskDTO.getId() == null) {
            task = new Task(
                    taskDTO.getName(),
                    taskDTO.getDescription(),
                    status,
                    category,
                    priority,
                    parentTask,
                    taskDTO.getStartedDate(),
                    taskDTO.isStartTimeHas(),
                    taskDTO.getDueTime(),
                    taskDTO.isDueTimeHas(),
                    taskDTO.getEstimateDate(),
                    taskDTO.getActivatedDate());
        } else {
            Optional<Task> optionalTask = taskRepository.findById(taskDTO.getId());
            if (optionalTask.isPresent()) {
                task = optionalTask.get();
                task.setName(taskDTO.getName());
                task.setDescription(taskDTO.getDescription());
                task.setStatus(status);
                task.setCategory(category);
                task.setPriority(priority);
                task.setParentTask(parentTask);
                task.setStartedDate(taskDTO.getStartedDate());
                task.setStartTimeHas(taskDTO.isStartTimeHas());
                task.setDueTime(taskDTO.getDueTime());
                task.setDueTimeHas(taskDTO.isDueTimeHas());
                task.setEstimateDate(taskDTO.getEstimateDate());
                task.setActivatedDate(taskDTO.getActivatedDate());
            } else {
                return new ApiResponse("Task id not found", false);
            }
        }
        taskRepository.save(task);
        return new ApiResponse("Task saved", true);
    }

    @Override
    public ApiResponse deleteTask(UUID taskId, User user) {
        try {
            taskRepository.deleteById(taskId);
            return new ApiResponse("Task deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    @Override
    public ApiResponse changeStatus(Long statusId, UUID taskId, User user, Long wId) {
        checkAuthority(wId, user.getId());

        Task editingTask = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("task id"));
        editingTask.setStatus(statusRepository.findById(statusId).orElseThrow(() -> new ResourceNotFoundException("status id")));
        taskRepository.save(editingTask);
        return new ApiResponse("Status is changed", true);
    }

    @Override
    public ApiResponse addOrDeleteTaskAttachment(TaskAttachmentDTO taskAttachmentDTO, User user) {
        checkAuthority(taskAttachmentDTO.getWorkspaceId(), user.getId());

        AddType addType = taskAttachmentDTO.getAddType();
        if (addType.equals(AddType.ADD)) {
            TaskAttachment taskAttachment = new TaskAttachment(
                    taskRepository.findById(taskAttachmentDTO.getTaskId()).orElseThrow(() -> new ResourceNotFoundException("task id")),
                    attachmentRepository.findById(taskAttachmentDTO.getAttachmentId()).orElseThrow(() -> new ResourceNotFoundException("attachment id")),
                    taskAttachmentDTO.isPinCoverImage()
            );
            taskAttachmentRepository.save(taskAttachment);
        } else if (addType.equals(AddType.REMOVE)) {
            try {
                taskAttachmentRepository.findById(taskAttachmentDTO.getId());
            } catch (Exception e) {
                return new ApiResponse("Error", false);
            }
        }
        return new ApiResponse(addType.equals(AddType.ADD) ? "Attachment assigned to task" : "Task Attachment deleted", true);
    }

    @Override
    public ApiResponse addOrDeleteTaskUser(TaskUserDTO taskUserDTO, User user) {
        checkAuthority(taskUserDTO.getWorkspaceId(), user.getId());

        AddType addType = taskUserDTO.getAddType();
        if (addType.equals(AddType.ADD)) {
            TaskUser taskUser = new TaskUser(
                    taskRepository.findById(taskUserDTO.getTaskId()).orElseThrow(() -> new ResourceNotFoundException("task id")),
                    userRepository.findById(taskUserDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user id"))
            );
            taskUserRepository.save(taskUser);
        } else if (addType.equals(AddType.REMOVE)) {
            try {
                taskUserRepository.findById(taskUserDTO.getId());
            } catch (Exception e) {
                return new ApiResponse("Error", false);
            }
        }
        return new ApiResponse(addType.equals(AddType.ADD) ? "User assigned to task" : "Task User deleted", true);

    }

    public ApiResponse checkAuthority(Long wId,UUID uId) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(wId, uId).get();
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }
        return null;
    }


}
