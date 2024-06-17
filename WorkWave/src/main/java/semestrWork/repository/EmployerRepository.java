package semestrWork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import semestrWork.model.Employer;


@Repository

public interface EmployerRepository extends JpaRepository<Employer, Long> {

    Employer findEmployerByUsername(String username);

    Employer findEmployerById(long id);
    Boolean existsEmployerByUsername(String username);
}
