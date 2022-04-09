package uz.pdp.demo3.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class CheckListDTO {
    private Long id;

    private String name;

    private UUID taskId;

}
