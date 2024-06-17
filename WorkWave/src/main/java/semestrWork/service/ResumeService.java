package semestrWork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semestrWork.model.Resume;
import semestrWork.model.Vacancy;
import semestrWork.model.Worker;
import semestrWork.repository.ResumeRepository;

import java.util.List;


@Service
public class ResumeService {

    @Autowired
    private ResumeRepository repository;

    @Transactional
    public Resume save(Resume resume) {
        return repository.save(resume);
    }


    public Iterable<Resume> findAll() {
        return repository.findAll();
    }
    public List<Resume> findResumeByWorkerList(Worker worker) {
        return repository.findResumeByWorker(worker);
    }

    public  Resume findResumeById(Long id) {
        return repository.findResumeById(id);
    }

    public  Iterable<Resume> findResumeByProfession(String profession){
        return repository.findResumeByProfession(profession);
    }
}
