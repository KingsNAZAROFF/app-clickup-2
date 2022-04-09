package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.demo3.entity.SpaceClickApp;

@RepositoryRestResource(path = "/spaceClickApp",collectionResourceRel = "list")
public interface SpaceClickAppRepository extends JpaRepository<SpaceClickApp, Long> {
}
