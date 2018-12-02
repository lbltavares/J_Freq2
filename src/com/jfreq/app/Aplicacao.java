package com.jfreq.app;

import com.jfreq.algoritmo.Algoritmos;
import com.jfreq.algoritmo.Resultado;
import com.jfreq.util.Util;

public class Aplicacao {

	private static void exec(String[] args) {
		try {
			String algoritmo = args[0];
			String arquivo = args[1];
			Resultado resultado = Algoritmos.executar(algoritmo, arquivo);
			resultado.print();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Houve um erro ao processar o comando: " + e.getMessage());
			Util.tryProcessarLinhas("log/helpmsg", System.out::println);
		}
	}

	public static void main(String[] args) throws Exception {
		args = new String[] { "hlista", "teste.txt" };
		exec(args);

		System.exit(0);

		if (args.length == 0) {
			// Iniciar GUI
		} else {
			exec(args);
		}
	}

}
