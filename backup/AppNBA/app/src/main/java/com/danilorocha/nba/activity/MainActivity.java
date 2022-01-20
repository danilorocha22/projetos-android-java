package com.danilorocha.nba.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.danilorocha.nba.R;
import com.danilorocha.nba.dao.Dao;
import com.danilorocha.nba.dao.DescricaoDao;
import com.danilorocha.nba.dao.FabricaConexao;
import com.danilorocha.nba.dao.ResultadoDao;
import com.danilorocha.nba.dao.TimeCasaDao;
import com.danilorocha.nba.dao.TimeForaDao;
import com.danilorocha.nba.dao.VencedorDao;
import com.danilorocha.nba.entity.Jogo;
import com.danilorocha.nba.entity.GeraradorIds;
import com.danilorocha.nba.service.JogoService;
import com.danilorocha.nba.ui.resultado.Resultado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.danilorocha.nba.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Jogo jogo  = new Jogo();
    private List<Resultado> listaResultados = new ArrayList<>();
    private Dao dao = new Dao(this);
    private FabricaConexao conexao = new FabricaConexao(this);
    private DescricaoDao descricaoDao;
    private TimeCasaDao timeCasaDao;
    private TimeForaDao timeForaDao;
    private VencedorDao vencedorDao;
    private ResultadoDao resultadoDao;
    private int ct = 0;
    private final int STOP = 19;
    private static final int COD_SOLICITACAO = 1;
    private static final String PERMISSAO = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private List<Integer> ids = GeraradorIds.getIds(20, 1, 46941);
    /********************************************************************************/
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitarPermissao();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_descricao, R.id.nav_casa, R.id.nav_fora, R.id.nav_resultado,
                R.id.nav_vencedor)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController,
                mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /*******************************************************************************/

        dao.abrirBanco();
        descricaoDao = new DescricaoDao(conexao);
        timeCasaDao = new TimeCasaDao(conexao);
        timeForaDao = new TimeForaDao(conexao);
        vencedorDao = new VencedorDao(conexao);
        resultadoDao = new ResultadoDao(conexao);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JogoService.BASE_URL)//url
                .addConverterFactory(GsonConverterFactory.create())//conversor de objetos
                .build();//retorna objeto Retrofit construido

        //retorna uma classe que implementa o contrato da interface
        JogoService service = retrofit.create(JogoService.class);

        for (int id : ids) {
            //retorna um objeto que faz a chamada da API
            Call<Jogo> call = service.getJogo(id);
            call.enqueue(new Callback<Jogo>() {//callback que será executado de forma assincrona
                @Override
                public void onResponse(Call<Jogo> call, Response<Jogo> response) {//requisição com sucesso
                    try {
                        if (response.isSuccessful()) {
                            jogo.setListaJogos(response.body());//body() faz parse objeto Gson para objeto Java;
                            if (ct == STOP)
                                salvarDadosJogos(jogo);
                            ct++;
                            Log.i("Log onResponse", call.request().toString());
                        } else
                            System.err.println(response.code());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }//onResponse

                @Override
                public void onFailure(Call<Jogo> call, Throwable t) {//requisição falhou
                    toast("Não foi possível obter os dados do jogo https://www.balldontlie.io/api/v1/games/"+id);
                    Log.i("Log onFailure", t.getMessage());
                }});
        }//for
    }//onCreate

    public void salvarDadosJogos(Jogo jogo) {
        descricaoDao.salvarDados(jogo.getListaJogos());
        timeCasaDao.salvarDados(jogo.getListaJogos());
        timeForaDao.salvarDados(jogo.getListaJogos());
        resultadoDao.salvarDados(jogo.getListaJogos());
        vencedorDao.salvarDados(jogo.getListaJogos());
    }//metodo

    private void solicitarPermissao() {
        int onPermissao = ContextCompat.checkSelfPermission(this, PERMISSAO);
        if (onPermissao != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{PERMISSAO},
                    COD_SOLICITACAO);
        else
            imprimirResultados();
    }//solicita permissão

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissoes,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissoes, grantResults);
        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                imprimirResultados();
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        MainActivity.this, permissoes[0])) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Atenção!")
                            .setMessage("A permissão é necessária para habilitar Armazenamento Externo")
                            .setCancelable(false)
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                                            permissoes[0]}, COD_SOLICITACAO);
                                }})
                            .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    toast("Não foi possível armazenar a lista com resultados dos jogos");
                                }})
                            .show();
                } else
                    finish();
            }//else if
        }//if
    }//metodo

    private void imprimirResultados() {
        listaResultados = resultadoDao.obterDados();
        //cria um arquivo de preferência com o nome "arquivo" com modo de leitura "privado".
        SharedPreferences preferences = getSharedPreferences("Resultado_Jogos", Context.MODE_PRIVATE);
        //permite editar o arquivo
        SharedPreferences.Editor editor = preferences.edit();
        final String[] res = {""};
        listaResultados.stream().forEach(r -> res[0] +=  r.toString());
        //inclui ao arquivo, uma String com "chave/valor".
        editor.putString("resultados", res[0]);
        //faz com que a informação seja salva no arquivo
        editor.commit();
        //atualiza o TextView com o nome armazenado
        String resultados = preferences.getString("resultados",null);
        //mostra o conteúdo do arquivo no dialogs
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Lista com Resultados dos Jogos")
                .setMessage(resultados)
                .setCancelable(false)
                .setNeutralButton("Fechar", null)
                .show();
    }//imprime externamente os resultados

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_mapa:
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }//localização da Reitoria

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }//metodo

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }//metodo

    @Override
    protected void onDestroy() {
        dao.fecharBanco();
        super.onDestroy();
    }//metodo

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }//metodo

}//classe