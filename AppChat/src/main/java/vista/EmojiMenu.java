package vista;

import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.lang.Math;
import tds.BubbleText;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmojiMenu {

	private int emoji = -1;
	public static void main(String[] args) {
		EmojiMenu e = new EmojiMenu();
	}
	public EmojiMenu() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			
			public void run(){
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}

				JFrame frame = new JFrame("Select an emoji to attach it to your message.");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JPanel testPane = new JPanel();
				testPane.setLayout(
						new GridLayout((int) Math.sqrt(BubbleText.MAXICONO), (int) Math.sqrt(BubbleText.MAXICONO)));
				int currentemoji = 0;
				for (int row = 0; row < BubbleText.MAXICONO / 2; row++) {
					if (currentemoji < BubbleText.MAXICONO) {
						JButton x = new JButton();
						x.setIcon(BubbleText.getEmoji(currentemoji));
						int n = currentemoji;
						x.addActionListener(e -> {
							emoji = n;
							System.out.println(emoji);
							frame.dispose();
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
								emoji = n;
								System.out.println(emoji);
								frame.dispose();
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
							emoji = -1;
							frame.dispose();
						});
						panel.add(btnCancel);
					}
				}
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

}