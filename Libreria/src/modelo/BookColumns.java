package modelo;

public enum BookColumns {
	TITULO("title",false), AUTOR("author",true), EDITORIAL("editorial",true), ISBN("isbn",false), FORMATO("format",true), ESTADO("state",true),
	PRECIO("price",false), STOCK("stock",false),GENERO("genre",true);

	private String translation;
	private boolean foreign;
	
	private BookColumns(String translation,boolean foreign) {
		this.translation = translation;
		this.foreign = foreign;
	}

	public String getTranslation() {
		return translation;
	}

	public boolean isForeign() {
		return foreign;
	}
}
