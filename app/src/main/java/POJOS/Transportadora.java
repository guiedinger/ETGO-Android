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

	public Transportadora(Integer idUsuario, String nome, String telefone, String email,
			Double saldo, String cnpj, List<Onibus> onibus, List<Motorista> motoristas, List<Linha> linhas) {
		super();
		this.cnpj = cnpj;
		this.onibus = onibus;
		this.motoristas = motoristas;
		this.linhas = linhas;
	}

	public Transportadora(){

	}
	
}
