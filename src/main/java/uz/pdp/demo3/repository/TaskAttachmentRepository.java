package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.demo3.entity.TaskAttachment;

@RepositoryRestResource(path = "/taskAttachment", collectionResourceRel = "list")
public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long> {
}
