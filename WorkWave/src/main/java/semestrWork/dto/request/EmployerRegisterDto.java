package semestrWork.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class EmployerRegisterDto {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String companyName;
    private String inn;
}
