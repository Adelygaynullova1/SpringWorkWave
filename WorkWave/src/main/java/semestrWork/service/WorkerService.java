package semestrWork.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semestrWork.model.Worker;
import semestrWork.repository.WorkerRepository;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public Boolean existsWorkerByUsername(String username) {
        return workerRepository.existsWorkerByUsername(username);
    }

    @Transactional
    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }

    public Worker findWorkerByUsername(String username) {
        return workerRepository.findWorkerByUsername(username);
    }

    public Worker findWorkerById(long id) {
        return workerRepository.findWorkerById(id);
    }
}
