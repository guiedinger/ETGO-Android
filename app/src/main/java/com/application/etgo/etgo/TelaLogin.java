package com.application.etgo.etgo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import POJOS.Login;
import ws.REST.LoginConsumer;

public class TelaLogin extends Activity {

    private EditText etLogin, etSenha;
    private Button btEntrar;
    private Login login;
    private TextView tvCadastrese;
    private LoginConsumer loginConsumer;
    private ResponseEntity<Login> loginResponseEntity;


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
                login.setUserName(etLogin.getText().toString());
                login.setPassword(etSenha.getText().toString());
                Log.i("debug","chegou");
                new HttpRequestTask().execute();
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

    public void chamaToastErro(){
        Toast.makeText(TelaLogin.this,"Erro ao logar!",Toast.LENGTH_LONG);
    }


    public void inicializaComponentes(){
        this.etLogin = (EditText)findViewById(R.id.et_login);
        this.etSenha = (EditText)findViewById(R.id.et_senha);
        this.btEntrar = (Button)findViewById(R.id.bt_entrar);
        this.tvCadastrese = (TextView)findViewById(R.id.tv_cadastrese);
        this.loginConsumer = new LoginConsumer();
        this.login = new Login();
        this.loginResponseEntity = new ResponseEntity<Login>(HttpStatus.ACCEPTED);
    }

    private class HttpRequestTask extends AsyncTask<Void,Void,Login> {
        @Override
        protected Login doInBackground(Void... params) {
            Log.i("debug",login.getUserName()+":"+login.getPassword());
            loginResponseEntity = loginConsumer.logar(login);
            if(loginResponseEntity.getStatusCode() == HttpStatus.OK){
                chamaTelaPassageiro();
            }else{
                //chamaToastErro();
                login.setToken("ERROR");
            }
            return login;
        }


        @Override
        protected void onPostExecute(Login login) {
            super.onPostExecute(login);

        }
    }
}
