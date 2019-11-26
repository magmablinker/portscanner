package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
		
		final MainFrame mainFrame = new MainFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.setSize(900, 700);
		mainFrame.setMinimumSize(new Dimension(400, 200));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

}
