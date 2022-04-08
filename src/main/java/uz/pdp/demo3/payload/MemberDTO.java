package uz.pdp.demo3.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.demo3.entity.enums.AddType;


import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDTO {

    private UUID id;

    private String name;

    private String email;

    private String roleName;

    private Timestamp lastActive;

    private UUID roleId;

    private AddType addType;   //ADD, EDIT, REMOVE

    public MemberDTO(UUID id, String name, String email, String roleName, Timestamp lastActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roleName = roleName;
        this.lastActive = lastActive;
    }
}
