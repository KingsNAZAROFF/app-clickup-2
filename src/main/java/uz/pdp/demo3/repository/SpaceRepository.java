package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.demo3.entity.Space;

import java.util.UUID;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
    boolean existsByNameAndOwnerId(String name, UUID owner_id);
}
