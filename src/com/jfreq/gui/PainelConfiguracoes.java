package com.jfreq.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.jfreq.util.Util;

public final class PainelConfiguracoes extends JPanel {
	private static final long serialVersionUID = 1L;

	private static PainelConfiguracoes INSTANCE;

	public static PainelConfiguracoes getInstance() {
		if (INSTANCE == null)
			INSTANCE = new PainelConfiguracoes();
		return INSTANCE;
	}

	private JPanel mainPanel;
	private GridBagConstraints gc;

	private JCheckBox inverterOrdemCB, desenharArvoreCB, substituirAcentuacaoCB, ignorarComunsCB;
	private JTextField listaComunsTF;
	private JSpinner minimoLetras, maximoLetras, maximoExibicoes;

	private PainelConfiguracoes() {
		super(new BorderLayout());
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBorder(new CompoundBorder(BorderFactory.createEtchedBorder(), new EmptyBorder(10, 10, 20, 10)));
		initComponents();
		restaurarPadroes();
	}

	private void adicionar(Component c, int x, int y) {
		gc.gridx = x;
		gc.gridy = y;
		mainPanel.add(c, gc);
	}

	private JLabel criarDescricao(String texto) {
		JLabel lbl = new JLabel(texto);
		lbl.setOpaque(true);
		lbl.setBackground(new Color(190, 190, 190));
		lbl.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		return lbl;
	}

	private void initComponents() {
		gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(4, 4, 4, 4);
		gc.gridx = gc.gridy = 0;

		adicionar(inverterOrdemCB = new JCheckBox("Inverter Ordem"), 0, 0);
		adicionar(criarDescricao("Inverte a ordenacao da lista (de 'z' para 'a')"), 1, 0);

		adicionar(desenharArvoreCB = new JCheckBox("Desenhar Arvore"), 0, 1);
		adicionar(criarDescricao("Desenha a arvore (apenas em modo Terminal)"), 1, 1);

		adicionar(substituirAcentuacaoCB = new JCheckBox("Substituir Acentuacao"), 0, 2);
		adicionar(criarDescricao("Substitui letras com acento para letras sem acento"), 1, 2);

		adicionar(ignorarComunsCB = new JCheckBox("Ignorar Palavras Comuns"), 0, 3);
		adicionar(criarDescricao("Ignora palavras comuns como 'e', 'ou'..."), 1, 3);

		adicionar(new JLabel("Palavras Comuns:"), 0, 4);
		adicionar(listaComunsTF = new JTextField(20), 1, 4);

		adicionar(new JLabel("Minimo de Letras:"), 0, 5);
		adicionar(minimoLetras = new JSpinner(new SpinnerNumberModel(2, 0, 10, 1)), 1, 5);

		adicionar(new JLabel("Maximo de Letras:"), 0, 6);
		adicionar(maximoLetras = new JSpinner(new SpinnerNumberModel(20, 10, 30, 1)), 1, 6);

		adicionar(new JLabel("Exibicoes:"), 0, 7);
		adicionar(maximoExibicoes = new JSpinner(new SpinnerNumberModel(1024, 1, 5000, 1)), 1, 7);

		add(mainPanel, BorderLayout.CENTER);
		add(criarBtnPanel(), BorderLayout.SOUTH);
	}

	private JPanel criarBtnPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JButton salvarBtn = new JButton("Salvar");
		salvarBtn.addActionListener(e -> salvarConfiguracoes());
		salvarBtn.setPreferredSize(new Dimension(70, 40));
		panel.add(salvarBtn, BorderLayout.EAST);

		JButton restaurarBtn = new JButton("Restaurar");
		restaurarBtn.addActionListener(e -> restaurarPadroes());
		restaurarBtn.setPreferredSize(new Dimension(90, 40));
		panel.add(restaurarBtn, BorderLayout.WEST);

		return panel;
	}

	private void restaurarPadroes() {
		inverterOrdemCB.setSelected(Util.lerConfig("INVERTER_ORDEM", false, Boolean::new));
		desenharArvoreCB.setSelected(Util.lerConfig("DESENHAR_ARVORE", false, Boolean::new));
		substituirAcentuacaoCB.setSelected(Util.lerConfig("SUBSTITUIR_ACENTUACAO", false, Boolean::new));
		ignorarComunsCB.setSelected(Util.lerConfig("IGNORAR_COMUNS", false, Boolean::new));
		listaComunsTF.setText(Util.lerConfig("PALAVRAS_COMUNS", "", String::new));
		minimoLetras.setValue(Util.lerConfig("MINIMO_LETRAS", 2, Integer::parseInt));
		maximoLetras.setValue(Util.lerConfig("MAXIMO_LETRAS", 20, Integer::parseInt));
		maximoExibicoes.setValue(Util.lerConfig("MAXIMO_EXIBICOES", 1024, Integer::parseInt));
	}

	private void salvarConfiguracoes() {
		try (PrintWriter pw = new PrintWriter(new File("config.ini"))) {
			pw.println("\n# Insira as linhas no formato <PARAMETRO> = <VALOR>");
			pw.println("\n# Nao coloque ponto-e-virgula no final da linha");
			pw.println("\n# Ignora as palavras comuns:");
			pw.println("IGNORAR_COMUNS = " + ignorarComunsCB.isSelected());
			pw.println("\n# Lista de palavras comuns a serem ignoradas, caso a opcao acima esteja ativada:");
			pw.println("\n# (separe por virgula)");
			pw.println("PALAVRAS_COMUNS = " + listaComunsTF.getText());
			pw.println("\n# Ignora palavras com numero de letras < que o valor:");
			pw.println("MINIMO_LETRAS = " + minimoLetras.getValue());
			pw.println("\n# Ignora palavras com numero de letras > que o valor:");
			pw.println("MAXIMO_LETRAS = " + maximoLetras.getValue());
			pw.println("\n# Inverte a ordem de exibicao de 'z' para 'a'");
			pw.println("INVERTER_ORDEM = " + inverterOrdemCB.isSelected());
			pw.println("\n# Numero de palavras a exibir na tela:");
			pw.println("MAXIMO_EXIBICOES = " + maximoExibicoes.getValue());
			pw.println("\n# Desenhar arvore no console");
			pw.println("DESENHAR_ARVORE = " + desenharArvoreCB.isSelected());
			pw.println("\n# Substitui letras com acento por suas correspondentes (ex: á -> a)");
			pw.println("# (recomendado deixar LIGADO)");
			pw.println("SUBSTITUIR_ACENTUACAO = " + substituirAcentuacaoCB.isSelected());
			JOptionPane.showMessageDialog(this, "Configuracoes salvas com sucesso!");
		} catch (Exception e) {

		}
	}

}
