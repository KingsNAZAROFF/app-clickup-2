package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.demo3.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByNameAndSpaceId(String name, Long space_id);
    boolean existsByNameAndSpaceIdAndIdNot(String name, Long space_id, Long id);
}
