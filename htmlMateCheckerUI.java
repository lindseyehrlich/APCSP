package htmlMateChecker;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.Font;

public class htmlMateCheckerUI implements ActionListener {

	private JFrame frmHtmlMateChecker;
	private JButton button;
	private JTextArea userInput;
	private JTextArea output;	
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					htmlMateCheckerUI window = new htmlMateCheckerUI();
					window.frmHtmlMateChecker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public htmlMateCheckerUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHtmlMateChecker = new JFrame();
		frmHtmlMateChecker.setTitle("HTML Mate Checker");
		frmHtmlMateChecker.setBounds(100, 100, 1043, 616);
		frmHtmlMateChecker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHtmlMateChecker.getContentPane().setLayout(null);
		
		button = new JButton();
		button.setText("Check Document");
		button.setBounds(435,303,168,46);
		frmHtmlMateChecker.getContentPane().add(button);
		button.addActionListener(this);
		
		userInput = new JTextArea();
		userInput.setFont(new Font("Monospaced", Font.PLAIN, 18));
		userInput.setBounds(144,126,746,149);
		frmHtmlMateChecker.getContentPane().add(userInput);
		
		output = new JTextArea();
		output.setFont(new Font("Monospaced", Font.PLAIN, 18));
		output.setBounds(144,375,746,166);
		output.setEditable(false);
		frmHtmlMateChecker.getContentPane().add(output);
		
		JLabel lblInstructions = new JLabel("Please enter a valid HTML document to make sure all tags have corresponding mates");
		lblInstructions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInstructions.setBounds(245, 57, 532, 46);
		frmHtmlMateChecker.getContentPane().add(lblInstructions);
		
		JLabel lblNewLabel = new JLabel("HTML Mate Checker");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(333, 28, 331, 30);
		frmHtmlMateChecker.getContentPane().add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String input = userInput.getText();
		htmlMateChecker to = new htmlMateChecker();
		String result = to.execute(input);
		output.setText(result);
	}
}