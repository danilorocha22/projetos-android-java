package com.danilorocha.permissoes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.danilorocha.permissoes.R;

public class MainActivity extends AppCompatActivity {

    private Button btnCamera, btnMicrofone, btnLocalizacao;
    private static final int COD_SOLICITACAO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCamera = findViewById(R.id.btnCamera);
        btnMicrofone = findViewById(R.id.btnMicrofone);
        btnLocalizacao = findViewById(R.id.btnLocalizacao);
    }//onCreate

    private void tratarPermissao(View view) {
        if (view.getId() == btnCamera.getId())
            solicitarPermissao(Manifest.permission.CAMERA);
        if (view.getId() == btnMicrofone.getId())
            solicitarPermissao(Manifest.permission.RECORD_AUDIO);
        if (view.getId() == btnLocalizacao.getId())
            solicitarPermissao(Manifest.permission.ACCESS_FINE_LOCATION);
    }//metodo

    private void solicitarPermissao(String permissao) {
        int onPermissao = ContextCompat.checkSelfPermission(this, permissao);
        if (onPermissao != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permissao},
                    COD_SOLICITACAO);
        } else {
            setResultadoActivity(permissao);
        }
    }//metodo

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissoes,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissoes, grantResults);
        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setResultadoActivity(permissoes[0]);
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        MainActivity.this, permissoes[0])) {
                    AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
                    dialog.setTitle("Atenção!");
                    dialog.setMessage("A permissão é necessária para habilitar "+ permissoes[0]);
                    dialog.setCancelable(false);
                    dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Sim",
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                                    permissoes[0]}, COD_SOLICITACAO);
                        }//onClick
                    });
                    dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Não",
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "A permissão era necessária " +
                                    "para funcionar", Toast.LENGTH_SHORT).show();
                            finish();
                        }//onClick
                    });
                    dialog.show();
                } else {
                    finish();
                }//else
            }//else if
        }//if
    }//metodo

    private void setResultadoActivity(String permissao) {
        Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
        intent.putExtra("permissao", permissao);
        startActivity(intent);
    }//metodo

    public void clicar(View view) {
        tratarPermissao(view);
    }//metodo

}//classe