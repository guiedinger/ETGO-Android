package POJOS;
import java.io.Serializable;
import java.util.Date;

public class Transacao implements Serializable{

	private Integer idTransacao;

	private Date data;

	private float valor;

	private TipoTransacao tipo;
	
	public Integer getIdTransacao() {
		return idTransacao;
	}
	public void setIdTransacao(Integer idTransacao) {
		this.idTransacao = idTransacao;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public Transacao(Integer idTransacao, Integer valor) {
		super();
		this.idTransacao = idTransacao;
		this.valor = valor;
	}
	
	public Transacao(){
		
	}
}
