package modelo;

import java.io.Serializable;

public class Libro implements Serializable {
	private String ISBN;
	private String titulo;
	private String autor;
	private String editorial;
	private float precio;
	private String formato;
	private String estado;
	private int cantidad;
	private String genero;

	public Libro(String iSBN, String titulo, String autor, String editorial, float precio, String formato,
			String estado,int cantidad,String genero) {
		super();
		ISBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.precio = precio;
		this.estado = estado;
		this.formato = formato;
		this.cantidad = cantidad;
		this.genero = genero;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getEditorial() {
		return editorial;
	}
	
	public String getGenero() {
		return this.genero;
	}

	public String getFormato() {
		return formato;
	}

	public String getEstado() {
		return estado;
	}
	

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	@Override
	public String toString() {
		return "Libro [ISBN=" + ISBN + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial
				+ ", precio=" + precio + ", formato=" + formato + ", estado=" + estado + "]";
	}
	
	

}
