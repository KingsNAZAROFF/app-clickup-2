package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.demo3.entity.TaskTag;

@RepositoryRestResource(path = "/taskTag", collectionResourceRel = "list")
public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {
}
