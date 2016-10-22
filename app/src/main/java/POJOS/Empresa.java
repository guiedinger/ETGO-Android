package POJOS;
import java.io.Serializable;
import java.util.List;

public class Empresa extends Usuario implements Serializable{

	private String cnpj;

	private List <Passageiro> passageiro;

	private List <Transacao> transacao;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Empresa(Integer idUsuario, String nome, String telefone, String email,
			Double saldo, String cnpj, List<Passageiro> passageiro, List<Transacao> transacao) {
		super();
		this.cnpj = cnpj;
		this.passageiro = passageiro;
		this.transacao = transacao;
	}

	public Empresa(){

	}
}
