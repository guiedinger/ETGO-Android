package POJOS;
        import java.io.Serializable;
        import java.util.List;


public class Passageiro extends Usuario implements Serializable{

    private String cpf;

    private List <Avaliacao> avaliacao;

    private List <Transacao> transacao;

    private List<Viagem> viagens;

    private TipoPassageiro tipo;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Avaliacao> getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(List<Avaliacao> avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Transacao> getTransacao() {
        return transacao;
    }

    public void setTransacao(List<Transacao> transacao) {
        this.transacao = transacao;
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }

    public TipoPassageiro getTipo() {
        return tipo;
    }

    public void setTipo(TipoPassageiro tipo) {
        this.tipo = tipo;
    }


    public Passageiro(Integer idUsuario,  String nome, String userName, String password, String telefone, String email, String cpf) {
        super(idUsuario, nome, userName, password, telefone, email);
        this.cpf = cpf;
    }

    public Passageiro(){

    }

}
