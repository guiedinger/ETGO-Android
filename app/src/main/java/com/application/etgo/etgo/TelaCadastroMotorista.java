package com.application.etgo.etgo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import POJOS.Motorista;
import POJOS.Transportadora;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.REST.ConnectionManager;

/**
 * Created by Guilherme on 16/12/2016.
 */

public class TelaCadastroMotorista extends Activity implements Validator.ValidationListener{
    private TextView tvCadastroMotorista;
    @NotEmpty(message = "Campo necessario")
    private EditText etMotoristaNome;
    @Length(min = 11, max = 16, message = "CPF invalido")
    private EditText etMotoristaCpf;
    private Button btCadastroMotorista;
    private Motorista motorista;
    private Bundle bundle;
    private Transportadora transportadora;
    private ProgressDialog progressDialog;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_motorista);
        inicializaComponentes();
        bundle = getIntent().getExtras();
        transportadora = (Transportadora) bundle.getSerializable("Transportadora");
        this.btCadastroMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        startLoad();
        motorista.setNome(etMotoristaNome.getText().toString());
        motorista.setCpf(etMotoristaCpf.getText().toString());
        ConnectionManager.postForAddMotorista(transportadora.getIdUsuario(),motorista).enqueue(new Callback<Transportadora>() {
            @Override
            public void onResponse(Call<Transportadora> call, Response<Transportadora> response) {
                if(response.code()==200){
                    chamaTelaTransportadora(response.body());
                    stopLoad();
                }else{
                    Log.i("debug","di error");
                    chamaToastErro();
                    stopLoad();
                }
            }

            @Override
            public void onFailure(Call<Transportadora> call, Throwable t) {
                Log.i("debug","di error");
                chamaToastErro();
                stopLoad();
            }
        });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(
                        this,
                        message,
                        Toast.LENGTH_LONG
                ).show();
            }
        }
    }

    private void startLoad(){
        progressDialog = ProgressDialog.show(TelaCadastroMotorista.this,null,"Carregando");
    }

    private void stopLoad(){
        progressDialog.dismiss();
    }

    public void chamaToastErro(){
        Toast.makeText(TelaCadastroMotorista.this,"Erro ao cadastrar!",Toast.LENGTH_LONG).show();
    }

    private void chamaTelaTransportadora(Transportadora transportadora){
        bundle.putSerializable("Transportadora",transportadora);
        Intent itTelaTransportadora = new Intent(this, TelaTransportadora.class);
        itTelaTransportadora.putExtras(bundle);
        startActivity(itTelaTransportadora);
        finish();
    }

    public void inicializaComponentes(){
        this.tvCadastroMotorista = (TextView) findViewById(R.id.tv_cadastro_motorista);
        this.etMotoristaNome = (EditText)findViewById(R.id.et_motorista_nome);
        this.etMotoristaCpf = (EditText)findViewById(R.id.et_motorista_cpf);
        this.btCadastroMotorista = (Button)findViewById(R.id.bt_cadastro_motorista);
        this.bundle = new Bundle();
        this.motorista = new Motorista();
        this.transportadora = new Transportadora();
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }
}
