package semestrWork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import semestrWork.model.Employer;
import semestrWork.model.FeedBack;
import semestrWork.model.Invitation;
import semestrWork.model.Worker;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {

    List<FeedBack> findFeedBackByWorker(Worker worker);

    List<FeedBack> findFeedBackByEmployer(Employer employer);

    boolean existsByResumeIdAndVacancyIdAndWorkerId(Long resumeId, Long vacancyId, Long workerId);
}
