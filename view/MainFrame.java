package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controller.StartListener;
import controller.StopListener;
import ressource.FrameConstants;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private JTextField ip;
	private JTextField minPort;
	private JTextField maxPort;
	private Thread thread;
	private boolean isThreadStarted = false;

	public MainFrame() {
		super(FrameConstants.FRAME_TITLE);
		this.add(createContent());
	}

	private Component createContent() {
		JPanel main = new JPanel(new BorderLayout(5, 5));

		main.add(new JPanel(), BorderLayout.NORTH);
		main.add(new JPanel(), BorderLayout.WEST);
		main.add(new JPanel(), BorderLayout.EAST);
		main.add(createContentMiddle(), BorderLayout.CENTER);
		main.add(new JPanel(), BorderLayout.SOUTH);

		return main;
	}

	private Component createContentMiddle() {
		JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
		JPanel centerPanelInner = new JPanel(new BorderLayout(5, 5));

		final DefaultTableModel model = new DefaultTableModel(FrameConstants.TABLE_HEADER, 0 );
		
		this.table = new JTable(model);
		
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model); 
		
		// Since that shit is bugged out ayy
		sorter.setComparator(1, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 == o2)
					return o1.compareTo(o2);
				else if(o1 < o2) 
					return 1;
				return -1;
			}
			
		});
		
		table.setRowSorter(sorter);
		table.setEnabled(false);
		
		List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		
		table.getRowSorter().toggleSortOrder(1);
		
		centerPanelInner.add(new JScrollPane(this.table), BorderLayout.CENTER);
		
		centerPanel.add(createContentTop(), BorderLayout.NORTH);
		centerPanel.add(centerPanelInner, BorderLayout.CENTER);
		centerPanel.add(createContentBottom(), BorderLayout.SOUTH);
		
		return centerPanel;
	}

	private Component createContentTop() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 5, 5));
		
		this.ip = new JTextField();
		this.ip.setText("Target IP");		
		this.ip.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if(ip.getText().equals("Target IP")) {
					ip.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(ip.getText().isEmpty()) {
					ip.setText("Target IP");
				}
			}
			
		});
		
		this.minPort = new JTextField();
		this.minPort.setText("Min Port");
		this.minPort.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if(minPort.getText().equals("Min Port")) {
					minPort.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(minPort.getText().isEmpty()) {
					minPort.setText("Min Port");
				}
			}
		});
		
		this.maxPort = new JTextField();
		this.maxPort.setText("Max Port");
		this.maxPort.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if(maxPort.getText().equals("Max Port")) {
					maxPort.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(maxPort.getText().isEmpty()) {
					maxPort.setText("Max Port");
				}
			}
			
		});
		
		panel.add(this.ip);
		panel.add(this.minPort);
		panel.add(this.maxPort);
		
		return panel;
	}
	
	private Component createContentBottom() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 5, 5));
		
		JButton bStart = new JButton(FrameConstants.BTN_START);
		bStart.addActionListener(new StartListener());
		
		JButton bStop = new JButton(FrameConstants.BTN_STOP);
		bStop.addActionListener(new StopListener());
		
		JButton bClear = new JButton(FrameConstants.BTN_CLEAR);
		bClear.addActionListener(new ClearListener());
		
		panel.add(bStart);
		panel.add(bStop);
		panel.add(bClear);
		
		return panel;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTextField getIp() {
		return ip;
	}

	public void setIp(JTextField ip) {
		this.ip = ip;
	}

	public JTextField getMinPort() {
		return minPort;
	}

	public void setMinPort(JTextField minPort) {
		this.minPort = minPort;
	}

	public JTextField getMaxPort() {
		return maxPort;
	}

	public void setMaxPort(JTextField maxPort) {
		this.maxPort = maxPort;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean isThreadStarted() {
		return isThreadStarted;
	}

	public void setThreadStarted(boolean isThreadStarted) {
		this.isThreadStarted = isThreadStarted;
	}

	public void clearTable() {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		
		while(model.getRowCount() > 0)
			model.removeRow(0);
	}


}