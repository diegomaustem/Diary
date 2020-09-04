package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListarContatosActivity extends AppCompatActivity {

    private ListView listView;
    private ContatoDAO dao;
    private List<Contato> contato;
    private List<Contato> contatosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contatos);

        listView = findViewById(R.id.lista_contatos);
        dao = new ContatoDAO(this);
        contato = dao.obterTodos();
        contatosFiltrados.addAll(contato);
        //ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,contatosFiltrados);
        ContatoAdapter adapt = new ContatoAdapter(this,contatosFiltrados);
        listView.setAdapter(adapt);
        registerForContextMenu(listView);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contato, menu);
        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                procuraContato(s);
                return false;
            }
        });
        return true;

    }


    public void onCreateContextMenu(android.view.ContextMenu menu, android.view.View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_secundario, menu);
    }

    public void procuraContato(String nome){
        contatosFiltrados.clear();
        for(Contato c : contato){
            if(c.getNome().toLowerCase().contains(nome.toLowerCase())){
                contatosFiltrados.add(c);
            }
        }
        listView.invalidateViews();
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Contato contatoExcluir = contatosFiltrados.get(menuInfo.position);


        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir contato?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        contatosFiltrados.remove(contatoExcluir);
                        contato.remove(contatoExcluir);
                        dao.excluir(contatoExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, CadastroContatoActivity.class);
        startActivity(it);
    }


    public void atualizar(MenuItem item){

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Contato contatoAtualizar = contatosFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastroContatoActivity.class);
        it.putExtra("contato", contatoAtualizar);
        startActivity(it);
    }

    @Override
    public void onResume(){
        super.onResume();
        contato = dao.obterTodos();
        contatosFiltrados.clear();
        contatosFiltrados.addAll(contato);
        listView.invalidateViews();
    }

    public void chamaContato(View view){

    }
}
