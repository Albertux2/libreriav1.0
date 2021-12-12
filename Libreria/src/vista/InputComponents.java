package vista;

import java.awt.Color;

import javax.swing.*;

import controlador.Events;

public class InputComponents {
	
	private JTextField isbnInput;
	private JTextField numberInput;
	
	public InputComponents() {
		isbnInput = new JTextField();
		isbnInput.addKeyListener(Events.getRestrictedIsbnText(isbnInput));
		numberInput = new JTextField();
		numberInput.addKeyListener(Events.getOnlyNumberEvent(numberInput));
	}
	
	public String getIsbnText() {
		if(isbnInput.getText().length() > 0) {
			String s = isbnInput.getText();
			isbnInput.setText("");
			isbnInput.setBackground(Color.WHITE);
			return s;			
		}
		return null;
	}
	
	public int getNumber() {
		if(numberInput.getText().length() > 0) {
			int number = Integer.valueOf(numberInput.getText());
			numberInput.setText("");
			return number;
		}
		return 0;
	}
	
	public JTextField getIsbnInput() {
		return isbnInput;
	}
	
	public JTextField getNumberInput() {
		return numberInput;
	}
	

}
