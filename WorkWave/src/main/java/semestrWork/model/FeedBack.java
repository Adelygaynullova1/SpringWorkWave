package semestrWork.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;




import static jakarta.persistence.InheritanceType.JOINED;

@Getter@Setter
@Entity
@Table(name = "feedback")
@Inheritance(strategy = JOINED)
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Worker worker;


    @ManyToOne(cascade = CascadeType.ALL)
    private Vacancy vacancy;

    @ManyToOne(cascade = CascadeType.ALL)
    private Resume resume;

    @ManyToOne(cascade = CascadeType.ALL)
    private Employer employer;
}
