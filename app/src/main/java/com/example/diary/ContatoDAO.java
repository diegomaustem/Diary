package com.example.diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private Conexao conexao;
    private SQLiteDatabase banco ;

    public ContatoDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir (Contato contato) {
        ContentValues values = new ContentValues();
        values.put("nomeContato", contato.getNome());
        values.put("nomeInstrumento", contato.getInstrumento());
        values.put("Contato", contato.getContato());
        values.put("Endereço", contato.getEndereco());
        return banco.insert("contato", null, values);
    }


    public List<Contato> obterTodos(){
        List<Contato> contatos = new ArrayList<>();
        Cursor cursor = banco.query("contato", new String []{"id", "nomeContato", "nomeInstrumento", "Contato",
                "Endereço"},null, null,null,null,null );

        while(cursor.moveToNext()){
            Contato c = new Contato();
            c.setId(cursor.getInt(0));
            c.setContato(cursor.getString(1));
            c.setInstrumento(cursor.getString(2));
            c.setContato(cursor.getString(3));
            c.setEndereco(cursor.getString(4));
            contatos.add(c);
        }

        return contatos;
    }

    public void excluir(Contato c){
        banco.delete("contato", "id = ?", new String[]{c.getId().toString()});

    }

    public void atualizar(Contato contato){
        ContentValues values = new ContentValues();
        values.put("nomeContato", contato.getNome());
        values.put("nomeInstrumento", contato.getInstrumento());
        values.put("Contato", contato.getContato());
        values.put("Endereço", contato.getEndereco());
        banco.update("contato", values, "id = ?", new String[]{contato.getId().toString()});

    }
}