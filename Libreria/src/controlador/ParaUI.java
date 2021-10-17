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
	Libreria libreria = new Libreria();
	EditPanel editPanel = new EditPanel();
	StockPanel stockPanel = new StockPanel();

	public ParaUI() {
		super();
		txtIsbn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				char keyTyped = e.getKeyChar();
				if (txtIsbn.getText().length() >= 13 || !Character.isDigit(keyTyped)) {
					e.consume();
				}
				if (validateIsbn()) {
					setValidBackground(txtIsbn);
				} else {
					setWrongBackground(txtIsbn);
				}
			}
		});
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
							Utiles.getSelectedRadio(editPanel.getBgEstado()),(Integer)spnStock.getValue()));
				}
				editPanel.vaciarCampos();
				libreria.rellenarTabla(tablaLibros);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "El ISBN introduce no es válido o no existe");
			}

		});
		txtAutor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				restrictText(e);
			}
		});
		txtEditorial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				restrictText(e);
			}
		});

		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				String keyTyped = String.valueOf(e.getKeyChar());
				String precio = txtPrecio.getText() + e.getKeyChar();
				restrictPrice(precio, keyTyped, e);
			}
		});

		btnGuardar.addActionListener((e) -> {
			try {
				addLibro();
				vaciarCampos();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "R");
			}
		});

		btnSalir.addActionListener((e) -> {
			System.exit(0);
		});

		btnBorrar.addActionListener((e) -> {
			try {
				String isbn = getIsbnTabla();
				if (isbn == null) {
					isbn = JOptionPane.showInputDialog("Introduce el ISBN a borrar");
				}
				borrarLibro(isbn);
				limpiarTabla();
				libreria.rellenarTabla(tablaLibros);
				vaciarCampos();
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(null, "ISBN no introducido o fila no seleccionada");
			}
		});

		btnConsultar.addActionListener((e) -> {
			String isbn = JOptionPane.showInputDialog("Introduce el ISBN a consultar");
			Libro libroAConsultar = libreria.getLibro(isbn);
			vaciarCampos();
			try {
				rellenarCampos(libroAConsultar);
				Utiles.setSelectedRadio(bgFormato, libroAConsultar.getFormato());
				Utiles.setSelectedRadio(bgEstado, libroAConsultar.getEstado());
			} catch (Exception e2) {
				txtIsbn.setText("El isbn no existe, introduce un isbn válido");
			}

		});
		editPanel.getTxtAutor().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				restrictText(e);
			}
		});
		editPanel.getTxtEditorial().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				restrictText(e);
			}
		});
		editPanel.getTxtPrecio().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				String keyTyped = String.valueOf(e.getKeyChar());
				String precio = editPanel.getTxtPrecio().getText() + e.getKeyChar();
				restrictPrice(precio, keyTyped, e);
			}
		});
		spnTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				super.keyTyped(e);
				char keyTyped = e.getKeyChar();
				String actualStock = spnTxt.getText() + keyTyped;
				if (!Character.isDigit(keyTyped) || !actualStock.matches("[0-9]{0,3}")) {
					e.consume();
				}
			}
		});
		btnStock.addActionListener((e) -> {
				JOptionPane.showMessageDialog(null, stockPanel);
		});
		stockPanel.getBtnDown().addActionListener((e) -> {
			Integer cantidad = 0;
			cantidad = askQuantity();
			String isbn = getISBNWithPane();
				if (isbn != null && cantidad != 0) {
					Libro libro = libreria.getLibro(isbn);
						if (libro.getCantidad() <= cantidad) {
							JOptionPane.showMessageDialog(null,"Cantidad mayor al stock, eliminando libro");
							libreria.removeLibro(isbn);
						}else {
							libro.setCantidad(libro.getCantidad()-cantidad);
							JOptionPane.showMessageDialog(null,"Se han eliminado "+cantidad+", stock restante: "+libro.getCantidad());
						}
						libreria.rellenarTabla(tablaLibros);
				}		
		});
		
		stockPanel.getBtnUp().addActionListener((e) -> {
			Integer cantidad = 0;
			cantidad = askQuantity();
			String isbn = getISBNWithPane();
			if (isbn != null && cantidad != 0) {
				Libro libro = libreria.getLibro(isbn);
				libro.setCantidad(libro.getCantidad()+cantidad);
				libreria.rellenarTabla(tablaLibros);
				JOptionPane.showMessageDialog(null,"Se han añadido "+cantidad+", stock : "+libro.getCantidad());
			}
		});
	}

	private void addLibro() {
		if (validateIsbn()) {
			if (!checkIfNull()) {
				libreria.addLibros(new Libro(txtIsbn.getText(), txtTitulo.getText(), txtAutor.getText(),
						txtEditorial.getText(), Float.valueOf(txtPrecio.getText()), Utiles.getSelectedRadio(bgFormato),
						Utiles.getSelectedRadio(bgEstado),(Integer)spnStock.getValue()));
				libreria.rellenarTabla(tablaLibros);
			} else {
				JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos");
			}
		}

		else {
			JOptionPane.showMessageDialog(null, "El isbn no es valido, minimo 13 caracteres");
		}
	}

	private void vaciarCampos() {
		txtAutor.setText("");
		txtIsbn.setBackground(Color.WHITE);
		txtIsbn.setText("");
		txtPrecio.setText("");
		txtEditorial.setText("");
		txtTitulo.setText("");
		deselectRadio(bgFormato, bgEstado);
	}

	private void rellenarCampos(Libro libro) {
		txtAutor.setText(libro.getAutor());
		txtEditorial.setText(libro.getEditorial());
		txtIsbn.setText(libro.getISBN());
		txtPrecio.setText(String.valueOf(libro.getPrecio()));
		txtTitulo.setText(libro.getTitulo());
	}

	private boolean validateIsbn() {
		boolean goodIsbn = txtIsbn.getText().length() == 13;
		return goodIsbn;
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

	private void restrictText(KeyEvent e) {
		String keyPulsed = String.valueOf(e.getKeyChar());
		if (!keyPulsed.matches("[A-Za-z ]"))
			e.consume();
	}

	private boolean checkIfNull() {
		return txtPrecio.getText().equals("") || txtAutor.getText().equals("") || txtEditorial.getText().equals("")
				|| txtTitulo.getText().equals("") || Utiles.getSelectedRadio(bgFormato).equals("")
				|| Utiles.getSelectedRadio(bgEstado).equals("");
	}

	private void deselectRadio(ButtonGroup bgFormato, ButtonGroup bgEstado) {
		bgFormato.clearSelection();
		bgEstado.clearSelection();
	}

	private void setValidBackground(Component component) {
		component.setBackground(new Color(146, 236, 103));
	}

	private void setWrongBackground(Component component) {
		component.setBackground(new Color(233, 84, 84));
	}

	private void restrictPrice(String textPrice, String keyTyped, KeyEvent e) {
		if (!keyTyped.matches("[0-9.]") || !textPrice.matches("(^[0-9]{0,}[.]{0,1}[0-9]{0,2}$)")) {
			e.consume();
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
	private int askQuantity() {
		try {
			return Integer.valueOf(JOptionPane.showInputDialog(null,"Introduce la cantidad"));
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
