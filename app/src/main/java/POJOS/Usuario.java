package POJOS;

import java.io.Serializable;

public abstract class Usuario implements Serializable {

    private Integer idUsuario;

    private String nome;

    private String userName;

    private String password;

    private String telefone;

    private String email;

    private Double saldo;

    public Usuario(Integer idUsuario, String nome, String userName, String password, String telefone, String email) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.userName = userName;
        this.password = password;
        this.telefone = telefone;
        this.email = email;
    }

    public Usuario(Double saldo, Integer idUsuario, String nome, String userName, String password, String telefone, String email) {
        this.saldo = saldo;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.userName = userName;
        this.password = password;
        this.telefone = telefone;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Usuario(){
    }
}
