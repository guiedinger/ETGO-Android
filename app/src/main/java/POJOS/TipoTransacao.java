package POJOS;

public enum TipoTransacao {
	DEBITO("Debito"),
	RECARGA("Recarga");
	private String code;

	private TipoTransacao(String code) {
		this.code = code;
	}

	public String getStatus() {
		return this.code;
	}
}
