package pl.barwinscy.Akbarapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.barwinscy.Akbarapp.entities.School;

import java.util.Set;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long>, SearchQueryRepository {
    @Query(value = "SELECT s.address.county FROM School as s")
    Set<String> findAllCounties();

}
