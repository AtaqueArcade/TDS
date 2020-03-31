package vista;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class ToolBarView extends JPanel{
	public ToolBarView() throws MalformedURLException {
		setLayout(new BorderLayout(0, 0));
		
		JPanel leftPanel = new JPanel();
		add(leftPanel, BorderLayout.WEST);
		
		JPanel rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);
		
		JPanel midPanel = new JPanel();
		add(midPanel, BorderLayout.CENTER);

		ImageIcon imageIcon = new ImageIcon(new URL("https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png"));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);

		
		JButton btnProfile = new JButton();
		btnProfile.setIcon(imageIcon);
		leftPanel.add(btnProfile);
		

		ImageIcon imageMenu = new ImageIcon(new URL("https://image.flaticon.com/icons/png/128/482/482620.png"));
		image = imageMenu.getImage();
		newimg = image.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH);
		imageMenu = new ImageIcon(newimg);
		JButton btnMenu = new JButton();
		btnMenu.setIcon(imageMenu);
		midPanel.add(btnMenu);
		
		ImageIcon imageContact = new ImageIcon(new URL("https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png"));
		image = imageContact.getImage();
		newimg = image.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH);
		imageContact = new ImageIcon(newimg);
		JButton btnContact = new JButton("Judah");
		btnContact.setIcon(imageContact);
		midPanel.add(btnContact);

		ImageIcon imageGlass = new ImageIcon(new URL("https://www.vippng.com/png/full/493-4938154_computer-icons-magnifier-magnifying-glass-magnifier-icon-png.png"));
		image = imageGlass.getImage();
		newimg = image.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH);
		imageGlass = new ImageIcon(newimg);
		JButton btnGlass = new JButton();
		btnGlass.setIcon(imageGlass);
		rightPanel.add(btnGlass);
		
		
		ImageIcon imageHam = new ImageIcon(new URL("https://www.festivalclaca.cat/imgfv/b/4-43869_hamburger-menu-icon-png-menu-icon-png.png"));
		image = imageHam.getImage();
		newimg = image.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH); 
		imageHam = new ImageIcon(newimg);
		JButton btnHam = new JButton();
		btnHam.setIcon(imageHam);
		rightPanel.add(btnHam);
	}

}
