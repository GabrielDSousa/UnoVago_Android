package com.gabriel.unovago;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gabriel.unovago.dao.ClienteDao;
import com.gabriel.unovago.database.Database;
import com.gabriel.unovago.dto.clientes.PassagensSync.PassagemSync;
import com.gabriel.unovago.dto.clientes.PassagensSync.PassagensSync;
import com.gabriel.unovago.dto.voos.VoosSync.VooSync;
import com.gabriel.unovago.model.Cliente;
import com.gabriel.unovago.retrofit.RetrofitInializador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MinhasPassagensActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_passagens);

        Database db = new Database(getApplicationContext());
        ClienteDao dao = new ClienteDao(db);
        Cliente cliente = dao.verificaLogin();

        final Call<PassagensSync> passagens = new RetrofitInializador().getClienteService().passagens(cliente.getId());

        passagens.enqueue(new Callback<PassagensSync>() {
            @Override
            public void onResponse(Call<PassagensSync> call, Response<PassagensSync> response) {
                PassagensSync resposta = response.body();
                if(resposta != null){
                    listView = findViewById(R.id.passagens_lista);
                    ArrayAdapter<PassagemSync> adapter = new ArrayAdapter<PassagemSync>(MinhasPassagensActivity.this, android.R.layout.simple_list_item_1,resposta.getPassagens());
                    listView.setAdapter(adapter);

                }else{
                    Throwable ex = new Throwable("Não há passagens");
                    onFailure(passagens, ex);
                }
            }

            @Override
            public void onFailure(Call<PassagensSync> call, Throwable t) {
                Toast.makeText(MinhasPassagensActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
