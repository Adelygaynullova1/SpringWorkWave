package semestrWork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semestrWork.model.Employer;
import semestrWork.model.FeedBack;
import semestrWork.model.Invitation;
import semestrWork.model.Worker;
import semestrWork.repository.FeedBackRepository;
import semestrWork.repository.InvitationRepository;

import java.util.List;

@Service
public class FeedBackService {
    @Autowired
    private FeedBackRepository feedBackRepository;

    @Transactional
    public FeedBack save(FeedBack feedBack) {
        return feedBackRepository.save(feedBack);
    }

    public List<FeedBack> findFeedBackByWorkerList(Worker worker) {
        return feedBackRepository.findFeedBackByWorker(worker);
    }

    public List<FeedBack> findFeedBackByEmployerList(Employer employer) {
        return feedBackRepository.findFeedBackByEmployer(employer);
    }

    public Boolean existsByResumeIdAndVacancyIdAndWorkerId(Long resumeId, Long vacancyId, Long workerId) {
        return feedBackRepository.existsByResumeIdAndVacancyIdAndWorkerId(resumeId,vacancyId,workerId);
    }
}
