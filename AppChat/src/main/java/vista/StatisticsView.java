package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;

public class StatisticsView {
	/**
	 * @wbp.parser.entryPoint
	 */
	public void show() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			UnsupportedLookAndFeelException {
		Font font = new Font("Open Sans", Font.PLAIN, 12);
		JFrame frame = new JFrame("Statistics");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.getContentPane().add(panel, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(40);
		panel.add(verticalStrut);

		JLabel lblNewLabel = new JLabel("AppChat scores:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel);

		Component verticalStrut_1 = Box.createVerticalStrut(30);
		panel.add(verticalStrut_1);

		JLabel lblMessagesSent = new JLabel("Messages sent:");
		lblMessagesSent.setForeground(Color.WHITE);
		lblMessagesSent.setAlignmentX(0.5f);
		panel.add(lblMessagesSent);

		Component verticalStrut_5 = Box.createVerticalStrut(5);
		panel.add(verticalStrut_5);

		JLabel label_1 = new JLabel("New label");
		label_1.setForeground(Color.WHITE);
		label_1.setAlignmentX(0.5f);
		panel.add(label_1);

		Component verticalStrut_2 = Box.createVerticalStrut(30);
		panel.add(verticalStrut_2);

		JLabel lblMessagesReceived = new JLabel("Messages received:");
		lblMessagesReceived.setForeground(Color.WHITE);
		lblMessagesReceived.setAlignmentX(0.5f);
		panel.add(lblMessagesReceived);

		Component verticalStrut_4 = Box.createVerticalStrut(5);
		panel.add(verticalStrut_4);

		JLabel label_3 = new JLabel("New label");
		label_3.setForeground(Color.WHITE);
		label_3.setAlignmentX(0.5f);
		panel.add(label_3);

		Component verticalStrut_3 = Box.createVerticalStrut(30);
		panel.add(verticalStrut_3);

		JLabel lblContactsAdded = new JLabel("Contacts added:");
		lblContactsAdded.setForeground(Color.WHITE);
		lblContactsAdded.setAlignmentX(0.5f);
		panel.add(lblContactsAdded);

		Component verticalStrut_6 = Box.createVerticalStrut(5);
		panel.add(verticalStrut_6);

		JLabel label_5 = new JLabel("New label");
		label_5.setForeground(Color.WHITE);
		label_5.setAlignmentX(0.5f);
		panel.add(label_5);

		Component verticalStrut_7 = Box.createVerticalStrut(80);
		panel.add(verticalStrut_7);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnNewButton);

		Component verticalStrut_8 = Box.createVerticalStrut(10);
		panel.add(verticalStrut_8);

		JButton button = new JButton("New button");
		button.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(button);

		Component horizontalStrut = Box.createHorizontalStrut(100);
		panel.add(horizontalStrut);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		panel_1.add(panel_2, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create Chart
		PieChart chart = new PieChartBuilder().width(100).height(150).title(getClass().getSimpleName()).build();
		// Customize Chart
		Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110),
				new Color(243, 180, 159), new Color(246, 199, 182) };
		chart.getStyler().setSeriesColors(sliceColors);
		chart.getStyler().setChartBackgroundColor(Color.DARK_GRAY);
		// Series
		chart.addSeries("Gold", 41);
		chart.addSeries("Silver", 71);
		chart.addSeries("Platinum", 39);
		frame.setVisible(true);
		XChartPanel<PieChart> chartPanel = new XChartPanel<PieChart>(chart);
		panel_2.add(chartPanel);
		XChartPanel<PieChart> chartPanel_1 = new XChartPanel<PieChart>(chart);
		;
		panel_2.add(chartPanel_1);
		XChartPanel<PieChart> chartPanel_2 = new XChartPanel<PieChart>(chart);
		panel_2.add(chartPanel_2);
		XChartPanel<PieChart> chartPanel_3 = new XChartPanel<PieChart>(chart);
		panel_1.add(chartPanel_3, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(750, 500));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
