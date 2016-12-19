package com.application.etgo.etgo;

import android.app.Activity;
import android.app.ListActivity;
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

import java.util.ArrayList;
import java.util.List;

import POJOS.Linha;
import POJOS.Motorista;
import POJOS.Onibus;
import POJOS.Transportadora;
import POJOS.Viagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.REST.ConnectionManager;

/**
 * Created by Guilherme on 16/12/2016.
 */

public class TelaInicioViagem extends Activity {
    private TextView tvInicioViagem;
    private Spinner spOnibus, spLinha, spMotorista;
    private EditText etValor;
    private Button btInicioViagem;
    private Viagem viagem;
    private Bundle bundle;
    private Transportadora transportadora;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicio_viagem);
        inicializaComponentes();
        bundle = getIntent().getExtras();
        transportadora = (Transportadora) bundle.getSerializable("Transportadora");

        final ArrayAdapter<Onibus> onibusArrayAdapter = new ArrayAdapter<Onibus>(this, android.R.layout.simple_spinner_item, transportadora.getOnibus());
        final ArrayAdapter<Linha> linhaArrayAdapter = new ArrayAdapter<Linha>(this, android.R.layout.simple_spinner_item, transportadora.getLinhas());
        final ArrayAdapter<Motorista> motoristaArrayAdapter = new ArrayAdapter<Motorista>(this, android.R.layout.simple_spinner_item, transportadora.getMotoristas());
        spOnibus.setAdapter(onibusArrayAdapter);
        spMotorista.setAdapter(motoristaArrayAdapter);
        spLinha.setAdapter(linhaArrayAdapter);

        this.spOnibus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viagem.setOnibus(onibusArrayAdapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.spLinha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viagem.setLinha(linhaArrayAdapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.spMotorista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viagem.setMotorista(motoristaArrayAdapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.btInicioViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoad();
                ConnectionManager.postForIniciarViagem(viagem).enqueue(new Callback<Transportadora>() {
                    @Override
                    public void onResponse(Call<Transportadora> call, Response<Transportadora> response) {
                        if (response.code() == 200) {
                            chamaTelaLeitor(transportadora);
                            stopLoad();
                        } else {
                            Log.i("debug", "di error");
                            stopLoad();
                        }
                    }

                    @Override
                    public void onFailure(Call<Transportadora> call, Throwable t) {
                        stopLoad();
                        Log.i("debug", "io error");
                    }
                });
            }
        });
    }

    private void startLoad() {
        progressDialog = ProgressDialog.show(TelaInicioViagem.this, null, "Carregando");
    }

    private void stopLoad() {
        progressDialog.dismiss();
    }

    private void chamaTelaLeitor(Transportadora transportadora) {
        bundle.putSerializable("Transportadora", transportadora);
        if(!etValor.getText().toString().equals("")) {
            bundle.putDouble("valor", Double.parseDouble(etValor.getText().toString()));
        }else{
            bundle.putDouble("valor", 2.50);
        }
        Intent itTelaPassageiro = new Intent(this, TelaLeitor.class);
        itTelaPassageiro.putExtras(bundle);
        startActivity(itTelaPassageiro);
        finish();
    }

    private void inicializaComponentes() {
        this.tvInicioViagem = (TextView) findViewById(R.id.tv_inicio_viagem);
        this.spLinha = (Spinner) findViewById(R.id.sp_linha);
        this.spMotorista = (Spinner) findViewById(R.id.sp_motorista);
        this.spOnibus = (Spinner) findViewById(R.id.sp_onibus);
        this.etValor = (EditText) findViewById(R.id.et_valor_viagem);
        this.btInicioViagem = (Button) findViewById(R.id.bt_inicio_viagem);
        this.bundle = new Bundle();
        this.viagem = new Viagem();
        this.transportadora = new Transportadora();
    }

}
