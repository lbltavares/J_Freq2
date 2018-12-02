package com.jfreq.algoritmo;

import java.util.List;

import com.jfreq.estruturas.BST;
import com.jfreq.util.Util;

public class arvore implements Algoritmo {

	private Resultado resultado;

	public arvore() {
		resultado = new Resultado();
		resultado.nomeAlgoritmo = "Arvore Binaria de Pesquisa sem Balanceamento";
	}

	@Override
	public Resultado executar(List<String> palavras) {
		resultado.tempo = System.nanoTime();
		BST bst = new BST();
		palavras.forEach(p -> bst.add(p, 1));
		resultado.palavras = bst;
		resultado.comparacoes = bst.getComparacoes();
		resultado.tempo = System.nanoTime() - resultado.tempo;

		if (Util.lerConfig("DESENHAR_ARVORE", false, Boolean::new))
			bst.print();

		return resultado;
	}

}
