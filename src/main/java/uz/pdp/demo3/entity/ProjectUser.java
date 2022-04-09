package uz.pdp.demo3.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.demo3.entity.enums.TaskPermission;
import uz.pdp.demo3.entity.template.AbsLongEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProjectUser extends AbsLongEntity {
    @ManyToOne
    private Project project;

    @ManyToOne
    private User user;


    @Enumerated(EnumType.STRING)
    private TaskPermission taskPermission;
}
