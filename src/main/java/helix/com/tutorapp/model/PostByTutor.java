package helix.com.tutorapp.model;

import javax.persistence.*;

/**
 * Created by helix on 10/14/2016.
 */
@Entity
public class PostByTutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String postContent;


    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

}
