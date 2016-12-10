package com.application.etgo.etgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import POJOS.Token;
import POJOS.Passageiro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.REST.LoginConnectionManager;

public class TelaCadastro extends Activity {

    private EditText etLoginCadastrar, etSenhaCadatrar, etNomeCadastrar, etTelefoneCadastrar, etEmailCadastrar, etCpfCnpjCadastrar;
    private Token token;
    private Passageiro passageiro;
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
                passageiro.setUserName(etLoginCadastrar.getText().toString());
                passageiro.setPassword(etSenhaCadatrar.getText().toString());
                passageiro.setNome(etNomeCadastrar.getText().toString());
                passageiro.setTelefone(etTelefoneCadastrar.getText().toString());
                passageiro.setEmail(etEmailCadastrar.getText().toString());
                passageiro.setCpf(etCpfCnpjCadastrar.getText().toString());
                LoginConnectionManager.postForCreate(passageiro).enqueue(new Callback<Passageiro>() {
                    @Override
                    public void onResponse(Call<Passageiro> call, Response<Passageiro> response) {
                        passageiro = response.body();
                        if (response.code()==200){
                            chamaTelaLogin();
                        }else{
                            btCadastrar.setEnabled(true);
                            pbCadastrar.setVisibility(View.INVISIBLE);
                            Toast.makeText(TelaCadastro.this, "Dados Inválidos!",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Passageiro> call, Throwable t) {
                        btCadastrar.setEnabled(true);
                        pbCadastrar.setVisibility(View.INVISIBLE);
                        Toast.makeText(TelaCadastro.this, "Problema ao conectar.",Toast.LENGTH_LONG).show();
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
        this.etNomeCadastrar = (EditText)findViewById(R.id.et_nome_cadastrar);
        this.etTelefoneCadastrar = (EditText)findViewById(R.id.et_telefone_cadastrar);
        this.etEmailCadastrar = (EditText)findViewById(R.id.et_email_cadastrar);
        this.etCpfCnpjCadastrar = (EditText)findViewById(R.id.et_cpf_cnpj_cadastrar);
        this.btCadastrar = (Button)findViewById(R.id.bt_cadastrar);
        this.pbCadastrar = (ProgressBar)findViewById(R.id.pb_cadastrar);
        this.token = new Token();
        this.passageiro = new Passageiro();

    }


}
