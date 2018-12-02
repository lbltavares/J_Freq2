package com.jfreq.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public final class Janela extends JFrame {
	private static final long serialVersionUID = 1L;

	private static Janela INSTANCE;

	public static Janela getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Janela();
		return INSTANCE;
	}

	private Janela() {
		super("jfreq");
		initLookAndFeel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		initComponents();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		PainelTabela.getInstance().updateTable(new String[][] {});
	}

	private void initLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equalsIgnoreCase(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					return;
				}
			}
			// Se nao encontrar o LnF Nimbus, usar o nativo do SO:
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignore) {
		}
	}

	private void initComponents() {
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.add("Frequencias", PainelPrincipal.getInstance());
		tabPane.add("Configuracoes", PainelConfiguracoes.getInstance());
		setContentPane(tabPane);
	}
}
