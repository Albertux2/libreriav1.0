package controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Libro;

public class Libreria implements Serializable{
	private HashMap<String,Libro> arrayLibro = new HashMap<String,Libro>();
	public void addLibros(Libro libro) {
		this.arrayLibro.put(libro.getISBN(), libro);
	}

	public void borrarLibro(String isbn) {
		try {
			arrayLibro.remove(isbn);
		} catch (Exception e) {
			
		}
	}

	public Libro getLibro(int index) {
		ArrayList<Libro> listLibros = new ArrayList<Libro>(arrayLibro.values());
		return listLibros.get(index);
	}
	
	
	public boolean containsISBN(String isbn) {
		return arrayLibro.containsKey(isbn);
	}
	
	public HashMap<String,Libro> getLibreria() {
		return this.arrayLibro;
	}
	
	public Libro getLibro(String isbn) {
		if (arrayLibro.containsKey(isbn)) {
			return arrayLibro.get(isbn);
		}
		return null;
	}
	
	public void removeLibro(String isbn) {
		arrayLibro.remove(isbn);
	}
	
	public void rellenarTabla(JTable tablaLibros) {
		String nombreColumna[] = { "ISBN", "Titulo", "Autor", "Editorial", "Precio","Formato","Estado","Cantidad","Genero" };
		String[][] filasTabla = new String[this.getLibreria().size()][nombreColumna.length];
		
		for (int i = 0; i < this.getLibreria().size(); i++) {
			filasTabla[i][0] = this.getLibro(i).getISBN();
			filasTabla[i][1] = this.getLibro(i).getTitulo();
			filasTabla[i][2] = this.getLibro(i).getAutor();
			filasTabla[i][3] = this.getLibro(i).getEditorial();
			String precio = String.valueOf(this.getLibro(i).getPrecio());
			filasTabla[i][4] = precio;
			filasTabla[i][5] = this.getLibro(i).getFormato();
			filasTabla[i][6] = this.getLibro(i).getEstado();
			filasTabla[i][7] = String.valueOf(this.getLibro(i).getCantidad());
			filasTabla[i][8] = this.getLibro(i).getGenero();
		}
		DefaultTableModel tablaCompleta = new DefaultTableModel(filasTabla, nombreColumna);
		tablaLibros.setModel(tablaCompleta);
	}

}
