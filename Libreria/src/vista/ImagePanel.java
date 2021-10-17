package vista;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private Image image;
	
	
	@Override
	public void paint(java.awt.Graphics g) {
		image = new ImageIcon(getClass().getResource("../img/libr.png")).getImage();
		g.drawImage(image,getWidth()-image.getWidth(null)/2,getHeight()/4,getWidth()/6,getHeight()/2,this);
		setOpaque(false);
		super.paint(g);
	}
}
