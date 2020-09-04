package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListarEventosActivity extends AppCompatActivity {


    private ListView listView;
    private EventoDAO dao;
    private List<Evento> eventos;
    private List<Evento> eventosFiltrados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_eventos);

        listView = findViewById(R.id.lista_eventos);
        dao = new EventoDAO(this);
        eventos = dao.listarTodos();
        eventosFiltrados.addAll(eventos);

        //arrayAdapter componente do Android
        //ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(this,android.R.layout.simple_list_item_1, eventosFiltrados);
        //Colocando no listview o adaptador
        EventoAdapter adaptador = new EventoAdapter(this,eventosFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);
        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraEvento(s);
                return false;
            }
        });
        return true;
    }


    public void onCreateContextMenu(android.view.ContextMenu menu, android.view.View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu,v, menuInfo);
            MenuInflater i = getMenuInflater();
            i.inflate(R.menu.menu_contexto, menu);
    }

    public void procuraEvento(String nomeEvento){
        eventosFiltrados.clear();
        for(Evento e : eventos){
            if(e.getNomeEvento().toLowerCase().contains(nomeEvento.toLowerCase())){
                eventosFiltrados.add(e);
            }
        }
        listView.invalidateViews();
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Evento eventoExcluir = eventosFiltrados.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir evento?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        eventosFiltrados.remove(eventoExcluir);
                        eventos.remove(eventoExcluir);
                        dao.excluir(eventoExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    public void cadastrarEvento(MenuItem item){
        Intent it = new Intent(this, CadastroEventoActivity.class);
        startActivity(it);
    }


    public void atualizar(MenuItem item){

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Evento eventoAtualizar = eventosFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastroEventoActivity.class);
        it.putExtra("evento", eventoAtualizar);
        startActivity(it);


    }

    @Override
    public void onResume(){
        super.onResume();
        eventos = dao.listarTodos();
        eventosFiltrados.clear();
        eventosFiltrados.addAll(eventos);
        listView.invalidateViews();
    }
}
