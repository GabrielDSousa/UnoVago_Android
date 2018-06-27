package com.gabriel.unovago;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.gabriel.unovago.dao.ClienteDao;
import com.gabriel.unovago.database.Database;
import com.gabriel.unovago.dto.voos.VoosSync.VoosSync;
import com.gabriel.unovago.dto.voos.VoosSync.VooSync;
import com.gabriel.unovago.envioDados.Pesquisar;
import com.gabriel.unovago.model.Cliente;
import com.gabriel.unovago.retrofit.RetrofitInializador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesquisaActivity extends AppCompatActivity {

    Spinner spinnerDestinos;
    Spinner spinnerOrigens;
    Spinner spinnerDatasIda;
    Button btnPesquisar;
    ListView listVoos;

//    Set<String> destinos = new HashSet<String>();
//    Set<String> origens = new HashSet<String>();
//    Set<String> datasIda = new HashSet<String>();

    List<String> destinoUnique = new ArrayList<>();
    List<String> origensUnique = new ArrayList<>();
    List<String> datasIdaUnique = new ArrayList<>();

    List<String> destinos =  new ArrayList<>();
    List<String> origens = new ArrayList<>();
    List<String> datasIda = new ArrayList<>();


    List<VooSync> publicListVoos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        spinnerDatasIda = findViewById(R.id.pesquisa_spninner_dtIda);
        spinnerDestinos = findViewById(R.id.pesquisa_spinner_destino);
        spinnerOrigens = findViewById(R.id.pesquisa_spinner_origem);
        listVoos = findViewById(R.id.pesquisar_listview_voos);

        destinos.add("Destino");
        origens.add("Origem");
        datasIda.add("Data");

        RetrofitVoos();

        btnPesquisar = findViewById(R.id.pesquisa_btnPesquisa);
        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDestino = (String) spinnerDestinos.getSelectedItem();
                String selectedOrigem = (String) spinnerOrigens.getSelectedItem();
                String selectedDataIda = (String) spinnerDatasIda.getSelectedItem();

                Pesquisar pesquisa = new Pesquisar(selectedOrigem,selectedDestino,selectedDataIda,"");

                final Call<VoosSync> voosServicePesquisa = new RetrofitInializador().getVooService().pesquisar(pesquisa);
                voosServicePesquisa.enqueue(new Callback<VoosSync>() {
                    @Override
                    public void onResponse(Call<VoosSync> call, Response<VoosSync> response) {
                        VoosSync voosPesquisa = response.body();
                        if(voosPesquisa != null){
                            if(voosPesquisa.getVoos().size() > 0){
                                ArrayAdapter<VooSync> adapter = new ArrayAdapter<VooSync>(PesquisaActivity.this, android.R.layout.simple_list_item_1, voosPesquisa.getVoos());
                                listVoos.setAdapter(adapter);
                            }else {
                                Throwable ex = new Throwable("Pesquisar retornou 0 voos");
                                onFailure(voosServicePesquisa, ex);
                            }
                        }else {
                            Throwable ex = new Throwable("Preencha todos os campos");
                            onFailure(voosServicePesquisa, ex);
                        }

                    }

                    @Override
                    public void onFailure(Call<VoosSync> call, Throwable t) {
                        Toast.makeText(PesquisaActivity.this, "Não há vôos com essas condições, tente outra combinação.", Toast.LENGTH_LONG).show();
                        Log.e("onFailure", t.getMessage());
                    }
                });

            }
        });

        listVoos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VooSync voo = (VooSync) listVoos.getItemAtPosition(position);
                Intent vaiParaDetalheDoVoo = new Intent(PesquisaActivity.this, DetalheDoVooActivity.class);
                vaiParaDetalheDoVoo.putExtra("voo", voo);
                startActivity(vaiParaDetalheDoVoo);
            }
        });

    }



    @Override
    protected void onResume() {
        preencheSpinners();
        super.onResume();
    }

    private void RetrofitVoos() {
        final Call<VoosSync> voosService = new RetrofitInializador().getVooService().voos();
        publicListVoos = new ArrayList<>();
        voosService.enqueue(new Callback<VoosSync>() {
            @Override
            public void onResponse(Call<VoosSync> call, Response<VoosSync> response) {
                VoosSync voos = response.body();
                if(voos != null){
                    for (VooSync voo :
                            voos.getVoos()) {
                        destinos.add(voo.getDestino());
                        origens.add(voo.getOrigem());
                        datasIda.add(voo.getDataSaida());
                        publicListVoos.add(voo);
                    }

                    Set<String> dSet = new HashSet<String>(destinos);
                    destinoUnique.addAll(dSet);
                    Set<String> oSet = new HashSet<String>(origens);
                    origensUnique.addAll(oSet);
                    Set<String> dISet = new HashSet<String>(datasIda);
                    datasIdaUnique.addAll(dISet);
                }else{
                    Throwable ex = new Throwable("Voos retornou erro 404");
                    onFailure(voosService, ex);
                }
            }

            @Override
            public void onFailure(Call<VoosSync> call, Throwable t) {
                Toast.makeText(PesquisaActivity.this, "Não foi possível recuperar os voos.", Toast.LENGTH_LONG).show();
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_main_passagens:
                Intent vaiParaMinhasPassagens = new Intent(PesquisaActivity.this, MinhasPassagensActivity.class);
                startActivity(vaiParaMinhasPassagens);
                break;
            case R.id.menu_main_sair:
                Database db = new Database(getApplicationContext());
                ClienteDao dao = new ClienteDao(db);
                Cliente cliente = dao.verificaLogin();
                dao.logout(cliente.getId());
                Intent vaiParaLogin = new Intent(PesquisaActivity.this, MainActivity.class);
                startActivity(vaiParaLogin);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void preencheSpinners() {
        spinnerDatasIda = findViewById(R.id.pesquisa_spninner_dtIda);
        spinnerDestinos = findViewById(R.id.pesquisa_spinner_destino);
        spinnerOrigens = findViewById(R.id.pesquisa_spinner_origem);


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(PesquisaActivity.this, android.R.layout.simple_spinner_item, datasIda);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDatasIda.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(PesquisaActivity.this, android.R.layout.simple_spinner_item, destinos);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDestinos.setAdapter(adapter2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PesquisaActivity.this, android.R.layout.simple_spinner_item, origens);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigens.setAdapter(adapter);
    }
}
