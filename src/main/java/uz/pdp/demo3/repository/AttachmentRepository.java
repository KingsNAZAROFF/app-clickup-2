package uz.pdp.demo3.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo3.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
