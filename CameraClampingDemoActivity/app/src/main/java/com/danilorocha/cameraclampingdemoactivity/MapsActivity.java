package com.danilorocha.cameraclampingdemoactivity;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.danilorocha.cameraclampingdemoactivity.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, OnCameraIdleListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private static final String TAG = MapsActivity.class.getSimpleName();

    private static final float ZOOM_DELTA = 2.0f;
    private static final float DEFAULT_MIN_ZOOM = 2.0f;
    private static final float DEFAULT_MAX_ZOOM = 22.0f;

    private static final LatLngBounds PALMAS = new LatLngBounds(
            new LatLng(-10.19, -48.33), new LatLng(-10.17, -48.35));
    private static final CameraPosition PALMAS_CAMERA = new CameraPosition.Builder()
            .target(new LatLng(-10.1879508, -48.3353066)).zoom(20.0f).bearing(0).tilt(0).build();

    private static final LatLngBounds PACIFIC = new LatLngBounds(
            new LatLng(-15.0, 165.0), new LatLng(15.0, -165.0));
    private static final CameraPosition PACIFIC_CAMERA = new CameraPosition.Builder()
            .target(new LatLng(0, -180)).zoom(4.0f).bearing(0).tilt(0).build();

    /**
     * Nível de zoom mínimo interno que pode ser alternado por meio da demonstração.
     */
    private float mMinZoom;

    /**
     * Nível máximo de zoom interno que pode ser alternado por meio da demonstração.
     */
    private float mMaxZoom;

    private TextView mCameraTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mMap = null;
        resetMinMaxZoom();

        mCameraTextView = (TextView) findViewById(R.id.camera_text);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setOnCameraIdleListener(this);

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

    @Override
    public void onCameraIdle() {
        mCameraTextView.setText(mMap.getCameraPosition().toString());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Antes de o mapa estar pronto, muitas chamadas falharão.
     * Deve ser chamado em todos os pontos de entrada que chamam métodos na API do Google Maps.
     */
    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(this, R.string.map_not_ready, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void toast(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void resetMinMaxZoom() {
        mMinZoom = DEFAULT_MIN_ZOOM;
        mMaxZoom = DEFAULT_MAX_ZOOM;
    }

    /**
     * Clique no manipulador para prender no botão Adelaide.
     * @param view
     */
    public void onClampToAdelaide(View view) {
        if (!checkReady()) {
            return;
        }
        mMap.setLatLngBoundsForCameraTarget(PALMAS);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(PALMAS_CAMERA));
    }

    /**
     * Clique no manipulador para fixar no botão Pacífico.
     * @param view
     */
    public void onClampToPacific(View view) {
        if (!checkReady()) {
            return;
        }
        mMap.setLatLngBoundsForCameraTarget(PACIFIC);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(PACIFIC_CAMERA));
    }

    public void onLatLngClampReset(View view) {
        if (!checkReady()) {
            return;
        }
        //  Definir limites como nulos remove todos os limites definidos anteriormente.
        mMap.setLatLngBoundsForCameraTarget(null);
        toast("LatLngBounds clamp reset.");
    }

    public void onSetMinZoomClamp(View view) {
        if (!checkReady()) {
            return;
        }
        mMinZoom += ZOOM_DELTA;
        // Limita o nível mínimo de zoom.
        mMap.setMinZoomPreference(mMinZoom);
        toast("Min zoom preference set to: " + mMinZoom);
    }

    public void onSetMaxZoomClamp(View view) {
        if (!checkReady()) {
            return;
        }
        mMaxZoom -= ZOOM_DELTA;
        // Limita o nível máximo de zoom.
        mMap.setMaxZoomPreference(mMaxZoom);
        toast("Max zoom preference set to: " + mMaxZoom);
    }

    public void onMinMaxZoomClampReset(View view) {
        if (!checkReady()) {
            return;
        }
        resetMinMaxZoom();
        mMap.resetMinMaxZoomPreference();
        toast("Min/Max zoom preferences reset.");
    }




}