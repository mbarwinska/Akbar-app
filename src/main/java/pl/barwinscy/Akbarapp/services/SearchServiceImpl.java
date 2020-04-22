package pl.barwinscy.Akbarapp.services;

import org.apache.commons.lang3.Functions;
import org.springframework.stereotype.Service;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public Set<String> getAllCounties() {
        return schoolRepository.findAllCounties();
    }

    @Override
    public Map<LocalDate, List<School>> schoolMapByDate(String calendarQuery) {
        List<School> schoolList = schoolRepository.searchByQuery(calendarQuery);
        Map<LocalDate, List<School>> collect = schoolList.stream()
                .collect(Collectors.groupingBy(school -> school.getSchedule()
                        .getContactDate(), Collectors.mapping(Function.identity(), Collectors.toList())));
        return collect;
    }
}
