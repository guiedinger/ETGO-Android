package com.application.etgo.etgo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import POJOS.Empresa;
import POJOS.Passageiro;
import POJOS.Transportadora;
import POJOS.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.REST.ConnectionManager;

public class TelaLogin extends Activity {

    private EditText etLogin, etSenha;
    private Button btEntrar;
    private TextView tvCadastrese;
    private ProgressBar pbLogin;
    private Passageiro passageiro;
    private Transportadora transportadora;
    private Empresa empresa;
    private Bundle bundle;
    private ProgressDialog progressDialog;

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
                startLoad();
                ConnectionManager.posForLoginPassageiro(etLogin.getText().toString(),etSenha.getText().toString()).enqueue(new Callback<Passageiro>() {
                    @Override
                    public void onResponse(Call<Passageiro> call, Response<Passageiro> response) {
                        if(response.code()==200){
                            chamaTelaPassageiro(response.body());
                            stopLoad();
                        }else{
                            ConnectionManager.posForLoginTransportadora(etLogin.getText().toString(),etSenha.getText().toString()).enqueue(new Callback<Transportadora>() {
                                @Override
                                public void onResponse(Call<Transportadora> call, Response<Transportadora> response) {
                                    if(response.code()==200){
                                        chamaTelaTransportadora(response.body());
                                        stopLoad();
                                    }else{
                                        ConnectionManager.postForLoginEmpresa(etLogin.getText().toString(),etSenha.getText().toString()).enqueue(new Callback<Empresa>() {
                                            @Override
                                            public void onResponse(Call<Empresa> call, Response<Empresa> response) {
                                                if(response.code()==200){
                                                    Toast.makeText(TelaLogin.this,"Empresa",Toast.LENGTH_SHORT).show();
                                                    stopLoad();
                                                }
                                                chamaToastDadosInvalidos();
                                                stopLoad();
                                            }

                                            @Override
                                            public void onFailure(Call<Empresa> call, Throwable t) {
                                                stopLoad();
                                                chamaToastErro();
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onFailure(Call<Transportadora> call, Throwable t) {
                                    stopLoad();
                                    chamaToastErro();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<Passageiro> call, Throwable t) {
                        stopLoad();
                        chamaToastErro();
                    }
                });

            }
        });
    }

    private void startLoad(){
        progressDialog = ProgressDialog.show(TelaLogin.this,null,"Carregando");
    }

    private void stopLoad(){
        progressDialog.dismiss();
    }

    private void chamaTelaCadastrar(){
        Intent itTelaCadastrar = new Intent(this, TelaCadastro.class);
        startActivity(itTelaCadastrar);
        finish();
    }

    private void chamaTelaPassageiro(Passageiro passageiro){
        bundle.putSerializable("Passageiro",passageiro);
        Intent itTelaPassageiro = new Intent(this, TelaPassageiro.class);
        itTelaPassageiro.putExtras(bundle);
        startActivity(itTelaPassageiro);
        finish();
    }

    private void chamaTelaTransportadora(Transportadora transportadora){
        bundle.putSerializable("Transportadora",transportadora);
        Intent itTelaTransportadora = new Intent(this, TelaTransportadora.class);
        itTelaTransportadora.putExtras(bundle);
        startActivity(itTelaTransportadora);
        finish();
    }
    private void chamaTelaLeitor(){
        Intent itTelaPassageiro = new Intent(this, TelaLeitor.class);
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
        this.passageiro = new Passageiro();
        this.transportadora = new Transportadora();
        this.empresa = new Empresa();
        this.bundle = new Bundle();

    }

}
