package com.jfreq.algoritmo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jfreq.util.Util;

public class Resultado {
	public String nomeAlgoritmo;
	public long tempo, comparacoes;
	public Map<String, Integer> palavras;

	public int maiorFreq, menorFreq;
	public String strMaisFreq, strMenosFreq;

	public void print() {
		int N = Util.lerConfig("MAXIMO_EXIBICOES", 1024, Integer::parseInt);

		printCabecalho();

		// Ordenacao de acordo com o Config.ini
		List<String> ordenadas = new ArrayList<>(palavras.keySet());
		Util.ordenar(ordenadas);

		// Calcula as maiores e menores frequencias
		calcularFrequencias();

		// Exibe as palavras
		Iterator<String> it = ordenadas.iterator();
		for (int i = 0; i < N && it.hasNext(); i++) {
			String p = it.next();
			int fre = palavras.get(p);
			Util.println("%10d    %s", fre, p);
		}

		// Exibe as estatisticas
		printEstatisticas();
	}

	public void calcularFrequencias() {
		menorFreq = Integer.MAX_VALUE;
		palavras.forEach((palavra, freq) -> {
			if (freq > maiorFreq) {
				maiorFreq = freq;
				strMaisFreq = palavra;
			}
			if (freq < menorFreq) {
				menorFreq = freq;
				strMenosFreq = palavra;
			}
		});
		menorFreq = menorFreq == Integer.MAX_VALUE ? 0 : menorFreq;
	}

	private void printCabecalho() {
		Util.printN("=", 36);
		Util.println("%10s    %s", "Frequencia", "Palavra");
		Util.printN("=", 36);
	}

	private void printEstatisticas() {
		Util.printN("=", 36);
		Util.println("* Algoritmo: " + nomeAlgoritmo);
		Util.println("* Tempo de execucao: %.2f ms (%d ns)", (tempo / 1000000.0), tempo);
		Util.println("* Numero de comparacoes: " + comparacoes);
		Util.println("* Numero de palavras: " + palavras.size());
		Util.println("* Uma das palavras mais frequentes: \"%s\" (%d ocorrencias)", strMaisFreq, maiorFreq);
		Util.println("* Uma das palavras menos frequentes: \"%s\" (%d ocorrencias)", strMenosFreq, menorFreq);
	}
}
