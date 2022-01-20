package com.danilorocha.pdm2_atv3_24_08_21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final float ZOOM_DELTA = 2.0f;
    private static final float DEFAULT_MIN_ZOOM = 2.0f;
    private static final float DEFAULT_MAX_ZOOM = 22.0f;

    private static final LatLngBounds ADELAIDE = new LatLngBounds(
            new LatLng(-35.0, 138.58), new LatLng(-34.9, 138.61));
    private static final CameraPosition ADELAIDE_CAMERA = new CameraPosition.Builder()
            .target(new LatLng(-34.92873, 138.59995)).zoom(20.0f).bearing(0).tilt(0).build();

    private static final LatLngBounds PACIFIC = new LatLngBounds(
            new LatLng(-15.0, 165.0), new LatLng(15.0, -165.0));
    private static final CameraPosition PACIFIC_CAMERA = new CameraPosition.Builder()
            .target(new LatLng(0, -180)).zoom(4.0f).bearing(0).tilt(0).build();

    private GoogleMap mMap;

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
        setContentView(R.layout.activity_main);

        mMap = null;
        resetMinMaxZoom();

        mCameraTextView = (TextView) findViewById(R.id.camera_text);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        map.setOnCameraIdleListener(this);
    }

    @Override
    public void onCameraIdle() {
        mCameraTextView.setText(mMap.getCameraPosition().toString());
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
        mMap.setLatLngBoundsForCameraTarget(ADELAIDE);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(ADELAIDE_CAMERA));
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