package com.danilorocha.cardviewapp.entity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danilorocha.cardviewapp.R;

import java.util.List;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder> {
    private List<Pessoa> lista;
    private View.OnClickListener onItemClickListener;

    public PessoaAdapter(List<Pessoa> lista) {
        this.lista = lista;
    }//construtor

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public Button btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtViewDados);
            btn = itemView.findViewById(R.id.btnExcluir);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }
    }//classe interna

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.simple_list_recycler_view, parent, false
        );
        return new MyViewHolder(view);
    }//metodo

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(String.valueOf(lista.get(position)));
        holder.btn.setOnClickListener(view -> removerItem(position));
    }//metodo

    @Override
    public int getItemCount() {
        /*return lista != null ? lista.size() : 0;*/
        return lista.size();
    }//metodo

    public void setItemClick(View.OnClickListener itemClick) {
        this.onItemClickListener = itemClick;
    }//metodo

    private void removerItem(int position) {
        lista.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, lista.size());
    }//metodo

}//classe
