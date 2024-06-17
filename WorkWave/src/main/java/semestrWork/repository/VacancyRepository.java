package semestrWork.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import semestrWork.model.Employer;
import semestrWork.model.Resume;
import semestrWork.model.Vacancy;


import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {


    List<Vacancy> findVacancyByEmployer(Employer employer);

    Vacancy findVacancyById(long id);

    @Query("SELECT v FROM Vacancy v WHERE UPPER(v.profession) LIKE UPPER(CONCAT( '%',:profession, '%'))")
    Iterable<Vacancy> findVacancyByProfession(@Param("profession") String profession);



}
