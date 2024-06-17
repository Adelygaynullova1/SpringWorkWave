package semestrWork.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;




import static jakarta.persistence.InheritanceType.JOINED;

@Getter@Setter
@Entity
@Table(name = "vacancy")
@Inheritance(strategy = JOINED)
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Employer employer;

    private String profession;
    private String office;
    private String email;
    private String schedule;
    private String education;
    private String workExperience;
    private String aboutVacancy;
    private String skills;
}
