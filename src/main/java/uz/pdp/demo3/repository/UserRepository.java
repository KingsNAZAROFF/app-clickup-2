package uz.pdp.demo3.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo3.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
