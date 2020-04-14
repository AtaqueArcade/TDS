package vista;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProfileSettingsView extends JPanel {
	public ProfileSettingsView() throws MalformedURLException {
		setPreferredSize(new Dimension(200, 250));
		setBorder(new EmptyBorder(50, 50, 50, 50));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(5);
		add(verticalStrut);

		JLabel lblNombredelusuario = new JLabel(Controlador.getInstance().getCurrentUser());
		lblNombredelusuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNombredelusuario);

		JPanel panel_2 = new JPanel();
		add(panel_2);

		JLabel lblCurrentPicture = new JLabel("Profile picture");
		ImageIcon imageIcon = new ImageIcon(new URL(
				"https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png"));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		lblCurrentPicture.setIcon(imageIcon);
		panel_2.add(lblCurrentPicture);

		JButton btnChangeYourPicture = new JButton("Change your picture");
		panel_2.add(btnChangeYourPicture);

		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
		
		MultiLineLabel label = new MultiLineLabel("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", Component.CENTER_ALIGNMENT);
		add(label);
		
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		add(verticalStrut_2);
		
		JButton button = new JButton("Change quote");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(button);
		
		Component verticalStrut_1 = Box.createVerticalStrut(60);
		add(verticalStrut_1);

	}
}
