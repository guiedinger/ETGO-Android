package com.application.etgo.etgo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import POJOS.Passageiro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.REST.ConnectionManager;

/**
 * Created by Guilherme on 15/12/2016.
 */

public class TelaRecarga extends Activity {
    private TextView tvRecarga;
    private EditText etValorRecarga;
    private Button btRecarregar;
    private Bundle bundle;
    private Passageiro passageiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_recarga);
        inicializaComponentes();
        bundle = getIntent().getExtras();
        passageiro = (Passageiro) bundle.getSerializable("Passageiro");
        this.btRecarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = ProgressDialog.show(TelaRecarga.this, null, "Carregando");
                ConnectionManager.putForSaldoUp(passageiro.getIdUsuario(),Double.parseDouble(etValorRecarga.getText().toString())).enqueue(new Callback<Passageiro>() {
                    @Override
                    public void onResponse(Call<Passageiro> call, Response<Passageiro> response) {
                        if(response.code()==200) {
                            chamaTelaPassageiro(response.body());
                            progressDialog.dismiss();
                        }else{
                            Toast.makeText(TelaRecarga.this,"Erro de conexão.",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<Passageiro> call, Throwable t) {
                        Toast.makeText(TelaRecarga.this,"Erro de conexão.",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        });
    }

    public void chamaTelaPassageiro(Passageiro passageiro){
        bundle.putSerializable("Passageiro", passageiro);
        Intent itTelaPassageiro = new Intent(this,TelaPassageiro.class);
        itTelaPassageiro.putExtras(bundle);
        startActivity(itTelaPassageiro);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        chamaTelaPassageiro(passageiro);
    }

    public void inicializaComponentes(){
        this.tvRecarga = (TextView)findViewById(R.id.tv_recarga);
        this.etValorRecarga = (EditText)findViewById(R.id.et_valor_recarga);
        this.btRecarregar = (Button)findViewById(R.id.bt_recarregar);
        this.bundle = new Bundle();
        this.passageiro = new Passageiro();
    }
}
