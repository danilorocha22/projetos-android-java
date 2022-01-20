package com.danilorocha.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.danilorocha.app.R;
import com.danilorocha.app.database.BDSQLite;

public class MainActivity extends AppCompatActivity {
    private BDSQLite bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }//onCreate

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btnCadastro:
                startActivity(CadastroActivity.class);
                break;

            case R.id.btnConsulta:
                startActivity(ConsultaActivity.class);
                break;

            case R.id.btnExclui:
                startActivity(ExcluiActivity.class);
                break;

            case R.id.btnAtualiza:
                startActivity(AtualizaActivity.class);
                break;
        }//switch
    }//click

    private void startActivity(Class activity) {
        Intent it = new Intent(this, activity);
        startActivity(it);
    }//metodo

    @Override
    protected void onDestroy() {
        bd.close();
        super.onDestroy();
    }//onDestroy


}//classe