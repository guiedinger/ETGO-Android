package POJOS;
import java.io.Serializable;

public class Avaliacao implements Serializable{

	private Integer idAvaliacao;

	private float nota;

	private String comentario;
	
	public Integer getIdAvaliacao() {
		return idAvaliacao;
	}
	public void setIdAvaliacao(Integer idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	
	public Avaliacao(Integer idAvaliacao, float nota) {
		super();
		this.idAvaliacao = idAvaliacao;
		this.nota = nota;
	}
	
	public Avaliacao(){
	
	}
	
}
