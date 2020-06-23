package vista;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import TDS.AppChat.AppChat_Constants;
import controlador.Controlador;
import modelo.ContactoIndividual;
import modelo.D1;
import modelo.D2;
import modelo.Descuento;
import modelo.Mensaje;
import tds.BubbleText;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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
	private PopupFactory pf;

	public ToolBarView()
			throws MalformedURLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		setBackground(Color.DARK_GRAY);
		setLayout(new BorderLayout(0, 0));
		Font font = new Font("Open Sans", Font.PLAIN, 20);
		// Images and icons
		ImageIcon imageIcon;
		String picture = Controlador.getInstance().getCurrentUserPicture();
		try {
			if (picture != null) {
				imageIcon = new ImageIcon(new URL(picture));
			} else {
				imageIcon = new ImageIcon(new URL("https://i.imgur.com/pE7yZPl.png"));
			}
		} catch (MalformedURLException e) {
			imageIcon = new ImageIcon(new URL(
					"https://cdn2.iconfinder.com/data/icons/ecommerce-tiny-line/64/profile_ecommerce_shop-512.png"));
		}
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		lblCurrentPicture = new JLabel();
		lblCurrentPicture.setIcon(imageIcon);
		BufferedImage bi = new BufferedImage(imageIcon.getIconWidth() + 0, imageIcon.getIconHeight() + 0,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		g.setColor(SystemColor.textHighlight);
		g.drawImage(imageIcon.getImage(), 1, 1, null);
		BasicStroke stroke = new BasicStroke(5);
		g.setStroke(stroke);
		g.drawRect(0, 0, bi.getWidth() - 1, bi.getHeight() - 1);
		imageIcon = new ImageIcon(bi);

		imageContact = new ImageIcon(new URL("https://i.imgur.com/NPRQMwe.png"));
		image = imageContact.getImage();
		newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		imageContact = new ImageIcon(newimg);
		lblContactPicture = new JLabel();
		lblContactPicture.setIcon(imageContact);
		bi = new BufferedImage(imageContact.getIconWidth() + 0, imageContact.getIconHeight() + 0,
				BufferedImage.TYPE_INT_ARGB);
		g = bi.createGraphics();
		g.setColor(SystemColor.textHighlight);
		g.drawImage(imageContact.getImage(), 1, 1, null);
		g.setStroke(stroke);
		g.drawRect(0, 0, bi.getWidth() - 1, bi.getHeight() - 1);
		imageContact = new ImageIcon(bi);

		ImageIcon imageMenu = new ImageIcon(new URL("https://i.imgur.com/NU99BzZ.png"));
		image = imageMenu.getImage();
		newimg = image.getScaledInstance(10, 40, java.awt.Image.SCALE_SMOOTH);
		imageMenu = new ImageIcon(newimg);
		ImageIcon imageGlass = new ImageIcon(new URL("https://i.imgur.com/1dbyOYn.png"));
		image = imageGlass.getImage();
		newimg = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		imageGlass = new ImageIcon(newimg);
		ImageIcon imageHam = new ImageIcon(new URL("https://i.imgur.com/xembiVA.png"));
		image = imageHam.getImage();
		newimg = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		imageHam = new ImageIcon(newimg);

		g.dispose();
		// Element initialization
		btnProfile = new JButton();
		btnProfile.setBackground(Color.DARK_GRAY);
		btnProfile.setContentAreaFilled(false);
		btnProfile.setOpaque(true);
		btnMenu = new JButton();
		btnMenu.setBackground(Color.DARK_GRAY);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setOpaque(true);
		btnContact = new JButton("No chat selected");
		btnContact.setFont(font);
		btnContact.setForeground(Color.WHITE);
		btnContact.setBackground(Color.DARK_GRAY);
		btnContact.setContentAreaFilled(false);
		btnContact.setOpaque(true);
		btnGlass = new JButton();
		btnGlass.setBackground(Color.DARK_GRAY);
		btnGlass.setContentAreaFilled(false);
		btnGlass.setOpaque(true);
		btnDelete = new JButton();
		btnDelete.setBackground(Color.DARK_GRAY);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setOpaque(true);

		lblUserName = new JLabel("No one selected");
		labelPhone = new MultiLineLabel();
		labelPhone.setText("[No contact selected]");
		labelPhone.setAlignmentX(Component.CENTER_ALIGNMENT);

		pf = new PopupFactory();

		profileSettingsJPanel = profileSettingsView(imageIcon);
		toolbarMenuJPanel = toolbarMenuView();
		searchJPanel = searchView();
		deleteJPanel = deleteView();
		urlJPanel = urlView();
		quoteJPanel = quoteView();

		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.DARK_GRAY);
		add(leftPanel, BorderLayout.WEST);
		JPanel midPanel = new JPanel();
		midPanel.setBackground(Color.DARK_GRAY);
		add(midPanel, BorderLayout.CENTER);
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.DARK_GRAY);
		add(rightPanel, BorderLayout.EAST);
		btnProfile.setIcon(imageIcon);
		btnProfile.addActionListener(e -> {
			btnProfile.setEnabled(false);
			poProfileSettings = pf.getPopup(this, profileSettingsJPanel,
					(int) btnProfile.getLocationOnScreen().getX() + 20,
					(int) btnProfile.getLocationOnScreen().getY() + 20);
			poProfileSettings.show();
		});
		btnMenu.setIcon(imageMenu);
		btnMenu.addActionListener(e -> {
			btnMenu.setEnabled(false);
			poToolbarMenu = pf.getPopup(this, toolbarMenuJPanel, (int) btnMenu.getLocationOnScreen().getX() + 12,
					(int) btnMenu.getLocationOnScreen().getY() + 8);
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
		btnContact.setText("No chat selected");

		btnGlass.setIcon(imageGlass);
		btnGlass.addActionListener(e -> {
			btnGlass.setEnabled(false);
			poSearch = pf.getPopup(this, searchJPanel, (int) btnGlass.getLocationOnScreen().getX() - 80,
					(int) btnGlass.getLocationOnScreen().getY() + 20);
			poSearch.show();
		});

		btnDelete.setIcon(imageHam);
		btnProfile.setIcon(imageIcon);
		btnDelete.addActionListener(e -> {
			btnDelete.setEnabled(false);
			poDelete = pf.getPopup(this, deleteJPanel, (int) btnDelete.getLocationOnScreen().getX() - 100,
					(int) btnDelete.getLocationOnScreen().getY() + 20);
			poDelete.show();
		});

		leftPanel.add(btnProfile);
		Component strut = Box.createHorizontalStrut(100);
		leftPanel.add(strut);
		leftPanel.add(btnMenu);
		leftPanel.add(btnContact);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
		rightPanel.add(btnGlass);
		rightPanel.add(btnDelete);

		// UI updater
		Timer timer = new Timer(AppChat_Constants.RATE, new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				String contactName = null;
				try {
					contactName = Controlador.getInstance().getContactName(0);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				if (contactName != null && !btnContact.getText().equals(contactName)) {
					btnContact.setText(contactName);
					lblUserName.setText(contactName);
					String pictureContacto = null;
					try {
						pictureContacto = Controlador.getInstance().getContactPicture(0);
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e2) {
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
							imageContact = new ImageIcon(new URL("https://i.imgur.com/NPRQMwe.png"));
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					Image image = imageContact.getImage();
					Image newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
					imageContact = new ImageIcon(newimg);

					newimg = image.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
					imageContact = new ImageIcon(newimg);
					lblContactPicture.setIcon(imageContact);
					BufferedImage bi = new BufferedImage(imageContact.getIconWidth() + 0,
							imageContact.getIconHeight() + 0, BufferedImage.TYPE_INT_ARGB);
					Graphics2D g = bi.createGraphics();
					g.setColor(SystemColor.textHighlight);
					g.drawImage(imageContact.getImage(), 1, 1, null);
					g.setStroke(stroke);
					g.drawRect(0, 0, bi.getWidth() - 1, bi.getHeight() - 1);
					imageContact = new ImageIcon(bi);

					btnContact.setIcon(imageContact);

					btnContact.repaint();
					lblContactPicture.repaint();
					try {
						labelPhone.setText(Controlador.getInstance().getContactPhone(0));
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
		profileSettingsView.setBackground(SystemColor.controlDkShadow);
		profileSettingsView.setPreferredSize(new Dimension(200, 280));
		profileSettingsView.setBorder(new EmptyBorder(50, 50, 50, 50));
		profileSettingsView.setLayout(new BoxLayout(profileSettingsView, BoxLayout.Y_AXIS));

		Component verticalStrut = Box.createVerticalStrut(10);
		profileSettingsView.add(verticalStrut);

		JLabel lblUserName = new JLabel(Controlador.getInstance().getCurrentUserName());
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setAlignmentX(Component.CENTER_ALIGNMENT);
		Font font = new Font("Open Sans", Font.PLAIN, 20);
		lblUserName.setFont(font);
		lblUserName.setBackground(SystemColor.controlDkShadow);
		profileSettingsView.add(lblUserName);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.controlDkShadow);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		profileSettingsView.add(panel_2);
		lblCurrentPicture.setAlignmentX(CENTER_ALIGNMENT);
		panel_2.add(lblCurrentPicture);

		Component verticalStrut_X = Box.createVerticalStrut(5);
		panel_2.add(verticalStrut_X);

		Font fontQuote = new Font("Open Sans", Font.PLAIN, 16);
		quoteLabel = new MultiLineLabel(Controlador.getInstance().getCurrentUserQuote(), Component.CENTER_ALIGNMENT);
		quoteLabel.setFont(fontQuote);
		quoteLabel.setForeground(Color.WHITE);
		quoteLabel.setAlignmentX(CENTER_ALIGNMENT);
		quoteLabel.setBackground(SystemColor.controlDkShadow);

		panel_2.add(quoteLabel);
		Component verticalStrut_X2 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_X2);
		font = new Font("Open Sans", Font.PLAIN, 12);
		JButton btnChangeYourPicture = new JButton("Change picture");
		btnChangeYourPicture.setFont(font);
		btnChangeYourPicture.setContentAreaFilled(false);
		btnChangeYourPicture.setOpaque(true);
		btnChangeYourPicture.setBackground(SystemColor.textHighlight);
		btnChangeYourPicture.addActionListener(a -> {
			poURL = pf.getPopup(this, urlJPanel, (int) btnChangeYourPicture.getLocationOnScreen().getX() + 20,
					(int) btnChangeYourPicture.getLocationOnScreen().getY() + 20);
			poURL.show();
		});
		btnChangeYourPicture.setPreferredSize(new Dimension(140, 30));
		btnChangeYourPicture.setMinimumSize(new Dimension(140, 30));
		btnChangeYourPicture.setMaximumSize(new Dimension(140, 30));
		btnChangeYourPicture.setAlignmentX(CENTER_ALIGNMENT);
		btnChangeYourPicture.setFont(font);
		panel_2.add(btnChangeYourPicture);

		Border blackline = BorderFactory.createLineBorder(Color.black);
		profileSettingsView.setBorder(blackline);

		Component verticalStrut_2 = Box.createVerticalStrut(10);
		profileSettingsView.add(verticalStrut_2);

		JButton btnQuote = new JButton("Change quote");
		btnQuote.addActionListener(a -> {
			poQuote = pf.getPopup(this, quoteJPanel, (int) btnQuote.getLocationOnScreen().getX() + 20,
					(int) btnQuote.getLocationOnScreen().getY() + 20);
			poQuote.show();
		});
		btnQuote.setFont(font);
		btnQuote.setContentAreaFilled(false);
		btnQuote.setOpaque(true);
		btnQuote.setBackground(SystemColor.textHighlight);
		btnQuote.setAlignmentX(CENTER_ALIGNMENT);
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
		btnClose.setFont(font);
		btnClose.setContentAreaFilled(false);
		btnClose.setOpaque(true);
		btnClose.setBackground(SystemColor.textHighlight);
		btnClose.setPreferredSize(new Dimension(140, 30));
		btnClose.setMinimumSize(new Dimension(140, 30));
		btnClose.setMaximumSize(new Dimension(140, 30));
		btnClose.setAlignmentX(CENTER_ALIGNMENT);
		profileSettingsView.add(btnClose);
		Component verticalStrut1 = Box.createVerticalStrut(10);
		profileSettingsView.add(verticalStrut1);
		return (profileSettingsView);
	}

	private JPanel toolbarMenuView() {
		Font font = new Font("Open Sans", Font.PLAIN, 12);
		ContactSettingsView contactSettingsView = new ContactSettingsView();
		GroupSettingsView groupSettingsView = new GroupSettingsView();
		StatisticsView statisticsView = new StatisticsView();
		JPanel toolbarMenuView = new JPanel();
		toolbarMenuView.setBackground(SystemColor.controlDkShadow);
		toolbarMenuView.setPreferredSize(new Dimension(200, 280));
		toolbarMenuView.setBorder(new EmptyBorder(50, 50, 50, 50));
		toolbarMenuView.setLayout(new BoxLayout(toolbarMenuView, BoxLayout.Y_AXIS));
		Border blackline = BorderFactory.createLineBorder(Color.black);
		toolbarMenuView.setBorder(blackline);
		Component verticalStrut = Box.createVerticalStrut(10);
		toolbarMenuView.add(verticalStrut);
		JButton btnContactSettings = new JButton("Contact Settings");
		btnContactSettings.setFont(font);
		btnContactSettings.setContentAreaFilled(false);
		btnContactSettings.setOpaque(true);
		btnContactSettings.setBackground(SystemColor.textHighlight);
		btnContactSettings.setAlignmentX(CENTER_ALIGNMENT);
		btnContactSettings.setPreferredSize(new Dimension(140, 30));
		btnContactSettings.setMinimumSize(new Dimension(140, 30));
		btnContactSettings.setMaximumSize(new Dimension(140, 30));
		btnContactSettings.addActionListener(ev -> {
			try {
				contactSettingsView.show();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
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
		btnGroupSettings.setFont(font);
		btnGroupSettings.setContentAreaFilled(false);
		btnGroupSettings.setOpaque(true);
		btnGroupSettings.setBackground(SystemColor.textHighlight);
		btnGroupSettings.setAlignmentX(CENTER_ALIGNMENT);
		btnGroupSettings.setPreferredSize(new Dimension(140, 30));
		btnGroupSettings.setMinimumSize(new Dimension(140, 30));
		btnGroupSettings.setMaximumSize(new Dimension(140, 30));
		toolbarMenuView.add(btnGroupSettings);
		Component verticalStrut_2 = Box.createVerticalStrut(5);
		toolbarMenuView.add(verticalStrut_2);
		JButton btnUserStatistics = new JButton("User Statistics");
		btnUserStatistics.addActionListener(ev -> {
			try {
				if (Controlador.getInstance().getCurrentUserPremium())
					statisticsView.show();
				else
					JOptionPane.showMessageDialog(new JFrame(),
							"This functionality is only available for premium users.", "Statistics",
							JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnUserStatistics.setFont(font);
		btnUserStatistics.setContentAreaFilled(false);
		btnUserStatistics.setOpaque(true);
		btnUserStatistics.setBackground(SystemColor.textHighlight);
		btnUserStatistics.setAlignmentX(CENTER_ALIGNMENT);
		btnUserStatistics.setPreferredSize(new Dimension(140, 30));
		btnUserStatistics.setMinimumSize(new Dimension(140, 30));
		btnUserStatistics.setMaximumSize(new Dimension(140, 30));
		toolbarMenuView.add(btnUserStatistics);
		Component verticalStrut_3 = Box.createVerticalStrut(5);
		toolbarMenuView.add(verticalStrut_3);
		JButton btnPremium = new JButton("Get Premium");
		btnPremium.addActionListener(ev -> {
			try {
				premiumView();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		btnPremium.setFont(font);
		btnPremium.setContentAreaFilled(false);
		btnPremium.setOpaque(true);
		btnPremium.setBackground(SystemColor.textHighlight);
		btnPremium.setAlignmentX(CENTER_ALIGNMENT);
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
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnLogOut.setFont(font);
		btnLogOut.setContentAreaFilled(false);
		btnLogOut.setOpaque(true);
		btnLogOut.setBackground(SystemColor.textHighlight);
		btnLogOut.setAlignmentX(CENTER_ALIGNMENT);
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
		btnClose.setFont(font);
		btnClose.setContentAreaFilled(false);
		btnClose.setOpaque(true);
		btnClose.setBackground(SystemColor.textHighlight);
		btnClose.setAlignmentX(CENTER_ALIGNMENT);
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
		currentContactView.setBackground(SystemColor.controlDkShadow);
		currentContactView.setPreferredSize(new Dimension(200, 280));
		currentContactView.setBorder(new EmptyBorder(50, 50, 50, 50));
		currentContactView.setLayout(new BoxLayout(currentContactView, BoxLayout.Y_AXIS));
		Component verticalStrut = Box.createVerticalStrut(10);
		currentContactView.add(verticalStrut);

		lblUserName.setForeground(Color.WHITE);
		lblUserName.setAlignmentX(Component.CENTER_ALIGNMENT);
		Font font = new Font("Open Sans", Font.PLAIN, 20);
		lblUserName.setFont(font);
		lblUserName.setBackground(SystemColor.controlDkShadow);
		currentContactView.add(lblUserName);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.controlDkShadow);
		currentContactView.add(panel_2);

		panel_2.add(lblContactPicture);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		currentContactView.setBorder(blackline);
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		currentContactView.add(verticalStrut_3);
		JLabel label = new JLabel("Contact phone:");
		label.setAlignmentX(CENTER_ALIGNMENT);
		Font fontlabel = new Font("Open Sans", Font.BOLD, 12);
		label.setFont(fontlabel);
		label.setBackground(SystemColor.controlDkShadow);
		label.setForeground(Color.WHITE);
		currentContactView.add(label);
		Component verticalStrut_4 = Box.createVerticalStrut(10);
		currentContactView.add(verticalStrut_4);
		labelPhone.setForeground(Color.WHITE);
		labelPhone.setBackground(SystemColor.controlDkShadow);
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
		Font fontClose = new Font("Open Sans", Font.PLAIN, 12);
		btnClose.setFont(fontClose);
		btnClose.setContentAreaFilled(false);
		btnClose.setOpaque(true);
		btnClose.setBackground(SystemColor.textHighlight);
		btnClose.setPreferredSize(new Dimension(140, 30));
		btnClose.setMinimumSize(new Dimension(140, 30));
		btnClose.setMaximumSize(new Dimension(140, 30));
		btnClose.setAlignmentX(CENTER_ALIGNMENT);
		btnClose.setPreferredSize(new Dimension(140, 30));
		btnClose.setMinimumSize(new Dimension(140, 30));
		btnClose.setMaximumSize(new Dimension(140, 30));
		currentContactView.add(btnClose);
		Component verticalStrut3 = Box.createVerticalStrut(10);
		currentContactView.add(verticalStrut3);
		return (currentContactView);
	}

	private void premiumView()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, MalformedURLException {
		JFrame frame = new JFrame("Go premium");
		Font font = new Font("Open Sans", Font.PLAIN, 12);
		frame.setPreferredSize(new Dimension(400, 400));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		JButton btnPremium = new JButton("Go Premium");
		btnPremium.setFont(font);
		btnPremium.setContentAreaFilled(false);
		btnPremium.setOpaque(true);
		btnPremium.setBackground(SystemColor.textHighlight);
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Controlador.getInstance().setCurrentUserPremium();
					JOptionPane.showMessageDialog(new JFrame(), "Premium account activated", "Premium",
							JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(btnPremium);
		JButton btnBack = new JButton("Back");
		btnBack.setFont(font);
		btnBack.setContentAreaFilled(false);
		btnBack.setOpaque(true);
		btnBack.setBackground(SystemColor.textHighlight);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel.add(btnBack);
		ImageIcon imageIcon;
		Descuento discount = Controlador.getInstance().getDiscount();
		if (discount instanceof D1)
			imageIcon = new ImageIcon(new URL("https://i.imgur.com/S2krqHB.png"));
		else if (discount instanceof D2)
			imageIcon = new ImageIcon(new URL("https://i.imgur.com/qs00RQf.png"));
		else
			imageIcon = new ImageIcon(new URL("https://i.imgur.com/ayCFZQ7.png"));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.add(new JLabel(imageIcon));
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	public JPanel searchView() {
		JPanel searchJPanel = new JPanel();
		searchJPanel.setBackground(SystemColor.controlDkShadow);
		searchJPanel.setPreferredSize(new Dimension(200, 50));
		searchJPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		searchJPanel.setLayout(new BoxLayout(searchJPanel, BoxLayout.Y_AXIS));
		Font font = new Font("Open Sans", Font.PLAIN, 12);
		Component verticalStrut = Box.createVerticalStrut(8);
		searchJPanel.add(verticalStrut);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.controlDkShadow);
		searchJPanel.add(panel_2);
		JTextField textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		if (isFocusOwner())
			textField.requestFocus();
		JButton btnFilter = new JButton("Search");
		btnFilter.setFont(font);
		btnFilter.setContentAreaFilled(false);
		btnFilter.setOpaque(true);
		btnFilter.setBackground(SystemColor.textHighlight);
		panel_2.add(btnFilter);
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
		Font font = new Font("Open Sans", Font.PLAIN, 12);
		deleteView.setBackground(SystemColor.controlDkShadow);
		deleteView.setPreferredSize(new Dimension(160, 130));
		deleteView.setBorder(new EmptyBorder(50, 50, 50, 50));
		deleteView.setLayout(new BoxLayout(deleteView, BoxLayout.Y_AXIS));
		Component verticalStrut = Box.createVerticalStrut(5);
		deleteView.add(verticalStrut);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.controlDkShadow);
		deleteView.add(panel_2);
		JButton button = new JButton("Delete contact");
		button.setFont(font);
		button.setContentAreaFilled(false);
		button.setOpaque(true);
		button.setBackground(Color.RED);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.addActionListener(e -> {
			try {
				if (Controlador.getInstance().isContactSelected()) {
					int response = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to delete " + Controlador.getInstance().getContactName(0) + "?",
							"Warning", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						try {
							if (Controlador.getInstance().deleteContact((ContactoIndividual) null))
								JOptionPane.showMessageDialog(new JFrame(), "Contact deleted succesfully.\n",
										"Delete contacts", JOptionPane.INFORMATION_MESSAGE);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			poDelete.hide();
			btnDelete.setEnabled(true);
		});
		button.setForeground(Color.YELLOW);
		button.setPreferredSize(new Dimension(140, 30));
		button.setMinimumSize(new Dimension(140, 30));
		button.setMaximumSize(new Dimension(140, 30));
		panel_2.add(button);

		Border blackline = BorderFactory.createLineBorder(Color.black);
		deleteView.setBorder(blackline);
		JButton button_1 = new JButton("Delete messages");
		button_1.setFont(font);
		button_1.setContentAreaFilled(false);
		button_1.setOpaque(true);
		button_1.setBackground(Color.RED);
		button_1.setAlignmentX(CENTER_ALIGNMENT);
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
		button_1.setForeground(Color.YELLOW);
		button_1.setPreferredSize(new Dimension(140, 30));
		button_1.setMinimumSize(new Dimension(140, 30));
		button_1.setMaximumSize(new Dimension(140, 30));
		deleteView.add(button_1);
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		deleteView.add(verticalStrut_2);
		JButton btnClose = new JButton("Close");
		btnClose.setFont(font);
		btnClose.setContentAreaFilled(false);
		btnClose.setOpaque(true);
		btnClose.setBackground(SystemColor.textHighlight);
		btnClose.setAlignmentX(CENTER_ALIGNMENT);
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
		urlJPanel.setBackground(SystemColor.controlDkShadow);
		Font font = new Font("Open Sans", Font.PLAIN, 12);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		urlJPanel.setBorder(blackline);
		JTextField textField = new JTextField(Controlador.getInstance().getCurrentUserPicture());
		JButton btnNewButton = new JButton("Change picture");
		btnNewButton.setFont(font);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setOpaque(true);
		btnNewButton.setBackground(SystemColor.textHighlight);
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
		Font font = new Font("Open Sans", Font.PLAIN, 12);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		urlJPanel.setBackground(SystemColor.controlDkShadow);
		urlJPanel.setBorder(blackline);
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setFont(font);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setOpaque(true);
		btnNewButton.setBackground(SystemColor.textHighlight);
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
		if (search.equals(""))
			return;
		try {
			resultsExist = Controlador.getInstance().getCurrentMessages().stream()
					.anyMatch(m -> (m.getText() != null) && m.getText().contains(search));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
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
				for (Mensaje m : Controlador.getInstance().getCurrentMessages()) {
					if (m.getText() != null && m.getText().contains(search)) {
						BubbleText bubble = new BubbleText(chat, m.getText(), Color.LIGHT_GRAY,
								Controlador.getInstance().getName(m.getSpeaker()), BubbleText.SENT);
						chat.add(bubble);
					}
				}
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "No coincidences were found", "Search",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}