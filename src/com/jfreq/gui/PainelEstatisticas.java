package com.jfreq.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jfreq.algoritmo.Resultado;

public class PainelEstatisticas extends JPanel {
	private static final long serialVersionUID = 1L;

	private static PainelEstatisticas INSTANCE;

	public static PainelEstatisticas getInstance() {
		if (INSTANCE == null)
			INSTANCE = new PainelEstatisticas();
		return INSTANCE;
	}

	private JPanel mainPanel;
	private JLabel algLbl, tempoLbl, compLbl, nPalLbl, maisFreqLbl, menosFreqLbl;

	private PainelEstatisticas() {
		super(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));
		initComponents();
	}

	public void atualizar(Resultado result) {
		result.calcularFrequencias();
		setAlgoritmo(result.nomeAlgoritmo);
		setTempo(result.tempo);
		setNumComparacoes(result.comparacoes);
		setNumPalavras(result.palavras.size());
		setPalavraMaisFrequente(result.strMaisFreq, result.maiorFreq);
		setPalavraMenosFrequente(result.strMenosFreq, result.menorFreq);
	}

	private void reset() {
		setAlgoritmo("");
		setTempo(0);
		setNumComparacoes(0);
		setNumPalavras(0);
		setPalavraMaisFrequente("", 0);
		setPalavraMenosFrequente("", 0);
	}

	public void setAlgoritmo(String alg) {
		algLbl.setText("<html><b>" + "Algoritmo: " + alg + "</b></html>");
	}

	public void setTempo(long ms) {
		String str = String.format("Tempo de execucao: %.2f ms (%d ns)", (ms / 1000000.0), ms);
		tempoLbl.setText("<html><b>" + str + "</b></html>");
	}

	public void setNumComparacoes(long comp) {
		compLbl.setText("<html><b>" + "Numero de Comparacoes: " + comp + "</b></html>");
	}

	public void setNumPalavras(int num) {
		nPalLbl.setText("<html><b>" + "Numero de Palavras: " + num + "</b></html>");
	}

	public void setPalavraMaisFrequente(String pal, int ocorrencias) {
		String str = String.format("Uma das palavras mais frequentes: \"%s\" (%d ocorrencias)", pal, ocorrencias);
		maisFreqLbl.setText("<html><b>" + str + "</b></html>");
	}

	public void setPalavraMenosFrequente(String pal, int ocorrencias) {
		String str = String.format("Uma das palavras menos frequentes: \"%s\" (%d ocorrencias)", pal, ocorrencias);
		menosFreqLbl.setText("<html><b>" + str + "</b></html>");
	}

	private void initComponents() {
		mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.WEST;
		gc.gridx = gc.gridy = 0;
		gc.insets = new Insets(5, 5, 5, 5);
		gc.fill = GridBagConstraints.BOTH;
		algLbl = new JLabel("");
		tempoLbl = new JLabel("");
		compLbl = new JLabel("");
		nPalLbl = new JLabel("");
		maisFreqLbl = new JLabel("");
		menosFreqLbl = new JLabel("");
		reset();
		gc.gridy = 0;
		mainPanel.add(algLbl, gc);
		gc.gridy = 1;
		mainPanel.add(tempoLbl, gc);
		gc.gridy = 2;
		mainPanel.add(compLbl, gc);
		gc.gridy = 3;
		mainPanel.add(nPalLbl, gc);
		gc.gridy = 4;
		mainPanel.add(maisFreqLbl, gc);
		gc.gridy = 5;
		mainPanel.add(menosFreqLbl, gc);
		add(mainPanel, BorderLayout.WEST);
	}
}
