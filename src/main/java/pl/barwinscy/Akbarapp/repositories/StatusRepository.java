package pl.barwinscy.Akbarapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.entities.Status;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {
    List<Status> findBySchool(School school);
}
