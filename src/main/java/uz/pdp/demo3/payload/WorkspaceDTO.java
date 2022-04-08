package uz.pdp.demo3.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkspaceDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String color;

    private String initialLetter;

    private UUID avatarId;

}
