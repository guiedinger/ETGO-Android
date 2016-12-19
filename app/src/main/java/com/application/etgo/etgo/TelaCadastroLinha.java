package com.application.etgo.etgo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import POJOS.Linha;
import POJOS.Onibus;
import POJOS.TipoServico;
import POJOS.Transportadora;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.REST.ConnectionManager;

/**
 * Created by Guilherme on 16/12/2016.
 */

public class TelaCadastroLinha extends Activity implements Validator.ValidationListener{
    private TextView tvCadastroLinha;
    @NotEmpty(message = "Campo precisa ser preenchido.")
    private EditText etDescricao;
    private Button btCadastroLinha;
    private Spinner spTipoServico;
    private Transportadora transportadora;
    private Bundle bundle;
    private ProgressDialog progressDialog;
    private Linha linha;
    private Validator validator;
    private TipoServico[] tipoServicos = new TipoServico[]{TipoServico.EXECUTIVO,TipoServico.INTERMUNICIPAL,TipoServico.METROPOLITANO,TipoServico.SELETIVO,TipoServico.URBANO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_linha);
        inicializaComponentes();
        bundle = getIntent().getExtras();
        transportadora = (Transportadora) bundle.getSerializable("Transportadora");
        ArrayAdapter<TipoServico> adapter = new ArrayAdapter<TipoServico>(this,android.R.layout.simple_spinner_item, tipoServicos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spTipoServico.setAdapter(adapter);

        this.btCadastroLinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
    }
        });



    }

    @Override
    public void onValidationSucceeded(){
        startLoad();
        linha.setDescricao(etDescricao.getText().toString());
        linha.setTipo(TipoServico.class.cast(spTipoServico.getAdapter().getItem(spTipoServico.getSelectedItemPosition())));
        Log.i("debug",linha.getTipo().getStatus());
        ConnectionManager.postForAddLinha(transportadora.getIdUsuario(), linha).enqueue(new Callback<Transportadora>() {
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
        progressDialog = ProgressDialog.show(TelaCadastroLinha.this,null,"Carregando");
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
        Toast.makeText(TelaCadastroLinha.this,"Erro ao cadastrar!",Toast.LENGTH_LONG).show();
    }

    public void inicializaComponentes(){
        this.tvCadastroLinha = (TextView)findViewById(R.id.tv_cadastro_linha);
        this.etDescricao = (EditText)findViewById(R.id.et_descricao);
        this.spTipoServico = (Spinner)findViewById(R.id.sp_tipo_servico);
        this.btCadastroLinha = (Button)findViewById(R.id.bt_cadastro_linha);
        this.transportadora = new Transportadora();
        this.bundle = new Bundle();
        this.linha = new Linha();
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }
}
