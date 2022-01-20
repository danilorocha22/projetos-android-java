package com.danilorocha.app.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danilorocha.app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.MyViewHolder> {
    private List<Contato> contatos;
    private List<Contato> contatosFilter;
    private View.OnClickListener onClickListener;

    public ContatoAdapter(List<Contato> contatos) {
        this.contatos = contatos;
        contatosFilter = new ArrayList<>();
        contatosFilter.addAll(contatos);
    }//construtor

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtViewItemDaLista);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }//construtor
    }//classe interna

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_da_lista, parent, false
        );
        return new MyViewHolder(view);
    }//metodo

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(contatos.get(position).getNome().toUpperCase());
    }//metodo

    @Override
    public int getItemCount() {
        return contatos.size();
    }//metodo

    public void setItemClick(View.OnClickListener itemClick) {
        onClickListener = itemClick;
    }//metodo

    public void filtro(String name) {
        contatos.clear();
        if (name.length() == 0) {
            contatos.addAll(contatosFilter);
        } else {
            for (Contato contato : contatosFilter) {
                if (contato.getNome().toLowerCase(Locale.getDefault()).contains(name)) {
                    contatos.add(contato);
                }//if
            }//for
        }//else
        notifyDataSetChanged();
    }//metodo

}//classe
