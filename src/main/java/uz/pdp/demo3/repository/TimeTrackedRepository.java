package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.demo3.entity.TimeTracked;

@RepositoryRestResource(path = "/timeTracked", collectionResourceRel = "list")
public interface TimeTrackedRepository extends JpaRepository<TimeTracked, Long> {
}
