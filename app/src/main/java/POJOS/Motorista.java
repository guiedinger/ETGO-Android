package POJOS;
import java.io.Serializable;
import java.util.List;

public class Motorista implements Serializable{

	private Integer idMotorista;

	private List<Viagem> viagem;

	private String nome;

	private String cpf;

	public Integer getIdMotorista() {
		return idMotorista;
	}

	public void setIdMotorista(Integer idMotorista) {
		this.idMotorista = idMotorista;
	}

	public List<Viagem> getViagem() {
		return viagem;
	}

	public void setViagem(List<Viagem> viagem) {
		this.viagem = viagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return nome;
	}

	public Motorista(){
		
	}
}
