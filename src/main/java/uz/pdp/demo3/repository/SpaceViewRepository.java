package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.demo3.entity.SpaceView;

import java.util.List;

@RepositoryRestResource(path = "/spaceView",collectionResourceRel = "list")
public interface SpaceViewRepository extends JpaRepository<SpaceView, Long> {
    List<SpaceView> findAllBySpaceId(Long space_id);
}
