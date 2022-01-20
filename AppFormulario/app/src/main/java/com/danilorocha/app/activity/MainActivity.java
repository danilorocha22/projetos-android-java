package com.danilorocha.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.danilorocha.app.R;
import com.danilorocha.app.entity.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText inputNome, inputIdade;
    private RadioGroup radioGroup;
    private CheckBox checkRock, checkSertanejo, checkPagode, checkForro, checkPop, checkOutro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputNome = findViewById(R.id.inputNome);
        inputIdade = findViewById(R.id.inputIdade);
        radioGroup = findViewById(R.id.radioGroup);
        checkRock = findViewById(R.id.checkRock);
        checkSertanejo = findViewById(R.id.checkSertanejo);
        checkPagode = findViewById(R.id.checkPagode);
        checkForro = findViewById(R.id.checkForro);
        checkPop = findViewById(R.id.checkPop);
        checkOutro = findViewById(R.id.checkOutro);
    }//onCreate

    public void click(View v) {
        salvarUsuario();
    }//click

    private void salvarUsuario() {
        Usuario usuario = criarUsuario();
        try {
            if (!usuario.equals(null)) {
                Usuario.listaUsuarios.add(usuario);
                enviarResultado();
            } else {
                toast();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//salvarUsuario

    private Usuario criarUsuario() {
        String nome = inputNome.getText().toString();
        String idade = inputIdade.getText().toString();

        try {
            if (!nome.isEmpty() && !idade.isEmpty() && radioGroup.getCheckedRadioButtonId()!=-1) {
                String sexo = null;
                if (!verificarSexo().equals(null))
                    sexo = verificarSexo();
                Usuario usuario = new Usuario(nome, Integer.valueOf(idade), sexo);
                setEstilosMusicais(usuario);
                return usuario;
            } else {
                toast();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }//criarUsuario

    private void enviarResultado() {
        Intent intent = new Intent(this, ResultadoActivity.class);
        startActivity(intent);
        limparInputs();
    }//enviarResultado

    private void setEstilosMusicais(Usuario usuario) {
        List<String> lista = new ArrayList<String>();
        if (checkRock.isChecked())
            lista.add(checkRock.getText().toString()) ;
        if (checkSertanejo.isChecked())
            lista.add(checkSertanejo.getText().toString());
        if (checkPagode.isChecked())
            lista.add(checkPagode.getText().toString());
        if (checkForro.isChecked())
            lista.add(checkForro.getText().toString()) ;
        if (checkPop.isChecked())
            lista.add(checkPop.getText().toString()) ;
        if (checkOutro.isChecked())
            lista.add(checkOutro.getText().toString()) ;
        usuario.setEstilosMusicais(lista);
    }//insere estilos musicais

    private String verificarSexo() {
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.radioMasc: return "Masculino";
            case R.id.radioFem: return "Feminino";
            default: return null;
        }
    }//verifica o sexo

    private void limparInputs() {
        inputNome.setText("");
        inputIdade.setText("");
        radioGroup.clearCheck();
        checkRock.setChecked(false);
        checkSertanejo.setChecked(false);
        checkPagode.setChecked(false);
        checkForro.setChecked(false);
        checkPop.setChecked(false);
        checkOutro.setChecked(false);
        inputNome.requestFocus();
    }//limpar entradas

    private void toast() {
        ViewGroup vg = findViewById(R.id.container_toast);
        Toast toast = new Toast(this);
        View view = getLayoutInflater().inflate(R.layout.toast_aviso, vg);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }//toast


}//classe