package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import FileZip.Zip;




public class MainWindow extends JFrame {
	
	JPanel panel = new JPanel();
	ArrayList<File> filesList;
        
	public MainWindow(){
		filesList = new ArrayList<File>();
		initUI();
	}
        
	private void initUI(){
		panel = new JPanel();

		//Changes the flow layout to nothing. Allows absolute positioning. 
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final JPanel filePanel = new JPanel();
		filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));
		final JButton addFileButton = new JButton("Add file(s) to the list to be zipped.");
		final JTextField fileBox = new JTextField(24);
		fileBox.setEditable(false);
		filePanel.add(addFileButton);
		filePanel.add(fileBox);

		panel.add(filePanel);

		final JPanel savePanel = new JPanel();
		savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.X_AXIS));
		final JButton saveFileButton = new JButton("Select a location and a name for the zipped file.");
		final JTextField savefileBox = new JTextField(24);
		savefileBox.setEditable(false);
		savePanel.add(saveFileButton);
		savePanel.add(savefileBox);

		panel.add(savePanel);

		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		final JButton createButton = new JButton("Create zip file");
		buttonPanel.add(createButton);
		final JButton clearFilesButton = new JButton("Clear file list");
		buttonPanel.add(clearFilesButton);

		panel.add(buttonPanel);

		/*
		 * Adds a file or files to the list of files we are going to zip.
		 */
		addFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fc = new JFileChooser();
				//Lets the user select multiple files.
				fc.setMultiSelectionEnabled(true);
				int returnVal = fc.showOpenDialog(MainWindow.this);
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File files[] = fc.getSelectedFiles();
					for(int i = 0; i < files.length; i++){
						File file = files[i];
						filesList.add(file);
						String filename = file.getName();
						//Update the files box. 
						String currentText = fileBox.getText();
						if(currentText.length() != 0){
							fileBox.setText(currentText+", "+filename);
						}else{
							fileBox.setText(filename);
						}

					}
				}

			}
		});

		/*
		 * Create a zip file.
		 */
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Zip zip = new Zip (filesList, savefileBox.getText());
				String result = zip.zipFiles();
				JOptionPane.showMessageDialog(null, result);

			}
		});

		/*
		 * Save file location.
		 */
		saveFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(MainWindow.this);
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file  = fc.getSelectedFile();
					savefileBox.setText(file.getAbsolutePath());
				}

			}
		});


		/*
		 * Clears the invertedIndex and the loaded files.
		 */
		clearFilesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//Clears the file box.
				fileBox.setText("");
			}
		});

		add(panel);
		pack();
		setTitle("Zip Master");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}