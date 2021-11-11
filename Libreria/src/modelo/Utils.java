package modelo;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import controlador.Bookstore;
import vista.InputComponents;

public class Utils {
	public static String getSelectedRadio(ButtonGroup group) {
		Enumeration<AbstractButton> elements = group.getElements();
		for (int i = 0; i < group.getButtonCount(); i++) {
			AbstractButton actual = elements.nextElement();
			if (actual.isSelected()) {
				return actual.getText();
			}
		}
		return "";
	}

	public static void setSelectedRadio(ButtonGroup group, String string) {
		Enumeration<AbstractButton> elements = group.getElements();
		for (int i = 0; i < group.getButtonCount(); i++) {
			AbstractButton actual = elements.nextElement();
			if (actual.getText().equals(string)) {
				actual.setSelected(true);
			}
		}
	}

	public static int askQuantity() {
		InputComponents inputs = new InputComponents();
		int buttonPressed = JOptionPane.showConfirmDialog(null,
				new Object[] { "Introduce la cantidad", inputs.getNumberInput() }, "Cantidad",
				JOptionPane.YES_NO_OPTION);
		int quantity = inputs.getNumber();
		if (buttonPressed == JOptionPane.OK_OPTION) {
			if (quantity > 0) {
				return quantity;
			}
		}
		return 0;
	}


	public static String getISBNWithPane(Bookstore bookstore) {
		InputComponents inputs = new InputComponents();
		int buttonPressed = JOptionPane.showConfirmDialog(null,
				new Object[] { "Introduce el ISBN", inputs.getIsbnInput() }, "Introduce el ISBN",
				JOptionPane.YES_NO_OPTION);
		String isbn = inputs.getIsbnText();
		if (buttonPressed == JOptionPane.OK_OPTION) {
			if (isbn != null) {
				if (bookstore.containsISBN(isbn))
					return isbn;
				else
					JOptionPane.showMessageDialog(null, "ISBN invalido o inexistente");
				return null;
			}
		}
		return null;
	}

	public static void setValidBackground(Component component) {
		component.setBackground(new Color(146, 236, 103));
	}

	public static void setWrongBackground(Component component) {
		component.setBackground(new Color(233, 84, 84));
	}

	public static boolean validateIsbn(String isbn) {
		boolean goodIsbn = isbn.length() == 13;
		return goodIsbn;
	}

}
