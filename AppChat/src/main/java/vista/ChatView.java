package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

import modelo.Mensaje;
import controlador.Controlador;
import tds.BubbleText;
import java.awt.Component;

@SuppressWarnings("serial")
public class ChatView extends JPanel {
	private JTextField inputTextField;
	private JPanel chat;

	public ChatView() {
		chat = new JPanel();
		setLayout(new BorderLayout(0, 0));
		chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
		chat.setSize(400, 700);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(chat);
		JPanel contactsPanel = new JPanel();
		add(contactsPanel, BorderLayout.WEST);
		JPanel toolbarPanel = new JPanel();
		add(toolbarPanel, BorderLayout.NORTH);

		JPanel tray = new JPanel();
		add(tray, BorderLayout.SOUTH);
		JPanel inputPanel = new JPanel();
		tray.add(inputPanel);

		JButton btnEmoji = new JButton("Emoji");
		btnEmoji.addActionListener(ev -> {
			abrirSelectorEmojis();
		});
		inputPanel.add(btnEmoji);

		inputTextField = new JTextField();
		inputPanel.add(inputTextField);
		inputTextField.setColumns(35);

		JPanel sendPanel = new JPanel();
		tray.add(sendPanel);

		JButton btnSend = new JButton("Send");
		sendPanel.add(btnSend);
		btnSend.addActionListener(e -> {
			// TODO añadir persistencia de mensajes
			if (inputTextField.getText() != "") {
				Mensaje message = new Mensaje(inputTextField.getText(), 0);
				BubbleText bubble;
				try {
					bubble = new BubbleText(chat, inputTextField.getText(), Color.LIGHT_GRAY,
							Controlador.getInstance().getCurrentUser(), BubbleText.SENT);
					chat.add(bubble);
					inputTextField.setText("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		inputTextField.addActionListener(e -> {
			btnSend.doClick();
		});
		add(scrollPane, BorderLayout.CENTER);

		Component horizontalStrut = Box.createHorizontalStrut(5);
		add(horizontalStrut, BorderLayout.EAST);
	}

	private void abrirSelectorEmojis() {
		JFrame frame = new JFrame("Select an emoji to send.");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel testPane = new JPanel();
		testPane.setLayout(new GridLayout((int) Math.sqrt(BubbleText.MAXICONO), (int) Math.sqrt(BubbleText.MAXICONO)));
		int currentemoji = 0;
		for (int row = 0; row < BubbleText.MAXICONO / 2; row++) {
			if (currentemoji < BubbleText.MAXICONO) {
				JButton x = new JButton();
				x.setIcon(BubbleText.getEmoji(currentemoji));
				int n = currentemoji;
				x.addActionListener(e -> {
					Mensaje message = new Mensaje(null, n);
					BubbleText bubble;
					try {
						bubble = new BubbleText(chat, n, Color.LIGHT_GRAY, Controlador.getInstance().getCurrentUser(),
								BubbleText.SENT, 18);
						chat.add(bubble);
						frame.dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
						Mensaje message = new Mensaje(null, n);
						System.out.println(n);
						BubbleText bubble;
						try {
							bubble = new BubbleText(chat, n, Color.LIGHT_GRAY,
									Controlador.getInstance().getCurrentUser(), BubbleText.SENT, 18);
							chat.add(bubble);
							frame.dispose();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					});
					testPane.add(y);
					currentemoji++;
				}
			}
		}
		frame.getContentPane().add(testPane);
		{
			JPanel panel = new JPanel();
			frame.getContentPane().add(panel, BorderLayout.NORTH);
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(e -> {
					frame.dispose();
				});
				panel.add(btnCancel);
			}
		}
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}