package github.com.Alisson98.myfinances.core.repository;

import github.com.Alisson98.myfinances.core.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseRepository extends JpaRepository<Entry, Long> {
}
