package berdnaski.github.io.rolessecurtiy.repository;

import berdnaski.github.io.rolessecurtiy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
