package com.danilorocha.app.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.danilorocha.app.R;
import com.danilorocha.app.dao.ContatoDao;
import com.danilorocha.app.dao.Dao;
import com.danilorocha.app.dao.FabricaConexao;
import com.danilorocha.app.model.Contato;
import com.danilorocha.app.util.AgendaUtil;

import java.util.ArrayList;
import java.util.List;

public class ContatoActivity extends AppCompatActivity {
    private LinearLayout formularioEdicao;
    private AlertDialog alertEditar;
    private Contato contato;
    private List<TextView> txtViews = new ArrayList<>();
    private List<EditText> inputs = new ArrayList<>();
    private Dao dao = new Dao(this);
    private FabricaConexao conexao = new FabricaConexao(this);
    private ContatoDao contatoDao = new ContatoDao(conexao);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        formularioEdicao = findViewById(R.id.linearInputsEdicao);
        txtViews.add(findViewById(R.id.txtViewNome));
        txtViews.add(findViewById(R.id.txtViewFone));
        txtViews.add(findViewById(R.id.txtViewEmail));
        inputs.add(findViewById(R.id.inputEditarNome));
        inputs.add(findViewById(R.id.inputEditarFone));
        inputs.add(findViewById(R.id.inputEditarEmail));
        contato = (Contato) getIntent().getSerializableExtra("contato");
        AgendaUtil.mostrarContato(txtViews, contato);
    }//onCreate

    private void mostrarFormEdicao() {
        if (formularioEdicao.getParent() != null) {
            ((ViewGroup) formularioEdicao.getParent()).removeView(formularioEdicao);
            formularioEdicao.setVisibility(View.VISIBLE);
            AgendaUtil.carregarInputs(inputs, contato);
            alertEditar = new AlertDialog.Builder(this).create();
            alertEditar.setTitle("Editar Contato");
            alertEditar.setView((View) formularioEdicao);
            alertEditar.show();
        }//if
    }//metodo

    private boolean atualizarContato() {
        try {
            if (AgendaUtil.verificarInputs(inputs)) {
                Contato contato = AgendaUtil.instanciarContato();
                contato.setId(this.contato.getId());
                contatoDao.atualizarContato(contato);
                this.contato = contato;
                AgendaUtil.mostrarContato(txtViews, this.contato);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }//catch
        return false;
    }//metodo

    private void excluirContato() {
        contatoDao.excluirContato(contato.getId());
        AgendaUtil.toast(this,"Excluído com sucesso");
    }//metodo

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btnEditarContato:
                mostrarFormEdicao();
                break;

            case R.id.btnSalvar:
                if (atualizarContato()) {
                    alertEditar.cancel();
                    AgendaUtil.limparInputs(inputs);
                    AgendaUtil.toast(this,"Atualizado com sucesso");
                } else
                    AgendaUtil.toast(this,"Não foi possível atualizar");
                break;
        }//switch
    }//metodo

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(intent);
                finish();
                break;

            case R.id.btnExcluir:
                excluirContato();
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }//metodo

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }//metodo

}//classe