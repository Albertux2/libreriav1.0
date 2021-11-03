package controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.*;
import vista.EditPanel;
import vista.StockPanel;
import vista.UI;

public class ParaUI extends UI {
	ControlFichero ficheros = new ControlFichero();
	String path = "./src/files/libreria.lib";
	Libreria libreria;
	EditPanel editPanel = new EditPanel();
	StockPanel stockPanel = new StockPanel();

	public ParaUI() {
		super();
		cargarLibreria();
		saveOnClose();
		btnEdit.addActionListener((e) -> {
			String isbn = JOptionPane.showInputDialog("Introduce el ISBN del libro a editar");
			try {
				editPanel.rellenarCampos(libreria.getLibro(isbn));
				JOptionPane.showMessageDialog(null, editPanel);
				if (!editPanel.checkIfNull()) {
					libreria.addLibros(new Libro(isbn, editPanel.getTxtTitulo().getText(),
							editPanel.getTxtAutor().getText(), editPanel.getTxtEditorial().getText(),
							Float.valueOf(editPanel.getTxtPrecio().getText()),
							Utiles.getSelectedRadio(editPanel.getBgFormato()),
							Utiles.getSelectedRadio(editPanel.getBgEstado()), (Integer) spnStock.getValue(),comboBox.getSelectedItem().toString()));
				}

				editPanel.vaciarCampos();
				libreria.rellenarTabla(tablaLibros);
				actualizarFichero();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "El ISBN introduce no es válido o no existe");
			}

		});
		btnGuardar.addActionListener((e) -> {
			try {
				if (Utiles.validateIsbn(getISBN())) {
					if (!checkIfNull()) {
						addLibro();
					} else {
						JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos");
					}
				} else {
					JOptionPane.showMessageDialog(null, "El isbn no es valido, minimo 13 caracteres");
				}
				vaciarCampos();
				actualizarFichero();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Error al guardar");
			}
		});
		
		btnBorrar.addActionListener((e) -> {
			String isbn = getIsbnTabla();
			if (isbn == null) {
				isbn = JOptionPane.showInputDialog("Introduce el ISBN a borrar");
				if (!libreria.containsISBN(isbn)) {
					JOptionPane.showMessageDialog(null, "El ISBN introducido no existe");
				}
			}
			borrarLibro(isbn);
			limpiarTabla();
			libreria.rellenarTabla(tablaLibros);
			vaciarCampos();
			actualizarFichero();
		});

		btnConsultar.addActionListener((e) -> {
			String isbn = JOptionPane.showInputDialog("Introduce el ISBN a consultar");
			Libro libroAConsultar = libreria.getLibro(isbn);
			vaciarCampos();
			try {
				rellenarCampos(libroAConsultar);
				setEnabledAll(false);
				btnClear.setEnabled(true);
				btnGuardar.setVisible(false);

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "El ISBN introducido no existe o no es válido");
			}

		});
		editPanel.getTxtAutor().addKeyListener(Events.getRestrictedTextEvent());
		editPanel.getTxtEditorial().addKeyListener(Events.getRestrictedTextEvent());
		editPanel.getTxtPrecio().addKeyListener(Events.getRestrictedPriceEvent(editPanel.getTxtPrecio().getText()));
		btnStock.addActionListener((e) -> {
			JOptionPane.showMessageDialog(null, stockPanel);
		});
		stockPanel.getBtnDown().addActionListener((e) -> {
			Integer cantidad = 0;
			cantidad = Utiles.askQuantity();
			String isbn = getISBNWithPane();
			if (isbn != null && cantidad != 0) {
				Libro libro = libreria.getLibro(isbn);
				if (libro.getCantidad() <= cantidad) {
					int showConfirmDialog = JOptionPane.showConfirmDialog(null,
							"Cantidad mayor al stock,Â¿desea eliminar el libro?");

					if (showConfirmDialog == JOptionPane.YES_OPTION) {
						libreria.removeLibro(isbn);
						JOptionPane.showMessageDialog(null, "Libro eliminado con exito");
					} else {
						libro.setCantidad(0);
					}
				} else {
					libro.setCantidad(libro.getCantidad() - cantidad);
					JOptionPane.showMessageDialog(null,
							"Se han eliminado " + cantidad + ", stock restante: " + libro.getCantidad());
				}
				libreria.rellenarTabla(tablaLibros);
				actualizarFichero();
			}
		});

		stockPanel.getBtnUp().addActionListener((e) -> {
			Integer cantidad = 0;
			cantidad = Utiles.askQuantity();
			String isbn = getISBNWithPane();
			if (isbn != null && cantidad != 0) {
				Libro libro = libreria.getLibro(isbn);
				libro.setCantidad(libro.getCantidad() + cantidad);
				libreria.rellenarTabla(tablaLibros);
				JOptionPane.showMessageDialog(null, "Se han añadido " + cantidad + ", stock : " + libro.getCantidad());
			}
			actualizarFichero();
		});
	}

	private void saveOnClose() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				ficheros.guardar(path, libreria, false);
			}
		});
	}

	private void addLibro() {
		libreria.addLibros(new Libro(getISBN(), txtTitulo.getText(), txtAutor.getText(), txtEditorial.getText(),
				Float.valueOf(txtPrecio.getText()), Utiles.getSelectedRadio(bgFormato),
				Utiles.getSelectedRadio(bgEstado), (Integer) spnStock.getValue(),comboBox.getSelectedItem().toString()));
		libreria.rellenarTabla(tablaLibros);
	}

	private void borrarLibro(String isbn) {
		libreria.borrarLibro(isbn);
	}

	private void limpiarTabla() {
		DefaultTableModel tablaVacia = new DefaultTableModel();
		tablaLibros.setModel(tablaVacia);
	}

	private String getIsbnTabla() {
		try {
			int columnaIsbn = 0;
			int fila = tablaLibros.getSelectedRow();
			return String.valueOf(tablaLibros.getValueAt(fila, columnaIsbn));
		} catch (Exception e) {
			return null;
		}
	}

	private String getISBNWithPane() {
		String isbn = JOptionPane.showInputDialog(null, "Introduce el ISBN a comprobar");
		if (libreria.containsISBN(isbn))
			return isbn;
		else
			JOptionPane.showMessageDialog(null, "ISBN invalido o inexistente");
		return null;
	}

	private void actualizarFichero() {
		ficheros.guardar(path, libreria, false);
	}

	private void cargarLibreria() {
		libreria = ficheros.get(path);
		if (libreria == null) {
			libreria = new Libreria();
		}
		libreria.rellenarTabla(tablaLibros);
	}
}
