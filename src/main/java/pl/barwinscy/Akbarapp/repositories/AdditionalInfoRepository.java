package pl.barwinscy.Akbarapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barwinscy.Akbarapp.entities.AdditionalInfo;

public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long> {
}
