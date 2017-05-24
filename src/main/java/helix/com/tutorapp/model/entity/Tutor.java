package helix.com.tutorapp.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helix on 10/14/2016.
 */
@Entity
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String currentJob;
    private String birth;
    private String name;
    private String location;

    //    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinTable(name = "tutor_has_posts",
//            inverseJoinColumns = {
//                    @JoinColumn(name = "tutor_id", referencedColumnName = "id")
//            },
//            joinColumns = {
//                    @Joinn(name = "postbytutor_id", referencedColumnName = "id")
//            }
//    )
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tutor")
    private List<PostByTutor> postByTutor = new ArrayList<PostByTutor>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tutor")
    private List<Messsage> messsageList = new ArrayList<Messsage>();

    public List<Messsage> getMesssageList() {
        return messsageList;
    }

    public void setMesssageList(List<Messsage> messsageList) {
        this.messsageList = messsageList;
    }

    public String getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public List<PostByTutor> getPosts() {
        return postByTutor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<PostByTutor> getPostByTutor() {
        return postByTutor;
    }

    public void setPostByTutor(List<PostByTutor> postByTutor) {
        this.postByTutor = postByTutor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
