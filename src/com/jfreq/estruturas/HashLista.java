package com.jfreq.estruturas;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashLista implements Map<String, Integer> {

	private List<Entry<String, Integer>>[] tabela;
	private int tamanho, comparacoes;

	public HashLista(int tamanho) {
		this.tamanho = tamanho;
		resetComparacoes();
		clear();
	}

	private int hash(String str) {
		return Math.abs(str.hashCode() % tamanho);
	}

	public int getComparacoes() {
		return comparacoes;
	}

	public void resetComparacoes() {
		comparacoes = 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		tabela = (List<Entry<String, Integer>>[]) new List[tamanho];
	}

	@Override
	public boolean containsKey(Object key) {
		if (key instanceof String) {
			int h = hash((String) key);
			if (tabela[h] != null) {
				for (Entry<String, Integer> e : tabela[h]) {
					if (e.getKey().equals(key))
						return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsValue(Object val) {
		for (int i = 0; i < tamanho; i++) {
			if (tabela[i] != null) {
				for (Entry<String, Integer> e : tabela[i]) {
					if (e.getValue().equals(val))
						return true;
				}
			}
		}
		return false;
	}

	@Override
	public Set<Entry<String, Integer>> entrySet() {
		Set<Entry<String, Integer>> set = new HashSet<>();
		for (int i = 0; i < tamanho; i++) {
			if (tabela[i] != null) {
				set.addAll(tabela[i]);
			}
		}
		return set;
	}

	@Override
	public Integer get(Object key) {
		if (key instanceof String) {
			int h = hash((String) key);
			if (tabela[h] != null) {
				for (Entry<String, Integer> e : tabela[h]) {
					if (e.getKey().equals(key))
						return e.getValue();
				}
			}
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < tamanho; i++) {
			if (tabela[i] != null && tabela[i].size() > 0)
				return false;
		}
		return true;
	}

	@Override
	public Set<String> keySet() {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < tamanho; i++) {
			if (tabela[i] != null)
				for (Entry<String, Integer> e : tabela[i])
					set.add(e.getKey());
		}
		return set;
	}

	public void add(String key, Integer value) {
		int h = hash(key);
		if (tabela[h] == null || tabela[h].isEmpty()) {
			comparacoes++;
			tabela[h] = new LinkedList<>();
			tabela[h].add(new SimpleEntry<>(key, value));
		} else {
			for (int i = 0; i < tabela[h].size(); i++) {
				comparacoes++;
				if (tabela[h].get(i).getKey().equals(key)) {
					int atual = tabela[h].get(i).getValue();
					tabela[h].get(i).setValue(atual + value);
					return;
				}
			}
			tabela[h].add(new SimpleEntry<>(key, value));
		}
	}

	@Override
	public Integer put(String key, Integer value) {
		int h = hash(key);
		if (tabela[h] == null || tabela[h].isEmpty()) {
			tabela[h] = new LinkedList<>();
			tabela[h].add(new SimpleEntry<>(key, value));
		} else {
			for (int i = 0; i < tabela[h].size(); i++) {
				if (tabela[h].get(i).getKey().equals(key)) {
					tabela[h].get(i).setValue(value);
					return value;
				}
			}
			tabela[h].add(new SimpleEntry<>(key, value));
		}
		return value;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Integer> m) {
		m.forEach(this::put);
	}

	@Override
	public Integer remove(Object key) {
		if (key instanceof String) {
			int h = hash((String) key);
			if (tabela[h] != null) {
				tabela[h].removeIf(e -> e.getKey().equals(key));
			}
		}
		return 0;
	}

	@Override
	public int size() {
		int size = 0;
		for (int i = 0; i < tamanho; i++) {
			if (tabela[i] != null)
				size += tabela[i].size();
		}
		return size;
	}

	@Override
	public Collection<Integer> values() {
		Collection<Integer> vals = new LinkedList<>();
		for (int i = 0; i < tamanho; i++) {
			if (tabela[i] != null) {
				tabela[i].forEach(e -> vals.add(e.getValue()));
			}
		}
		return vals;
	}

}