package semestrWork.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

import static jakarta.persistence.InheritanceType.JOINED;

@Getter@Setter
@Entity
@Table(name = "resume")
@Inheritance(strategy = JOINED)
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Worker worker;

    private String gender;
    private String city;
    private String citizenship;
    private Date birthDate;
    private String profession;
    private String education;
    private String workExperience;
    private String aboutMe;
    private String skills;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private ImageFileData avatar;

}
