package pl.barwinscy.Akbarapp;

import org.springframework.stereotype.Service;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.SearchQueryRepository;

import java.util.List;

@Service
public class SearchService {

    private SearchQueryRepository searchQueryRepository;

    public SearchService(SearchQueryRepository searchQueryRepository) {
        this.searchQueryRepository = searchQueryRepository;
    }

    public List<School> getSearchedSchools(String schoolQuery){
         return searchQueryRepository.searchByQuery(schoolQuery);
    }
}
