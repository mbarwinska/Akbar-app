package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import pl.barwinscy.Akbarapp.entities.Phone;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

import java.util.List;
import java.util.Set;

@Service
public class SearchService {

    private SchoolRepository schoolRepository;

    public SearchService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> getSearchedSchools(String schoolQuery) {
        List<School> schools = schoolRepository.searchByQuery(schoolQuery);

        for (School school : schools) {
            Phone publicPhone = school.getPhones()
                    .stream()
                    .filter(phone -> phone.getNote().equals("publiczny"))
                    .findFirst().orElseThrow(() -> new RuntimeException("NO PHONE!"));

            school.getPhones().clear();
            school.setPhones(publicPhone);
        }
        return schools;
    }

    public Set<String> getAllCounties() {
        return schoolRepository.findAllCounties();
    }
}
