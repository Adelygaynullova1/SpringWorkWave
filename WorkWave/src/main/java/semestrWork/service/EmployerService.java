package semestrWork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semestrWork.model.Employer;
import semestrWork.model.Worker;
import semestrWork.repository.EmployerRepository;

@Service
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    public boolean existsEmployerByUsername(String username) {
        return employerRepository.existsEmployerByUsername(username);
    }

    @Transactional
    public Employer save(Employer employer) {
        return employerRepository.save(employer);
    }

    public Employer findEmployerByUsername(String username) {
        return employerRepository.findEmployerByUsername(username);
    }

    public Employer findEmployerById(long id) {
        return employerRepository.findEmployerById(id);
    }
}
