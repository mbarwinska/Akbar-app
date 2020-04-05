package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

import java.util.List;

@Service
public class SearchService {

    private SchoolRepository schoolRepository;

    public SearchService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> getSearchedSchools(String schoolQuery) {
        return schoolRepository.searchByQuery(schoolQuery);
    }
}
