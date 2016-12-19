package POJOS;
import java.util.List;

public class Transportadora extends Usuario{

	private String cnpj;

	private List<Onibus> onibus;

	private List<Motorista> motoristas;

	private List<Linha> linhas;

	public List<Onibus> getOnibus() {
		return onibus;
	}

	public void setOnibus(List<Onibus> onibus) {
		this.onibus = onibus;
	}

	public List<Motorista> getMotoristas() {
		return motoristas;
	}

	public void setMotoristas(List<Motorista> motoristas) {
		this.motoristas = motoristas;
	}

	public List<Linha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<Linha> linhas) {
		this.linhas = linhas;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Transportadora(Integer idUsuario, String nome, String userName, String password, String telefone, String email, String cnpj) {
		super(idUsuario, nome, userName, password, telefone, email);
		this.cnpj = cnpj;
	}

	public Transportadora(){

	}
	
}
