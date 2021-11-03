package modelo;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

public class Utiles {
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
		try {
			return Integer.valueOf(JOptionPane.showInputDialog(null, "Introduce la cantidad"));
		} catch (NumberFormatException e) {
			return 0;
		}
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
