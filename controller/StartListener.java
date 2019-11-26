package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Socket;

import util.ReferenceFinder;
import view.MainFrame;

public class StartListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) e.getSource());

		if(!frame.isThreadStarted()) {
			Runnable r = new MyRunnable(e);
			Thread thread = new Thread(r);
			frame.setThreadStarted(true);
			frame.setThread(thread);
			thread.start();
		}
		
	}
	
}
