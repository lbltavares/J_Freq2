package com.jfreq.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.jfreq.algoritmo.Algoritmos;
import com.jfreq.algoritmo.Resultado;
import com.jfreq.util.Util;

public class PainelSelecao extends JPanel {
	private static final long serialVersionUID = 1L;

	private static PainelSelecao INSTANCE;

	public static PainelSelecao getInstance() {
		if (INSTANCE == null)
			INSTANCE = new PainelSelecao();
		return INSTANCE;
	}

	private JComboBox<String> algoritmoCB;
	private GridBagConstraints gc;

	private JPanel mainPanel;

	private PainelSelecao() {
		super(new BorderLayout());
		setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
		initComponents();
	}

	private void adicionar(Component c, int x, int y) {
		gc.gridx = x;
		gc.gridy = y;
		mainPanel.add(c, gc);
	}

	private JTextField arquivoTF;

	private JPanel criarTFArquivo() {
		JPanel panel = new JPanel(new BorderLayout());
		arquivoTF = new JTextField(20);
		JButton btn = new JButton("...");
		btn.addActionListener(e -> {
			File f = chooseFile("Escolha um arquivo", "Arquivos de texto (.txt)", "txt");
			if (f != null && f.exists()) {
				arquivoTF.setText(f.getAbsolutePath());
			}
		});
		panel.add(arquivoTF, BorderLayout.CENTER);
		panel.add(btn, BorderLayout.EAST);
		return panel;
	}

	private void executar(String file) {
		try {
			List<String> palavras = Util.getPalavrasFormatadas(file);
			Resultado res = null;
			if (algoritmoCB.getSelectedIndex() == 0) {
				res = Algoritmos.executar("hlista", palavras);
			} else if (algoritmoCB.getSelectedIndex() == 1) {
				res = Algoritmos.executar("haberto", palavras);
			} else if (algoritmoCB.getSelectedIndex() == 2) {
				res = Algoritmos.executar("arvore", palavras);
			} else if (algoritmoCB.getSelectedIndex() == 3) {
				res = Algoritmos.executar("pseq", palavras);
			} else if (algoritmoCB.getSelectedIndex() == 4) {
				res = Algoritmos.executar("pbinaria", palavras);
			}

			Map<String, Integer> mapa = res.palavras;

			List<String> ordenadas = new ArrayList<>(mapa.keySet());
			Util.ordenar(ordenadas);

			int maxExib = Util.lerConfig("MAXIMO_EXIBICOES", 1024, Integer::parseInt);
			if (maxExib < ordenadas.size()) {
				ordenadas = ordenadas.subList(0, maxExib);
			}

			Object[][] data = new Object[ordenadas.size()][2];
			for (int i = 0; i < ordenadas.size(); i++) {
				data[i][0] = ordenadas.get(i);
				data[i][1] = mapa.get(ordenadas.get(i));
			}

			PainelTabela.getInstance().updateTable(data);

			PainelEstatisticas.getInstance().atualizar(res);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void initComponents() {
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(2, 15, 2, 2);
		gc.gridx = gc.gridy = 0;

		algoritmoCB = new JComboBox<>(new String[] { //
				"Hash com lista encadeada", //
				"Hash aberto", //
				"Arvore binária de pesquisa (BST)", //
				"Pesquisa sequencial", //
				"Pesquisa binária", //
		});

		adicionar(new JLabel("<html><b>Algoritmo:</b></html>"), 0, 0);
		adicionar(algoritmoCB, 1, 0);

		adicionar(new JLabel("<html><b>Arquivo .txt:</b></html>"), 0, 1);
		adicionar(criarTFArquivo(), 1, 1);

		JButton executarBtn = new JButton("Executar");
		executarBtn.addActionListener(e -> {
			if (!arquivoTF.getText().isEmpty()) {
				if (new File(arquivoTF.getText()).exists()) {
					executar(arquivoTF.getText());
				} else
					JOptionPane.showMessageDialog(this, "Selecione um arquivo valido!");
			}
		});

		gc.fill = GridBagConstraints.NONE;
		adicionar(executarBtn, 1, 2);

		add(mainPanel, BorderLayout.CENTER);
	}

	private static File chooseFile(String title, String filterLbl, String... ext) {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle(title);
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(filterLbl, ext);
		jfc.addChoosableFileFilter(filter);
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			return file;
		}
		return null;
	}
}
