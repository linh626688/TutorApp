package helix.com.tutorapp.dto;

/**
 * Created by DangThanhLinh on 06/12/2016.
 */
public class PostByTutorDTO {
    private Long id;
    private String subject;
    private String times;
    private String timePost;
    private String salaryDesired;
    private String locationDesired;
    private String classRequirement;
    private String classLevel;
    private String period;


    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


    public String getSalaryDesired() {
        return salaryDesired;
    }

    public void setSalaryDesired(String salaryDesired) {
        this.salaryDesired = salaryDesired;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }

    public String getLocationDesired() {
        return locationDesired;
    }

    public void setLocationDesired(String locationDesired) {
        this.locationDesired = locationDesired;
    }

    public String getClassRequirement() {
        return classRequirement;
    }

    public void setClassRequirement(String classRequirement) {
        this.classRequirement = classRequirement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTimePost() {
        return timePost;
    }

    public void setTimePost(String timePost) {
        this.timePost = timePost;
    }
}
