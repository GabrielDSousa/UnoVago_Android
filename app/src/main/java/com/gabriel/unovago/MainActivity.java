package com.gabriel.unovago;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gabriel.unovago.dao.ClienteDao;
import com.gabriel.unovago.database.Database;
import com.gabriel.unovago.dto.clientes.LoginSync;
import com.gabriel.unovago.envioDados.Login;
import com.gabriel.unovago.model.Cliente;
import com.gabriel.unovago.retrofit.RetrofitInializador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnCadastrar;
    Button btnEntrar;
    EditText email;
    EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database db = new Database(getApplicationContext());
        ClienteDao dao = new ClienteDao(db);

        Cliente cliente = dao.verificaLogin();

        if(cliente.getCreatedAt() != null){
            Intent vaiParaTelaDePesquisa = new Intent(MainActivity.this, PesquisaActivity.class);
            startActivity(vaiParaTelaDePesquisa);
            finish();
        }

        btnEntrar = findViewById(R.id.main_btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = findViewById(R.id.main_login_email);
                senha = findViewById(R.id.main_login_senha);

                if( email.getText().toString().trim().equals(""))
                {
                    email.setError( "Email é um campo obrigatório" );
                    email.setHint("Entre com um email");
                } else if (senha.getText().toString().trim().equals(""))
                {
                    senha.setError( "Senha é um campo obrigatório" );
                    senha.setHint("Entre com uma senha");
                } else{
                    Login(email.getText().toString(), senha.getText().toString());
                }
            }
        });

        btnCadastrar = findViewById(R.id.main_btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiParaCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(vaiParaCadastro);
            }
        });
    }

    private void Login(String email, String senha) {
        Login login = new Login(email, senha);
        final Call<LoginSync> entrar = new RetrofitInializador().getClienteService().entrar(login);
        entrar.enqueue(new Callback<LoginSync>() {
            @Override
            public void onResponse(Call<LoginSync> call, Response<LoginSync> response) {
                LoginSync loginSync = response.body();
                if(loginSync != null){
                    Cliente cliente = loginSync.getCliente();
                    Database db = new Database(getApplicationContext());
                    ClienteDao dao = new ClienteDao(db);
                    dao.login(cliente);

                    Intent vaiParaTelaDePesquisa = new Intent(MainActivity.this, PesquisaActivity.class);
                    startActivity(vaiParaTelaDePesquisa);
                    finish();
                }else{
                    Throwable ex = new Throwable("Não foi possível fazer login, verifique suas credenciais.");
                    onFailure(entrar, ex);
                }
            }

            @Override
            public void onFailure(Call<LoginSync> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("onFailure", t.getMessage());
            }
        });
    }
}
