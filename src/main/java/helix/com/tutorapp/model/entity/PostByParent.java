package helix.com.tutorapp.model.entity;

import javax.persistence.*;

/**
 * Created by helix on 10/14/2016.
 */
@Entity
public class PostByParent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    private String timePost;

    private String classRequirement;

    private String contact;

    private String classLevel;
    private String times;
    private String subject;
    private String period;

    private String salaryDesired;

    private String locationDesired;


    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getClassRequirement() {
        return classRequirement;
    }

    public void setClassRequirement(String classRequirement) {
        this.classRequirement = classRequirement;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSalaryDesired() {
        return salaryDesired;
    }

    public void setSalaryDesired(String salaryDesỉed) {
        this.salaryDesired = salaryDesỉed;
    }

    public String getLocationDesired() {
        return locationDesired;
    }

    public void setLocationDesired(String locationDesired) {
        this.locationDesired = locationDesired;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Parent parent() {
        return parent;
    }

    public void setTutor(Parent parent) {
        this.parent = parent;
    }

    public String getTimePost() {
        return timePost;
    }

    public void setTimePost(String timePost) {
        this.timePost = timePost;
    }
}
