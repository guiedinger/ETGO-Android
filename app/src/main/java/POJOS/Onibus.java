package POJOS;
import java.io.Serializable;
import java.util.List;

public class Onibus implements Serializable{

	private Integer idOnibus;

	private List<Viagem> viagens;

	private String placa;

	private String modelo;

	private TipoAchado tipo;
	
	public Integer getIdOnibus() {
		return idOnibus;
	}
	
	public void setIdOnibus(Integer idOnibus) {
		this.idOnibus = idOnibus;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Onibus(Integer idOnibus, String placa, String modelo) {
		super();
		this.idOnibus = idOnibus;
		this.placa = placa;
		this.modelo = modelo;
	}
	
	public Onibus(){
		
	}
}
