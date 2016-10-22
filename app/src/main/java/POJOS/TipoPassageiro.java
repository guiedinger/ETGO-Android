package POJOS;

public enum TipoPassageiro {
	
	VALETRANSPORTE("ValeTransporte"),
	FUNCIONARIO("Funcionario"),
	ANTECIPADO("Antecipado"),
	ISENTO("Isento"),
	IDOSO("Idoso"),
	ESCOLAR("Escolar");
	private String status;

	private TipoPassageiro(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
