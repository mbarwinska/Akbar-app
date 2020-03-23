package pl.barwinscy.Akbarapp.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.barwinscy.Akbarapp.entities.School;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findByNameContains(String name);
}
