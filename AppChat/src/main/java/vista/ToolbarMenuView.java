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

public class ToolbarMenuView extends JPanel {
	public ToolbarMenuView() throws MalformedURLException {
		setPreferredSize(new Dimension(150, 180));
		setBorder(new EmptyBorder(50, 50, 50, 50));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);

		Component verticalStrut = Box.createVerticalStrut(5);
		add(verticalStrut);

		JButton btnContactSettings = new JButton("Contact Settings");
		btnContactSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnContactSettings.setPreferredSize(new Dimension(40, 40));
		add(btnContactSettings);

		Component verticalStrut_1 = Box.createVerticalStrut(5);
		add(verticalStrut_1);

		JButton btnGroupSettings = new JButton("Group Settings");
		btnGroupSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnGroupSettings.setPreferredSize(new Dimension(40, 40));
		add(btnGroupSettings);

		Component verticalStrut_2 = Box.createVerticalStrut(5);
		add(verticalStrut_2);

		JButton btnUserStatistics = new JButton("User Statistics");
		btnUserStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnUserStatistics.setPreferredSize(new Dimension(40, 40));
		add(btnUserStatistics);

		Component verticalStrut_3 = Box.createVerticalStrut(5);
		add(verticalStrut_3);

		JButton btnPremium = new JButton("Upgrade to Premium");
		btnPremium.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPremium.setPreferredSize(new Dimension(40, 40));
		add(btnPremium);

		Component verticalStrut_4 = Box.createVerticalStrut(5);
		add(verticalStrut_4);

		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogOut.setPreferredSize(new Dimension(40, 40));
		add(btnLogOut);
		
		Component verticalStrut_5 = Box.createVerticalStrut(5);
		add(verticalStrut_5); 
	}
}
