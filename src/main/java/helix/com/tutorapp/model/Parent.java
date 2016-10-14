package helix.com.tutorapp.model;

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

    private String userName;
    private String password;

    private String fristName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String token;
    private Date tokenExpiry;
    private String state;
    private String ava;
    private String gender;
    private int rank;



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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
