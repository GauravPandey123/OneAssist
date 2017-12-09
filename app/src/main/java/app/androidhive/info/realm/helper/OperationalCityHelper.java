package app.androidhive.info.realm.helper;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by sheelu on 30/11/15.
 */
public class OperationalCityHelper {

    public static String getDetectedOperationalCity(Context context, Location location) {
        String city = null;
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            double latitude  = location.getLatitude();
            double longitude = location.getLongitude();
            addresses = gcd.getFromLocation(latitude, longitude, 1);
        } catch (IOException e){
            e.printStackTrace();
        }
        if (addresses != null){
            if (addresses.size() > 0) {
                String locality = addresses.get(0).getLocality();
                return locality;
            }
        }
        return city;
    }

}
