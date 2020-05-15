package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.Timer;
import modelo.Mensaje;
import modelo.RefreshRate;
import controlador.Controlador;
import tds.BubbleText;
import java.awt.Component;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class ChatView extends JPanel {
	private JTextField inputTextField;
	private JPanel chat;
	private LinkedList<Mensaje> messages;
	private Popup poEmoji;
	private JPanel emojiJPanel;
	private PopupFactory pf;
	private JButton btnEmoji;

	public ChatView() {
		setBackground(Color.DARK_GRAY);
		pf = new PopupFactory();
		chat = new JPanel();
		chat.setBackground(SystemColor.controlDkShadow);
		messages = new LinkedList<Mensaje>();
		setLayout(new BorderLayout(0, 0));
		chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(chat);
		JPanel contactsPanel = new JPanel();
		contactsPanel.setBackground(Color.DARK_GRAY);
		add(contactsPanel, BorderLayout.WEST);
		JPanel toolbarPanel = new JPanel();
		toolbarPanel.setBackground(Color.DARK_GRAY);
		add(toolbarPanel, BorderLayout.NORTH);

		JPanel tray = new JPanel();
		tray.setBackground(Color.DARK_GRAY);
		add(tray, BorderLayout.SOUTH);
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.DARK_GRAY);
		tray.add(inputPanel);

		btnEmoji = new JButton("Emoji");
		emojiJPanel = emojiView();
		btnEmoji.addActionListener(e -> {
			try {
				if (Controlador.getInstance().isContactSelected()) {
					btnEmoji.setEnabled(false);
					poEmoji = pf.getPopup(this, emojiJPanel, (int) btnEmoji.getLocationOnScreen().getX() - 35,
							(int) btnEmoji.getLocationOnScreen().getY() - 210);
					poEmoji.show();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		inputPanel.add(btnEmoji);

		inputTextField = new JTextField();
		inputPanel.add(inputTextField);
		inputTextField.setColumns(35);

		JPanel sendPanel = new JPanel();
		sendPanel.setBackground(Color.DARK_GRAY);
		tray.add(sendPanel);

		JButton btnSend = new JButton("Send");
		sendPanel.add(btnSend);
		btnSend.addActionListener(e -> {
			try {
				if (!inputTextField.getText().equals("") && Controlador.getInstance().isContactSelected()) {
					try {
						Controlador.getInstance().addMessageToCurrent(inputTextField.getText(), 0);
						inputTextField.setText("");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		inputTextField.addActionListener(e -> {
			btnSend.doClick();
		});
		add(scrollPane, BorderLayout.CENTER);

		Component horizontalStrut = Box.createHorizontalStrut(5);
		add(horizontalStrut, BorderLayout.EAST);

		Timer timer = new Timer(RefreshRate.RATE, new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				setChat();
				repaint();
			}
		});
		timer.start();
	}

	private JPanel emojiView() {
		// The emoji panel will adapt to the number of emojis added
		JPanel panel = new JPanel();
		JPanel testPane = new JPanel();
		testPane.setLayout(new GridLayout((int) Math.sqrt(BubbleText.MAXICONO), (int) Math.sqrt(BubbleText.MAXICONO)));
		int currentemoji = 0;
		for (int row = 0; row < BubbleText.MAXICONO / 2; row++) {
			if (currentemoji < BubbleText.MAXICONO) {
				JButton x = new JButton();
				x.setIcon(BubbleText.getEmoji(currentemoji));
				int n = currentemoji;
				x.addActionListener(e -> {
					try {
						Controlador.getInstance().addMessageToCurrent(null, n);
						poEmoji.hide();
						btnEmoji.setEnabled(true);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				});
				testPane.add(x);
				currentemoji++;
			}
			for (int col = 0; col < BubbleText.MAXICONO / 2; col++) {
				if (currentemoji < BubbleText.MAXICONO) {
					JButton y = new JButton();
					y.setIcon(BubbleText.getEmoji(currentemoji));
					int n = currentemoji;
					y.addActionListener(e -> {
						try {
							Controlador.getInstance().addMessageToCurrent(null, n);
							poEmoji.hide();
							btnEmoji.setEnabled(true);
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					});
					testPane.add(y);
					currentemoji++;
				}
			}
		}
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(testPane);
		JPanel panelb = new JPanel(new BorderLayout());
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> {
			poEmoji.hide();
			btnEmoji.setEnabled(true);
		});
		panelb.add(btnCancel, BorderLayout.CENTER);
		panel.add(panelb);
		return panel;
	}

	public void setChat() {
		try {
			if (Controlador.getInstance().isContactSelected()) {
				if (!messages.equals(Controlador.getInstance().getCurrentMessages())) {
					chat.removeAll();
					messages.clear();
					messages.addAll(Controlador.getInstance().getCurrentMessages());
					for (Mensaje m : messages) {
						BubbleText bubble;
						int side = 1;
						if (m.getSpeaker().equals(Controlador.getInstance().getCurrentUserName()))
							side = 0;
						if (m.getEmoticon() == 0)
							bubble = new BubbleText(chat, m.getText(), Color.WHITE, m.getSpeaker(), side);
						else
							bubble = new BubbleText(chat, m.getEmoticon(), Color.LIGHT_GRAY, m.getSpeaker(), side, 18);
						chat.add(bubble);
					}
					revalidate();
					repaint();
				}
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}