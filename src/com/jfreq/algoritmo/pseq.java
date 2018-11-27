package com.jfreq.algoritmo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class pseq implements Algoritmo {

	private Resultado resultado;

	public pseq() {
		resultado = new Resultado();
		resultado.nomeAlgoritmo = "Pesquisa Sequencial";
	}

	@Override
	public Resultado executar(List<String> palavras) {
		// Inicio da contagem:
		resultado.tempo = System.nanoTime();

		// Montagem da lista unica:
		Set<String> palavrasUnicas = new HashSet<>(palavras);
		Map<String, Integer> mapa = new HashMap<>();

		palavrasUnicas.forEach(un -> {
			int freq = 0;
			for (int i = 0; i < palavras.size(); i++) {
				if (un.equals(palavras.get(i))) {
					freq++;
					resultado.comparacoes++;
				}
			}
			mapa.put(un, freq);
		});

		// Termino da contagem:
		resultado.tempo = System.nanoTime() - resultado.tempo;
		resultado.palavras = mapa;

		return resultado;
	}
}
