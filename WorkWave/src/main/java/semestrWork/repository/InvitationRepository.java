package semestrWork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import semestrWork.model.Employer;
import semestrWork.model.Invitation;
import semestrWork.model.Resume;
import semestrWork.model.Worker;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    List<Invitation> findInvitationByWorker(Worker worker);

    List<Invitation> findInvitationByEmployer(Employer employer);

    boolean existsByResumeIdAndVacancyIdAndEmployerId(Long resumeId, Long vacancyId, Long employerId);
}
