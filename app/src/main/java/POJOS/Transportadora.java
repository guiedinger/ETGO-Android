package POJOS;
import java.util.List;

public class Transportadora extends Usuario{

	private String cnpj;

	private List<Onibus> onibus;

	private List<Motorista> motoristas;

	private List<Linha> linhas;
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Transportadora(Integer idUsuario, Token token, String nome, String userName, String password, String telefone, String email, String cnpj) {
		super(idUsuario, token, nome, userName, password, telefone, email);
		this.cnpj = cnpj;
	}

	public Transportadora(){

	}
	
}
