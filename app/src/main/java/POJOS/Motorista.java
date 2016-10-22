package POJOS;
import java.io.Serializable;
import java.util.List;

public class Motorista implements Serializable{

	private Integer idMotorista;

	private List<Viagem> viagem;

	private String nome;

	private String cpf;



	public Motorista(){
		
	}
}
