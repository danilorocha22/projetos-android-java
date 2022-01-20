package com.danilorocha.exemplopermissoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private static final int COD_SOLICITACAO = 1;
    private static final String PERMISSAO = Manifest.permission.CAMERA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btnHello);
    }//onCreate

    private void solicitarPermissao() {
        //checkSelfPermission returna um inteiro
        int temPermissao = ContextCompat.checkSelfPermission(this, PERMISSAO);

        //Comparar o inteiro anterior, se for diferente de zero o app não tem a permissão e será
        // necessário o method requestPermissions
        if (temPermissao != PackageManager.PERMISSION_GRANTED) {
            //requestPermissions tem um callback que devolve o resultado das permissões
            ActivityCompat.requestPermissions(this, new String[]{PERMISSAO},
                    COD_SOLICITACAO);
        } else {
            setSegundaActivity();
        }//else
    }//method

    //retorno do método requestPermissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setSegundaActivity();
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        MainActivity.this, PERMISSAO)) {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Atenção")
                            .setMessage("A permissão é necessária para ...")
                            .setCancelable(false)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ActivityCompat.requestPermissions(MainActivity.this,
                                            new String[]{PERMISSAO}, COD_SOLICITACAO);
                                }//onClick
                            })
                            .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getApplicationContext(),
                                            "É necessário a permissão para funcionar",
                                            Toast.LENGTH_SHORT).show();
                                    finish();
                                }//onClick
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    finish();
                }//else
            }//else if
        }//if
    }//method

    public void clicar(View view) {
        solicitarPermissao();
    }

    private void setSegundaActivity() {
        Intent intent = new Intent(getApplicationContext(), SegundaActivity.class);
        startActivity(intent);
    }//method

}//class