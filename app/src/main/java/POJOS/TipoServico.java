package POJOS;

public enum TipoServico {
	URBANO("Urbano"),
	INTERMUNICIPAL("Intermunicipal"),
	METROPOLITANO("Metropolitano"),
	EXECUTIVO("Executivo"),
	SELETIVO("Seletivo");
	private String status;

	private TipoServico(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
}
