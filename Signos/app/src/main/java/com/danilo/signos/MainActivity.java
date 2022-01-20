package com.danilo.signos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {
    
    private Spinner spinnerDia = null;
    private Spinner spinnerMes = null;

    private void validarData() {
        int dia = spinnerDia.getSelectedItemPosition();
        int mes = spinnerMes.getSelectedItemPosition();

        dia++;//o spinner inicia em zero, por isso é necessário o incremento;
        mes++;

        //aqui está sendo tratado a posição do spinner que começa em zero, então por exemplo
        //a posição 29 corresponde ao dia 30 e a posição 28 ao dia 29.
        if (dia > 29 && mes == 2) {
            spinnerDia.setSelection(28);
        } else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia > 30)) {
            spinnerDia.setSelection(29);
        }
    }

    @Override
    //este é o método que será executado ao se criar a aplicação
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //definindo qual é o layout, ou seja, arquivo xml que está atrelado ao código java

        /**
         * Estamos atribuindo as variaveis, os Objetos localizado na parte visual, ou seja, no XML
         */
        spinnerDia = (Spinner) findViewById(R.id.spinnerDia);//A classe 'R' é que faz a comunicação entre o Java e XML.
        spinnerMes = (Spinner) findViewById(R.id.spinnerMes);

        /**
         * Adapter serve para pegar os dados de uma fonte, seja uma base de dados ou um array, neste caso
         * ele vai pegar os dados do XML e colocar no spinner
         */
        ArrayAdapter<CharSequence> adapter_dia = ArrayAdapter.createFromResource(this, R.array.dias,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_mes = ArrayAdapter.createFromResource(this, R.array.meses,
                android.R.layout.simple_spinner_item);

        //setamos o tipo do spinner que será inserido nos objetos do array, q neste caso será do tipo dropdown
        adapter_dia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_mes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //setar o adapter
        spinnerDia.setAdapter(adapter_dia);
        spinnerMes.setAdapter(adapter_mes);

        //tratando o selected do dropdown do dia, parecido com que fazemos para tratar o click no botão
        spinnerDia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //chamada para o método de validar data
                validarData();
            }

            //esse método não nos interessa
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //a mesma coisa feita acima para o dia, é feito para o mês
        spinnerMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                validarData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //referenciar o botão enviar com XML
        Button enviar = (Button) findViewById(R.id.buttonEnviar);

        //tratar o click no botão
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicaoDia = spinnerDia.getSelectedItemPosition();
                int posicaoMes = spinnerMes.getSelectedItemPosition();

                posicaoDia++;//o spinner inicia em zero, por isso se faz necessário o incremento;
                posicaoMes++;

                InterpretadorSigno Interpretador = new InterpretadorSigno();

                Signo signoResultado = Interpretador.interpretar(posicaoDia, posicaoMes);

                //Para passar os dados do signo é atraves do Bundle
                Bundle args = new Bundle();
                args.putSerializable("resultado", signoResultado);

                //Intent é a forma de se trafegar dados entre as telas no android
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("signo", args);

                //Envia os dados do MainActivity para ResultActivity
                startActivity(intent);
            }
        });

    }
}