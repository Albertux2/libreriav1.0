package controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Book;

public class Bookstore implements Serializable{
	private HashMap<String,Book> arrayLibro = new HashMap<String,Book>();
	public void addBook(Book libro) {
		this.arrayLibro.put(libro.getISBN(), libro);
	}

	public void deleteBook(String isbn) {
		try {
			arrayLibro.remove(isbn);
		} catch (Exception e) {
			
		}
	}
	
	public void setBooks(HashMap books) {
		this.arrayLibro = books;
	}
	public Book getBook(int index) {
		ArrayList<Book> listLibros = new ArrayList<Book>(arrayLibro.values());
		return listLibros.get(index);
	}
	
	
	public boolean containsISBN(String isbn) {
		return arrayLibro.containsKey(isbn);
	}
	
	public HashMap<String,Book> getBookstore() {
		return this.arrayLibro;
	}
	
	public Book getBook(String isbn) {
		if (arrayLibro.containsKey(isbn)) {
			return arrayLibro.get(isbn);
		}
		return null;
	}
	
	public void removeBook(String isbn) {
		arrayLibro.remove(isbn);
	}
	
	public void fillTable(JTable tablaLibros) {
		String nombreColumna[] = { "ISBN", "Titulo", "Autor", "Editorial", "Precio","Formato","Estado","Cantidad","Genero" };
		String[][] filasTabla = new String[this.getBookstore().size()][nombreColumna.length];
		
		for (int i = 0; i < this.getBookstore().size(); i++) {
			filasTabla[i][0] = this.getBook(i).getISBN();
			filasTabla[i][1] = this.getBook(i).getTitle();
			filasTabla[i][2] = this.getBook(i).getAuthor();
			filasTabla[i][3] = this.getBook(i).getEditorial();
			String precio = String.valueOf(this.getBook(i).getPrice());
			filasTabla[i][4] = precio;
			filasTabla[i][5] = this.getBook(i).getFormat();
			filasTabla[i][6] = this.getBook(i).getState();
			filasTabla[i][7] = String.valueOf(this.getBook(i).getQuantity());
			filasTabla[i][8] = this.getBook(i).getGenre();
		}
		DefaultTableModel tablaCompleta = new DefaultTableModel(filasTabla, nombreColumna);
		tablaLibros.setModel(tablaCompleta);
	}

}
