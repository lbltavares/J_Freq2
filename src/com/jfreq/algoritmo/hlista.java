package com.jfreq.algoritmo;

import java.util.List;

import com.jfreq.estruturas.HashLista;

public class hlista implements Algoritmo {
	private Resultado resultado;

	public hlista() {
		resultado = new Resultado();
		resultado.nomeAlgoritmo = "Hash com Lista";
	}

	@Override
	public Resultado executar(List<String> palavras) {
		resultado.tempo = System.nanoTime();
		HashLista hash = new HashLista(palavras.size());
		palavras.forEach(p -> hash.add(p, 1));
		resultado.palavras = hash;
		resultado.comparacoes = hash.getComparacoes();
		resultado.tempo = System.nanoTime() - resultado.tempo;
		return resultado;
	}
}
