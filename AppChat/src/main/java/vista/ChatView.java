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

import TDS.AppChat.AppChat_Constants;
import modelo.Mensaje;
import controlador.Controlador;
import tds.BubbleText;
import java.awt.Component;
import java.awt.Font;
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
		Font font = new Font("Open Sans", Font.PLAIN, 12);
		setBackground(Color.DARK_GRAY);
		pf = new PopupFactory();
		chat = new JPanel();
		chat.setBackground(SystemColor.activeCaption);
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
		btnEmoji.setFont(font);
		btnEmoji.setContentAreaFilled(false);
		btnEmoji.setOpaque(true);
		btnEmoji.setBackground(SystemColor.textHighlight);
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
		btnSend.setFont(font);
		btnSend.setContentAreaFilled(false);
		btnSend.setOpaque(true);
		btnSend.setBackground(SystemColor.textHighlight);
		sendPanel.add(btnSend);
		btnSend.addActionListener(e -> {
			try {
				if (!inputTextField.getText().equals("") && Controlador.getInstance().isContactSelected()) {
					try {
						Controlador.getInstance().addMessage(inputTextField.getText(), -1, null);
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

		Timer timer = new Timer(AppChat_Constants.RATE, new ActionListener() {
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
				x.setBackground(Color.DARK_GRAY);
				x.setContentAreaFilled(false);
				x.setOpaque(true);
				int n = currentemoji;
				x.addActionListener(e -> {
					try {
						Controlador.getInstance().addMessage(null, n, null);
						poEmoji.hide();
						btnEmoji.setEnabled(true);
					} catch (Exception e2) {
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
					y.setBackground(Color.DARK_GRAY);
					y.setContentAreaFilled(false);
					y.setOpaque(true);
					int n = currentemoji;
					y.addActionListener(e -> {
						try {
							Controlador.getInstance().addMessage(null, n, null);
							poEmoji.hide();
							btnEmoji.setEnabled(true);
						} catch (Exception e2) {
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
		Font font = new Font("Open Sans", Font.PLAIN, 12);
		btnCancel.setFont(font);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setOpaque(true);
		btnCancel.setBackground(SystemColor.textHighlight);
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
					if (Controlador.getInstance().getCurrentMessages() != null)
						messages.addAll(Controlador.getInstance().getCurrentMessages());
					for (Mensaje m : messages) {
						BubbleText bubble;
						int side = 1;
						Color color = Color.LIGHT_GRAY;
						if (Controlador.getInstance().isCurrentUser(m.getSpeaker())) {
							side = 0;
							color = SystemColor.controlHighlight;
						}
						if (m.getEmoticon() == -1)
							bubble = new BubbleText(chat, m.getText(), color,
									Controlador.getInstance().getName(m.getSpeaker()), side);
						else
							bubble = new BubbleText(chat, m.getEmoticon(), color,
									Controlador.getInstance().getName(m.getSpeaker()), side, 18);
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