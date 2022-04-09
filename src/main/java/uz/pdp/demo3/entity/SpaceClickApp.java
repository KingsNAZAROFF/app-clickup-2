package uz.pdp.demo3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.demo3.entity.template.AbsLongEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SpaceClickApp extends AbsLongEntity {
    @ManyToOne(optional = false)
    private Space space;

    @ManyToOne(optional = false)
    private ClickApp clickApp;
}
