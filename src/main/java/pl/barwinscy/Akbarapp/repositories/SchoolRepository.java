package pl.barwinscy.Akbarapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.barwinscy.Akbarapp.entities.School;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long>, SearchQueryRepository {
    List<School> findByNameContains(String name);

}
