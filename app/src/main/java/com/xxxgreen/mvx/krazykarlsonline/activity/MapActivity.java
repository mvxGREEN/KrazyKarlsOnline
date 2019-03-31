package com.xxxgreen.mvx.krazykarlsonline.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.xxxgreen.mvx.krazykarlsonline.R;
import com.xxxgreen.mvx.krazykarlsonline.data.parcels.Location;
import com.xxxgreen.mvx.krazykarlsonline.data.sqlite.DatabaseManager;
import com.xxxgreen.mvx.krazykarlsonline.fragment.home.FeatureFrag;
import com.xxxgreen.mvx.krazykarlsonline.fragment.sections.FragAbout;
import com.xxxgreen.mvx.krazykarlsonline.fragment.sections.FragHome;
import com.xxxgreen.mvx.krazykarlsonline.fragment.sections.FragMap;
import com.xxxgreen.mvx.krazykarlsonline.fragment.sections.FragMenu;
import com.xxxgreen.mvx.krazykarlsonline.fragment.sections.FragOrder;
import com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils;

import java.util.ArrayList;
import java.util.List;

import static com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils.DEFAULT_CAMERA_ZOOM;
import static com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils.CAM_CORNER_NORTHEAST;
import static com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils.CAM_CORNER_SOUTHWEST;
import static com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils.ELIZABETH;
import static com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils.FASTEST_INTERVAL;
import static com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils.REQUEST_FINE_LOCATION;
import static com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils.TIMBERLINE;
import static com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils.UPDATE_INTERVAL;
import static com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils.getNumPoints;
import static com.xxxgreen.mvx.krazykarlsonline.utils.MapUtils.getPoint;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private static final String TAG = "MapActivity";

    private GoogleMap mMap;
    protected LatLng userLatLng, cameraCenter;
    protected android.location.Location mLastLocation;
    private static LayoutInflater layoutInflater;
    private Location selectedLocation, searchedLocation;
    DatabaseManager mDbm;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager panel;

    private static int zone1filler, zone2filler, zone1Stroke, zone2Stroke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Init static colors
        zone1filler = getContext().getResources().getColor(R.color.elizabethLightTransparent);
        zone2filler = getContext().getResources().getColor(R.color.timberlineGreenTransparent);
        zone1Stroke = getContext().getResources().getColor(R.color.elizabethLightTransparent);
        zone2Stroke = getContext().getResources().getColor(R.color.timberlineGreenTransparent);

        // Init Google Map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Init SQLite Database Manager
        mDbm = DatabaseManager.getInstance(this);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        setMapFocus(null, mMap);

        // PANEL
        panel = findViewById(R.id.view_pager_map);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        panel.setAdapter(mSectionsPagerAdapter);

        // Connect panel viewpager to tab layout
        TabLayout tabLayout = findViewById(R.id.tab_layout_map);
        tabLayout.setupWithViewPager(panel);

        // FEATURE VIEWPAGER ADAPTER


        // CAMERA
        cameraCenter = new LatLng(MapUtils.MAP_CENTER.latitude, MapUtils.MAP_CENTER.longitude);
        LatLngBounds cameraBounds = LatLngBounds.builder()
                .include(CAM_CORNER_SOUTHWEST)
                .include(CAM_CORNER_NORTHEAST)
                .build();
        mMap.setLatLngBoundsForCameraTarget(cameraBounds);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraCenter, DEFAULT_CAMERA_ZOOM));

        // Check and request sensitive permissions (fine location, coarse location, ...)
        checkAndRequestPermissions(this);

        setMapFocus(null, mMap);

        // Set marker onCLick listener
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i(TAG, "Marker clicked: " + marker.getTitle());

                marker.showInfoWindow();
                // Select store / begin order
                LatLng markerLatLng = marker.getPosition();
                if (markerLatLng.equals(new LatLng(ELIZABETH.latitude, ELIZABETH.longitude))) {
                    selectedLocation = ELIZABETH;
                } else if (markerLatLng.equals(new LatLng(TIMBERLINE.latitude, TIMBERLINE.longitude))) {
                    selectedLocation = TIMBERLINE;
                } else {
                    selectedLocation = new Location(0, markerLatLng.latitude,
                            markerLatLng.longitude, marker.getTitle(), marker.getSnippet(), false,
                            "");
                }
                setMapFocus(selectedLocation, mMap);
                return true;
            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.i(TAG, "Map clicked!");
                selectedLocation = null;
                setMapFocus(null, mMap);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (selectedLocation != null) {
            selectedLocation = null;
            setMapFocus(null, mMap);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        // Get user location
                        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                            // Request if not already given
                            ActivityCompat.requestPermissions(this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
                        }

                        // Set up location updates
                        android.location.Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            Log.i(TAG, "Updated user location");
                            userLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                        } else {
                            Log.i(TAG, "Couldn't locate user");
                        }
                        LocationRequest mLocationRequest = LocationRequest.create();
                        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                        mLocationRequest.setInterval(UPDATE_INTERVAL);
                        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                REQUEST_FINE_LOCATION);
                    }
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "MapActivity.onConnected!");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.w(TAG, "MapActivity.onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "MapActivity.onConnectionFailed\n" + connectionResult.getErrorMessage());
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        Log.i(TAG, "New location:\n"
                + "LAT=" + location.getLatitude() + "  " + "LONG=" + location.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                .title(getString(R.string.title_map_snippet_my_location))
                .snippet("???")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .zIndex(16)
        );
    }

    private Context getContext() {
        return this;
    }

    private void checkAndRequestPermissions(Context context) {
        // Check fine location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Request permission
            Log.w(TAG, "Permission:\n" +
                    "Fine Location=NOT GRANTED");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);

        } else {
            Log.i(TAG, "Permission:\n" +
                    "Fine Location=GRANTED");

            // Get user location
            LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            android.location.Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {
                Log.i(TAG, "User location:\n"
                        + "LAT=" + location.getLatitude() + "  " + "LONG=" + location.getLongitude());

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(), location.getLongitude()))
                        .title(getString(R.string.title_map_snippet_my_location))
                        .snippet(getString(R.string.hint_map_searchbox))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        .zIndex(4)
                );

            } else {
                Log.w(TAG, "User location unknown");
            }

            // Set up auto location updates
            LocationRequest mLocationRequest = LocationRequest.create();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setInterval(UPDATE_INTERVAL);
            mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        }
    }

    public static void setMapFocus(@Nullable Location focusLocation, GoogleMap qMap) {
        qMap.clear();

        // Draw Zone 1
        List<LatLng> zone1BoundsList = new ArrayList<>();
        for (int i = 0; i < getNumPoints(1); i++) {
            zone1BoundsList.add(getPoint(1, i));
        }
        PolygonOptions optionsZone1 = new PolygonOptions()
                .addAll(zone1BoundsList)
                .fillColor(zone1filler)
                .strokeColor(zone1Stroke)
                .strokeWidth(8.0f)
                .clickable(false);
        qMap.addPolygon(optionsZone1);

        // Draw Zone 2
        List<LatLng> zone2BoundsList = new ArrayList<>();
        for (int i = 0; i < getNumPoints(2); i++) {
            zone2BoundsList.add(getPoint(2, i));
        }
        PolygonOptions optionsZone2 = new PolygonOptions()
                .addAll(zone2BoundsList)
                .fillColor(zone2filler)
                .strokeColor(zone2Stroke)
                .strokeWidth(8.0f)
                .clickable(false);
        qMap.addPolygon(optionsZone2);

        // Add marker on Elizabeth location
        qMap.addMarker(new MarkerOptions()
                .position(new LatLng(ELIZABETH.latitude, ELIZABETH.longitude))
                .title("Pickup")
                .snippet(ELIZABETH.address_1)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        );

        // Add marker on Timberline location
        qMap.addMarker(new MarkerOptions()
                .position(new LatLng(TIMBERLINE.latitude, TIMBERLINE.longitude))
                .title("Pickup")
                .snippet(TIMBERLINE.address_1)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        );


        // (IF NECESSARY) Add marker on location; adjust camera
        if (focusLocation != null && !focusLocation.isStore) {
            qMap.addMarker(new MarkerOptions()
                    .position(new LatLng(focusLocation.latitude, focusLocation.longitude))
                    .title("My Location")
                    .snippet(focusLocation.address_1)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }
    }

    // Adapter instantiates proper fragment, given index of page
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        FragmentManager mFm;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            mFm = fm;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a Fragment (defined as a static inner class below).
            Log.i(TAG, "Instantiating overlay for page #" + (position+1));

            if (position == 0) {
                return FragHome.newInstance(position);
            } else if (position == 1) {
                return FragMap.newInstance(position);
            } else if (position == 2) {
                return FragMenu.newInstance(position, mFm);
            } else if (position == 3) {
                return FragOrder.newInstance(position);
            } else {
                return FragAbout.newInstance(position);
            }
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Home";
            } else if (position == 1) {
                return "Map";
            } else if (position == 2) {
                return "Menu";
            } else if (position == 3) {
                return "Order";
            } else if (position == 4) {
                return "About";
            }
            Log.w(TAG, "Index, " + position + ", is outside array length: " + 2);
            return "";
        }
    }

    // Adapter instantiates proper fragment, given index of page
    public class FeaturePagerAdapter extends FragmentPagerAdapter {

        public FeaturePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a Fragment (defined as a static inner class below).
            Log.i(TAG, "Instantiating overlay for page #" + (position+1));

            if (position == 0) {
                return FeatureFrag.newInstance(position);
            } else if (position == 1) {
                return FeatureFrag.newInstance(position);
            } else if (position == 2) {
                return FeatureFrag.newInstance(position);
            } else {
                return FeatureFrag.newInstance(position);
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Main";
            } else if (position == 1) {
                return "POW";
            } else if (position == 2) {
                return "Specials";
            } else if (position == 3) {
                return "News";
            }
            Log.w(TAG, "Index, " + position + ", is outside array length: " + 2);
            return "";
        }
    }
}
