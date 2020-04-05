package pl.barwinscy.Akbarapp.repositories;

import org.springframework.stereotype.Repository;
import pl.barwinscy.Akbarapp.entities.School;

import java.util.List;

@Repository
public interface SearchQueryRepository {

    List<School> searchByQuery(String query);
}
