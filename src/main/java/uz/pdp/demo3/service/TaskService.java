package uz.pdp.demo3.service;




import uz.pdp.demo3.entity.User;
import uz.pdp.demo3.payload.ApiResponse;
import uz.pdp.demo3.payload.TaskAttachmentDTO;
import uz.pdp.demo3.payload.TaskDTO;
import uz.pdp.demo3.payload.TaskUserDTO;

import java.util.UUID;

public interface TaskService {
    ApiResponse addOrEditTask(TaskDTO taskDTO, User user);

    ApiResponse deleteTask(UUID taskId, User user);

    ApiResponse changeStatus(Long statusId, UUID taskId, User user, Long wId);

    ApiResponse addOrDeleteTaskAttachment(TaskAttachmentDTO taskAttachmentDTO, User user);

    ApiResponse addOrDeleteTaskUser(TaskUserDTO taskUserDTO, User user);

}
