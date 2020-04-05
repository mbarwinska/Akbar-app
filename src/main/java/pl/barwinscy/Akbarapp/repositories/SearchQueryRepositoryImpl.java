package pl.barwinscy.Akbarapp.repositories;

import org.springframework.stereotype.Repository;
import pl.barwinscy.Akbarapp.entities.School;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SearchQueryRepositoryImpl implements SearchQueryRepository {

    @PersistenceContext
    private EntityManager em;

    public SearchQueryRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<School> searchByQuery(String query) {
        return em.createQuery(query, School.class).getResultList();
    }
}
