package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroEventoActivity extends AppCompatActivity {

    private EditText nomeEvento;
    private EditText nomeOrganizador;

    private EventoDAO dao;
    private Evento evento = null;

    private EditText dataEvento;
    private EditText horaEvento;
    private EditText cidade;
    private EditText endereco;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_eventos);

        nomeEvento = findViewById(R.id.editEvento);
        nomeOrganizador = findViewById(R.id.editOrganizador);
        dataEvento = findViewById(R.id.editData);
        horaEvento = findViewById(R.id.editHora);
        cidade = findViewById(R.id.editCidade);
        endereco = findViewById(R.id.editEndereco);
        dao = new EventoDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("evento")){
            evento = (Evento) it.getSerializableExtra("evento");
            nomeEvento.setText(evento.getNomeEvento());
            nomeOrganizador.setText(evento.getNomeOrganizador());
            dataEvento.setText(evento.getDataEvento());
            horaEvento.setText(evento.getHoraEvento());
            cidade.setText(evento.getCidade());
            endereco.setText(evento.getEndereco());
        }
    }

    public void salvar(View view){
        if(evento == null) {
            Evento evento = new Evento();
            evento.setNomeEvento(nomeEvento.getText().toString());
            evento.setNomeOrganizador(nomeOrganizador.getText().toString());
            evento.setDataEvento(dataEvento.getText().toString());
            evento.setHoraEvento(horaEvento.getText().toString());
            evento.setCidade(cidade.getText().toString());
            evento.setEndereco(endereco.getText().toString());
            long id = dao.inserir(evento);

            Toast.makeText(this, "Evento inserido com ID:" + id, Toast.LENGTH_SHORT).show();
        }else{
            evento.setNomeEvento(nomeEvento.getText().toString());
            evento.setNomeOrganizador(nomeOrganizador.getText().toString());
            evento.setDataEvento(dataEvento.getText().toString());
            evento.setHoraEvento(horaEvento.getText().toString());
            evento.setCidade(cidade.getText().toString());
            evento.setEndereco(endereco.getText().toString());
            dao.atualizar(evento);
            Toast.makeText(this, "Evento foi atuzalizado", Toast.LENGTH_SHORT).show();
        }
    }
}
