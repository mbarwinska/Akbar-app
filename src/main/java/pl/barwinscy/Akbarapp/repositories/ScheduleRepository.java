package pl.barwinscy.Akbarapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barwinscy.Akbarapp.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
