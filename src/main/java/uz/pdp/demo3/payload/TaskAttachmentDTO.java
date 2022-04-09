package uz.pdp.demo3.payload;

import lombok.Data;
import uz.pdp.demo3.entity.enums.AddType;

import java.util.UUID;

@Data
public class TaskAttachmentDTO {
    private Long id;

    private UUID taskId;

    private UUID attachmentId;

    private boolean pinCoverImage;

    private AddType addType;

    private Long workspaceId;

}
