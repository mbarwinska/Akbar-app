package pl.barwinscy.Akbarapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barwinscy.Akbarapp.entities.School;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {
    Optional<School> findByNameContains(String name);
}
