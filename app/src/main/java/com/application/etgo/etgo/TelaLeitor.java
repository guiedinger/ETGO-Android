package com.application.etgo.etgo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.zxing.Result;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

import POJOS.Transportadora;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.REST.ConnectionManager;


/**
 * Created by Guilherme on 11/12/2016.
 */

public class TelaLeitor extends Activity implements QRCodeReaderView.OnQRCodeReadListener{
    Calendar actualCalendar = Calendar.getInstance();
    Calendar lastCalendar = Calendar.getInstance();
    String scanningURL;
    Date actualDate;
    String lastTransactionToken = "";
    Date lastTransacionDate = new Date();
    private QRCodeReaderView mydecoderview;
    private TextView tvQr, tvValorPassagem;
    private Transportadora transportadora;
    private Bundle bundle;
    private Double valor;
    private Handler handler;
    private Button btfinalizarViagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_leitor);
        inicializaComponentes();
        lastCalendar.setTime(lastTransacionDate);
        bundle = getIntent().getExtras();
        transportadora = (Transportadora) bundle.getSerializable("Transportadora");
        valor = bundle.getDouble("valor");
        tvValorPassagem.setText("Valor: "+ NumberFormat.getCurrencyInstance().format(valor));
        tvQr.setText("Procurando QR");

        //mydecoderview.setFrontCamera();
        mydecoderview.setBackCamera();
        mydecoderview.setOnQRCodeReadListener(this);

        this.btfinalizarViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaTransportadora(transportadora);
            }
        });
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        scanningURL=text;
        actualDate = new Date();
        actualCalendar.setTime(actualDate);
        tvQr.setText("Efetuando Pagamento...");

        //if(!scanningURL.equals(lastTransactionToken) || (actualCalendar.getTimeInMillis()-lastCalendar.getTimeInMillis())>5000) {
            Log.i("debug",""+(actualCalendar.getTimeInMillis()-lastCalendar.getTimeInMillis()));
        try {
            synchronized (this) {
                ConnectionManager.putForEfetuarPagamento(transportadora.getIdUsuario(), scanningURL, valor).enqueue(new Callback<Transportadora>() {
                    @Override
                    public void onResponse(Call<Transportadora> call, Response<Transportadora> response) {
                        if (response.code() == 200) {
                            Log.i("debug", "entrou");
                            lastTransactionToken = scanningURL;
                            lastTransacionDate = actualDate;
                            Toast.makeText(TelaLeitor.this, "Pagamento efetuado", Toast.LENGTH_SHORT).show();
                        } else {
                            tvQr.setText("Pagamento não autorizado!");
                        }
                    }

                    @Override
                    public void onFailure(Call<Transportadora> call, Throwable t) {
                        tvQr.setText("Pagamento não autorizado!");

                        //waitToPay();
                        //tvQr.setText("Procurando QR");
                    }
                });
                wait(6000);
                tvQr.setText("Procurando QR");
            }
        }catch (InterruptedException e){

        }
        tvQr.setText("Procurando QR");
    }

/*
    @Override
    public void cameraNotFound() {

    }

    @Override
    public void QRCodeNotFoundOnCamImage() {

    }
*/


    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 50);
        }else
        mydecoderview.startCamera();
    }

    private void waitToPay(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{Thread.sleep(5000);}catch (InterruptedException e){}
            }
        }).start();
    }

    private void chamaTelaTransportadora(Transportadora transportadora){
        bundle.putSerializable("Transportadora",transportadora);
        Intent itTelaTransportadora = new Intent(this, TelaTransportadora.class);
        itTelaTransportadora.putExtras(bundle);
        startActivity(itTelaTransportadora);
        finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mydecoderview.stopCamera();
                //getCameraManager().stopPreview();
    }

    private void inicializaComponentes(){
        this.transportadora = new Transportadora();
        this.bundle = new Bundle();
        this.tvQr = (TextView)findViewById(R.id.tv_qr);
        this.mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        this.tvValorPassagem = (TextView)findViewById(R.id.tv_valor_passagem);
        this.handler = new Handler();
        this.btfinalizarViagem = (Button)findViewById(R.id.bt_encerrar_viagem);
    }
    }





