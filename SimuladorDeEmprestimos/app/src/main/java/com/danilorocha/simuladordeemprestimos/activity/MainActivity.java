package com.danilorocha.simuladordeemprestimos.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.danilorocha.simuladordeemprestimos.R;
import com.danilorocha.simuladordeemprestimos.entity.Cliente;

public class MainActivity extends AppCompatActivity {
    private Cliente cliente;
    private TextView viewNome, viewSalario, viewEmprestimo;
    private Spinner spinnerPrazo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewNome = findViewById(R.id.txtNome);
        viewSalario = findViewById(R.id.txtSalario);
        viewEmprestimo = findViewById(R.id.txtEmprestimo);
        spinnerPrazo = findViewById(R.id.spinnerPrazo);

        //passar as informações do xml para o spinner
        ArrayAdapter<CharSequence> adapterPrazo = ArrayAdapter.createFromResource(this,
                R.array.prazo, android.R.layout.simple_spinner_item);

        //setar o tipo do spinner
        adapterPrazo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //setar o adapter
        spinnerPrazo.setAdapter(adapterPrazo);
    }//onCreate

    public void getDados() {

        String nome = viewNome.getText().toString();
        String strSalario = viewSalario.getText().toString();
        String strEmprestimo = viewEmprestimo.getText().toString();
        int opcao = spinnerPrazo.getSelectedItemPosition();

        try {
            if (!nome.isEmpty() && !strSalario.isEmpty() && !strEmprestimo.isEmpty() && opcao > 0) {

                cliente = new Cliente(nome, Float.parseFloat(strSalario), Float.parseFloat(strEmprestimo));

                calcularValordaParcela(cliente, opcao);
            } else
                Toast.makeText(getApplicationContext(),"Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//metodo

    private float calcularValordaParcela(Cliente cliente, int opcao) {
        int prazo = cliente.getEmprestimo().prazo(opcao);
        float juros = cliente.getEmprestimo().calcularJuros(cliente.getValorEmprestimo(), prazo);
        return (cliente.getValorEmprestimo() + juros) / prazo;
        //verificarPossibilidadeDeEmprestimo(nome, salario, parcela, prazo);
    }//metodo

    private void verificarPossibilidadeDeEmprestimo(String nome, float salario, float parcela, int prazo) {
        String resultado = cliente.getEmprestimo().verificar(cliente.getSalario(), parcela);
        cliente.setParcela(parcela);
        mostrarResultado(resultado);
    }//metodo

    private Cliente criarCliente(String nome, float salario, float emprestimo) {
        return new Cliente(nome, salario, emprestimo);
    }//metodo

    private void mostrarResultado(String resultado) {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
        dialog.setTitle("Análise de Empréstimo");
        dialog.setMessage(cliente.toString()+"\n");
        dialog.setCancelable(false);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Fechar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewNome.setText(""); viewSalario.setText(""); viewEmprestimo.setText("");
                viewNome.requestFocus();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Editar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }//metodo

    public void click(View view) {getDados();}//metodo

}//classe