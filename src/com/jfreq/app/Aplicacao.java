package com.jfreq.app;

import com.jfreq.algoritmo.Algoritmos;
import com.jfreq.algoritmo.Resultado;
import com.jfreq.estruturas.BST;
import com.jfreq.util.Util;

public class Aplicacao {

	private static void exec(String[] args) {
		try {
			String algoritmo = args[0];
			String arquivo = args[1];
			Resultado resultado = Algoritmos.executar(algoritmo, arquivo);
			int maximo_exibicoes = Util.lerConfig("MAXIMO_EXIBICOES", 1024, Integer::parseInt);
			resultado.print(maximo_exibicoes);
		} catch (Exception e) {
			System.err.println("Houve um erro ao processar o comando: " + e.getMessage());
			Util.tryProcessarLinhas("log/helpmsg", System.out::println);
		}
	}

	public static void main(String[] args) {
		BST bst = new BST();

		bst.put("carolina", 4);
		bst.put("bernardo", 49);
		bst.put("daniel", 30);
		bst.put("ana", 93);
		bst.add("paulo", 93);
		bst.put("lucas", 93);
		bst.put("barbara", 49);
		bst.add("paulo", 1);
		bst.put("felipe", 93);
		bst.put("wanderson", 93);
		bst.put("yan", 93);
		bst.put("bruno", 93);
		bst.put("rodrigo", 93);

		bst.print();

		System.exit(0);

		if (args.length == 0) {
			// Iniciar GUI
		} else {
			exec(args);
		}
	}

}
