package semestrWork.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import semestrWork.model.Resume;
import semestrWork.model.Worker;
@Repository

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    Worker findWorkerByUsername(String username);

    Worker findWorkerById(long id);
    Boolean existsWorkerByUsername(String username);
}
