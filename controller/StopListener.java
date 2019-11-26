package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.ReferenceFinder;
import view.MainFrame;

public class StopListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());

		if(frame.isThreadStarted()) {
			frame.getThread().stop();
			frame.setThreadStarted(false);
		}
		
	}

}
