package com.danilorocha.app.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danilorocha.app.R;
import com.danilorocha.app.dao.AgendaDao;
import com.danilorocha.app.model.Contato;
import com.danilorocha.app.model.ContatoAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayout formulario, linearEditarExcluir;
    private EditText inputNome, inputFone, inputEmail;
    private Button btnCadastrar, btnAtualizar, btnAtualizarAlert;
    private AlertDialog alertCadastrar, alertAtualizar, alertAtualizarOuExcluir;
    private SearchView searchView;
    private String nome, fone, email;
    private ContatoAdapter adapter;
    private Contato contato;
    private List<Contato> contatos;
    private AgendaDao agendaDao = new AgendaDao(this);
    private final String CADASTRAR = "cadastrar", ATUALIZAR = "atualizar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewId);
        formulario = findViewById(R.id.linearInputsId);
        linearEditarExcluir = findViewById(R.id.linearEditarExcluir);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnAtualizarAlert = findViewById(R.id.btnAlertAtualizar);
        inputNome = findViewById(R.id.inputNome);
        inputFone = findViewById(R.id.inputFone);
        inputEmail = findViewById(R.id.inputEmail);
        searchView = findViewById(R.id.searchViewId);

        recyclerView.setHasFixedSize(true);
        listarContatos();
        searchView.setOnQueryTextListener(pesquisarContato());
    }//onCrate

    private void listarContatos() {
        contatos = agendaDao.listar();
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
                contato = contatos.get(pos);
                alertAtualizarOuExcluir();
            }
        };
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

    private void alertAtualizarOuExcluir() {
        if (linearEditarExcluir.getParent() != null) {
            ((ViewGroup) linearEditarExcluir.getParent()).removeView(linearEditarExcluir);
            linearEditarExcluir.setVisibility(View.VISIBLE);
            alertAtualizarOuExcluir = new AlertDialog.Builder(this).create();
            alertAtualizarOuExcluir.setView((View) linearEditarExcluir);
            alertAtualizarOuExcluir.show();
        }//if
    }//metodo

    private void criarFormulario(int id) {
        if (formulario.getParent() != null) {
            ((ViewGroup) formulario.getParent()).removeView(formulario);
            formulario.setVisibility(View.VISIBLE);

            switch (id) {
                case R.id.btnFlutuante:
                    formularioDeCadastro();
                    break;

                case R.id.btnAlertAtualizar:
                    formularioDeAtualizacao();
                    break;
            }//switch
        }//if
    }//metodo

    private void formularioDeCadastro() {
        alertCadastrar = new AlertDialog.Builder(this).create();
        alertCadastrar.setTitle("Novo Contato");
        alertCadastrar.setView((View) formulario);
        alertCadastrar.show();
        setBotao(CADASTRAR);
    }//metodo

    private void formularioDeAtualizacao() {
        carregarInputs();
        alertAtualizar = new AlertDialog.Builder(this).create();
        alertAtualizar.setTitle("Atualizar Contato");
        alertAtualizar.setView((View) formulario);
        alertAtualizar.show();
        setBotao(ATUALIZAR);
    }//metodo

    private void setBotao(String botao) {
        if (botao.equals(CADASTRAR)) {
            btnAtualizar.setVisibility(View.INVISIBLE);
            btnCadastrar.setVisibility(View.VISIBLE);
        } else {
            btnCadastrar.setVisibility(View.INVISIBLE);
            btnAtualizar.setVisibility(View.VISIBLE);
        }//else
    }//metodo

    private void cadastrarContato() {
        if (verificarInputs()) {
            agendaDao.inserir(criarContato());
            listarContatos();
            limparInputs();
            alertCadastrar.cancel();
            toast("Cadastrado com sucesso");
        } else {
            toast("Informe todos os campos");
        }//else
    }//metodo

    private void atualizarContato() {
        if (verificarInputs()) {
            Contato contato = criarContato();
            contato.setId(this.contato.getId());
            agendaDao.update(contato);
            listarContatos();
            limparInputs();
            toast("Atualizado com sucesso");
        } else {
            toast("Não foi possível atualizar contato");
        }//else
    }//metodo

    private void excluirContato() {
        agendaDao.excluir(contato.getId());
        toast("Excluído com sucesso");
        listarContatos();
        alertAtualizarOuExcluir.cancel();
    }//metodo

    private Contato criarContato() {
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setFone(fone);
        contato.setEmail(email);
        return contato;
    }//metodo

    private boolean verificarInputs() {
        nome = inputNome.getText().toString();
        fone = inputFone.getText().toString();
        email = inputEmail.getText().toString();
        return (!nome.isEmpty() && !fone.isEmpty() && !email.isEmpty());
    }//metodo

    private void carregarInputs() {
        inputNome.setText(contato.getNome());
        inputFone.setText(contato.getFone());
        inputEmail.setText(contato.getEmail());
    }//metodo

    private void limparInputs() {
        inputNome.setText("");
        inputFone.setText("");
        inputEmail.setText("");
        inputNome.requestFocus();
    }//metodo

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }//metodo

    public void click(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnFlutuante:
                limparInputs();
                criarFormulario(id);
                break;

            case R.id.btnAlertAtualizar:
                criarFormulario(id);
                break;

            case R.id.btnCadastrar:
                cadastrarContato();
                break;

            case R.id.btnAtualizar:
                atualizarContato();
                alertAtualizarOuExcluir.cancel();
                alertAtualizar.cancel();
                break;

            case R.id.btnExcluir:
                excluirContato();
                alertAtualizarOuExcluir.cancel();
                break;
        }//switch
    }//metodo
}//classe