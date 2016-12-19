package com.application.etgo.etgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import POJOS.Empresa;
import POJOS.Passageiro;
import POJOS.Transportadora;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.REST.ConnectionManager;

public class TelaCadastro extends Activity implements Validator.ValidationListener{
    @NotEmpty(message = "Campo precisa ser preenchido.")
    private EditText etLoginCadastrar;
    @Password(message = "Senha invalida")
    private EditText etSenhaCadatrar;
    @NotEmpty(message = "Campo precisa ser preenchido.")
    private EditText etNomeCadastrar;
    @NotEmpty(message = "Campo precisa ser preenchido.")
    @Length(min = 8, max = 13, message = "Telefone invalido")
    private EditText etTelefoneCadastrar;
    @Email(message = "Email invalido")
    private EditText etEmailCadastrar;
    @NotEmpty(message = "Campo precisa ser preenchido.")
    @Length(min = 11, max = 18, message = "CPF/CNPJ Invalido")
    private EditText etCpfCnpjCadastrar;


    private Passageiro passageiro;
    private Transportadora transportadora;
    private Empresa empresa;
    private Button btCadastrar;
    private ProgressBar pbCadastrar;
    private RadioButton rbPassageiro, rbTransportadora, rbEmpresa;
    private RadioGroup rgTipoPassageiro;
    private Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        this.inicializaComponentes();
        this.btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
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

    @Override
    public void onValidationSucceeded() {
        startLoad();
        if(rbPassageiro.isChecked()){
            passageiro.setUserName(etLoginCadastrar.getText().toString());
            passageiro.setPassword(etSenhaCadatrar.getText().toString());
            passageiro.setNome(etNomeCadastrar.getText().toString());
            passageiro.setTelefone(etTelefoneCadastrar.getText().toString());
            passageiro.setEmail(etEmailCadastrar.getText().toString());
            passageiro.setCpf(etCpfCnpjCadastrar.getText().toString());
            ConnectionManager.postForCreatePassageiro(passageiro).enqueue(new Callback<Passageiro>() {
                @Override
                public void onResponse(Call<Passageiro> call, Response<Passageiro> response) {
                    if (response.code()==200){
                        chamaTelaLogin();
                    }else{
                        stopLoad();
                        Toast.makeText(TelaCadastro.this, "Dados Inválidos!",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Passageiro> call, Throwable t) {
                    stopLoad();
                    Toast.makeText(TelaCadastro.this, "Problema ao conectar.",Toast.LENGTH_LONG).show();
                    Log.i("debug","NÃO CADASTROU  :"+t.getMessage());
                }
            });
        }else if(rbEmpresa.isChecked()){
            empresa.setUserName(etLoginCadastrar.getText().toString());
            empresa.setPassword(etSenhaCadatrar.getText().toString());
            empresa.setNome(etNomeCadastrar.getText().toString());
            empresa.setEmail(etEmailCadastrar.getText().toString());
            empresa.setTelefone(etTelefoneCadastrar.getText().toString());
            empresa.setCnpj(etCpfCnpjCadastrar.getText().toString());
            ConnectionManager.postForCreateEmpresa(empresa).enqueue(new Callback<Empresa>() {
                @Override
                public void onResponse(Call<Empresa> call, Response<Empresa> response) {
                    if (response.code()==200){
                        chamaTelaLogin();
                    }else{
                        stopLoad();
                        Toast.makeText(TelaCadastro.this, "Dados Inválidos!",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Empresa> call, Throwable t) {
                    stopLoad();
                    Toast.makeText(TelaCadastro.this, "Problema ao conectar.",Toast.LENGTH_LONG).show();
                    Log.i("debug","NÃO CADASTROU  :"+t.getMessage());
                }
            });
        }else if(rbTransportadora.isChecked()){
            transportadora.setUserName(etLoginCadastrar.getText().toString());
            transportadora.setPassword(etSenhaCadatrar.getText().toString());
            transportadora.setNome(etNomeCadastrar.getText().toString());
            transportadora.setEmail(etEmailCadastrar.getText().toString());
            transportadora.setTelefone(etTelefoneCadastrar.getText().toString());
            transportadora.setCnpj(etCpfCnpjCadastrar.getText().toString());
            ConnectionManager.postForCreateTransportadora(transportadora).enqueue(new Callback<Transportadora>() {
                @Override
                public void onResponse(Call<Transportadora> call, Response<Transportadora> response) {
                    if (response.code()==200){
                        chamaTelaLogin();
                    }else{
                        stopLoad();
                        Toast.makeText(TelaCadastro.this, "Dados Inválidos!",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Transportadora> call, Throwable t) {
                    stopLoad();
                    Toast.makeText(TelaCadastro.this, "Problema ao conectar.",Toast.LENGTH_LONG).show();
                    Log.i("debug","NÃO CADASTROU  :"+t.getMessage());
                }
            });
        }
    }

    private void startLoad(){
        btCadastrar.setEnabled(false);
        pbCadastrar.setVisibility(View.VISIBLE);
    }

    private void stopLoad(){
        btCadastrar.setEnabled(true);
        pbCadastrar.setVisibility(View.INVISIBLE);
    }
    private void chamaTelaLogin(){
        Intent itTelaLogin = new Intent(this, TelaLogin.class);
        startActivity(itTelaLogin);
        finish();
    }

    @Override
    public void onBackPressed() {
        chamaTelaLogin();
        super.onBackPressed();
    }

    public void inicializaComponentes(){
        this.etLoginCadastrar = (EditText)findViewById(R.id.et_login_cadastrar);
        this.etSenhaCadatrar = (EditText)findViewById(R.id.et_senha_cadastrar);
        this.etNomeCadastrar = (EditText)findViewById(R.id.et_nome_cadastrar);
        this.etTelefoneCadastrar = (EditText)findViewById(R.id.et_telefone_cadastrar);
        this.etEmailCadastrar = (EditText)findViewById(R.id.et_email_cadastrar);
        this.etCpfCnpjCadastrar = (EditText)findViewById(R.id.et_cpf_cnpj_cadastrar);
        this.btCadastrar = (Button)findViewById(R.id.bt_cadastrar);
        this.pbCadastrar = (ProgressBar)findViewById(R.id.pb_cadastrar);
        this.passageiro = new Passageiro();
        this.transportadora = new Transportadora();
        this.empresa = new Empresa();
        this.rbEmpresa = (RadioButton)findViewById(R.id.rb_empresa);
        this.rbPassageiro = (RadioButton)findViewById(R.id.rb_passageiro);
        this.rbTransportadora = (RadioButton)findViewById(R.id.rb_transportadora);
        this.rgTipoPassageiro = (RadioGroup)findViewById(R.id.rg_tipo_usuario);
        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }


}
