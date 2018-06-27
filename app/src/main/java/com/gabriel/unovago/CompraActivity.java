package com.gabriel.unovago;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gabriel.unovago.dao.ClienteDao;
import com.gabriel.unovago.database.Database;
import com.gabriel.unovago.dto.clientes.ValidarCartaoSync;
import com.gabriel.unovago.dto.voos.VoosSync.CompraSync;
import com.gabriel.unovago.dto.voos.VoosSync.VooSync;
import com.gabriel.unovago.envioDados.ComprarPassagem;
import com.gabriel.unovago.envioDados.ValidarCartao;
import com.gabriel.unovago.model.Cliente;
import com.gabriel.unovago.retrofit.RetrofitInializador;
import com.gabriel.unovago.utils.MaskEditUtil;

import java.lang.reflect.Array;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompraActivity extends AppCompatActivity {

    EditText numeroCartao;
    EditText nomeCartao;
    EditText cvv;
    EditText vencimento;
    Spinner quantidade;
    Button btnFinalizar;
    VooSync voo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        String[] qtd = {"Quantidade","1","2","3","4","5","6","7","8","9"};

        numeroCartao = findViewById(R.id.comprar_numeroCartao);
        nomeCartao = findViewById(R.id.comprar_nomeCartao);
        cvv = findViewById(R.id.comprar_cvv);
        vencimento = findViewById(R.id.comprar_vencimento);
        quantidade = findViewById(R.id.comprar_spinnerQtd);
        btnFinalizar = findViewById(R.id.comprar_btnFinalizar);

        vencimento.addTextChangedListener(MaskEditUtil.mask(vencimento, MaskEditUtil.FORMAT_VENCIMENTO));
        numeroCartao.addTextChangedListener(MaskEditUtil.mask(numeroCartao, MaskEditUtil.FORMAT_CARTAO));

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(CompraActivity.this, android.R.layout.simple_spinner_item, qtd);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantidade.setAdapter(adapter1);

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( numeroCartao.getText().toString().trim().equals("")  || numeroCartao.getText().toString().trim().length() < 16 )
                {
                    numeroCartao.setError( "Número de cartão é um campo obrigatório" );
                    numeroCartao.setHint("Entre com um número válido");
                } else if (nomeCartao.getText().toString().trim().equals(""))
                {
                    nomeCartao.setError( "Nome é um campo obrigatório" );
                    nomeCartao.setHint("Entre com um nome");
                }else if (cvv.getText().toString().trim().equals("") || cvv.getText().toString().trim().length() < 3)
                {
                    cvv.setError( "Cvv é um campo obrigatório" );
                    cvv.setHint("Entre com um cvv válido");
                }else if (vencimento.getText().toString().trim().equals("") || vencimento.getText().toString().trim().length() < 5)
                {
                    vencimento.setError( "Cvv é um campo obrigatório" );
                    vencimento.setHint("Entre com um código");
                }else if (quantidade.getSelectedItem().toString().equals("Quantidade"))
                {
                    Toast.makeText(CompraActivity.this, "Selecione uma quantidade validade", Toast.LENGTH_SHORT).show();
                }else{
                    ValidarCartao cartao = new ValidarCartao();
                    cartao.setCartao(numeroCartao.getText().toString());
                    final Call<ValidarCartaoSync> validarCartaoSyncCall = new RetrofitInializador().getClienteService().validarCartao(cartao);
                    validarCartaoSyncCall.enqueue(new Callback<ValidarCartaoSync>() {
                        @Override
                        public void onResponse(Call<ValidarCartaoSync> call, Response<ValidarCartaoSync> response) {
                            ValidarCartaoSync resultado = response.body();
                            if(resultado != null){
                                if(resultado.getIsValid().equals("true")){
                                    Intent intent = getIntent();
                                    voo = (VooSync)  intent.getSerializableExtra("voo");
                                    Database db = new Database(getApplicationContext());
                                    ClienteDao dao = new ClienteDao(db);
                                    Cliente cliente = dao.verificaLogin();

                                    ComprarPassagem compra = new ComprarPassagem();
                                    compra.setIdCliente(cliente.getId());
                                    compra.setIdVoo(voo.getId());
                                    int qtd = Integer.parseInt(quantidade.getSelectedItem().toString());
                                    compra.setQuantidade(qtd);
                                    compra.setPagamento((qtd*voo.getValor()));

                                    final Call<CompraSync> compraSyncCall = new RetrofitInializador().getVooService().comprarPassagem(compra);
                                    compraSyncCall.enqueue(new Callback<CompraSync>() {
                                        @Override
                                        public void onResponse(Call<CompraSync> call, Response<CompraSync> response) {
                                            CompraSync passagensCompras = response.body();
                                            if(passagensCompras != null){
                                                Intent vaiParaMinhasPassagens = new Intent(CompraActivity.this, MinhasPassagensActivity.class);
                                                startActivity(vaiParaMinhasPassagens);
                                                finish();

                                            }else{
                                                Throwable ex = new Throwable("Não foi possível comprar as passagens, todas foram vendidas");
                                                onFailure(compraSyncCall, ex);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CompraSync> call, Throwable t) {
                                            Toast.makeText(CompraActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }else {
                                    Throwable ex = new Throwable("Cartão na autorizado, utilizar outro");
                                    onFailure(validarCartaoSyncCall, ex);
                                }
                            }else{
                                Throwable ex = new Throwable("Cartão na autorizado, utilizar outro");
                                onFailure(validarCartaoSyncCall, ex);
                            }
                        }

                        @Override
                        public void onFailure(Call<ValidarCartaoSync> call, Throwable t) {
                            Toast.makeText(CompraActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
