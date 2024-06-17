package semestrWork.dto;

import lombok.Getter;
import lombok.Setter;
import semestrWork.model.FeedBack;
import semestrWork.model.Invitation;

@Getter
@Setter
public class InvitationDto {
    private Long resume_id;
    private Long worker_id;
    private Long vacancy_id;

    public static InvitationDto from(Invitation invitation){
        InvitationDto dto = new InvitationDto();
        dto.setResume_id(invitation.getResume().getId());
        dto.setWorker_id(invitation.getWorker().getId());
        dto.setVacancy_id(invitation.getVacancy().getId());

        return dto;
    }
}
