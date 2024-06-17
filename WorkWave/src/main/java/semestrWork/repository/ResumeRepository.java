package semestrWork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import semestrWork.model.Resume;
import semestrWork.model.Vacancy;
import semestrWork.model.Worker;

import java.util.List;

@Repository

public interface ResumeRepository  extends JpaRepository<Resume, Long> {



    List<Resume> findResumeByWorker(Worker worker);

    Resume findResumeById(long id);



    @Query("SELECT r FROM Resume r WHERE UPPER(r.profession) LIKE UPPER(CONCAT('%', :profession, '%'))")
    Iterable<Resume> findResumeByProfession(@Param("profession") String profession);
}

