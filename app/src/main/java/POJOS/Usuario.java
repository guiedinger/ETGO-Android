package POJOS;

public abstract class Usuario {

    private Integer idUsuario;

    private Login login;

    private String nome;

    private String telefone;

    private String email;

    private Double saldo;

    public Usuario(Integer idUsuario, Login login, String nome, String telefone, String email, Double saldo) {
        this.idUsuario = idUsuario;
        this.login = login;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.saldo = saldo;
    }

    public Usuario(Integer idUsuario, Login login, String nome, String telefone, String email) {
        this.idUsuario = idUsuario;
        this.login = login;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
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
