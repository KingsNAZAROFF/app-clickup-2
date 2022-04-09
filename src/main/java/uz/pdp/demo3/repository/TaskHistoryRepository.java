package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.demo3.entity.TaskHistory;

import java.util.UUID;

@RepositoryRestResource(path = "/taskHistory", collectionResourceRel = "list")
public interface TaskHistoryRepository extends JpaRepository<TaskHistory, UUID> {
}
