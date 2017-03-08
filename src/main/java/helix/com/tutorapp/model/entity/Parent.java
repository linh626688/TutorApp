package helix.com.tutorapp.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by helix on 10/14/2016.
 */
@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fristName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String token;
    private Date tokenExpiry;
    private String state;

    private String gender;
    private int rank;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "parent_has_posts",
            inverseJoinColumns = {
                    @JoinColumn(name = "parent_id", referencedColumnName = "id")
            },
            joinColumns = {
                    @JoinColumn(name = "postbyparent_id", referencedColumnName = "id")
            }
    )
    private List<PostByParent> postByParent = new ArrayList<PostByParent>();


    public List<PostByParent> getPosts() {
        return postByParent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List<PostByParent> getPostByParent() {
        return postByParent;
    }

    public void setPostByParent(List<PostByParent> postByParent) {
        this.postByParent = postByParent;
    }
}
