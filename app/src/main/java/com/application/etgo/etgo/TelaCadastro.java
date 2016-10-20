package com.application.etgo.etgo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Guilherme on 20/10/2016.
 */
public class TelaCadastro extends Activity {

    private EditText etLogin, etSenha;
    private Button btCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
    }

    public void inicializaComponentes(){
        this.etLogin = (EditText)findViewById(R.id.et_login_cadastrar);
        this.etSenha = (EditText)findViewById(R.id.et_senha_cadastrar);
        this.btCadastrar = (Button)findViewById(R.id.bt_cadastrar);
    }
}
