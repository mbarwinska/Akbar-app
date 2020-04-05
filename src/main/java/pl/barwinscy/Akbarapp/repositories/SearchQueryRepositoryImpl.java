package pl.barwinscy.Akbarapp.repositories;

import pl.barwinscy.Akbarapp.entities.School;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SearchQueryRepositoryImpl implements SearchQueryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<School> searchByQuery(String query) {
        return entityManager.createQuery(query, School.class).getResultList();
    }
}
