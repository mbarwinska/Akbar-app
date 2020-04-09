package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

import java.util.List;
import java.util.Set;

@Service
public class SearchServiceImpl implements SearchService {

    private SchoolRepository schoolRepository;

    public SearchServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<School> getSearchedSchools(String schoolQuery) {
        return schoolRepository.searchByQuery(schoolQuery);
    }

    @Override
    public Set<String> getAllCounties() {
        return schoolRepository.findAllCounties();
    }
}
