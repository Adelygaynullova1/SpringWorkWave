package semestrWork.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDto {
    private String profession;
    private String office;
    private String email;
    private String schedule;
    private String education;
    private String workExperience;
    private String aboutVacancy;
    private String skills;
}
