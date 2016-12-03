package com.application.etgo.etgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import POJOS.Login;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.REST.LoginConnectionManager;

public class TelaCadastro extends Activity {

    private EditText etLoginCadastrar, etSenhaCadatrar;
    private Login login;
    private Button btCadastrar;
    private ProgressBar pbCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        this.inicializaComponentes();
        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btCadastrar.setEnabled(false);
                pbCadastrar.setVisibility(View.VISIBLE);
                Log.i("debug","passou");
                login.setUserName(etLoginCadastrar.getText().toString());
                login.setPassword(etSenhaCadatrar.getText().toString());

                LoginConnectionManager.postForCreate(login).enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        login = response.body();
                        chamaTelaLogin();
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Log.i("debug","NÃO CADASTROU  :"+t.getMessage());
                    }
                });
            }
        });

    }


    private void chamaTelaLogin(){
        Intent itTelaLogin = new Intent(this, TelaLogin.class);
        startActivity(itTelaLogin);
        finish();
    }

    public void inicializaComponentes(){
        this.etLoginCadastrar = (EditText)findViewById(R.id.et_login_cadastrar);
        this.etSenhaCadatrar = (EditText)findViewById(R.id.et_senha_cadastrar);
        this.btCadastrar = (Button)findViewById(R.id.bt_cadastrar);
        this.login = new Login();
        this.pbCadastrar = (ProgressBar)findViewById(R.id.pb_cadastrar);

    }


}
