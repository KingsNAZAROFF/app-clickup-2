package uz.pdp.demo3.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo3.entity.Workspace;

import java.util.UUID;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    boolean existsByOwnerIdAndName(UUID owner_id, String name);
}
