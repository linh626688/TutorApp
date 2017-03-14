package helix.com.tutorapp.dto;

/**
 * Created by DangThanhLinh on 06/12/2016.
 */
public class PostByParentDTO {
    private Long id;

    private String postContent;
    private String subject;
    private String time;
    private String timePost;
    private String status;
    private String gender;
    private String level;

    private String contact;
    private String salaryDesired;
    private String locationDesired;

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

    public String getSalaryDesired() {
        return salaryDesired;
    }

    public void setSalaryDesired(String salaryDesired) {
        this.salaryDesired = salaryDesired;
    }

    public String getLocationDesired() {
        return locationDesired;
    }

    public void setLocationDesired(String locationDesired) {
        this.locationDesired = locationDesired;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
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
}
