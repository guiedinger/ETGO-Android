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
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * Created by Guilherme on 11/12/2016.
 */

public class TelaLeitor extends Activity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    private QRCodeReaderView qrCodeReaderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_leitor);
        inicializaComponentes();
    }
    public void inicializaComponentes(){
        this.qrCodeReaderView = (QRCodeReaderView)findViewById(R.id.qrdecoderview);
    }

    @Override
    public void handleResult(Result result) {
        Log.e("handle",result.getText());
        Log.e("handle",result.getBarcodeFormat().toString());
        Toast.makeText(this, result.getText(), Toast.LENGTH_SHORT).show();
       /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();*/
    }

    public void QRScanner(View view){
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 50);
        }else
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mScannerView.stopCamera();
    }
}
