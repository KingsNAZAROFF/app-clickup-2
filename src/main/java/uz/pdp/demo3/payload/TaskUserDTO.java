package uz.pdp.demo3.payload;

import lombok.Data;
import uz.pdp.demo3.entity.enums.AddType;

import java.util.UUID;

@Data
public class TaskUserDTO {
    private UUID id;

    private UUID taskId;

    private UUID userId;

    private AddType addType;

    private Long workspaceId;

}
