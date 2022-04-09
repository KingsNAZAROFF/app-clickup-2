package uz.pdp.demo3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.demo3.entity.enums.StatusType;
import uz.pdp.demo3.entity.template.AbsLongEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Status extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Space space;

    @ManyToOne(optional = false)
    private Project project;

    @ManyToOne(optional = false)
    private Category category;

    @Column(nullable = false)
    private String color;

    private StatusType type;


}
