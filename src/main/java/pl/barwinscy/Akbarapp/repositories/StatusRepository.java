package pl.barwinscy.Akbarapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barwinscy.Akbarapp.entities.Employee;
import pl.barwinscy.Akbarapp.entities.Status;

public interface StatusRepository  extends JpaRepository<Status, Long> {

    Status findBySchoolId(Long id);
}
