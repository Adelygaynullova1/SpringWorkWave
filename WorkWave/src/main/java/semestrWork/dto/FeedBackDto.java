package semestrWork.dto;

import lombok.Getter;
import lombok.Setter;
import semestrWork.model.FeedBack;

@Getter
@Setter
public class FeedBackDto {
    private Long resume_id;
    private Long employer_id;

    private Long vacancy_id;

    public static FeedBackDto from(FeedBack feedBack){
        FeedBackDto dto = new FeedBackDto();
        dto.setResume_id(feedBack.getResume().getId());
        dto.setEmployer_id(feedBack.getEmployer().getId());
        dto.setVacancy_id(feedBack.getVacancy().getId());

        return dto;
    }
}