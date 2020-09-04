package com.example.diary;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/* Classe para acessar os dados do banco */

public class EventoDAO {

    private Conexao conexao;
    private SQLiteDatabase banco ;

    public EventoDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir (Evento evento) {
        ContentValues values = new ContentValues();
        values.put("nomeEvento", evento.getNomeEvento());
        values.put("nomeOrganizador", evento.getNomeOrganizador());
        values.put("dataEvento", evento.getDataEvento());
        values.put("horaEvento", evento.getHoraEvento());
        values.put("nomeEvento", evento.getNomeEvento());
        values.put("cidade", evento.getCidade());
        values.put("endereco", evento.getEndereco());
        return banco.insert("evento", null, values);
    }


    public List<Evento> listarTodos(){
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = banco.query("evento", new String []{"id", "nomeEvento", "nomeOrganizador", "dataEvento",
        "horaEvento","cidade","endereco"},null, null,null,null,null );

                while(cursor.moveToNext()){
                    Evento e = new Evento();
                    e.setId(cursor.getInt(0));
                    e.setNomeEvento(cursor.getString(1));
                    e.setNomeOrganizador(cursor.getString(2));
                    e.setDataEvento(cursor.getString(3));
                    e.setCidade(cursor.getString(4));
                    e.setHoraEvento(cursor.getString(5));
                    e.setEndereco(cursor.getString(6));

                    eventos.add(e);
                }

                    return eventos;
    }

    public void excluir(Evento e){
        banco.delete("evento", "id = ?", new String[]{e.getId().toString()});

    }
    public void atualizar(Evento evento){
        ContentValues values = new ContentValues();
        values.put("nomeEvento", evento.getNomeEvento());
        values.put("nomeOrganizador", evento.getNomeOrganizador());
        values.put("dataEvento", evento.getDataEvento());
        values.put("horaEvento", evento.getHoraEvento());
        values.put("nomeEvento", evento.getNomeEvento());
        values.put("cidade", evento.getCidade());
        values.put("endereco", evento.getEndereco());
        banco.update("evento", values, "id = ?", new String[]{evento.getId().toString()});

    }

}

