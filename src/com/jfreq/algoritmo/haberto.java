package com.jfreq.algoritmo;

import java.util.List;

import com.jfreq.estruturas.HashAberto;

public class haberto implements Algoritmo {
	private Resultado resultado;

	public haberto() {
		resultado = new Resultado();
		resultado.nomeAlgoritmo = "Hash com Enderecamento Aberto";
	}

	@Override
	public Resultado executar(List<String> palavras) {
		// area do hash = palavras.size e area reserva = palavras.size / 2
		resultado.tempo = System.nanoTime();
		HashAberto hash = new HashAberto(palavras.size(), palavras.size() / 2);
		palavras.forEach(p -> hash.add(p, 1));
		resultado.palavras = hash;
		resultado.comparacoes = hash.getComparacoes();
		resultado.tempo = System.nanoTime() - resultado.tempo;
		return resultado;
	}
}
