package vista;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

public class ProfileSettingsView extends JPanel {
	public ProfileSettingsView() throws MalformedURLException {
		Border blackline = BorderFactory.createLineBorder(Color.black);
		setBorder(blackline);
		setBorder(new EmptyBorder(50, 50, 50, 50));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JPanel panel = new JPanel();
		add(panel, gbc);

		JLabel lblNombredelusuario = new JLabel("Nombre_del_usuario");
		panel.add(lblNombredelusuario);

		JPanel panel_2 = new JPanel() {
			@Override
			public Dimension getMaximumSize() {
				return getPreferredSize();
			}
		};

		add(panel_2, gbc);

		JLabel label = new JLabel("New label");
		ImageIcon imageIcon = new ImageIcon(new URL(
				"https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png"));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		label.setIcon(imageIcon);
		panel_2.add(label);

		JButton button = new JButton("Change picture");
		panel_2.add(button);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, gbc);

		JLabel lblfraseEscogidaPor = new JLabel("\"Frase escogida por el usuaio para mostrar en su perfil\"");
		panel_1.add(lblfraseEscogidaPor);

		JButton button_1 = new JButton("Change quote");
		panel_1.add(button_1);
	}
}
