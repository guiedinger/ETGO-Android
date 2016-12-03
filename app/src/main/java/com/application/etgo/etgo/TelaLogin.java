package com.application.etgo.etgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import POJOS.Login;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.REST.LoginConnectionManager;

public class TelaLogin extends Activity {

    private EditText etLogin, etSenha;
    private Button btEntrar;
    private Login login;
    private TextView tvCadastrese;
    private ProgressBar pbLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        this.inicializaComponentes();
        this.tvCadastrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaCadastrar();
            }
        });
        this.btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btEntrar.setEnabled(false);
                pbLogin.setVisibility(View.VISIBLE);
/*                login.setUserName(etLogin.getText().toString());
                login.setPassword(etSenha.getText().toString());*/
                LoginConnectionManager.posForLogin(etLogin.getText().toString(),etSenha.getText().toString()).enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if(response.isSuccessful()){
                            chamaTelaPassageiro();
                        }else{
                            chamaToastDadosInvalidos();
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        chamaToastErro();
                        Log.i("debug","io error :"+t.getMessage());
                    }
                });
                btEntrar.setEnabled(true);
                pbLogin.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void chamaTelaCadastrar(){
        Intent itTelaCadastrar = new Intent(this, TelaCadastro.class);
        startActivity(itTelaCadastrar);
        finish();
    }

    private void chamaTelaPassageiro(){
        Intent itTelaPassageiro = new Intent(this, TelaPassageiro.class);
        startActivity(itTelaPassageiro);
        finish();
    }

    public void chamaToastDadosInvalidos(){
        Toast.makeText(TelaLogin.this, "Dados Inválidos!",Toast.LENGTH_LONG).show();
    }

    public void chamaToastErro(){
        Toast.makeText(TelaLogin.this,"Erro ao logar!",Toast.LENGTH_LONG).show();
    }


    public void inicializaComponentes(){
        this.etLogin = (EditText)findViewById(R.id.et_login);
        this.etSenha = (EditText)findViewById(R.id.et_senha);
        this.btEntrar = (Button)findViewById(R.id.bt_entrar);
        this.tvCadastrese = (TextView)findViewById(R.id.tv_cadastrese);
        this.pbLogin = (ProgressBar) findViewById(R.id.pb_login);
        this.login = new Login();
    }

}
