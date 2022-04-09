package uz.pdp.demo3.payload;

import lombok.Data;
import uz.pdp.demo3.entity.enums.TaskPermission;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ProjectUserDTO {
    @NotNull
    private Long projectId;

    @NotNull
    private UUID userId;

    @NotNull
    private TaskPermission taskPermission;

    @NotNull
    private Long workspaceId;


}
