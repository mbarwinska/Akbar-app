package pl.barwinscy.Akbarapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.barwinscy.Akbarapp.entities.School;

import java.util.List;
import java.util.Set;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long>, SearchQueryRepository {

    @Query(value = "SELECT s.address.county FROM School as s")
    Set<String> findAllCounties();

    @Query(value = "SELECT s FROM School as s LEFT JOIN Phone as p ON s.id = p.schoolRSPO " +
            "LEFT JOIN Status as st ON s.id = st.school.id " +
            "LEFT JOIN Schedule as sch ON s.id = sch.school.id " +
            "LEFT JOIN Employee as e ON s.employee.id = e.id " +
            "LEFT JOIN AdditionalInfo as a ON s.id = a.school.id " +
            "WHERE s.id = :schoolId")
    School findSchoolWithAllInfo(@Param("schoolId")Long schoolId);


}
