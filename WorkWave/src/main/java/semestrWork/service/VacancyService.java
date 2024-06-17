package semestrWork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semestrWork.model.Employer;
import semestrWork.model.Vacancy;
import semestrWork.repository.VacancyRepository;

import java.util.List;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository repository;

    @Transactional
    public Vacancy save(Vacancy vacancy) {
        return repository.save(vacancy);
    }



    public List<Vacancy> findVacancyByEmployerList(Employer employer) {
        return repository.findVacancyByEmployer(employer);
    }

    public  Vacancy findVacancyById(Long id) {
        return repository.findVacancyById(id);
    }

    public  Iterable<Vacancy> findVacancyByProfession( String profession){
        return repository.findVacancyByProfession(profession);
    }
}
