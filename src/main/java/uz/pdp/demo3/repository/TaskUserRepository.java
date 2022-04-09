package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.demo3.entity.TaskUser;

import java.util.UUID;

@RepositoryRestResource(path = "/taskUser", collectionResourceRel = "list")
public interface TaskUserRepository extends JpaRepository<TaskUser, UUID> {
}
