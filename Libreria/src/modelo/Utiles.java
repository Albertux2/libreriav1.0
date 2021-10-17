package modelo;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

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
}
