package POJOS;
import java.io.Serializable;
import java.util.List;

public class Linha implements Serializable{

	private Integer idLinha;

	private List<Viagem> viagens;

	private String descricao;

	private TipoServico tipo;

	public List<Viagem> getViagens() {
		return viagens;
	}

	public void setViagens(List<Viagem> viagens) {
		this.viagens = viagens;
	}

	public TipoServico getTipo() {
		return tipo;
	}

	public void setTipo(TipoServico tipo) {
		this.tipo = tipo;
	}

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

    @Override
    public String toString() {
        return descricao;
    }

    public Linha(){
		
	}
}
