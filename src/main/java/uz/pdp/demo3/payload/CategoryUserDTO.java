package uz.pdp.demo3.payload;

import lombok.Data;
import uz.pdp.demo3.entity.enums.TaskPermission;

import java.util.UUID;

@Data
public class CategoryUserDTO {
    private String name;

    private Long categoryId;

    private UUID userId;

    private TaskPermission taskPermission;
}
