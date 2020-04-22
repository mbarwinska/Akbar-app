package pl.barwinscy.Akbarapp.services;

import pl.barwinscy.Akbarapp.entities.School;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SearchService {

    List<School> getSearchedSchools(String schoolQuery);

    List<String> getAllCounties();

    Map<LocalDate, List <School>> schoolMapByDate(String calendarQuery);

}
