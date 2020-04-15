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
	private Popup poProfileSettings, poToolbarMenu;
	private JPanel profileSettingsJPanel;
	private JPanel toolbarMenuJPanel;
	private PopupFactory pf = new PopupFactory();

	public ToolBarView() throws MalformedURLException {
		setLayout(new BorderLayout(0, 0));

		JPanel leftPanel = new JPanel();
		add(leftPanel, BorderLayout.WEST);

		JPanel rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);

		JPanel midPanel = new JPanel();
		add(midPanel, BorderLayout.CENTER);

		ImageIcon imageIcon = new ImageIcon(new URL(
				"https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png"));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);

		JButton btnProfile = new JButton();
		btnProfile.setIcon(imageIcon);
		leftPanel.add(btnProfile);
		btnProfile.addActionListener(e -> {
			btnProfile.setEnabled(false);
			poProfileSettings = pf.getPopup(this, profileSettingsJPanel,
					(int) btnProfile.getLocationOnScreen().getX() + 20,
					(int) btnProfile.getLocationOnScreen().getY() + 20);
			poProfileSettings.show();
		});

		ImageIcon imageMenu = new ImageIcon(new URL("https://image.flaticon.com/icons/png/128/482/482620.png"));
		image = imageMenu.getImage();
		newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageMenu = new ImageIcon(newimg);
		JButton btnMenu = new JButton();
		btnMenu.setIcon(imageMenu);
		midPanel.add(btnMenu);
		btnMenu.addActionListener(e -> {
			btnMenu.setEnabled(false);
			poToolbarMenu = pf.getPopup(this, toolbarMenuJPanel, (int) btnMenu.getLocationOnScreen().getX() + 20,
					(int) btnMenu.getLocationOnScreen().getY() + 20);
			poToolbarMenu.show();
		});

		ImageIcon imageContact = new ImageIcon(new URL(
				"https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png"));
		image = imageContact.getImage();
		newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageContact = new ImageIcon(newimg);
		JButton btnContact = new JButton("Judah");
		btnContact.setIcon(imageContact);
		midPanel.add(btnContact);

		ImageIcon imageGlass = new ImageIcon(new URL(
				"https://www.vippng.com/png/full/493-4938154_computer-icons-magnifier-magnifying-glass-magnifier-icon-png.png"));
		image = imageGlass.getImage();
		newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageGlass = new ImageIcon(newimg);
		JButton btnGlass = new JButton();
		btnGlass.setIcon(imageGlass);
		btnGlass.addActionListener(e -> {
		});
		rightPanel.add(btnGlass);

		ImageIcon imageHam = new ImageIcon(
				new URL("https://www.festivalclaca.cat/imgfv/b/4-43869_hamburger-menu-icon-png-menu-icon-png.png"));
		image = imageHam.getImage();
		newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageHam = new ImageIcon(newimg);
		JButton btnHam = new JButton();
		btnHam.setIcon(imageHam);
		rightPanel.add(btnHam);
		//
		profileSettingsJPanel = ProfileSettingsView(imageIcon);
		toolbarMenuJPanel = ToolbarMenuView();

		JButton btnProfileSettingsJPanel = new JButton("Close");
		btnProfileSettingsJPanel.addActionListener(e -> {
			poProfileSettings.hide();
			btnProfile.setEnabled(true);
		});
		btnProfileSettingsJPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnProfileSettingsJPanel.setPreferredSize(new Dimension(140, 30));
		btnProfileSettingsJPanel.setMinimumSize(new Dimension(140, 30));
		btnProfileSettingsJPanel.setMaximumSize(new Dimension(140, 30));
		profileSettingsJPanel.add(btnProfileSettingsJPanel);
		Component verticalStrut1 = Box.createVerticalStrut(10);
		profileSettingsJPanel.add(verticalStrut1);

		JButton btntoolbarMenuJPanel = new JButton("Close");
		btntoolbarMenuJPanel.addActionListener(e -> {
			poToolbarMenu.hide();
			btnMenu.setEnabled(true);
		});
		btntoolbarMenuJPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		btntoolbarMenuJPanel.setPreferredSize(new Dimension(140, 30));
		btntoolbarMenuJPanel.setMinimumSize(new Dimension(140, 30));
		btntoolbarMenuJPanel.setMaximumSize(new Dimension(140, 30));
		toolbarMenuJPanel.add(btntoolbarMenuJPanel);
		Component verticalStrut2 = Box.createVerticalStrut(10);
		toolbarMenuJPanel.add(verticalStrut2);

	}

	private JPanel ProfileSettingsView(ImageIcon imageIcon) {
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
		return (profileSettingsView);
	}

	private JPanel ToolbarMenuView() {
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
			ContactSettingsView();
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
			ContactSettingsView();
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
			StatisticsView();
		});
		btnUserStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnUserStatistics.setPreferredSize(new Dimension(140, 30));
		btnUserStatistics.setMinimumSize(new Dimension(140, 30));
		btnUserStatistics.setMaximumSize(new Dimension(140, 30));
		toolbarMenuView.add(btnUserStatistics);

		Component verticalStrut_3 = Box.createVerticalStrut(5);
		toolbarMenuView.add(verticalStrut_3);

		JButton btnPremium = new JButton("Upgrade to Premium");
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
		return (toolbarMenuView);
	}

	private void ContactSettingsView() {
		JFrame frame = new JFrame("Contact settings");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("New contact", null, panel, "Add new contatcs to your contact list");
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		panel.add(panel_1, BorderLayout.WEST);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_3);
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 20));
		textField.setMinimumSize(new Dimension(100, 20));
		textField.setMaximumSize(new Dimension(100, 20));
		panel_1.add(textField);
		textField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		Component verticalStrut_4 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_4);
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnSearch.setMinimumSize(new Dimension(100, 30));
		btnSearch.setMaximumSize(new Dimension(100, 30));
		btnSearch.setAlignmentX(0.5f);
		panel_1.add(btnSearch);

		Component horizontalStrut_3 = Box.createHorizontalStrut(120);
		panel_1.add(horizontalStrut_3);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		panel.add(panel_2, BorderLayout.EAST);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_1);

		JButton btnAdd = new JButton("Add contacts");
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAdd.setPreferredSize(new Dimension(100, 30));
		btnAdd.setMinimumSize(new Dimension(100, 30));
		btnAdd.setMaximumSize(new Dimension(100, 30));
		panel_2.add(btnAdd);

		Component horizontalStrut_2 = Box.createHorizontalStrut(120);
		panel_2.add(horizontalStrut_2);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		JList list = new JList();
		scrollPane.setViewportView(list);
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Contact manager", null, panel2, "Hide, show or delete contacts on your list");
		panel2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel2.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut_2);
		JButton btnHide = new JButton("Hide");
		panel_3.add(btnHide);
		btnHide.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnHide.setPreferredSize(new Dimension(100, 30));
		btnHide.setMinimumSize(new Dimension(100, 30));
		btnHide.setMaximumSize(new Dimension(100, 30));
		JButton btnShow = new JButton("Show");
		panel_3.add(btnShow);
		btnShow.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShow.setPreferredSize(new Dimension(100, 30));
		btnShow.setMinimumSize(new Dimension(100, 30));
		btnShow.setMaximumSize(new Dimension(100, 30));
		JButton btnDelete = new JButton("Delete");
		panel_3.add(btnDelete);
		btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDelete.setPreferredSize(new Dimension(100, 30));
		btnDelete.setMinimumSize(new Dimension(100, 30));
		btnDelete.setMaximumSize(new Dimension(100, 30));

		Component horizontalStrut_1 = Box.createHorizontalStrut(120);
		panel_3.add(horizontalStrut_1);
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		Component verticalStrut = Box.createVerticalStrut(20);
		panel_4.add(verticalStrut);
		JLabel label = new JLabel("[User info]");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(label);
		panel2.add(panel_4, BorderLayout.EAST);

		JLabel lblmoreUserInfo = new JLabel("[More user info]");
		lblmoreUserInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(lblmoreUserInfo);

		Component horizontalStrut = Box.createHorizontalStrut(120);
		panel_4.add(horizontalStrut);

		Component verticalStrut_long = Box.createVerticalStrut(250);
		panel_4.add(verticalStrut_long);

		JList list_1 = new JList();
		JScrollPane scrollPane_1 = new JScrollPane(list_1);

		panel2.add(scrollPane_1, BorderLayout.CENTER);

		frame.getContentPane().add(tabbedPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void StatisticsView() {
		JFrame frame = new JFrame("AppChat statistics");
		JPanel panel = new JPanel();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}