package com.gabriel.unovago;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gabriel.unovago.dao.ClienteDao;
import com.gabriel.unovago.database.Database;
import com.gabriel.unovago.dto.clientes.PassagensSync.PassagensSync;
import com.gabriel.unovago.dto.voos.VoosSync.VooSync;
import com.gabriel.unovago.model.Cliente;
import com.gabriel.unovago.retrofit.RetrofitInializador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetalheDoVooActivity extends AppCompatActivity {

    TextView destino;
    TextView origem;
    TextView dtSaida;
    TextView dtChegada;
    TextView preco;
    TextView aeronave;
    Button btnComprar;
    VooSync voo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_do_voo);

        destino = findViewById(R.id.detalhe_destino);
        origem = findViewById((R.id.detalhe_origem));
        dtSaida = findViewById(R.id.detalhe_data_partida);
        dtChegada = findViewById(R.id.detalhe_data_chegada);
        preco = findViewById(R.id.detalhe_preco);
        aeronave = findViewById(R.id.detalhe_aeronave);

        Intent intent = getIntent();
        voo = (VooSync)  intent.getSerializableExtra("voo");


        destino.setText(voo.getDestino());
        origem.setText(voo.getOrigem());
        dtSaida.setText(voo.getDataSaida());
        dtChegada.setText(voo.getDataChegada());
        preco.setText(String.valueOf(voo.getValor()));
        aeronave.setText(String.valueOf(voo.getAeronave().getNome()));

        btnComprar = findViewById(R.id.detalhe_comprar);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext());
                ClienteDao dao = new ClienteDao(db);
                Cliente cliente = dao.verificaLogin();

                Intent vaiParaCompra = new Intent(DetalheDoVooActivity.this, CompraActivity.class);
                vaiParaCompra.putExtra("voo", voo);
                startActivity(vaiParaCompra);

            }
        });

    }
}
