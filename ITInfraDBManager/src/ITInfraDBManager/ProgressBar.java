package ITInfraDBManager;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Toolkit;

public class ProgressBar {

	JFrame frame;
	private final JLabel lblPleaseWaitWhile = new JLabel("Please Wait while the ITInfraDB Manager Loads");
	private final JProgressBar progressBar = new JProgressBar();
	/**
	 * Create the application.
	 */
	public ProgressBar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mike.neel\\Pictures\\DBIcon.jpg"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 400, 77);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		GridBagConstraints gbc_lblPleaseWaitWhile = new GridBagConstraints();
		gbc_lblPleaseWaitWhile.insets = new Insets(0, 0, 5, 0);
		gbc_lblPleaseWaitWhile.gridx = 0;
		gbc_lblPleaseWaitWhile.gridy = 0;
		frame.getContentPane().add(lblPleaseWaitWhile, gbc_lblPleaseWaitWhile);
		
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 1;
		progressBar.setForeground(new Color(0, 255, 0));
		progressBar.setIndeterminate(true);
		frame.getContentPane().add(progressBar, gbc_progressBar);
	}


}
