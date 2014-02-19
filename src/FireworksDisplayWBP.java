import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;


public class FireworksDisplayWBP {

	private JFrame MainDisplayFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FireworksDisplayWBP window = new FireworksDisplayWBP();
					window.MainDisplayFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FireworksDisplayWBP() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainDisplayFrame = new JFrame();
		MainDisplayFrame.setTitle("Fireworks Display");
		MainDisplayFrame.setResizable(false);
		MainDisplayFrame.setBounds(100, 100, 600, 450);
		MainDisplayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainDisplayFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		MainDisplayFrame.getContentPane().add(displayPanel, BorderLayout.CENTER);
		displayPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel toolPanel = new JPanel();
		MainDisplayFrame.getContentPane().add(toolPanel, BorderLayout.EAST);
		GridBagLayout gbl_toolPanel = new GridBagLayout();
		gbl_toolPanel.columnWidths = new int[]{105, 105, 0};
		gbl_toolPanel.rowHeights = new int[]{45, 45, 45, 45, 45, 45, 0, 0, 0, 0, 0, 0};
		gbl_toolPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_toolPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		toolPanel.setLayout(gbl_toolPanel);
		
		JLabel lblCurrentFireworkType = new JLabel("Current Firework: ");
		GridBagConstraints gbc_lblCurrentFireworkType = new GridBagConstraints();
		gbc_lblCurrentFireworkType.gridwidth = 2;
		gbc_lblCurrentFireworkType.fill = GridBagConstraints.VERTICAL;
		gbc_lblCurrentFireworkType.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentFireworkType.gridx = 0;
		gbc_lblCurrentFireworkType.gridy = 0;
		toolPanel.add(lblCurrentFireworkType, gbc_lblCurrentFireworkType);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Test");
			}
		});
		
		JButton btnConfigure = new JButton("Configure");
		btnConfigure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JLabel lblFireworksRemaining = new JLabel("Fireworks Remaining: ");
		GridBagConstraints gbc_lblFireworksRemaining = new GridBagConstraints();
		gbc_lblFireworksRemaining.gridwidth = 2;
		gbc_lblFireworksRemaining.fill = GridBagConstraints.VERTICAL;
		gbc_lblFireworksRemaining.insets = new Insets(0, 0, 5, 0);
		gbc_lblFireworksRemaining.gridx = 0;
		gbc_lblFireworksRemaining.gridy = 1;
		toolPanel.add(lblFireworksRemaining, gbc_lblFireworksRemaining);
		
		JLabel lblCurrentTime = new JLabel("Current Time:");
		GridBagConstraints gbc_lblCurrentTime = new GridBagConstraints();
		gbc_lblCurrentTime.gridwidth = 2;
		gbc_lblCurrentTime.fill = GridBagConstraints.VERTICAL;
		gbc_lblCurrentTime.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentTime.gridx = 0;
		gbc_lblCurrentTime.gridy = 2;
		toolPanel.add(lblCurrentTime, gbc_lblCurrentTime);
		GridBagConstraints gbc_btnConfigure = new GridBagConstraints();
		gbc_btnConfigure.gridheight = 2;
		gbc_btnConfigure.gridwidth = 2;
		gbc_btnConfigure.fill = GridBagConstraints.VERTICAL;
		gbc_btnConfigure.insets = new Insets(0, 0, 5, 0);
		gbc_btnConfigure.gridx = 0;
		gbc_btnConfigure.gridy = 7;
		toolPanel.add(btnConfigure, gbc_btnConfigure);
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.gridheight = 2;
		gbc_btnStart.fill = GridBagConstraints.BOTH;
		gbc_btnStart.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 9;
		toolPanel.add(btnStart, gbc_btnStart);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnQuit = new GridBagConstraints();
		gbc_btnQuit.gridheight = 2;
		gbc_btnQuit.fill = GridBagConstraints.BOTH;
		gbc_btnQuit.gridx = 1;
		gbc_btnQuit.gridy = 9;
		toolPanel.add(btnQuit, gbc_btnQuit);
	}
}
