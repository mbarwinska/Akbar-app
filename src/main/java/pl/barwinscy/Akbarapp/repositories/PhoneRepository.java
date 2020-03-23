package pl.barwinscy.Akbarapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barwinscy.Akbarapp.entities.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
