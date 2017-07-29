package Main;

import GUI.MainWindow;

import javax.swing.SwingUtilities;

public class MainProg {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow ex = new MainWindow();
				ex.setVisible(true);
			}
		});

	}
} 