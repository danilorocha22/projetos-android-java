package com.danilorocha.map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity
        implements OnMapReadyCallback, OnCameraIdleListener {

    private GoogleMap map;
    private EditText inputLat, inputLng;
    private Button btnPesq, btnLocDisp;
    private String latString, lngString;
    private double lat, lng;
    private static LatLngBounds latLngBounds;
    private static CameraPosition cameraPosition;
    private final float ZOOM = 8F;
    private TextView mCameraTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        map = null;
        mCameraTextView = findViewById(R.id.camera_text);
        inputLat = findViewById(R.id.inputLat);
        inputLng = findViewById(R.id.inputLng);
        btnPesq = findViewById(R.id.btnPesquisar);
        btnLocDisp = findViewById(R.id.btnLocalizarDisp);
        btnPesq.setOnClickListener(tratarCoordenadas());
        btnLocDisp.setOnClickListener(localizarDispositivo());

        //Construir o Mapa
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }//onCreate

    private View.OnClickListener localizarDispositivo() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCameraTextView.setText(map.getCameraPosition().target.toString());
            }
        };
    }//metodo

    private View.OnClickListener tratarCoordenadas() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                latString = inputLat.getText().toString();
                lngString = inputLng.getText().toString();
                try {
                    if (latString.isEmpty() && !lngString.isEmpty()) {
                        toast("Informe a Latitude");

                    } else if (lngString.isEmpty() && !latString.isEmpty()) {
                        toast("Informe a Longitude");

                    } else if (latString.isEmpty() && lngString.isEmpty()){
                        toast("Informe a Latitude e Longitude");
                    } else {
                        lat = Float.parseFloat(latString);
                        lng = Float.parseFloat(lngString);
                        instanciarCoordenadas(lat, lng);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                };
            }
        };
    }//metodo

    private void instanciarCoordenadas(double lat, double lng) {
        latLngBounds = new LatLngBounds(new LatLng(lat, lng), new LatLng(lat, lng));
        cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(lat, lng)).zoom(ZOOM).bearing(0).tilt(0).build();
        setCoordenadas();
    }//metodo

    public void setCoordenadas() {
        if (!checkReady()) {
            return;
        }
        map.setLatLngBoundsForCameraTarget(latLngBounds);
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }//metodo

    private void toast(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }//metodo

    public void limparPesquisa(View view) {
        if (!checkReady()) return;
        map.setLatLngBoundsForCameraTarget(null);
        Toast.makeText(getBaseContext(), "Lat/Lng resetados", Toast.LENGTH_SHORT).show();
        inputLat.setText("");
        inputLng.setText("");
    }//metodo

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        googleMap.setOnCameraIdleListener(this);
        marcadorPalmas();
        marcadorNovaYork();
        marcadorMadrid();
    }//metodo

   private void marcadorPalmas() {
        LatLng palmas = new LatLng(-10.18, -48.33);
        map.addMarker(new MarkerOptions()
                .position(palmas)
                .title("Marcador em Palmas")
                .snippet("Olá")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        //move a camera para Palmas
        map.moveCamera(CameraUpdateFactory.newLatLng(palmas));
    }//metodo

    private void marcadorNovaYork() {
        LatLng nova_york = new LatLng(40.75, -74.03);
        map.addMarker(new MarkerOptions()
                .position(nova_york)
                .title("Marcador em Nova York")
                .snippet("Hello")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
    }//metodo

    private void marcadorMadrid() {
        LatLng madrid = new LatLng(40.43, -3.81);
        map.addMarker(new MarkerOptions()
                .position(madrid)
                .title("Marcador em Madrid")
                .snippet("Hola")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
    }//metodo

    @Override
    protected void onResume() {
        super.onResume();
    }//metodo

    @Override
    public void onCameraIdle() {
        //mCameraTextView.setText(map.getCameraPosition().toString());
    }//metodo

    /**
     * Antes do mapa estar pronto, muitas chamadas falharão.
     * Deve ser chamado em todos os pontos de entrada que chamam métodos na API do Google Maps.
     */
    private boolean checkReady() {
        if (map == null) {
            Toast.makeText(this, R.string.mapa_nao_esta_pronto, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }//metodo

}//classe