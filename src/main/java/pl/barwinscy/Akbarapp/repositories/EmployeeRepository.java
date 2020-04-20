package pl.barwinscy.Akbarapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barwinscy.Akbarapp.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
