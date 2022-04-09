package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.demo3.entity.View;

@RepositoryRestResource(path = "/view",collectionResourceRel = "list")
public interface ViewRepository extends JpaRepository<View, Long> {
}
