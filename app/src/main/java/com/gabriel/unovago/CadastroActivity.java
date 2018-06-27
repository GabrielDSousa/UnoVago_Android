package com.gabriel.unovago;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gabriel.unovago.dto.clientes.NovoClienteSync;
import com.gabriel.unovago.envioDados.NovoCliente;
import com.gabriel.unovago.model.Cliente;
import com.gabriel.unovago.retrofit.RetrofitInializador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    EditText nome;
    EditText email;
    EditText senha;
    EditText confirmaSenha;
    Button btnCadastar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        btnCadastar = findViewById(R.id.cadastro_cadastrar);
        btnCadastar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cadastrar();
            }
        });
    }

    private void Cadastrar() {
        nome = findViewById(R.id.cadastro_nome);
        email = findViewById(R.id.cadastro_email);
        senha = findViewById(R.id.cadastro_senha);
        confirmaSenha = findViewById(R.id.cadastro_confirmaSenha);
        if( nome.getText().toString().trim().equals(""))
        {
            nome.setError( "Nome é um campo obrigatório" );
            nome.setHint("Nome com um email");
        } else if( email.getText().toString().trim().equals(""))
        {
            email.setError( "Email é um campo obrigatório" );
            email.setHint("Entre com um email");
        } else if (senha.getText().toString().trim().equals(""))
        {
            senha.setError( "Senha é um campo obrigatório" );
            senha.setHint("Entre com uma senha");
        } else if (confirmaSenha.getText().toString().trim().equals(""))
        {
            confirmaSenha.setError( "Confirmação é um campo obrigatório" );
            confirmaSenha.setHint("Entre com uma senha para conformação");
        }else if (!confirmaSenha.getText().toString().equals(senha.getText().toString()) )
        {
            senha.setError( "Confirmação não bate com a senha" );
            confirmaSenha.setError( "Confirmação não bate com a senha" );
        }else{
            String nomeS = nome.getText().toString();
            String emailS = email.getText().toString();
            String senhaS = senha.getText().toString();
            NovoCliente cliente = new NovoCliente(nomeS,emailS,senhaS);
            final Call<NovoClienteSync> novoClienteSyncCall = new RetrofitInializador().getClienteService().novoCliente(cliente);
            novoClienteSyncCall.enqueue(new Callback<NovoClienteSync>() {
                @Override
                public void onResponse(Call<NovoClienteSync> call, Response<NovoClienteSync> response) {
                    NovoClienteSync novoCiente = response.body();
                    if(novoCiente.getCliente().getCreatedAt() != null){
                        String[] bemVindo = novoCiente.getCliente().getNome().split(" ");
                        Toast.makeText(CadastroActivity.this, bemVindo[0]+" agora é só fazer o login", Toast.LENGTH_SHORT).show();
                        Intent vaiParaTelaDeLogin = new Intent(CadastroActivity.this, MainActivity.class);
                        startActivity(vaiParaTelaDeLogin);
                        finish();
                    }else{
                        Throwable ex = new Throwable("NovoClienteSync retornou erro 404");
                        onFailure(novoClienteSyncCall, ex);
                    }
                }

                @Override
                public void onFailure(Call<NovoClienteSync> call, Throwable t) {
                    Toast.makeText(CadastroActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("onFailure", t.getMessage());
                }
            });
        }
    }
}
