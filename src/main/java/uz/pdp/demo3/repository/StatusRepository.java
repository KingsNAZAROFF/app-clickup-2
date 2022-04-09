package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.demo3.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}
