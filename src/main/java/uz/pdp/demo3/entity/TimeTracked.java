package uz.pdp.demo3.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.demo3.entity.template.AbsLongEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TimeTracked extends AbsLongEntity {
    @ManyToOne
    private  Task task;

    @Column(nullable = false)
    private Date startedDate;

    @Column(nullable = false)
    private Date stoppedDate;

}
