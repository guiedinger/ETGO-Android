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
                //chamaTelaLeitor();
                passageiro.setUserName(etLogin.getText().toString());
                passageiro.setPassword(etSenha.getText().toString());
                ConnectionManager.posForLoginPassageiro(etLogin.getText().toString(),etSenha.getText().toString()).enqueue(new Callback<Passageiro>() {
                    @Override
                    public void onResponse(Call<Passageiro> call, Response<Passageiro> response) {
                        if(response.code()==200){
                            bundle.putSerializable("Passageiro",response.body());
                            Intent itTelaPassageiro = new Intent(TelaLogin.this, TelaPassageiro.class);
                            itTelaPassageiro.putExtras(bundle);
                            startActivity(itTelaPassageiro);

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
