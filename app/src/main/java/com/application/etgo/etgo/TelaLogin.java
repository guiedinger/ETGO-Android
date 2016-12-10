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

import POJOS.Passageiro;
import POJOS.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.REST.LoginConnectionManager;

public class TelaLogin extends Activity {

    private EditText etLogin, etSenha;
    private Button btEntrar;
    private Token token;
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
/*                token.setUserName(etLogin.getText().toString());
                token.setPassword(etSenha.getText().toString());*/
                LoginConnectionManager.posForLogin(etLogin.getText().toString(),etSenha.getText().toString()).enqueue(new Callback<Passageiro>() {
                    @Override
                    public void onResponse(Call<Passageiro> call, Response<Passageiro> response) {
                        if(response.code()==200){
                            response.body();
                            chamaTelaPassageiro();
                        }else{
                            chamaToastDadosInvalidos();
                            btEntrar.setEnabled(true);
                            pbLogin.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<Passageiro> call, Throwable t) {
                        chamaToastErro();
                        Log.i("debug","io error :"+t.getMessage());
                        btEntrar.setEnabled(true);
                        pbLogin.setVisibility(View.INVISIBLE);
                    }
                });

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
        this.token = new Token();
    }

}
