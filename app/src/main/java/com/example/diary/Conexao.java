package com.example.diary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;

    public Conexao(Context context){
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table evento(id integer primary key autoincrement, " + "nomeEvento varchar(50)," +
                "nomeOrganizador varchar(50),dataEvento varchar(20)," +
                "horaEvento varchar(12),cidade varchar(20), endereco varchar(40) )");

        db.execSQL("create table contato(id integer primary key autoincrement, " + "nomeContato varchar(50)," +
                "nomeInstrumento varchar(50),contato varchar(20)," +
                "endereco varchar(12))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
