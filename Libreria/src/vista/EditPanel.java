package vista;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;

import controlador.Events;

import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import modelo.*;

public class EditPanel extends JPanel {
	private JTextField txtTitulo;
	private JTextField txtAutor;
	private JTextField txtEditorial;
	private JTextField txtPrecio;
	private ButtonGroup bgEstado = new ButtonGroup();
	private ButtonGroup bgFormato = new ButtonGroup();
	private JPanel panelEstado;
	private JPanel panelFormato;

	/**
	 * Create the panel.
	 */
	public EditPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 97, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Titulo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		txtTitulo = new JTextField();
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.insets = new Insets(0, 20, 5, 20);
		gbc_txtTitulo.gridx = 1;
		gbc_txtTitulo.gridy = 0;
		add(txtTitulo, gbc_txtTitulo);
		txtTitulo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Autor");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		txtAutor = new JTextField();
		GridBagConstraints gbc_txtAutor = new GridBagConstraints();
		gbc_txtAutor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAutor.insets = new Insets(0, 20, 5, 20);
		gbc_txtAutor.gridx = 1;
		gbc_txtAutor.gridy = 1;
		add(txtAutor, gbc_txtAutor);
		txtAutor.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Editorial");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		txtEditorial = new JTextField();
		GridBagConstraints gbc_txtEditorial = new GridBagConstraints();
		gbc_txtEditorial.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEditorial.insets = new Insets(0, 20, 5, 20);
		gbc_txtEditorial.gridx = 1;
		gbc_txtEditorial.gridy = 2;
		add(txtEditorial, gbc_txtEditorial);
		txtEditorial.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Precio");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		add(lblNewLabel_3, gbc_lblNewLabel_3);

		txtPrecio = new JTextField();
		GridBagConstraints gbc_txtPrecio = new GridBagConstraints();
		gbc_txtPrecio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrecio.insets = new Insets(0, 20, 5, 20);
		gbc_txtPrecio.gridx = 1;
		gbc_txtPrecio.gridy = 3;
		add(txtPrecio, gbc_txtPrecio);
		txtPrecio.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Formato");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 4;
		add(lblNewLabel_5, gbc_lblNewLabel_5);

		panelFormato = new JPanel();
		GridBagConstraints gbc_panelFormato = new GridBagConstraints();
		gbc_panelFormato.insets = new Insets(0, 0, 5, 0);
		gbc_panelFormato.gridx = 1;
		gbc_panelFormato.gridy = 4;
		add(panelFormato, gbc_panelFormato);
		panelFormato.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));

		JRadioButton rdbtnCartone = new JRadioButton("Cartone");
		panelFormato.add(rdbtnCartone);

		JRadioButton rdbtnRustica = new JRadioButton("Rustica");
		panelFormato.add(rdbtnRustica);

		JRadioButton rdbtnGrapada = new JRadioButton("Grapada");
		panelFormato.add(rdbtnGrapada);

		JRadioButton rdbtnEspiral = new JRadioButton("Espiral");
		panelFormato.add(rdbtnEspiral);

		JLabel lblNewLabel_6 = new JLabel("Estado");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 5;
		add(lblNewLabel_6, gbc_lblNewLabel_6);

		panelEstado = new JPanel();
		GridBagConstraints gbc_panelEstado = new GridBagConstraints();
		gbc_panelEstado.gridx = 1;
		gbc_panelEstado.gridy = 5;
		add(panelEstado, gbc_panelEstado);
		panelEstado.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));

		JRadioButton rdbtnGrapada_1 = new JRadioButton("Reedicion");
		panelEstado.add(rdbtnGrapada_1);

		JRadioButton rdbtnEspiral_1 = new JRadioButton("Novedad");
		panelEstado.add(rdbtnEspiral_1);
		addRadiosToGroup(panelEstado, bgEstado);
		addRadiosToGroup(panelFormato, bgFormato);
		getTxtAutor().addKeyListener(Events.getRestrictedTextEvent());
		getTxtEditorial().addKeyListener(Events.getRestrictedTextEvent());
		getTxtPrecio().addKeyListener(Events.getRestrictedPriceEvent(getTxtPrecio()));
	}

	private void addRadiosToGroup(JPanel panel, ButtonGroup group) {
		for (Component component : panel.getComponents()) {
			group.add((JRadioButton) component);
		}
	}

	public void clearFields() {
		txtAutor.setText("");
		txtPrecio.setText("");
		txtTitulo.setText("");
		txtEditorial.setText("");
	}

	public JTextField getTxtTitulo() {
		return txtTitulo;
	}

	public JTextField getTxtAutor() {
		return txtAutor;
	}

	public JTextField getTxtEditorial() {
		return txtEditorial;
	}

	public JTextField getTxtPrecio() {
		return txtPrecio;
	}

	public ButtonGroup getBgEstado() {
		return bgEstado;
	}

	public ButtonGroup getBgFormato() {
		return bgFormato;
	}

	public JPanel getPanelEstado() {
		return panelEstado;
	}

	public JPanel getPanelFormato() {
		return panelFormato;
	}

	public boolean checkIfNull() {
		return txtPrecio.getText().equals("") || txtAutor.getText().equals("") || txtEditorial.getText().equals("")
				|| txtTitulo.getText().equals("") || Utils.getSelectedRadio(bgFormato).equals("")
				|| Utils.getSelectedRadio(bgEstado).equals("");
	}

	public void fillFields(Book libro) {
		txtAutor.setText(libro.getAuthor());
		txtEditorial.setText(libro.getEditorial());
		txtPrecio.setText(String.valueOf(libro.getPrice()));
		txtTitulo.setText(libro.getTitle());
		Utils.setSelectedRadio(bgFormato, libro.getFormat());
		Utils.setSelectedRadio(bgEstado, libro.getState());
	}


}
