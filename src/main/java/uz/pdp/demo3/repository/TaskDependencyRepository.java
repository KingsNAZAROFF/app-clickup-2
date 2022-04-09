package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.demo3.entity.TaskDependency;

@Repository
public interface TaskDependencyRepository extends JpaRepository<TaskDependency, Long> {

}
