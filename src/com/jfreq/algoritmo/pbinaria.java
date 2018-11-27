package com.jfreq.algoritmo;

import java.text.Collator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class pbinaria implements Algoritmo {

	private Resultado resultado;

	public pbinaria() {
		resultado = new Resultado();
		resultado.nomeAlgoritmo = "Pesquisa Binaria";
	}

	@Override
	public Resultado executar(List<String> palavras) {
		// Ordena a lista de palavras:
		palavras.sort(Collator.getInstance());
		String[] arr = palavras.toArray(new String[palavras.size()]);

		// Cria o mapa:
		Map<String, Integer> mapa = new HashMap<>();

		// Inicio da contagem:
		resultado.tempo = System.nanoTime();

		// Cria um conjunto de palavras unicas:
		Set<String> unicas = new HashSet<>(palavras);

		unicas.forEach(un -> {
			int indice = buscaBinaria(arr, 0, arr.length, un);
			int dir = contarADireita(arr, indice, un) - 1;
			int esq = contarAEsquerda(arr, indice, un) - 1;
			int freq = dir + esq + 1;
			mapa.put(un, freq);
		});

		// Termino da contagem:
		resultado.tempo = System.nanoTime() - resultado.tempo;
		resultado.palavras = mapa;

		return resultado;
	}

	private int contarADireita(String arr[], int i, String elemento) {
		if (i == -1 || i >= arr.length || !arr[i].equals(elemento))
			return 0;
		return 1 + contarADireita(arr, i + 1, elemento);
	}

	private int contarAEsquerda(String arr[], int i, String elemento) {
		if (i == -1 || i < 0 || !arr[i].equals(elemento))
			return 0;
		return 1 + contarAEsquerda(arr, i - 1, elemento);
	}

	private int buscaBinaria(String arr[], int inf, int sup, String elemento) {
		int m;
		while (sup - inf > 1) {
			m = inf + (sup - inf) / 2;
			resultado.comparacoes++;
			if (arr[m].compareToIgnoreCase(elemento) <= 0)
				inf = m;
			else
				sup = m;
		}
		resultado.comparacoes++;
		if (arr[inf].equals(elemento))
			return inf;
		else
			return -1;
	}
}
