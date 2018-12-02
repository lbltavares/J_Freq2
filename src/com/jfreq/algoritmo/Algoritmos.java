package com.jfreq.algoritmo;

import java.util.List;

import com.jfreq.util.Util;

public final class Algoritmos {

	private Algoritmos() {
		// Construtor privado para nao ser instanciado
	}

	public static Resultado executar(String nome, String arquivo) throws Exception {
		List<String> palavras = Util.getPalavrasFormatadas(arquivo);
		return executar(nome, palavras);
	}

	public static Resultado executar(String nome, List<String> palavras) throws Exception {
		Algoritmo algoritmo = getByNome(nome);
		return algoritmo.executar(palavras);
	}

	public static Algoritmo getByNome(String nome) throws Exception {
		if (nome.equalsIgnoreCase("pseq")) {
			return new pseq();
		} else if (nome.equalsIgnoreCase("pbinaria")) {
			return new pbinaria();
		} else if (nome.equalsIgnoreCase("hlista")) {

		} else if (nome.equalsIgnoreCase("haberto")) {

		} else if (nome.equalsIgnoreCase("arvore")) {
			return new arvore();
		}
		throw new Exception("Algoritmo invalido: " + nome);
	}

}
