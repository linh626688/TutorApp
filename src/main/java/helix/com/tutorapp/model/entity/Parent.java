package helix.com.tutorapp.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helix on 10/14/2016.
 */
@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinTable(name = "parent_has_posts",
//            inverseJoinColumns = {
//                    @JoinColumn(name = "parent_id", referencedColumnName = "id")
//            },
//            joinColumns = {
//                    @JoinColumn(name = "postbyparent_id", referencedColumnName = "id")
//            }
//    )
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<PostByParent> getPostByParent() {
        return postByParent;
    }

    public void setPostByParent(List<PostByParent> postByParent) {
        this.postByParent = postByParent;
    }
}
