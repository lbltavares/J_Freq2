package com.jfreq.algoritmo;

import java.util.List;

public class arvore implements Algoritmo {

	private Resultado resultado;

	public arvore() {
		resultado = new Resultado();
		resultado.nomeAlgoritmo = "Arvore Binaria de Pesquisa sem Balanceamento";
	}

	@Override
	public Resultado executar(List<String> palavras) {

		resultado.tempo = System.nanoTime();

		resultado.tempo = System.nanoTime() - resultado.tempo;
		return resultado;
	}

}
