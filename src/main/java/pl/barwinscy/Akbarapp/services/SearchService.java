package pl.barwinscy.Akbarapp.services;

import pl.barwinscy.Akbarapp.entities.School;

import java.util.List;
import java.util.Set;

public interface SearchService {

    List<School> getSearchedSchools(String schoolQuery);

    Set<String> getAllCounties();
}
