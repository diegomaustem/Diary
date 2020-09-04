package com.example.diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroContatoActivity extends AppCompatActivity {

    private EditText nome;
    private EditText instrumento;
    private EditText contato;
    private EditText endereco;
    private ContatoDAO dao;
    private Contato contat = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        nome = findViewById(R.id.editNome);
        instrumento = findViewById(R.id.editInstrumento);
        contato = findViewById(R.id.editContato);
        endereco = findViewById(R.id.editEndereco);
        dao = new ContatoDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("contato")){
            contat = (Contato) it.getSerializableExtra("contato");
            nome.setText(contat.getContato());
            instrumento.setText(contat.getInstrumento());
            contato.setText(contat.getContato());
            endereco.setText(contat.getEndereco());
        }

    }

    public void salvar(View view) {

        if(contat == null) {
        Contato c = new Contato();
            c.setNome(nome.getText().toString());
            c.setInstrumento(instrumento.getText().toString());
            c.setContato(contato.getText().toString());
            c.setEndereco(endereco.getText().toString());

        long id =  dao.inserir(c);
        Toast.makeText(this, "Contato inserido com ID:" + id, Toast.LENGTH_SHORT).show();

    }else{
            contat.setNome(nome.getText().toString());
            contat.setInstrumento(instrumento.getText().toString());
            contat.setContato(contato.getText().toString());
            contat.setEndereco(endereco.getText().toString());
            dao.atualizar(contat);
            Toast.makeText(this, "Contato foi atualizado", Toast.LENGTH_SHORT).show();
        }

    }

}
