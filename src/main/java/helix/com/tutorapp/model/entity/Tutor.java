package helix.com.tutorapp.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    private String token;
    private Date tokenExpiry;
    private String state;
    private String ava;
    private String gender;

    private String fristName;
    private String lastName;

    private String location;

    private int rank;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tutor_has_posts",
            inverseJoinColumns = {
                    @JoinColumn(name = "tutor_id", referencedColumnName = "id")
            },
            joinColumns = {
                    @JoinColumn(name = "postbytutor_id", referencedColumnName = "id")
            }
    )
    private List<PostByTutor> postByTutor = new ArrayList<PostByTutor>();


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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExpiry() {
        return tokenExpiry;
    }

    public void setTokenExpiry(Date tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<PostByTutor> getPostByTutor() {
        return postByTutor;
    }

    public void setPostByTutor(List<PostByTutor> postByTutor) {
        this.postByTutor = postByTutor;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
