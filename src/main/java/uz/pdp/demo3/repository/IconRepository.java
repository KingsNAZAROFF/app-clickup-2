package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.demo3.entity.Icon;

@RepositoryRestResource(path = "/icon", collectionResourceRel = "list")
public interface IconRepository extends JpaRepository<Icon, Long> {
}
