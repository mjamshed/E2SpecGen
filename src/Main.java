import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Main {

	private JFrame frame;
	private JTextField txtPath;
	private String sourceFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Source");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtPath = new JTextField();
		txtPath.setBounds(10, 10, 414, 21);
		frame.getContentPane().add(txtPath);
		txtPath.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(10, 41, 87, 23);
		frame.getContentPane().add(btnBrowse);

		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();

				// For Directory
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				// For File
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				fileChooser.setAcceptAllFileFilterUsed(false);

				int rVal = fileChooser.showOpenDialog(null);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					txtPath.setText(fileChooser.getSelectedFile().toString());
					sourceFile = fileChooser.getSelectedFile().toString();
					frame.hide();
					XMLTreeView();
				}

			}
		});
	}

	private void XMLTreeView() {
		JFrame frame = new JFrame("XMLTreeView: [ Xml File ]");
		frame.setSize(400, 400);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});

		new XMLTreeView(frame, sourceFile);
	}
}