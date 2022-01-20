package com.danilorocha.pdm1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("all")
public class ResultadoActivity extends AppCompatActivity {

    private TextView textView;
    private Button btnToast, btnAlertDialog;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        nome = getIntent().getStringExtra("dado");

        textView = (TextView) findViewById(R.id.textViewResultado);
        textView.setText(nome);

        btnToast = (Button) findViewById(R.id.btnToast);
        btnToast.setOnClickListener(toast());

        btnAlertDialog = (Button) findViewById(R.id.btnDialog);
        btnAlertDialog.setOnClickListener(dialog());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private Context getActivity() {
        return this;
    }

    private View.OnClickListener toast() {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), nome,Toast.LENGTH_LONG).show();
            }
        };
    }

    private View.OnClickListener dialog() {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
                dialog.setTitle("Alerta");
                dialog.setMessage(nome);
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Sair", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}