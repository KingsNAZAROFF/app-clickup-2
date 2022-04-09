package uz.pdp.demo3.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.demo3.entity.template.AbsLongEntity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbsLongEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Project project;

    @Column(nullable = false)
    private String accessType;

    @Column(nullable = false)
    private boolean archived;


    @Column(nullable = false)
    private String color;

}
