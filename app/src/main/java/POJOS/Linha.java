package POJOS;
import java.io.Serializable;
import java.util.List;

public class Linha implements Serializable{

	private Integer idLinha;

	private List<Transportadora> transportadora;

	private List<Viagem> viagens;

	private String descricao;

	private TipoServico tipo;
	
	public Integer getIdLinha() {
		return idLinha;
	}
	public void setIdLinha(Integer idLinha) {
		this.idLinha = idLinha;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Linha(Integer idLinha, String descricao) {
		super();
		this.idLinha = idLinha;
		this.descricao = descricao;
	}
	public Linha(){
		
	}
}
