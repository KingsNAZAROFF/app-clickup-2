package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.demo3.entity.SpaceUser;

import java.util.UUID;

@RepositoryRestResource(path = "/spaceUser", collectionResourceRel = "list")
public interface SpaceUserRepository extends JpaRepository<SpaceUser, UUID> {


    boolean existsBySpaceIdAndMemberId(Long space_id, UUID member_id);
}
