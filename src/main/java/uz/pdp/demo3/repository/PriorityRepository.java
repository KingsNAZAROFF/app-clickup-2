package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.demo3.entity.Priority;

@RepositoryRestResource(path = "/priority", collectionResourceRel = "list")
public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
