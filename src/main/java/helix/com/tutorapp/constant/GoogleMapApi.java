package helix.com.tutorapp.constant;

/**
 * Created by DangThanhLinh on 30/03/2017.
 */
public class GoogleMapApi {
    private static final String KEY_GOOGLE_MAP = "AIzaSyAeVbvt3vr5Ow3u_souFU44APqH067A2ck";
    public static final String GOOGLE_MAP = "https://maps.googleapis.com/maps/api/place/textsearch";
    public static final String QUERY_LAT_LNG = GOOGLE_MAP + "/json?query={location}&key=" + KEY_GOOGLE_MAP;

    public static final String GOOGLE_MAP_TEST = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=Q.CầuGiấy&key=AIzaSyAeVbvt3vr5Ow3u_souFU44APqH067A2ck";

}
