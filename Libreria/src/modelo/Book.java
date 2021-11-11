package modelo;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
	private String ISBN;
	private String title;
	private String author;
	private String editorial;
	private float price;
	private String format;
	private String state;
	private int quantity;
	private String genre;

	public Book(String iSBN, String titulo, String autor, String editorial, float precio, String formato,
			String estado,int cantidad,String genero) {
		super();
		ISBN = iSBN;
		this.title = titulo;
		this.author = autor;
		this.editorial = editorial;
		this.price = precio;
		this.state = estado;
		this.format = formato;
		this.quantity = cantidad;
		this.genre = genero;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float precio) {
		this.price = precio;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getEditorial() {
		return editorial;
	}
	
	public String getGenre() {
		return this.genre;
	}

	public String getFormat() {
		return format;
	}

	public String getState() {
		return state;
	}
	

	public void setQuantity(int cantidad) {
		this.quantity = cantidad;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "Libro [ISBN=" + ISBN + ", titulo=" + title + ", autor=" + author + ", editorial=" + editorial
				+ ", precio=" + price + ", formato=" + format + ", estado=" + state + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ISBN, author, quantity, editorial, state, format, genre, price, title);
	}

	public boolean equals(Book obj) {
		return this.getISBN() == obj.getISBN();
	}
	
	
	

}
