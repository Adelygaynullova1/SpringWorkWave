package semestrWork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semestrWork.model.Employer;
import semestrWork.model.Invitation;
import semestrWork.model.Resume;
import semestrWork.model.Worker;
import semestrWork.repository.InvitationRepository;

import java.util.List;

@Service
public class InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;

    @Transactional
    public Invitation save(Invitation invitation) {
        return invitationRepository.save(invitation);
    }

    public List<Invitation> findInvitationByWorkerList(Worker worker) {
        return invitationRepository.findInvitationByWorker(worker);
    }

    public List<Invitation> findInvitationByEmployerList(Employer employer) {
        return invitationRepository.findInvitationByEmployer(employer);
    }

    public Boolean existsByResumeIdAndVacancyIdAndEmployerId(Long resumeId, Long vacancyId, Long employerId) {
        return invitationRepository.existsByResumeIdAndVacancyIdAndEmployerId(resumeId,vacancyId,employerId);
    }
}
