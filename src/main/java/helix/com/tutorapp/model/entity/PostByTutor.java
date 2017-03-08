package helix.com.tutorapp.model.entity;

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


    private String subject;
    private String time;
    private String timePost;
    private String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
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

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
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
