package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private SchoolRepository schoolRepository;

    public SearchService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> getSearchedSchools(String schoolQuery) {


        List<School> schoolList = schoolRepository.searchByQuery(schoolQuery);

        for (School school : schoolList) {
            school.getPhones()
                    .stream()
                    .filter(phone -> phone.getNote().equals("publiczny"))
                    .collect(Collectors.toSet());
        }

        return schoolList;
    }

    public Set<String> getAllCounties() {
        return schoolRepository.findAllCounties();
    }
}
