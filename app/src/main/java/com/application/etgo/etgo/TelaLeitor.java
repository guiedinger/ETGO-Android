package com.application.etgo.etgo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * Created by Guilherme on 11/12/2016.
 */

public class TelaLeitor extends Activity implements QRCodeReaderView.OnQRCodeReadListener{
    String scanningURL;
    private QRCodeReaderView mydecoderview;
    private TextView tvQr;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_leitor);


        tvQr = (TextView)findViewById(R.id.tv_qr);
        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        //mydecoderview.setFrontCamera();
        mydecoderview.setBackCamera();
        mydecoderview.setOnQRCodeReadListener(this);


    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {



        scanningURL=text;

        Toast.makeText(getApplicationContext()," scanned text"+""+scanningURL,Toast.LENGTH_LONG).show();
        tvQr.setText(scanningURL);
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

    @Override
    protected void onPause() {
        super.onPause();
        mydecoderview.stopCamera();
                //getCameraManager().stopPreview();
    }
    }





