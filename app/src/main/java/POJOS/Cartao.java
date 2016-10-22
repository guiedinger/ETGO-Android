package POJOS;
import java.io.Serializable;

public class Cartao implements Serializable {

	private Integer idCartao;

	private String numCartao;

	private String codSeguranca;

	private String operadora;

	public Integer getIdCartao() {
	return idCartao;
	}
	public void setIdCartao(Integer idCartao) {
	this.idCartao = idCartao;
	}
	public String getNumCartao() {
		return numCartao;
	}
	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
	public String getCodSeguranca() {
		return codSeguranca;
	}
	public void setCodSeguranca(String codSeguranca) {
		this.codSeguranca = codSeguranca;
	}
	public String getOperadora() {
		return operadora;
	}
	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}
	public Cartao(Integer idCartao, String numCartao, String codSeguranca, String operadora) {
		super();
		this.idCartao = idCartao;
		this.numCartao = numCartao;
		this.codSeguranca = codSeguranca;
		this.operadora = operadora;
	}
	
	public Cartao(){
		
	}

}
