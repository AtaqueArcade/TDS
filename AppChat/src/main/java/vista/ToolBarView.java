package vista;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarView extends JPanel {
	private Popup poProfileSettings, poToolbarMenu, poCurrentContact, poSearch, poDelete;
	private JPanel profileSettingsJPanel, toolbarMenuJPanel, currentContactJPanel, searchJPanel, deleteJPanel;
	JButton btnProfile, btnMenu, btnContact, btnGlass, btnDelete;
	private PopupFactory pf = new PopupFactory();

	public ToolBarView() throws MalformedURLException {
		ImageIcon imageIcon = new ImageIcon(new URL(
				"https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png"));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		ImageIcon imageContact = new ImageIcon(new URL(
				"https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png"));
		image = imageContact.getImage();
		newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageContact = new ImageIcon(newimg);
		ImageIcon imageMenu = new ImageIcon(new URL("https://image.flaticon.com/icons/png/128/482/482620.png"));
		image = imageMenu.getImage();
		newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageMenu = new ImageIcon(newimg);
		ImageIcon imageGlass = new ImageIcon(new URL(
				"https://www.vippng.com/png/full/493-4938154_computer-icons-magnifier-magnifying-glass-magnifier-icon-png.png"));
		image = imageGlass.getImage();
		newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageGlass = new ImageIcon(newimg);
		ImageIcon imageHam = new ImageIcon(
				new URL("https://www.festivalclaca.cat/imgfv/b/4-43869_hamburger-menu-icon-png-menu-icon-png.png"));
		image = imageHam.getImage();
		newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageHam = new ImageIcon(newimg);

		btnProfile = new JButton();
		btnMenu = new JButton();
		btnContact = new JButton("Judah");
		btnGlass = new JButton();
		btnDelete = new JButton();

		profileSettingsJPanel = profileSettingsView(imageIcon);
		currentContactJPanel = currentContactView(imageIcon, "");
		toolbarMenuJPanel = toolbarMenuView();
		searchJPanel = searchView();
		deleteJPanel = deleteView();

		setLayout(new BorderLayout(0, 0));

		JPanel leftPanel = new JPanel();
		add(leftPanel, BorderLayout.WEST);

		JPanel rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);

		JPanel midPanel = new JPanel();
		add(midPanel, BorderLayout.CENTER);

		btnProfile.setIcon(imageIcon);
		leftPanel.add(btnProfile);
		btnProfile.addActionListener(e -> {
			btnProfile.setEnabled(false);
			poProfileSettings = pf.getPopup(this, profileSettingsJPanel,
					(int) btnProfile.getLocationOnScreen().getX() + 20,
					(int) btnProfile.getLocationOnScreen().getY() + 20);
			poProfileSettings.show();
		});

		btnMenu.setIcon(imageMenu);
		midPanel.add(btnMenu);
		btnMenu.addActionListener(e -> {
			btnMenu.setEnabled(false);
			poToolbarMenu = pf.getPopup(this, toolbarMenuJPanel, (int) btnMenu.getLocationOnScreen().getX() + 20,
					(int) btnMenu.getLocationOnScreen().getY() + 20);
			poToolbarMenu.show();
		});

		btnContact.setIcon(imageContact);
		btnContact.addActionListener(e -> {
			btnContact.setEnabled(false);
			poCurrentContact = pf.getPopup(this, currentContactJPanel,
					(int) btnContact.getLocationOnScreen().getX() + 20,
					(int) btnContact.getLocationOnScreen().getY() + 20);
			poCurrentContact.show();
		});
		midPanel.add(btnContact);

		btnGlass.setIcon(imageGlass);
		btnGlass.addActionListener(e -> {
			btnGlass.setEnabled(false);
			poSearch = pf.getPopup(this, searchJPanel, (int) btnGlass.getLocationOnScreen().getX() - 80,
					(int) btnGlass.getLocationOnScreen().getY() + 20);
			poSearch.show();
		});
		rightPanel.add(btnGlass);

		btnDelete.setIcon(imageHam);
		btnProfile.setIcon(imageIcon);
		leftPanel.add(btnProfile);
		btnDelete.addActionListener(e -> {
			btnDelete.setEnabled(false);
			poDelete = pf.getPopup(this, deleteJPanel, (int) btnDelete.getLocationOnScreen().getX() - 100,
					(int) btnDelete.getLocationOnScreen().getY() + 20);
			poDelete.show();
		});
		rightPanel.add(btnDelete);
	}

	private JPanel profileSettingsView(ImageIcon imageIcon) {
		JPanel profileSettingsView = new JPanel();
		profileSettingsView.setPreferredSize(new Dimension(200, 280));
		profileSettingsView.setBorder(new EmptyBorder(50, 50, 50, 50));
		profileSettingsView.setLayout(new BoxLayout(profileSettingsView, BoxLayout.Y_AXIS));

		Component verticalStrut = Box.createVerticalStrut(10);
		profileSettingsView.add(verticalStrut);

		JLabel lblUserName = new JLabel(Controlador.getInstance().getCurrentUser());
		lblUserName.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 14));
		profileSettingsView.add(lblUserName);

		JPanel panel_2 = new JPanel();
		profileSettingsView.add(panel_2);

		JLabel lblCurrentPicture = new JLabel();
		lblCurrentPicture.setIcon(imageIcon);
		panel_2.add(lblCurrentPicture);

		JButton btnChangeYourPicture = new JButton("Change your picture");
		btnChangeYourPicture.setPreferredSize(new Dimension(140, 30));
		btnChangeYourPicture.setMinimumSize(new Dimension(140, 30));
		btnChangeYourPicture.setMaximumSize(new Dimension(140, 30));
		panel_2.add(btnChangeYourPicture);

		Border blackline = BorderFactory.createLineBorder(Color.black);
		profileSettingsView.setBorder(blackline);

		Component verticalStrut_3 = Box.createVerticalStrut(10);
		profileSettingsView.add(verticalStrut_3);

		MultiLineLabel label = new MultiLineLabel("Frase del usuario", Component.CENTER_ALIGNMENT);
		profileSettingsView.add(label);

		Component verticalStrut_2 = Box.createVerticalStrut(10);
		profileSettingsView.add(verticalStrut_2);

		JButton btnQuote = new JButton("Change quote");
		btnQuote.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnQuote.setPreferredSize(new Dimension(140, 30));
		btnQuote.setMinimumSize(new Dimension(140, 30));
		btnQuote.setMaximumSize(new Dimension(140, 30));
		profileSettingsView.add(btnQuote);

		Component verticalStrut_1 = Box.createVerticalStrut(40);
		profileSettingsView.add(verticalStrut_1);
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(e -> {
			poProfileSettings.hide();
			btnProfile.setEnabled(true);
		});
		btnClose.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnClose.setPreferredSize(new Dimension(140, 30));
		btnClose.setMinimumSize(new Dimension(140, 30));
		btnClose.setMaximumSize(new Dimension(140, 30));
		profileSettingsView.add(btnClose);
		Component verticalStrut1 = Box.createVerticalStrut(10);
		profileSettingsView.add(verticalStrut1);
		return (profileSettingsView);
	}

	private JPanel toolbarMenuView() {
		ContactSettingsView contactSettingsView = new ContactSettingsView();
		GroupSettingsView groupSettingsView = new GroupSettingsView();
		JPanel toolbarMenuView = new JPanel();
		toolbarMenuView.setPreferredSize(new Dimension(200, 280));
		toolbarMenuView.setBorder(new EmptyBorder(50, 50, 50, 50));
		toolbarMenuView.setLayout(new BoxLayout(toolbarMenuView, BoxLayout.Y_AXIS));
		Border blackline = BorderFactory.createLineBorder(Color.black);
		toolbarMenuView.setBorder(blackline);
		Component verticalStrut = Box.createVerticalStrut(10);
		toolbarMenuView.add(verticalStrut);
		JButton btnContactSettings = new JButton("Contact Settings");
		btnContactSettings.addActionListener(ev -> {
			contactSettingsView.show();
		});
		btnContactSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnContactSettings.setPreferredSize(new Dimension(140, 30));
		btnContactSettings.setMinimumSize(new Dimension(140, 30));
		btnContactSettings.setMaximumSize(new Dimension(140, 30));
		toolbarMenuView.add(btnContactSettings);
		Component verticalStrut_1 = Box.createVerticalStrut(5);
		toolbarMenuView.add(verticalStrut_1);
		JButton btnGroupSettings = new JButton("Group Settings");
		btnGroupSettings.addActionListener(ev -> {
			groupSettingsView.show();
		});
		btnGroupSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnGroupSettings.setPreferredSize(new Dimension(140, 30));
		btnGroupSettings.setMinimumSize(new Dimension(140, 30));
		btnGroupSettings.setMaximumSize(new Dimension(140, 30));
		toolbarMenuView.add(btnGroupSettings);
		Component verticalStrut_2 = Box.createVerticalStrut(5);
		toolbarMenuView.add(verticalStrut_2);
		JButton btnUserStatistics = new JButton("User Statistics");
		btnUserStatistics.addActionListener(ev -> {
			statisticsView();
		});
		btnUserStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnUserStatistics.setPreferredSize(new Dimension(140, 30));
		btnUserStatistics.setMinimumSize(new Dimension(140, 30));
		btnUserStatistics.setMaximumSize(new Dimension(140, 30));
		toolbarMenuView.add(btnUserStatistics);
		Component verticalStrut_3 = Box.createVerticalStrut(5);
		toolbarMenuView.add(verticalStrut_3);
		JButton btnPremium = new JButton("Upgrade to Premium");
		btnPremium.addActionListener(ev -> {
			premiumView();
		});
		btnPremium.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPremium.setPreferredSize(new Dimension(140, 30));
		btnPremium.setMinimumSize(new Dimension(140, 30));
		btnPremium.setMaximumSize(new Dimension(140, 30));
		toolbarMenuView.add(btnPremium);
		Component verticalStrut_4 = Box.createVerticalStrut(5);
		toolbarMenuView.add(verticalStrut_4);
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(ev -> {
			// Controlador.logOut();
			Ventana v = new Ventana();
		});
		btnLogOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogOut.setPreferredSize(new Dimension(140, 30));
		btnLogOut.setMinimumSize(new Dimension(140, 30));
		btnLogOut.setMaximumSize(new Dimension(140, 30));
		toolbarMenuView.add(btnLogOut);
		Component verticalStrut_5 = Box.createVerticalStrut(60);
		toolbarMenuView.add(verticalStrut_5);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(e -> {
			poToolbarMenu.hide();
			btnMenu.setEnabled(true);
		});
		btnClose.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnClose.setPreferredSize(new Dimension(140, 30));
		btnClose.setMinimumSize(new Dimension(140, 30));
		btnClose.setMaximumSize(new Dimension(140, 30));
		toolbarMenuView.add(btnClose);
		Component verticalStrut2 = Box.createVerticalStrut(10);
		toolbarMenuView.add(verticalStrut2);
		return (toolbarMenuView);
	}

	private JPanel currentContactView(ImageIcon imageIcon, String phone) {
		JPanel currentContactView = new JPanel();
		currentContactView.setPreferredSize(new Dimension(200, 280));
		currentContactView.setBorder(new EmptyBorder(50, 50, 50, 50));
		currentContactView.setLayout(new BoxLayout(currentContactView, BoxLayout.Y_AXIS));
		Component verticalStrut = Box.createVerticalStrut(10);
		currentContactView.add(verticalStrut);

		// load contact info
		JLabel lblUserName = new JLabel(Controlador.getInstance().getCurrentUser());
		lblUserName.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 14));
		currentContactView.add(lblUserName);

		JPanel panel_2 = new JPanel();
		currentContactView.add(panel_2);
		JLabel lblCurrentPicture = new JLabel();
		lblCurrentPicture.setIcon(imageIcon);
		panel_2.add(lblCurrentPicture);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		currentContactView.setBorder(blackline);
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		currentContactView.add(verticalStrut_3);
		MultiLineLabel label = new MultiLineLabel("Teléfono del contacto", Component.CENTER_ALIGNMENT);
		currentContactView.add(label);
		Component verticalStrut_4 = Box.createVerticalStrut(10);
		currentContactView.add(verticalStrut_4);
		JLabel label_1 = new JLabel("00-000000000");
		label_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentContactView.add(label_1);
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		currentContactView.add(verticalStrut_2);
		Component verticalStrut_1 = Box.createVerticalStrut(40);
		currentContactView.add(verticalStrut_1);
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(e -> {
			poCurrentContact.hide();
			btnContact.setEnabled(true);
		});
		btnClose.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnClose.setPreferredSize(new Dimension(140, 30));
		btnClose.setMinimumSize(new Dimension(140, 30));
		btnClose.setMaximumSize(new Dimension(140, 30));
		currentContactView.add(btnClose);
		Component verticalStrut3 = Box.createVerticalStrut(10);
		currentContactView.add(verticalStrut3);
		return (currentContactView);
	}

	private void statisticsView() {
		JFrame frame = new JFrame("AppChat statistics");
		JPanel panel = new JPanel();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void premiumView() {
		JFrame frame = new JFrame("Go premium");
		frame.setPreferredSize(new Dimension(400, 300));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		JButton btnPremium = new JButton("Go Premium");
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(btnPremium);
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel.add(btnBack);
		ImageIcon imageIcon = new ImageIcon("C:/Users/Dani/git/TDS/AppChat/resources/Singtel-Music-8-Mar-2017.jpg");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(380, 220, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		JPanel panel_1 = new JPanel();
		panel_1.add(new JLabel(imageIcon));
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	public JPanel searchView() {
		JPanel searchJPanel = new JPanel();
		searchJPanel.setPreferredSize(new Dimension(200, 50));
		searchJPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		searchJPanel.setLayout(new BoxLayout(searchJPanel, BoxLayout.Y_AXIS));
		Component verticalStrut = Box.createVerticalStrut(8);
		searchJPanel.add(verticalStrut);
		JPanel panel_2 = new JPanel();
		searchJPanel.add(panel_2);
		JTextField textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		JButton btnFilter = new JButton("Search");
		panel_2.add(btnFilter);
		btnFilter.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnFilter.addActionListener(e -> {
			poSearch.hide();
			btnGlass.setEnabled(true);
		});
		Border blackline = BorderFactory.createLineBorder(Color.black);
		searchJPanel.setBorder(blackline);
		return (searchJPanel);
	}

	public JPanel deleteView() {
		JPanel deleteView = new JPanel();
		deleteView.setPreferredSize(new Dimension(160, 130));
		deleteView.setBorder(new EmptyBorder(50, 50, 50, 50));
		deleteView.setLayout(new BoxLayout(deleteView, BoxLayout.Y_AXIS));
		Component verticalStrut = Box.createVerticalStrut(5);
		deleteView.add(verticalStrut);
		JPanel panel_2 = new JPanel();
		deleteView.add(panel_2);
		JButton button = new JButton("Delete contact");
		button.addActionListener(e -> {
			int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete [Contact]?", "Warning",
					JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				//
			}
			poDelete.hide();
			btnDelete.setEnabled(true);
		});
		button.setForeground(Color.RED);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setPreferredSize(new Dimension(140, 30));
		button.setMinimumSize(new Dimension(140, 30));
		button.setMaximumSize(new Dimension(140, 30));
		panel_2.add(button);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		deleteView.setBorder(blackline);
		JButton button_1 = new JButton("Delete messages");
		button_1.addActionListener(e -> {
			int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all message history?",
					"Warning", JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				//
			}
			poDelete.hide();
			btnDelete.setEnabled(true);
		});
		button_1.setForeground(Color.RED);
		button_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		button_1.setPreferredSize(new Dimension(140, 30));
		button_1.setMinimumSize(new Dimension(140, 30));
		button_1.setMaximumSize(new Dimension(140, 30));
		deleteView.add(button_1);
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		deleteView.add(verticalStrut_2);
		JButton btnClose = new JButton("Close");
		btnClose.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnClose.setPreferredSize(new Dimension(140, 30));
		btnClose.setMinimumSize(new Dimension(140, 30));
		btnClose.setMaximumSize(new Dimension(140, 30));
		btnClose.addActionListener(e -> {
			poDelete.hide();
			btnDelete.setEnabled(true);
		});
		deleteView.add(btnClose);
		Component verticalStrut3 = Box.createVerticalStrut(10);
		deleteView.add(verticalStrut3);
		return (deleteView);
	}

	private void updateView() {
	}
}