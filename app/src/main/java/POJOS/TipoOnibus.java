package POJOS;

public enum TipoOnibus {

	MICROONIBUS("Micro Onibus"),
	MIDIBUS("Midibus"),
	ONIBUS("Onibus"),
	ARTICULADO("Articulado"),
	CATEGORIAESPECIAL("Categoria Especial");
	
	private String status;

	private TipoOnibus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
