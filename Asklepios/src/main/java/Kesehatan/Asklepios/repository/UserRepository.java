package Kesehatan.Asklepios.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import Kesehatan.Asklepios.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
