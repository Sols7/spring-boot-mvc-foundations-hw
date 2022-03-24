package ru.itsjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsjava.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
}
