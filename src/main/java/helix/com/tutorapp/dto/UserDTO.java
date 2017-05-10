package helix.com.tutorapp.dto;

import helix.com.tutorapp.model.entity.Parent;
import helix.com.tutorapp.model.entity.Role;
import helix.com.tutorapp.model.entity.Tutor;

/**
 * Created by helix on 10/18/2016.
 */
public class UserDTO {
    private String username;
    private String password;
    private Long id;
    private Role role;
    private String token;
    private TutorDTO tutor;
    private ParentDTO parent;

    public TutorDTO getTutor() {
        return tutor;
    }

    public void setTutor(TutorDTO tutor) {
        this.tutor = tutor;
    }

    public ParentDTO getParent() {
        return parent;
    }

    public void setParent(ParentDTO parent) {
        this.parent = parent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
