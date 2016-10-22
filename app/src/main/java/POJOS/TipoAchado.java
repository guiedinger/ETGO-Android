package POJOS;

public enum TipoAchado {

	ELETRONICO("Eletronico"),
	DOCUMENTO("Documento"),
	CADERNO("Caderno"),
	DIVERSOS("Diversos"),
	ROUPA("Roupa"),
	MOCHILA("Mochila"),
	BOLSA("Bolsa"),
	CARTEIRA("Carteira"),
	SACOLA("Sacola");
	private String status;

	private TipoAchado(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
