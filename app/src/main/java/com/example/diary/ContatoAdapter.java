package com.example.diary;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContatoAdapter extends BaseAdapter {

    private List<Contato> contatos;
    private Activity act;

    public ContatoAdapter(Activity act,List<Contato> contatos) {
        this.act = act;
        this.contatos = contatos;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int i) {
        return contatos.get(i);
    }

    @Override
    public long getItemId(int i) {

        return contatos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vw = act.getLayoutInflater().inflate(R.layout.item, viewGroup,false);

        TextView nome = vw.findViewById(R.id.txt_nome);
        TextView inst = vw.findViewById(R.id.txt_instrumento);
        TextView cont = vw.findViewById(R.id.txt_contato);
        TextView end = vw.findViewById(R.id.txt_endereco);

        Contato ct = contatos.get(i);
        nome.setText(ct.getNome());
        inst.setText(ct.getInstrumento());
        cont.setText(ct.getContato());
        end.setText(ct.getEndereco());

        return vw;
    }
}
