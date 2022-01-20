package com.danilorocha.sistemasolar.entitys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.danilorocha.sistemasolar.R;

import java.util.List;

public class PlanetaAdapter extends BaseAdapter {
    LayoutInflater inflater;
    List<Planeta> planetas;

    public PlanetaAdapter(Context context, List<Planeta> planetas) {
        this.inflater = LayoutInflater.from(context);
        this.planetas = planetas;
    }

    @Override
    public int getCount() {
        return planetas.size();
    }

    @Override
    public Object getItem(int i) {
        return planetas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Planeta planeta = planetas.get(i);
        view = inflater.inflate(R.layout.item_lista_simples_adapter, null);
        ((ImageView) view.findViewById(R.id.imgPlaneta)).setImageResource(planeta.getImagemId());
        ((TextView) view.findViewById(R.id.txtDescricao)).setText(planeta.getDescricao());
        return view;
    }


}//classe
