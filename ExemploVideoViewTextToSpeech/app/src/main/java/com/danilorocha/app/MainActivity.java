package com.danilorocha.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextNome);
    }//onCreate

    public void clicar(View view) {
        if (view.getId() == R.id.buttonProximo) {
            String nome = editText.getText().toString();
            Intent it = new Intent(getApplicationContext(), SegundaActivity.class);
            it.putExtra("nome", nome);
            startActivity(it);
            editText.setText("");
        }
    }//metodo

}//classe