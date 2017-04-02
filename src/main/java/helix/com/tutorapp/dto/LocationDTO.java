package helix.com.tutorapp.dto;

/**
 * Created by DangThanhLinh on 30/03/2017.
 */
public class LocationDTO {
    private String location;
    private float lat;
    private float lng;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
