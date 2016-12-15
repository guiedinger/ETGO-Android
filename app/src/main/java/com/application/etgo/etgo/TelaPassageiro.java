package com.application.etgo.etgo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.QRCode;

import POJOS.Passageiro;

/**
 * Created by Guilherme on 23/10/2016.
 */

public class TelaPassageiro extends Activity {
    private ImageView ivQrCode;
    private TextView tvSaldo;
    private Button btGerarQR;
    private String QRcode;
    private Bundle bundle;
    private Passageiro passageiro;
    public final static int WIDTH=500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_passageiro);
        this.inicializaComponentes();
        bundle = getIntent().getExtras();
        passageiro = (Passageiro) bundle.getSerializable("Passageiro");
        tvSaldo.setText("Saldo : R$"+passageiro.getSaldo() );
        Thread t = new Thread(new Runnable() {
            public void run() {
                QRcode = passageiro.getToken();

                try {
                    synchronized (this) {
                        wait(5000);
// runOnUiThread method used to do UI task in main thread.
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Bitmap bitmap = null;

                                    bitmap = encodeAsBitmap(QRcode);
                                    ivQrCode.setImageBitmap(bitmap);

                                } catch (WriterException e) {
                                    e.printStackTrace();
                                } // end of catch block

                            } // end of run method
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



    public void inicializaComponentes(){
        this.ivQrCode = (ImageView) findViewById(R.id.iv_qr_code_image);
        this.bundle = new Bundle();
        this.passageiro = new Passageiro();
        this.tvSaldo = (TextView)findViewById(R.id.tv_saldo);
        this.btGerarQR = (Button)findViewById(R.id.bt_gerar_qr);
    }
}
