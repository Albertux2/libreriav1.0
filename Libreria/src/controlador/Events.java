package controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import modelo.Utils;

public class Events {
	public static KeyAdapter getRestrictedTextEvent() {
		return new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				super.keyTyped(e);
				String keyPulsed = String.valueOf(e.getKeyChar());
				if (!keyPulsed.matches("[A-Za-z ]"))
					e.consume();
			}
		};
	}

	public static KeyAdapter getRestrictedPriceEvent(JTextField priceText) {
		return new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				String keyTyped = String.valueOf(e.getKeyChar());
				String precio = priceText.getText() + e.getKeyChar();
				if (!keyTyped.matches("[0-9.]") || !precio.matches("(^[0-9]{0,3}[.]{0,1}[0-9]{0,2}$)")) {
					e.consume();
				}
			}
		};
	}

	public static KeyAdapter getOnlyNumberEvent(JTextField numberText) {
		return new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				char keyTyped = e.getKeyChar();
				if(!Character.isDigit(keyTyped) || numberText.getText().length()>6) {
					e.consume();
				}
			}
		};
	}

	public static KeyAdapter getRestrictedIsbnText(JTextField txtIsbn) {
		return new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				char keyTyped = e.getKeyChar();
				String isbn = txtIsbn.getText() + keyTyped;
				if (isbn.length() > 13 || !Character.isDigit(keyTyped)) {
					e.consume();
				}
				if (isbn.length() >= 13) {
					Utils.setValidBackground(txtIsbn);
				} else {
					Utils.setWrongBackground(txtIsbn);
				}
			}
		};
	}
}
