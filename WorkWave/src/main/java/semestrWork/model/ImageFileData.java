package semestrWork.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter
@Entity
public class ImageFileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String path;

    private String fileName;

    @OneToOne(cascade = CascadeType.ALL)
    private Resume resume;

}
