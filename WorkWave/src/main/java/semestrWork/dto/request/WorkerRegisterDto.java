package semestrWork.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerRegisterDto {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
}
