package vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;

public class WelcomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public WelcomePanel() {
		this.setBackground(new Color(58, 175, 185));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 70, 376, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 3, true));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 75, 0, 75);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("LIBRERIA DE ALBERTO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		panel.add(lblNewLabel);
		panel.setBackground(new Color(22, 88, 93));
		
		JLabel lblImg = new JLabel("");
		GridBagConstraints gbc_lblImg = new GridBagConstraints();
		gbc_lblImg.gridx = 0;
		gbc_lblImg.gridy = 1;
		add(lblImg, gbc_lblImg);
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource(("logo.gif")));
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				lblImg.setIcon(new ImageIcon(img.getImage().getScaledInstance(getWidth()/2,getHeight()/2, Image.SCALE_DEFAULT)));
				if (getWidth()<650) {
					lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
				}else {
					lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
				}
			}
		});
	}
}
