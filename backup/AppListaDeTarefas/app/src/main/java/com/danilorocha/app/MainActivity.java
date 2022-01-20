package com.danilorocha.app;

import android.os.Bundle;
import android.view.View;

import com.danilorocha.app.ui.novaTarefa.NovaTarefaFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.danilorocha.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private NovaTarefaFragment novaTarefaFragment;
    private CardView cardNovaTarefa;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dashboard, R.id.navigation_tarefa_agendada, R.id.navigation_tarefa_concluida,
                R.id.navigation_nova_tarefa)
                .build();
        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        //novaTarefaFragment = new NovaTarefaFragment();
        //cardNovaTarefa = findViewById(R.id.cardNovaTarefa);
        //cardNovaTarefa.setOnClickListener(novaTarefa());
    }//onCreate

    /*private View.OnClickListener novaTarefa() {
        return v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.idFragmentNovaTarefa, novaTarefaFragment);
            transaction.commit();
        };
    }//click*/


    /*public void click(View view) {
        switch (view.getId()) {
            case R.id.cardNovaTarefa:
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.navigation_nova_tarefa, novaTarefaFragment);
                transaction.commit();
                break;
        }//switch
    }//click*/

}//classe