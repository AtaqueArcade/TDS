package vista;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import TDS.AppChat.App;
import controlador.Controlador;
import modelo.Mensaje;
import modelo.RefreshRate;
import tds.BubbleText;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarView extends JPanel {

	private static final long serialVersionUID = 1L;
	private Popup poProfileSettings, poToolbarMenu, poCurrentContact, poSearch, poDelete, poURL, poQuote;
	private JPanel profileSettingsJPanel, toolbarMenuJPanel, currentContactJPanel, searchJPanel, deleteJPanel,
			urlJPanel, quoteJPanel;
	private JButton btnProfile, btnMenu, btnContact, btnGlass, btnDelete;
	private JLabel lblCurrentPicture, lblUserName, lblContactPicture;
	MultiLineLabel labelPhone;
	private MultiLineLabel quoteLabel;
	private ImageIcon imageContact;
	private PopupFactory pf = new PopupFactory();

	public ToolBarView()
			throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// Images and icons
		ImageIcon imageIcon;
		String picture = Controlador.getInstance().getCurrentUserPicture();
		try {
			if (picture != null) {
				imageIcon = new ImageIcon(new URL(picture));
			} else {
				imageIcon = new ImageIcon(new URL(
						"https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png"));
			}
		} catch (MalformedURLException e) {
			imageIcon = new ImageIcon(new URL(
					"https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png"));
		}

		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);

		imageContact = new ImageIcon(new URL(
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

		// Element initialization
		btnProfile = new JButton();
		btnMenu = new JButton();
		btnContact = new JButton("No chat selected");
		btnGlass = new JButton();
		btnDelete = new JButton();

		lblCurrentPicture = new JLabel();
		lblCurrentPicture.setIcon(imageIcon);
		lblUserName = new JLabel("No one selected");
		lblContactPicture = new JLabel();
		lblContactPicture.setIcon(imageContact);
		labelPhone = new MultiLineLabel();
		labelPhone.setText("[No contact selected]");
		labelPhone.setAlignmentX(Component.CENTER_ALIGNMENT);

		profileSettingsJPanel = profileSettingsView(imageIcon);
		toolbarMenuJPanel = toolbarMenuView();
		searchJPanel = searchView();
		deleteJPanel = deleteView();
		urlJPanel = urlView();
		quoteJPanel = quoteView();

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
			try {
				currentContactJPanel = currentContactView();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			poCurrentContact = pf.getPopup(this, currentContactJPanel,
					(int) btnContact.getLocationOnScreen().getX() + 20,
					(int) btnContact.getLocationOnScreen().getY() + 20);
			poCurrentContact.show();
		});
		midPanel.add(btnContact);
		btnContact.setText("No chat selected");
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

		// UI updater
		Timer timer = new Timer(RefreshRate.RATE, new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				String contactName = null;
				try {
					contactName = Controlador.getInstance().getCurrentContactName();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (contactName != null && !btnContact.getText().equals(contactName)) {
					btnContact.setText(contactName);
					lblUserName.setText(contactName);
					String pictureContacto = null;
					try {
						pictureContacto = Controlador.getInstance().getCurrentContactPicture();
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if (pictureContacto != null) {
						try {
							imageContact = new ImageIcon(new URL(pictureContacto));
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						try {
							imageContact = new ImageIcon(new URL(
									"https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png"));
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					Image image = imageContact.getImage();
					Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
					imageContact = new ImageIcon(newimg);
					btnContact.setIcon(imageContact);
					lblContactPicture.setIcon(imageContact);
					btnContact.repaint();
					lblContactPicture.repaint();
					try {
						labelPhone.setText(Controlador.getInstance().getCurrentContactPhone());
						labelPhone.repaint();
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		timer.start();
	}

	private JPanel profileSettingsView(ImageIcon imageIcon)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		JPanel profileSettingsView = new JPanel();
		profileSettingsView.setPreferredSize(new Dimension(200, 280));
		profileSettingsView.setBorder(new EmptyBorder(50, 50, 50, 50));
		profileSettingsView.setLayout(new BoxLayout(profileSettingsView, BoxLayout.Y_AXIS));

		Component verticalStrut = Box.createVerticalStrut(10);
		profileSettingsView.add(verticalStrut);

		JLabel lblUserName = new JLabel(Controlador.getInstance().getCurrentUserName());
		lblUserName.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 14));
		profileSettingsView.add(lblUserName);

		JPanel panel_2 = new JPanel();
		profileSettingsView.add(panel_2);

		panel_2.add(lblCurrentPicture);

		JButton btnChangeYourPicture = new JButton("Change your picture");
		btnChangeYourPicture.addActionListener(a -> {
			poURL = pf.getPopup(this, urlJPanel, (int) btnChangeYourPicture.getLocationOnScreen().getX() + 20,
					(int) btnChangeYourPicture.getLocationOnScreen().getY() + 20);
			poURL.show();
		});
		btnChangeYourPicture.setPreferredSize(new Dimension(140, 30));
		btnChangeYourPicture.setMinimumSize(new Dimension(140, 30));
		btnChangeYourPicture.setMaximumSize(new Dimension(140, 30));
		panel_2.add(btnChangeYourPicture);

		Border blackline = BorderFactory.createLineBorder(Color.black);
		profileSettingsView.setBorder(blackline);

		Component verticalStrut_3 = Box.createVerticalStrut(10);
		profileSettingsView.add(verticalStrut_3);

		quoteLabel = new MultiLineLabel(Controlador.getInstance().getCurrentUserQuote(), Component.CENTER_ALIGNMENT);
		profileSettingsView.add(quoteLabel);

		Component verticalStrut_2 = Box.createVerticalStrut(10);
		profileSettingsView.add(verticalStrut_2);

		JButton btnQuote = new JButton("Change quote");
		btnQuote.addActionListener(a -> {
			poQuote = pf.getPopup(this, quoteJPanel, (int) btnQuote.getLocationOnScreen().getX() + 20,
					(int) btnQuote.getLocationOnScreen().getY() + 20);
			poQuote.show();
		});
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
			try {
				contactSettingsView.show();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			try {
				groupSettingsView.show();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			try {
				Controlador.getInstance().logOut();
				java.awt.Window win[] = java.awt.Window.getWindows();
				for (int i = 0; i < win.length; i++) {
					win[i].dispose();
				}
				App.main(null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

	private JPanel currentContactView() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		JPanel currentContactView = new JPanel();
		currentContactView.setPreferredSize(new Dimension(200, 280));
		currentContactView.setBorder(new EmptyBorder(50, 50, 50, 50));
		currentContactView.setLayout(new BoxLayout(currentContactView, BoxLayout.Y_AXIS));
		Component verticalStrut = Box.createVerticalStrut(10);
		currentContactView.add(verticalStrut);

		lblUserName.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 14));
		currentContactView.add(lblUserName);

		JPanel panel_2 = new JPanel();
		currentContactView.add(panel_2);

		panel_2.add(lblContactPicture);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		currentContactView.setBorder(blackline);
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		currentContactView.add(verticalStrut_3);
		MultiLineLabel label = new MultiLineLabel("Teléfono del contacto", Component.CENTER_ALIGNMENT);
		currentContactView.add(label);
		Component verticalStrut_4 = Box.createVerticalStrut(10);
		currentContactView.add(verticalStrut_4);
		currentContactView.add(labelPhone);
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
		if (isFocusOwner())
			textField.requestFocus();
		JButton btnFilter = new JButton("Search");
		panel_2.add(btnFilter);
		btnFilter.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnFilter.addActionListener(e -> {
			try {
				if (Controlador.getInstance().isContactSelected())
					SearchResultsView(textField.getText().trim());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			poSearch.hide();
			btnGlass.setEnabled(true);
		});
		textField.addActionListener(e -> {
			btnFilter.doClick();
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
			try {
				if (Controlador.getInstance().isContactSelected()) {
					int response = JOptionPane
							.showConfirmDialog(null,
									"Are you sure you want to delete "
											+ Controlador.getInstance().getCurrentContactName() + "?",
									"Warning", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						try {
							if (Controlador.getInstance().deleteContacts(null))
								JOptionPane.showMessageDialog(new JFrame(),
										"Contact " + Controlador.getInstance().getCurrentContactName()
												+ " deleted succesfully.\n",
										"Delete contacts", JOptionPane.INFORMATION_MESSAGE);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
			try {
				if (Controlador.getInstance().isContactSelected()) {
					int response = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to delete all message history?", "Warning",
							JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						Controlador.getInstance().deleteMessages();
					}
					poDelete.hide();
					btnDelete.setEnabled(true);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

	private JPanel urlView() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		JPanel urlJPanel = new JPanel();
		Border blackline = BorderFactory.createLineBorder(Color.black);
		urlJPanel.setBorder(blackline);
		JTextField textField = new JTextField(Controlador.getInstance().getCurrentUserPicture());
		JButton btnNewButton = new JButton("Change picture");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Controlador.getInstance().setCurrentUserPicture(textField.getText().trim());
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon imageIcon = null;
				try {
					imageIcon = new ImageIcon(new URL(textField.getText().trim()));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Image image = imageIcon.getImage();
				Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(newimg);
				btnProfile.setIcon(imageIcon);
				btnProfile.repaint();
				lblCurrentPicture.setIcon(imageIcon);
				lblCurrentPicture.repaint();
				poURL.hide();
			}
		});
		urlJPanel.add(btnNewButton, BorderLayout.EAST);
		textField.setColumns(30);
		urlJPanel.add(textField, BorderLayout.CENTER);
		return urlJPanel;

	}

	private JPanel quoteView() {
		JPanel urlJPanel = new JPanel();
		Border blackline = BorderFactory.createLineBorder(Color.black);
		urlJPanel.setBorder(blackline);
		JButton btnNewButton = new JButton("Confirm");
		JTextField textField = new JTextField();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				poQuote.hide();
				try {
					Controlador.getInstance().setCurrentUserQuote(textField.getText().trim());
					quoteLabel.setText(textField.getText().trim());
					quoteLabel.paintImmediately(quoteLabel.getVisibleRect());
					quoteLabel.repaint();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		urlJPanel.add(btnNewButton, BorderLayout.EAST);
		textField.setColumns(30);
		urlJPanel.add(textField, BorderLayout.CENTER);
		return urlJPanel;
	}

	private void SearchResultsView(String search) {
		boolean resultsExist = false;
		try {
			resultsExist = Controlador.getInstance().getCurrentMessages().stream()
					.anyMatch(m -> (m.getText() != null) && m.getText().contains(search));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (resultsExist) {
			JFrame frame = new JFrame("Search results");
			frame.setPreferredSize(new Dimension(400, 300));
			frame.setMinimumSize(new Dimension(400, 300));
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JPanel chat = new JPanel();
			chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setViewportView(chat);
			frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			try {
				// TODO hacer en stream
				for (Mensaje m : Controlador.getInstance().getCurrentMessages()) {
					if (m.getText() != null && m.getText().contains(search)) {
						BubbleText bubble = new BubbleText(chat, m.getText(), Color.LIGHT_GRAY, m.getSpeaker(),
								BubbleText.SENT);
						chat.add(bubble);
					}
				}
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "No coincidences were found", "Search",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}