package uz.pdp.demo3.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class CheckListItemDTO {
    private Long id;

    private String name;

    private Long checkListId;

    private boolean resolved;

    private UUID assignedUserId;

}
