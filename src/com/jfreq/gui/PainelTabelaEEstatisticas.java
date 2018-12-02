package com.jfreq.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PainelTabelaEEstatisticas extends JPanel {
	private static final long serialVersionUID = 1L;

	private static PainelTabelaEEstatisticas INSTANCE;

	public static PainelTabelaEEstatisticas getInstance() {
		if (INSTANCE == null)
			INSTANCE = new PainelTabelaEEstatisticas();
		return INSTANCE;
	}

	private PainelTabelaEEstatisticas() {
		super(new BorderLayout());
		initComponents();
	}

	private void initComponents() {
		JTabbedPane mainPanel = new JTabbedPane();
		
		mainPanel.addTab("Tabela", PainelTabela.getInstance());
		mainPanel.addTab("Estatisticas", PainelEstatisticas.getInstance());

		add(mainPanel, BorderLayout.CENTER);
	}
}
