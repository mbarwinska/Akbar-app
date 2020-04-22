package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

import java.text.Collator;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
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

    @Override
    public Map<LocalDate, List<School>> schoolMapByDate(String calendarQuery, String dateType) {
        List<School> schoolList = schoolRepository.searchByQuery(calendarQuery);

        switch (dateType) {
            case "contactDate":
                return new TreeMap<>(schoolList.stream()
                        .collect(Collectors.groupingBy(school -> school.getSchedule()
                                .getContactDate(), Collectors.mapping(Function.identity(), Collectors.toList()))));
            case "photographingDate":
                return new TreeMap<>(schoolList.stream()
                        .collect(Collectors.groupingBy(school -> school.getSchedule()
                                .getPhotographingDate(), Collectors.mapping(Function.identity(), Collectors.toList()))));
            case "payoffDate":
                return new TreeMap<>(schoolList.stream()
                        .collect(Collectors.groupingBy(school -> school.getSchedule()
                                .getPayoffDate(), Collectors.mapping(Function.identity(), Collectors.toList()))));
            default:
                return new TreeMap<>(schoolList.stream()
                        .collect(Collectors.groupingBy(school -> school.getSchedule()
                                .getOtherDate(), Collectors.mapping(Function.identity(), Collectors.toList()))));
        }
    }
}
