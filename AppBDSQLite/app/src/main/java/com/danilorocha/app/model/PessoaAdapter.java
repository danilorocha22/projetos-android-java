package com.danilorocha.app.model;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danilorocha.app.R;
import com.danilorocha.app.activity.ConsultaActivity;

import java.util.List;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder> {
    private List<Pessoa> pessoas;
    private View.OnClickListener onClickListener;

    public PessoaAdapter(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }//construtor

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtViewDados);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }//construtor
    }//classe interna

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.lista_simples_recylcer_view, parent, false
        );
        return new MyViewHolder(view);
    }//metodo

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(pessoas.get(position).toString());
    }//metodo

    @Override
    public int getItemCount() {
        return pessoas.size();
    }//metodo

    public void setItemClick(View.OnClickListener itemLick) {
        this.onClickListener = itemLick;
    }//metodo

}//classe
