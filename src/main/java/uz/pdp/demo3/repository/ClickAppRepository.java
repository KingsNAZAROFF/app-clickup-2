package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.demo3.entity.ClickApp;

@RepositoryRestResource(path = "/clickApp",collectionResourceRel = "list")
public interface ClickAppRepository extends JpaRepository<ClickApp, Long> {
}
