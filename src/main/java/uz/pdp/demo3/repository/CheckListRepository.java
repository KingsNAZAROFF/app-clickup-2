package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.demo3.entity.CheckList;

import java.util.UUID;

@Repository
public interface CheckListRepository extends JpaRepository<CheckList, Long> {
    boolean existsByNameAndTaskId(String name, UUID task_id);
    boolean existsByNameAndTaskIdNot(String name, UUID task_id);
}
