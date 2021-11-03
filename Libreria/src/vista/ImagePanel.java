package vista;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private Image image;
	
	
	@Override
	public void paint(java.awt.Graphics g) {
		try {
			image = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/libr.png"))).getImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image,getWidth()-image.getWidth(null)/2,getHeight()/4,getWidth()/6,getHeight()/2,this);
		setOpaque(false);
		super.paint(g);
	}
}
