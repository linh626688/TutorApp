package helix.com.tutorapp.api.googlemapresponse;

/**
 * Created by DangThanhLinh on 31/03/2017.
 */
public class Results {

    public String formatted_address;

    public Geometry geometry;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }
}
