package util;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

public class ReferenceFinder {
	public static JFrame findFrame(Component item) {
		if (item instanceof JFrame) {
			return (JFrame) item;
		} else if (item instanceof JPopupMenu) {
			JPopupMenu pop = (JPopupMenu) item;
			return findFrame(pop.getInvoker());
		} else {
			Container parent = item.getParent();
			return parent == null ? null : findFrame(parent);
		}
	}
}
