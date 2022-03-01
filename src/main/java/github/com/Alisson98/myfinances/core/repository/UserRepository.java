package github.com.Alisson98.myfinances.core.repository;

import github.com.Alisson98.myfinances.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
