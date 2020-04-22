package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<String> getAllCounties() {
        Set<String> allCounties = schoolRepository.findAllCounties();
        List<String> countiesList = new ArrayList<>(allCounties);
        countiesList.sort(Collator.getInstance(new Locale("pl", "PL")));
        return countiesList;
    }
}
