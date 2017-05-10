package helix.com.tutorapp.dto;

import helix.com.tutorapp.model.entity.Tutor;

/**
 * Created by naophang113 on 5/8/2017.
 */
public class MesssageDTO {
    private Long id;

    private Boolean state;
    private String contact;
    private String email;
    private String detailRequest;
    private Tutor tutor;
    private String timeSend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(String timeSend) {
        this.timeSend = timeSend;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
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
