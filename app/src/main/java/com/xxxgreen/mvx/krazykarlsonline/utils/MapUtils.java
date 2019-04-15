package com.xxxgreen.mvx.krazykarlsonline.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.xxxgreen.mvx.krazykarlsonline.data.parcels.Location;

public final class MapUtils {
    private static final String TAG = "MapUtils";
    // CAMERA
    public static final LatLng CAM_CORNER_SOUTHWEST = new LatLng(40.420745, -105.224842),
            CAM_CORNER_NORTHEAST = new LatLng(40.711165, -104.886907),
            SEARCH_CORNER_SOUTHWEST = new LatLng(40.420745, -105.224842),
            SEARCH_CORNER_NORTHEAST = new LatLng(40.711165, -104.886907);
    public static final Float DEFAULT_CAMERA_ZOOM = 11.2f, CAMERA_ZOOM_FOCUSED = 10.0f;
    public static final LatLng MAP_CENTER = new LatLng(40.543525, -105.057087);

    // LOCATIONS
    public static final int UPDATE_INTERVAL = 10000, FASTEST_INTERVAL = 10000;
    public static final int REQUEST_FINE_LOCATION = 101, REQUEST_COARSE_LOCATION = 102;
    public static final Location ELIZABETH =
            new Location(1, 40.574997, -105.097076,
                    "@ W Elizabeth St", "1124 W Elizabeth St", true,
                    "(970) 224-2000");
    public static final Location TIMBERLINE =
            new Location(2, 40.550328, -105.037031,
                    "@ S Timberline Rd", "2620 S Timberline Rd", true,
                    "(970) 223-8600");

    private static Location MY_LOCATION;

    // DATA KEYS
    public static final String SELECTED_STORE_LOCATION_KEY = "location_name";
    public static final String SECTION_NUMBER_KEY = "section_number";

    // List of coordinates describing the delivery areas
    private static final LatLng[] MAP_ZONE_1_POINTS = {
            new LatLng(40.639421, -105.057123),
            new LatLng(40.567059, -105.057948),
            new LatLng(40.567059, -105.077113),
            new LatLng(40.537952, -105.077113),
            new LatLng(40.538148, -105.096146),
            new LatLng(40.480165, -105.096146),
            new LatLng(40.480084, -105.115643),
            new LatLng(40.523630, -105.115123),
            new LatLng(40.524679, -105.140082),
            new LatLng(40.532218, -105.141409),
            new LatLng(40.609159, -105.173949),
            new LatLng(40.632986, -105.155192)
    };
    private static final LatLng[] MAP_ZONE_2_POINTS = {
            new LatLng(40.639421, -105.057123),
            new LatLng(40.567059, -105.057948),
            new LatLng(40.567059, -105.077113),
            new LatLng(40.537952, -105.077113),
            new LatLng(40.538148, -105.096146),
            new LatLng(40.480165, -105.096146),
            new LatLng(40.480384, -105.032340),
            new LatLng(40.483565, -105.029520),
            new LatLng(40.480227, -105.021932),
            new LatLng(40.479206, -105.001347),
            new LatLng(40.436002, -105.001554),
            new LatLng(40.435903, -104.944248),
            new LatLng(40.624724, -104.943636)

    };

    public static int getNumPoints(int zoneNum) {
        if (zoneNum == 1) {
            return MAP_ZONE_1_POINTS.length;
        } else if (zoneNum == 2) {
            return MAP_ZONE_2_POINTS.length;
        }
        return 0;
    }
    public static LatLng getPoint(int zoneNum, int index) {
        if (index >= 0 && index < getNumPoints(zoneNum)) {
            if (zoneNum == 1) {
                return MAP_ZONE_1_POINTS[index];
            } else if (zoneNum == 2) {
                return MAP_ZONE_2_POINTS[index];
            } else {
                Log.w(TAG, "Zone not recognized");
                return null;
            }
        }  else {
            Log.w(TAG, "Index out of bounds");
            return null;
        }
    }


    public static void setMyLocation(Location myLocation) {
        Log.i(TAG, "Setting MY_LOCATION to: (" + myLocation.latitude + ", "
                + myLocation.longitude + ")");
        MY_LOCATION = myLocation;
    }

    public static Location getMyLocation() {
        return MY_LOCATION;
    }

}