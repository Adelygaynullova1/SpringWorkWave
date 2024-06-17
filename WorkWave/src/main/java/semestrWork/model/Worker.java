package semestrWork.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.InheritanceType.JOINED;

@Getter@Setter
@Entity
@Table(name = "worker")

public class Worker extends User{

    private String name;
    private String surname;
    private String patronymic;

}
