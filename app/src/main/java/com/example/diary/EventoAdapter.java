package com.example.diary;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class EventoAdapter extends BaseAdapter {

    private List<Evento> eventos;
    private Activity activity;

    public EventoAdapter(Activity activity, List<Evento> eventos) {
        this.activity = activity;
        this.eventos = eventos;
    }

    @Override
    public int getCount() {

        return eventos.size();
    }

    @Override
    public Object getItem(int i) {
        return eventos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return eventos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = activity.getLayoutInflater().inflate(R.layout.item, viewGroup,false);

        TextView name = v.findViewById(R.id.txt_name);
        TextView even = v.findViewById(R.id.txt_instrumento);
        TextView org = v.findViewById(R.id.txt_instrumento);
        TextView dat = v.findViewById(R.id.txt_data);
        TextView hor = v.findViewById(R.id.txt_hora);
        TextView cid = v.findViewById(R.id.txt_cidade);
        TextView end = v.findViewById(R.id.txt_contato);

        Evento e = eventos.get(i);
        name.setText(e.getNomeEvento());
        even.setText(e.getNomeEvento());
        org.setText(e.getNomeOrganizador());
        dat.setText(e.getDataEvento());
        hor.setText(e.getHoraEvento());
        cid.setText(e.getCidade());
        end.setText(e.getEndereco());


        return v;
    }
}
