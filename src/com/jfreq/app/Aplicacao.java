package com.jfreq.app;

import java.awt.EventQueue;

import com.jfreq.algoritmo.Algoritmos;
import com.jfreq.algoritmo.Resultado;
import com.jfreq.gui.Janela;
import com.jfreq.util.Util;

public class Aplicacao {

	private static void exec(String[] args) {
		try {
			String algoritmo = args[0];
			String arquivo = args[1];
			Resultado resultado = Algoritmos.executar(algoritmo, arquivo);
			resultado.print();
		} catch (Exception e) {
			System.err.println("Houve um erro ao processar o comando: " + e.getMessage());
			Util.exibirHelp();
		}
	}

	public static void main(String[] args) throws Exception {
		args = new String[] {"asd", "teste.txt"};
		exec(args);
		System.exit(0);
		
		if (args.length == 0) {
			EventQueue.invokeLater(Janela::getInstance);
		} else {
			exec(args);
		}
	}

}
