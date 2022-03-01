package github.com.Alisson98.myfinances.core.repository;

import github.com.Alisson98.myfinances.core.entities.Release;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseRepository extends JpaRepository<Release, Long> {
}
