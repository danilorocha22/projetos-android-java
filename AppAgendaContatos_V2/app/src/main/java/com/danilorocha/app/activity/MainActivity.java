package com.danilorocha.app.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danilorocha.app.R;
import com.danilorocha.app.dao.ContatoDao;
import com.danilorocha.app.dao.Dao;
import com.danilorocha.app.dao.FabricaConexao;
import com.danilorocha.app.model.Contato;
import com.danilorocha.app.model.ContatoAdapter;
import com.danilorocha.app.util.AgendaUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayout formCadastro;
    private AlertDialog alertFormCadastro;
    private SearchView searchView;
    private ContatoAdapter adapter;
    private List<Contato> contatos;
    private List<EditText> inputs = new ArrayList<>();
    private Dao dao = new Dao(this);
    private FabricaConexao conexao = new FabricaConexao(this);
    private ContatoDao contatoDao = new ContatoDao(conexao);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewId);
        formCadastro = findViewById(R.id.linearInputsId);
        inputs.add(findViewById(R.id.inputNome));
        inputs.add(findViewById(R.id.inputFone));
        inputs.add(findViewById(R.id.inputEmail));
        searchView = findViewById(R.id.searchViewId);
        recyclerView.setHasFixedSize(true);
        dao.abrirBanco();
        listarContatos();
        searchView.setOnQueryTextListener(pesquisarContato());
    }//onCrate

    public void listarContatos() {
        contatos = contatoDao.listarContatos();
        adapter = new ContatoAdapter(contatos);
        adapter.setItemClick(clickItemLista());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }//metodo

    private View.OnClickListener clickItemLista() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
                int pos = viewHolder.getAdapterPosition();
                mostrarContato(contatos.get(pos));
            }
        };
    }//metodo

    private void mostrarContato(Contato contato) {
        Intent intent = new Intent(this, ContatoActivity.class);
        intent.putExtra("contato", contato);
        startActivity(intent);
    }//metodo

    private SearchView.OnQueryTextListener pesquisarContato() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String nome) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nome) {
                adapter.filtro(nome.toLowerCase());
                return false;
            }
        };
    }//metodo

    private void mostrarFormCadastro() {
        if (formCadastro.getParent() != null) {
            ((ViewGroup) formCadastro.getParent()).removeView(formCadastro);
            formCadastro.setVisibility(View.VISIBLE);
            alertFormCadastro = new AlertDialog.Builder(this).create();
            alertFormCadastro.setTitle("Novo Contato");
            alertFormCadastro.setView((View) formCadastro);
            alertFormCadastro.show();
        }//if
    }//metodo

    private boolean cadastrarContato() {
        try {
            if (AgendaUtil.verificarInputs(inputs)) {
                contatoDao.salvarContato(AgendaUtil.instanciarContato());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }//metodo

    public void click(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnFormularioCadastro:
                mostrarFormCadastro();
                break;

            case R.id.btnCadastrar:
                if (cadastrarContato()) {
                    listarContatos();
                    AgendaUtil.limparInputs(inputs);
                    alertFormCadastro.cancel();
                    AgendaUtil.toast(this, "Cadastrado com sucesso");
                } else
                    AgendaUtil.toast(this, "Preencha todos os campos");
                break;
        }//switch
    }//metodo

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dao.fecharBanco();
    }//metodo
}//classe