package pl.barwinscy.Akbarapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.barwinscy.Akbarapp.entities.Employee;
import pl.barwinscy.Akbarapp.entities.Salesman;

public interface SalesmanRepository extends JpaRepository<Salesman, Long> {

    Salesman findByFirstNameAndLastName(String firstName, String lastName);
}
