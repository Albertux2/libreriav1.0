package controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.DB.DatabaseAccess;
import modelo.*;
import vista.EditPanel;
import vista.InputComponents;
import vista.StockPanel;
import vista.UI;

public class ParaUI extends UI {
	Bookstore bookstore;
	EditPanel editPanel = new EditPanel();
	StockPanel stockPanel = new StockPanel();
	DatabaseAccess databaseAccess;
	public ParaUI() {
		super();
		loadBookstore();
		
		btnEdit.addActionListener((e) -> {
			String isbn = Utils.getISBNWithPane(bookstore);
			if (isbn != null) {
				try {
					editPanel.fillFields(bookstore.getBook(isbn));
					JOptionPane.showMessageDialog(null, editPanel);
					if (!editPanel.checkIfNull()) {
						bookstore.addBook(new Book(isbn, editPanel.getTxtTitulo().getText(),
								editPanel.getTxtAutor().getText(), editPanel.getTxtEditorial().getText(),
								Float.valueOf(editPanel.getTxtPrecio().getText()),
								Utils.getSelectedRadio(editPanel.getBgFormato()),
								Utils.getSelectedRadio(editPanel.getBgEstado()), (Integer) spnStock.getValue(),
								comboBox.getSelectedItem().toString()));
					}
					checkIfNewData(editPanel.getTxtAutor().getText(),editPanel.getTxtEditorial().getText());
					editPanel.clearFields();
					bookstore.fillTable(tablaLibros);
					updateBook(bookstore.getBook(isbn));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "El ISBN introduce no es válido o no existe");
				}
			}

		});
		btnGuardar.addActionListener((e) -> {
			try {
				if (Utils.validateIsbn(getISBN())) {
					if (!checkIfNull()) {
						if (!bookstore.containsISBN(getISBN())) {
							addBook();
							saveBookInDatabase(bookstore.getBook(getISBN()));
						} else {
							JOptionPane.showMessageDialog(null, "Ya existe un libro con ese ISBN, introduzca otro.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"El isbn no es valido o esta vacio, debe contener 13 caracteres");
				}
				clearFields();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Error al guardar");
			}
		});

		btnBorrar.addActionListener((e) -> {
			String isbn = getIsbnFromTable();
			if (isbn == null) {
				isbn = Utils.getISBNWithPane(bookstore);
				if (isbn != null) {
					if (!bookstore.containsISBN(isbn)) {
						JOptionPane.showMessageDialog(null, "El ISBN introducido no existe");
					}
				}
			}
			this.deleteBookFromDatabase(isbn);
			removeBook(isbn);
			clearTable();
			bookstore.fillTable(tablaLibros);
			clearFields();
		});

		btnConsultar.addActionListener((e) -> {
			String isbn = Utils.getISBNWithPane(bookstore);
			if (isbn != null) {
				Book libroAConsultar = bookstore.getBook(isbn);
				clearFields();
				try {
					fillFields(libroAConsultar);
					setEnabledAll(false);
					btnClear.setEnabled(true);
					btnGuardar.setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "El ISBN introducido no existe o no es válido");
				}
			}
		});
		btnStock.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null, stockPanel);
		});
		stockPanel.getBtnDown().addActionListener((e) -> {
			Integer cantidad;
			cantidad = Utils.askQuantity();
			if (cantidad != 0) {
				String isbn = Utils.getISBNWithPane(bookstore);
				if (isbn != null) {
					Book libro = bookstore.getBook(isbn);
					if (libro.getQuantity() <= cantidad) {
						int showConfirmDialog = JOptionPane.showConfirmDialog(null,
								"Cantidad mayor al stock,Â¿desea eliminar el libro?");

						if (showConfirmDialog == JOptionPane.YES_OPTION) {
							bookstore.removeBook(isbn);
							deleteBookFromDatabase(isbn);
							JOptionPane.showMessageDialog(null, "Libro eliminado con exito");
						} else {
							libro.setQuantity(0);
						}
					} else {
						libro.setQuantity(libro.getQuantity() - cantidad);
						JOptionPane.showMessageDialog(null,
								"Se han eliminado " + cantidad + ", stock restante: " + libro.getQuantity());
					}
					bookstore.fillTable(tablaLibros);
					updateStock(bookstore.getBook(isbn));
				}
			}
		});

		stockPanel.getBtnUp().addActionListener((e) -> {
			Integer cantidad = 0;
			cantidad = Utils.askQuantity();
			if (cantidad != 0) {
				String isbn = Utils.getISBNWithPane(bookstore);
				if (isbn != null) {
					Book libro = bookstore.getBook(isbn);
					libro.setQuantity(libro.getQuantity() + cantidad);
					bookstore.fillTable(tablaLibros);
					JOptionPane.showMessageDialog(null,
							"Se han añadido " + cantidad + ", stock : " + libro.getQuantity());
					deleteBookFromDatabase(isbn);
					saveBookInDatabase(bookstore.getBook(isbn));
				}
			}
		});
	}

	private void addBook() {
		bookstore.addBook(new Book(getISBN(), txtTitulo.getText(), txtAutor.getText(), txtEditorial.getText(),
				Float.valueOf(txtPrecio.getText()), Utils.getSelectedRadio(bgFormato), Utils.getSelectedRadio(bgEstado),
				(Integer) spnStock.getValue(), comboBox.getSelectedItem().toString()));
		bookstore.fillTable(tablaLibros);
	}

	private void removeBook(String isbn) {
		bookstore.removeBook(isbn);
	}

	private void saveBookInDatabase(Book libro) {
		this.checkIfNewData(txtAutor.getText(),txtEditorial.getText());
		databaseAccess.insertBook(libro);
	}
	
	private void updateBook(Book book) {
		databaseAccess.updateBook(book);
	}
	
	private void updateStock(Book book) {
		databaseAccess.updateStock(book);
	}
	
	private void checkIfNewData(String author,String editorial) {
		if(!databaseAccess.containsAuthor(author)) {
			databaseAccess.insertAuthor(author);
		}
		if(!databaseAccess.containsEditorial(editorial)){
			databaseAccess.insertEditorial(editorial);
		}
	}

	private void deleteBookFromDatabase(String isbn) {
		databaseAccess.deleteBook(isbn);
	}

	private void loadBookstore() {
		databaseAccess = new DatabaseAccess();
		this.genres = databaseAccess.getGenres();
		comboBox.setModel(new DefaultComboBoxModel(genres));
		bookstore = new Bookstore();
		bookstore.setBooks(databaseAccess.getBooks());
		bookstore.fillTable(tablaLibros);
	}
}
