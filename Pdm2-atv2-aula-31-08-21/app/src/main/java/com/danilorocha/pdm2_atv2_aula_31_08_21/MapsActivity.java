package com.danilorocha.pdm2_atv2_aula_31_08_21;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.danilorocha.pdm2_atv2_aula_31_08_21.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
                .icon(bitmapDescriptorFromVector(getApplicationContext(),
                        R.drawable.ic_baseline_thumb_up_24)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }//metodo

    /*este m??todo recebe o contexto da aplication e um imagem (no formato SVG) e retorna um
    BitmapDescriptor*/
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectoDrawableResourceId) {
        //retorna um Drawable a partir do contexto e a imagem SVG
        Drawable background = ContextCompat.getDrawable(context, vectoDrawableResourceId);

        //configura a posi????o da imagem: esquerda, em cima, direita e em baixo
        background.setBounds(0, 0, background.getIntrinsicWidth(),
                background.getIntrinsicHeight());

        //cria um Bitmap com os valores de comprimento, altura e cada pixels tem 4 bytes.
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(),
                background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        //cria uma tela
        Canvas canvas = new Canvas(bitmap);

        //desenha na tela criada
        background.draw(canvas);

        //cria um BitmapDescriptor atrav??s de um bitmap
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }//metodo

}//classe