package com.jfreq.util;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public final class Util {

	public static final String[][] correspondencia_acentuacao = { //
			{ "á", "a" }, //
			{ "à", "a" }, //
			{ "ã", "a" }, //
			{ "â", "a" }, //
			{ "é", "e" }, //
			{ "è", "e" }, //
			{ "ê", "e" }, //
			{ "ẽ", "e" }, //
			{ "ë", "e" }, //
			{ "í", "i" }, //
			{ "ì", "i" }, //
			{ "î", "i" }, //
			{ "ï", "i" }, //
			{ "ó", "o" }, //
			{ "ò", "o" }, //
			{ "ô", "o" }, //
			{ "õ", "o" }, //
			{ "ú", "u" }, //
			{ "ü", "u" }, //
			{ "ç", "c" }, //
	};

	private Util() {
		// Construtor privado para nao ser instanciado
	}

	public static void processarLinhas(String arquivo, Consumer<String> cons) throws Exception {
		BufferedReader reader = Files.newBufferedReader(Paths.get(arquivo));
		reader.lines().forEach(cons);
		reader.close();
	}

	public static void tryProcessarLinhas(String arquivo, Consumer<String> cons) {
		try {
			processarLinhas(arquivo, cons);
		} catch (Exception e) {
			System.err.println("Erro ao abrir o arquivo " + arquivo);
		}
	}

	public static String formatarPalavra(String palavra) {
		palavra = palavra.toLowerCase();
		for (char c : palavra.toCharArray()) {
			if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c))
				palavra = palavra.replace(c, ' ');
		}

		palavra.replace(" ", "");
		if (!palavra.isEmpty() && lerConfig("SUBSTITUIR_ACENTUACAO", true, Boolean::parseBoolean)) {
			for (String[] corr : correspondencia_acentuacao)
				palavra = palavra.replace(corr[0], corr[1]);
		}
		return palavra.trim();
	}

	public static List<String> getPalavrasFormatadas(String arquivo) throws Exception {
		int maximo_letras = lerConfig("MAXIMO_LETRAS", 20, Integer::parseInt);
		int minimo_letras = lerConfig("MINIMO_LETRAS", 3, Integer::parseInt);
		List<String> palavrasFormatadas = new ArrayList<>();
		processarLinhas(arquivo, linha -> {
			if (!linha.isEmpty()) {
				String[] palavras = linha.split(" ");
				for (String p : palavras) {
					String formatado = formatarPalavra(p);
					if (!formatado.isEmpty() && formatado.length() <= maximo_letras
							&& formatado.length() >= minimo_letras)
						palavrasFormatadas.add(formatado);
				}
			}
		});

		boolean ignorar_comuns = lerConfig("IGNORAR_COMUNS", false, Boolean::parseBoolean);
		if (ignorar_comuns) {
			List<String> comuns = lerConfig("PALAVRAS_COMUNS", new ArrayList<>(), val -> {
				String[] arr = val.split(",");
				List<String> _comuns = new ArrayList<>();
				Arrays.asList(arr).forEach(p -> {
					String formatada = formatarPalavra(p);
					if (!formatada.isEmpty())
						_comuns.add(formatada);
				});
				return _comuns;
			});
			palavrasFormatadas.removeIf(comuns::contains);
		}

		return palavrasFormatadas;
	}

	public static <V> V lerConfig(String config, V valorPadrao, Function<String, V> conversor) {
		class Container {
			V result;
		}
		Container c = new Container();
		c.result = valorPadrao;
		try {
			processarLinhas("config.ini", linha -> {
				if (linha.isEmpty() || linha.startsWith("#"))
					return;
				if (linha.split("=")[0].trim().equalsIgnoreCase(config)) {
					c.result = conversor.apply(linha.split("=")[1].trim());
				}
			});
		} catch (Exception ignore) {
		}
		return c.result;
	}

	public static void ordenar(List<String> lista) {
		boolean inverter = Util.lerConfig("INVERTER_ORDEM", false, Boolean::parseBoolean);
		if (inverter)
			lista.sort(Collator.getInstance().reversed());
		else
			lista.sort(Collator.getInstance());
	}

	public static void println(String format, Object... args) {
		System.out.println(String.format(format, args));
	}

	public static void println(String str) {
		System.out.println(str);
	}

	public static void printN(String seq, int N) {
		for (int i = 0; i < N; i++)
			System.out.print(seq);
		System.out.println();
	}

	public static void exibirHelp() {
		println("Comando:");
		println("java -jar jfreq.jar [algoritmo] [arquivo.txt]");
		println("\n[Algoritmo]:");
		println("pseq     - Pesquisa Sequencial");
		println("pbinaria - Pesquisa Binaria");
		println("arvore   - Arvore Binaria de Pesquisa (BST)");
		println("haberto  - Hash com Enderecamento aberto");
		println("hlista   - Hash com Lista Encadeada");
		println("\n[Arquivo]:");
		println("Arquivo de texto (.txt), nao-binario");
		println("\nExemplo:");
		println("java -jar jfreq.jar hlista artigo.txt");
	}

}
