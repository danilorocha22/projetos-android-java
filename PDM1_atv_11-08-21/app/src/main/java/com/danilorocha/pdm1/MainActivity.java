package com.danilorocha.pdm1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editTextNome);
        button = (Button) findViewById(R.id.btnEnviar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = editText.getText().toString();
                Intent intent = new Intent(getActivity(), ResultadoActivity.class);
                intent.putExtra("dado", nome);
                startActivity(intent);
            }
        });
    }

    public Context getActivity(){
        return this;
    }

}