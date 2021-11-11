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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.*;
import vista.EditPanel;
import vista.InputComponents;
import vista.StockPanel;
import vista.UI;

public class ParaUI extends UI {
	FileControl fileControl = FileControl.getInstance();
	String path = "./src/files/libros.lib";
	Bookstore bookstore;
	EditPanel editPanel = new EditPanel();
	StockPanel stockPanel = new StockPanel();

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
					editPanel.clearFields();
					bookstore.fillTable(tablaLibros);
					deleteBook(isbn);
					saveBookInFile(bookstore.getBook(isbn));
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
							saveBookInFile(bookstore.getBook(getISBN()));
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
			this.deleteBook(isbn);
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
		editPanel.getTxtAutor().addKeyListener(Events.getRestrictedTextEvent());
		editPanel.getTxtEditorial().addKeyListener(Events.getRestrictedTextEvent());
		editPanel.getTxtPrecio().addKeyListener(Events.getRestrictedPriceEvent(editPanel.getTxtPrecio()));
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
							deleteBook(isbn);
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
					deleteBook(isbn);
					saveBookInFile(bookstore.getBook(isbn));
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
					deleteBook(isbn);
					saveBookInFile(bookstore.getBook(isbn));
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

	private void saveBookInFile(Book libro) {
		fileControl.save(path, libro, true);
	}

	private void deleteBook(String isbn) {
		ArrayList<Book> nonDeletedBooks = fileControl.delete(path, isbn);
		fileControl.saveList(path, nonDeletedBooks);
	}

	private void loadBookstore() {
		int index = 0;
		bookstore = new Bookstore();
		Book libro = fileControl.getWithIndex(path, index);
		while (libro != null) {
			bookstore.addBook(libro);
			index++;
			libro = fileControl.getWithIndex(path, index);
		}
		bookstore.fillTable(tablaLibros);
	}
}
