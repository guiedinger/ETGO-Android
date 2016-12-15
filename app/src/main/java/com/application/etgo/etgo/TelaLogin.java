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
    private Boolean ioError;
    private Boolean diError;

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
                ioError = false;
                diError = false;
                ConnectionManager.posForLoginPassageiro(etLogin.getText().toString(),etSenha.getText().toString()).enqueue(new Callback<Passageiro>() {
                    @Override
                    public void onResponse(Call<Passageiro> call, Response<Passageiro> response) {
                        if(response.code()==200){
                            chamaTelaPassageiro(response.body());
                            stopLoad();
                        }else{
                            diErrorTrue();
                        }
                    }

                    @Override
                    public void onFailure(Call<Passageiro> call, Throwable t) {
                        ioErrorTrue();
                    }
                });

                ConnectionManager.posForLoginTransportadora(etLogin.getText().toString(),etSenha.getText().toString()).enqueue(new Callback<Transportadora>() {
                    @Override
                    public void onResponse(Call<Transportadora> call, Response<Transportadora> response) {
                        if(response.code()==200) {
                            chamaTelaLeitor();
                            stopLoad();
                        }else{
                            diErrorTrue();
                        }
                    }

                    @Override
                    public void onFailure(Call<Transportadora> call, Throwable t) {
                        ioErrorTrue();
                    }
                });

                ConnectionManager.postForLoginEmpresa(etLogin.getText().toString(),etSenha.getText().toString()).enqueue(new Callback<Empresa>() {
                    @Override
                    public void onResponse(Call<Empresa> call, Response<Empresa> response) {
                        Toast.makeText(TelaLogin.this,"Empresa",Toast.LENGTH_SHORT);
                        diErrorTrue();
                    }

                    @Override
                    public void onFailure(Call<Empresa> call, Throwable t) {
                        ioErrorTrue();
                    }
                });
                if(ioError) {
                    chamaToastErro();
                    Log.i("debug", "io error");
                    btEntrar.setEnabled(true);
                    pbLogin.setVisibility(View.INVISIBLE);
                }
                if(!diError) {
                    Log.i("debug", "di error");
                    chamaToastDadosInvalidos();
                    stopLoad();
                }
                //stopLoad();

            }
        });
    }

    private void startLoad(){
        btEntrar.setEnabled(false);
        pbLogin.setVisibility(View.VISIBLE);
    }

    private void stopLoad(){
        btEntrar.setEnabled(true);
        pbLogin.setVisibility(View.INVISIBLE);
    }
    private void ioErrorTrue(){
        ioError = true;
    }
    private void diErrorTrue(){
        diError = true;
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
