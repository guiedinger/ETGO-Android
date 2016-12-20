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

import POJOS.Onibus;
import POJOS.Transportadora;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.REST.ConnectionManager;

/**
 * Created by Guilherme on 15/12/2016.
 */

public class TelaCadastroOnibus extends Activity implements Validator.ValidationListener{
    private TextView tvCadastroOnibus;
    @Length(min = 6, max = 7, message = "Placa invalida")
    private EditText etPlaca;
    @NotEmpty(message = "Campo necessario")
    private EditText etModelo;
    private Button btCadastroOnibus;
    private Transportadora transportadora;
    private Onibus onibus;
    private Bundle bundle;
    private ProgressDialog progressDialog;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_onibus);
        inicializaComponentes();
        bundle = getIntent().getExtras();
        transportadora = (Transportadora) bundle.getSerializable("Transportadora");
        this.btCadastroOnibus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        startLoad();
        onibus.setPlaca(etPlaca.getText().toString());
        onibus.setModelo(etModelo.getText().toString());
        ConnectionManager.postForAddOnibus(transportadora.getIdUsuario(), onibus).enqueue(new Callback<Transportadora>() {
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
                Log.i("debug","io error");
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
        progressDialog = ProgressDialog.show(TelaCadastroOnibus.this,null,"Carregando");
    }

    private void stopLoad(){
        progressDialog.dismiss();
    }

    private void chamaTelaTransportadora(Transportadora transportadora){
        bundle.putSerializable("Transportadora",transportadora);
        Intent itTelaTransportadora = new Intent(this, TelaTransportadora.class);
        itTelaTransportadora.putExtras(bundle);
        startActivity(itTelaTransportadora);
        finish();
    }

    public void chamaToastErro(){
        Toast.makeText(TelaCadastroOnibus.this,"Erro ao cadastrar!",Toast.LENGTH_LONG).show();
    }

    public void inicializaComponentes(){
        this.tvCadastroOnibus = (TextView)findViewById(R.id.tv_cadastro_onibus);
        this.etPlaca = (EditText)findViewById(R.id.et_placa);
        this.etModelo = (EditText)findViewById(R.id.et_modelo);
        this.btCadastroOnibus = (Button)findViewById(R.id.bt_cadastro_onibus);
        this.transportadora = new Transportadora();
        this.onibus = new Onibus();
        this.bundle = new Bundle();
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }

}
