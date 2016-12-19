package com.application.etgo.etgo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.QRCode;

import java.text.NumberFormat;

import POJOS.Passageiro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.REST.ConnectionManager;

/**
 * Created by Guilherme on 23/10/2016.
 */

public class TelaPassageiro extends Activity {
    private ImageView ivQrCode;
    private TextView tvSaldo, tvRecarregar;
    private Button btGerarQR;
    private String QRcode;
    private Bundle bundle;
    private Handler handler;
    private Passageiro passageiro;
    public final static int WIDTH=500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_passageiro);
        this.inicializaComponentes();
        bundle = getIntent().getExtras();
        passageiro = (Passageiro) bundle.getSerializable("Passageiro");
        tvSaldo.setText("Saldo: "+ NumberFormat.getCurrencyInstance().format(passageiro.getSaldo()));
        QRcode = passageiro.getToken();
        carregarQR(QRcode);

        atualizar();

        this.btGerarQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSaldo.setText("Saldo: "+ NumberFormat.getCurrencyInstance().format(passageiro.getSaldo()));
                ConnectionManager.putForTokenUp(passageiro.getIdUsuario()).enqueue(new Callback<Passageiro>() {
                    @Override
                    public void onResponse(Call<Passageiro> call, Response<Passageiro> response) {
                        if(response.code()==200) {
                            Passageiro upToken = response.body();
                            tvSaldo.setText("Saldo: "+ NumberFormat.getCurrencyInstance().format(response.body().getSaldo()));
                            carregarQR(upToken.getToken());
                        }else{
                            Toast.makeText(TelaPassageiro.this,"Erro de conexão",Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onFailure(Call<Passageiro> call, Throwable t) {

                    }
                });

            }
        });
        this.tvRecarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaRecarga(passageiro);
            }
        });

    }
private void carregarQR(final String qrToken) {
    final ProgressDialog progressDialog = ProgressDialog.show(TelaPassageiro.this, null, "Carregando QR");
    Thread t = new Thread(new Runnable() {
        public void run() {
            QRcode = qrToken;
            try {
                synchronized (this) {
                    wait(5000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Bitmap bitmap = null;
                                bitmap = encodeAsBitmap(QRcode);
                                ivQrCode.setImageBitmap(bitmap);

                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }

                    });

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    });
    t.start();
}

    // this is method call from on create and return bitmap image of QRCode.
    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? getResources().getColor(R.color.black):getResources().getColor(R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, 500, 0, 0, w, h);
        return bitmap;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void atualizar(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ConnectionManager.getForPassageiro(passageiro.getIdUsuario()).enqueue(new Callback<Passageiro>() {
                    @Override
                    public void onResponse(Call<Passageiro> call, Response<Passageiro> response) {
                        if(response.code()==200){
                            tvSaldo.setText("Saldo: "+ NumberFormat.getCurrencyInstance().format(response.body().getSaldo()));

                            if(!passageiro.getSaldo().toString().equals(response.body().getSaldo().toString())){
                                Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                                vibrator.vibrate(60);
                            }
                            passageiro = response.body();
                        }
                    }

                    @Override
                    public void onFailure(Call<Passageiro> call, Throwable t) {

                    }
                });
                handler.postDelayed(this,5000);
            }
        },5000);
    }

    public void chamaTelaRecarga(Passageiro passageiro){
        bundle.putSerializable("Passageiro",passageiro);
        Intent itTelaRecarga = new Intent(this, TelaRecarga.class);
        itTelaRecarga.putExtras(bundle);
        startActivity(itTelaRecarga);
        finish();
    }
    public void inicializaComponentes(){
        this.ivQrCode = (ImageView) findViewById(R.id.iv_qr_code_image);
        this.bundle = new Bundle();
        this.passageiro = new Passageiro();
        this.tvSaldo = (TextView)findViewById(R.id.tv_saldo);
        this.btGerarQR = (Button)findViewById(R.id.bt_gerar_qr);
        this.tvRecarregar = (TextView)findViewById(R.id.tv_recarregar);
        this.handler = new Handler();
    }
}
