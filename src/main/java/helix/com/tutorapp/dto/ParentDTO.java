package helix.com.tutorapp.dto;

import helix.com.tutorapp.model.entity.Role;

import java.util.Date;

/**
 * Created by helix on 11/1/2016.
 */
public class ParentDTO {
    private Long id;
    private Role role;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

}
