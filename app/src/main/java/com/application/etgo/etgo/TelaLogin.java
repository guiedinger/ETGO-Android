package com.application.etgo.etgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import POJOS.Login;

public class TelaLogin extends Activity {

    private EditText etLogin, etSenha;
    private Button btEntrar;
    private Login login;
    private TextView tvCadastrese;


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
/*        this.btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setUserName(etLogin.getText().toString());
                login.setSenha(etSenha.getText().toString());

            }
        });*/
    }

    private void chamaTelaCadastrar(){
        Intent itTelaCadastrar = new Intent(this, TelaCadastro.class);
        startActivity(itTelaCadastrar);
        finish();
    }

    public void inicializaComponentes(){
        this.etLogin = (EditText)findViewById(R.id.et_login);
        this.etSenha = (EditText)findViewById(R.id.et_senha);
        this.btEntrar = (Button)findViewById(R.id.bt_entrar);
        this.tvCadastrese = (TextView)findViewById(R.id.tv_cadastrese);

    }
}
