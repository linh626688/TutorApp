package helix.com.tutorapp.model;

import javax.persistence.*;

/**
 * Created by helix on 10/14/2016.
 */
@Entity
public class PostByParent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String postContent;


    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Parent parent;

    private String subject;

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    private String time;
    private String timePost;
    private String status;
    private String gender;
    private String level;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Parent parent() {
        return parent;
    }

    public void setTutor(Parent parent) {
        this.parent = parent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimePost() {
        return timePost;
    }

    public void setTimePost(String timePost) {
        this.timePost = timePost;
    }
}
