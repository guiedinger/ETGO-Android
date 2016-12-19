package com.application.etgo.etgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import POJOS.Onibus;
import POJOS.Transportadora;

/**
 * Created by Guilherme on 15/12/2016.
 */

public class TelaTransportadora extends Activity {
    private Button btCadastrarOnibus, btCadastrarLinha, btCadastrarMotorista, btIniciarViagem;
    private Transportadora transportadora;
    private Onibus onibus;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_transportadora);
        inicializaComponentes();
        bundle = getIntent().getExtras();
        transportadora = (Transportadora) bundle.getSerializable("Transportadora");

        this.btCadastrarOnibus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaCadastroOnibus(transportadora);
            }
        });

        this.btCadastrarMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaCadastroMotorista(transportadora);
            }
        });

        this.btCadastrarLinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaCadastroLinha(transportadora);
            }
        });

        this.btIniciarViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaInicioViagem(transportadora);
            }
        });
    }

    public void chamaTelaCadastroOnibus(Transportadora transportadora){
        bundle.putSerializable("Transportadora",transportadora);
        Intent itTelaCadastroOnibus = new Intent(this,TelaCadastroOnibus.class);
        itTelaCadastroOnibus.putExtras(bundle);
        startActivity(itTelaCadastroOnibus);
        finish();
    }

    public void chamaTelaCadastroMotorista(Transportadora transportadora){
        bundle.putSerializable("Transportadora",transportadora);
        Intent itTelaCadastroMotorista = new Intent(this,TelaCadastroMotorista.class);
        itTelaCadastroMotorista.putExtras(bundle);
        startActivity(itTelaCadastroMotorista);
        finish();
    }

    public void chamaTelaCadastroLinha(Transportadora transportadora){
        bundle.putSerializable("Transportadora",transportadora);
        Intent itTelaCadastroLinha = new Intent(this,TelaCadastroLinha.class);
        itTelaCadastroLinha.putExtras(bundle);
        startActivity(itTelaCadastroLinha);
        finish();
    }

    public void chamaTelaInicioViagem(Transportadora transportadora){
        bundle.putSerializable("Transportadora",transportadora);
        Intent itTelaInicioViagem = new Intent(this,TelaInicioViagem.class);
        itTelaInicioViagem.putExtras(bundle);
        startActivity(itTelaInicioViagem);
        finish();
    }


    public void inicializaComponentes(){
        this.btCadastrarLinha = (Button)findViewById(R.id.bt_cadastrar_linha);
        this.btCadastrarMotorista = (Button)findViewById(R.id.bt_cadastrar_motorista);
        this.btCadastrarOnibus = (Button)findViewById(R.id.bt_cadastrar_onibus);
        this.btIniciarViagem = (Button)findViewById(R.id.bt_iniciar_viagem);
        this.transportadora = new Transportadora();
        this.bundle = new Bundle();
        this.onibus = new Onibus();
    }
}
