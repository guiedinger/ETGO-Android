package POJOS;
import java.io.Serializable;

public class Achado implements Serializable{

	private Integer idAchado;

	private Viagem viagem;

	private String descricao;

	private String foto;

	private TipoAchado tipo;
	
	public Integer getIdAchado() {
		return idAchado;
	}
	public void setIdAchado(Integer idAchado) {
		this.idAchado = idAchado;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Achado(Integer idAchado, String descricao) {
		super();
		this.idAchado = idAchado;
		this.descricao = descricao;
	}
	
	public Achado(){
		
	}
}
