package semestrWork.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    private String gender;
    private String city;
    private String citizenship;
    private String birthDate;
    private String profession;
    private String education;
    private String workExperience;
    private String aboutMe;
    private String skills;
    private String email;
}
