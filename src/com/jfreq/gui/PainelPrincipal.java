package com.jfreq.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public final class PainelPrincipal extends JPanel {
	private static final long serialVersionUID = 1L;

	private static PainelPrincipal INSTANCE;

	public static PainelPrincipal getInstance() {
		if (INSTANCE == null)
			INSTANCE = new PainelPrincipal();
		return INSTANCE;
	}

	private PainelPrincipal() {
		super(new BorderLayout());
		initComponents();
	}

	private void initComponents() {
		add(PainelSelecao.getInstance(), BorderLayout.NORTH);
		add(PainelTabelaEEstatisticas.getInstance(), BorderLayout.CENTER);
	}

}
