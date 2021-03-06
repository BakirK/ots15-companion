package org.opentech.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.opentech.R;
import org.opentech.db.DatabaseManager;

import java.util.Locale;

public class MapFragment extends SupportMapFragment{

    private static final double DESTINATION_LATITUDE = 52.52433;
    private static final double DESTINATION_LONGITUDE = 13.389893;
        private static final String DESTINATION_NAME = "Kalkscheune Johannisstraße 2  10117 Berlin Germany";
    private GoogleMap mMap;
    String map_url ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
       // DatabaseManager db =  new DatabaseManager();
       // db.getTrackMapUrl()
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMap = getMap();
        if(mMap != null) {
            MarkerOptions eventMarker = new MarkerOptions().position(new LatLng(
                    DESTINATION_LATITUDE,
                    DESTINATION_LONGITUDE)).title(DESTINATION_NAME);
            CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(eventMarker.getPosition(), 15.0f);
            mMap.addMarker(eventMarker);
            mMap.animateCamera(cu);

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.map, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.directions:
                launchDirections();
                return true;
        }
        return false;
    }

    private void launchDirections() {
        // Build intent to start Google Maps directions
        String uri = String.format(Locale.US,
                "https://www.google.com/maps/search/%1$s/@%2$f,%3$f,17z",
                DESTINATION_NAME, DESTINATION_LATITUDE, DESTINATION_LONGITUDE);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

        startActivity(intent);
    }

    private void get_Latlng(){

    }

}
