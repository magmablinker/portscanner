package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ressource.FrameConstants;
import util.HostCheck;
import util.ReferenceFinder;
import view.MainFrame;

public class MyRunnable implements Runnable {

	private ActionEvent e;
	private boolean exit = false;

	public MyRunnable(ActionEvent e) {
		this.e = e;
	}

	@Override
	public void run() {
		MainFrame frame = (MainFrame) ReferenceFinder.findFrame((Component) this.e.getSource());
		JTable table = frame.getTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		frame.clearTable();

		String ip = "";
		int minPort = 0;
		int maxPort = 0;

		boolean isValid = false;

		while (!exit) {

			try {
				ip = frame.getIp().getText();
				minPort = Integer.parseInt(frame.getMinPort().getText());
				maxPort = Integer.parseInt(frame.getMaxPort().getText());
				isValid = true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, FrameConstants.TEXT_INVALID);
			}

			if(isValid) {
				String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
				if (!ip.matches(PATTERN)) {
					JOptionPane.showMessageDialog(frame, FrameConstants.TEXT_INVALID_IP);
					isValid = false;
				}

				if (minPort < 1) {
					JOptionPane.showMessageDialog(frame, FrameConstants.TEXT_INVALID_MIN_PORT);
					isValid = false;
				}

				if (maxPort > 65535) {
					JOptionPane.showMessageDialog(frame, FrameConstants.TEXT_INVALID_MAX_PORT);
					isValid = false;
				}
			}
				
			if (isValid) {
				try {
					if(InetAddress.getByName(ip).isReachable(500)) {
						HostCheck hc = new HostCheck(ip);

						for (int i = minPort; i <= maxPort; i++) {
							model.addRow(new Object[] { ip, i, hc.checkOpen(i), new Timestamp(new Date().getTime()) });
						}	
					} else {
						JOptionPane.showMessageDialog(frame, FrameConstants.TEXT_HOST_UNREACHABLE);
					}
				} catch (UnknownHostException e1) {
					JOptionPane.showMessageDialog(frame, FrameConstants.TEXT_HOST_UNREACHABLE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, FrameConstants.TEXT_HOST_UNREACHABLE);
				}
			}

			exit = true;
		}

		frame.setThreadStarted(false);
	}

}
