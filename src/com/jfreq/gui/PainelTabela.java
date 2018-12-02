package com.jfreq.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PainelTabela extends JPanel {
	private static final long serialVersionUID = 1L;

	private static PainelTabela INSTANCE;

	public static PainelTabela getInstance() {
		if (INSTANCE == null)
			INSTANCE = new PainelTabela();
		return INSTANCE;
	}

	private JTable table;

	private PainelTabela() {
		super(new BorderLayout());
		initComponents();
	}

	private void initComponents() {
		table = new JTable(new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int x, int y) {
				return false;
			}
		});
		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(500, 250));
		add(jsp, BorderLayout.CENTER);
	}

	public void updateTable(Object[][] data) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		dtm.setDataVector(data, new String[] { //
				"Palavra", //
				"Frequencia", //
		});
	}
}
