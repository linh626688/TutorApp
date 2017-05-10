package helix.com.tutorapp.model.entity;

import javax.persistence.*;

/**
 * Created by naophang113 on 5/8/2017.
 */
@Entity
public class Messsage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setState(Boolean state) {
        this.state = state;
    }

    private Boolean state;
    private String contact;
    private String email;
    private String detailRequest;
    private String timeSend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    public Boolean getState() {
        return state;
    }

    public String getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(String timeSend) {
        this.timeSend = timeSend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDetailRequest() {
        return detailRequest;
    }

    public void setDetailRequest(String detailRequest) {
        this.detailRequest = detailRequest;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}