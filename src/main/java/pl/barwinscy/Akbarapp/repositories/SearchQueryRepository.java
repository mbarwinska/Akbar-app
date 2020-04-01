package pl.barwinscy.Akbarapp.repositories;

import pl.barwinscy.Akbarapp.entities.School;

import java.util.List;

public interface SearchQueryRepository {

    List<School> searchByQuery(String query);
}
