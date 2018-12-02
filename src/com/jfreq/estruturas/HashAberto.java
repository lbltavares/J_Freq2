package com.jfreq.estruturas;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashAberto implements Map<String, Integer> {

	private Entry<String, Integer>[] tabela;
	private int tamanho, reserva, comparacoes;

	public HashAberto(int tamanho, int reserva) {
		this.tamanho = tamanho;
		this.reserva = reserva;
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
		tabela = (Entry<String, Integer>[]) new Entry[tamanho + reserva];
	}

	@Override
	public boolean containsKey(Object key) {
		if (key instanceof String) {
			int h = hash((String) key);
			if (tabela[h] != null) {
				if (tabela[h].getKey().equals(key))
					return true;
				else {
					for (int i = tamanho; i < tamanho + reserva; i++) {
						if (tabela[i] != null && tabela[i].getKey().equals(key))
							return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsValue(Object val) {
		if (val instanceof Integer) {
			for (int i = 0; i < tamanho + reserva; i++) {
				if (tabela[i] != null && tabela[i].getValue().equals(val))
					return true;
			}
		}
		return false;
	}

	@Override
	public Set<Entry<String, Integer>> entrySet() {
		Set<Entry<String, Integer>> set = new HashSet<>();
		for (int i = 0; i < tamanho + reserva; i++) {
			if (tabela[i] != null)
				set.add(tabela[i]);
		}
		return set;
	}

	@Override
	public Integer get(Object key) {
		if (key instanceof String) {
			int h = hash((String) key);
			if (tabela[h] != null && tabela[h].getKey().equals(key)) {
				comparacoes++;
				return tabela[h].getValue();
			} else {
				for (int i = tamanho; i < tamanho + reserva; i++) {
					if (tabela[i] != null && tabela[i].getKey().equals(key)) {
						comparacoes++;
						return tabela[i].getValue();
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < tamanho + reserva; i++) {
			if (tabela[i] != null)
				return false;
		}
		return true;
	}

	@Override
	public Set<String> keySet() {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < tamanho + reserva; i++) {
			if (tabela[i] != null)
				set.add(tabela[i].getKey());
		}
		return set;
	}

	public void add(String key, Integer value) {
		int h = hash(key);
		if (tabela[h] == null) {
			comparacoes += 1;
			tabela[h] = new SimpleEntry<>(key, value);
		} else if (tabela[h].getKey().equals(key)) {
			comparacoes += 2;
			tabela[h] = new SimpleEntry<>(key, tabela[h].getValue() + value);
		} else {
			comparacoes += 3;
			int n = -1;
			for (int i = tamanho; i < tamanho + reserva; i++) {
				if (tabela[i] == null) {
					comparacoes++;
					n = i;
				} else if (tabela[i].getKey().equals(key)) {
					comparacoes += 2;
					tabela[i] = new SimpleEntry<>(key, tabela[i].getValue() + value);
					return;
				}
			}
			// Se nao encontrar nenhum, criar um novo
			if (n != -1)
				tabela[n] = new SimpleEntry<>(key, value);
		}
	}

	@Override
	public Integer put(String key, Integer value) {
		int h = hash(key);
		if (tabela[h] == null || tabela[h].getKey().equals(key))
			tabela[h] = new SimpleEntry<>(key, value);
		else {
			int n = -1;
			for (int i = tamanho; i < tamanho + reserva; i++) {
				if (tabela[i] == null)
					n = i;
				else if (tabela[i].getKey().equals(key)) {
					tabela[i] = new SimpleEntry<>(key, value);
					return value;
				}
			}
			if (n != -1) {
				tabela[n] = new SimpleEntry<>(key, value);
				return value;
			}
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Integer> m) {
		m.forEach(this::put);
	}

	@Override
	public Integer remove(Object key) {
		if (key instanceof String) {
			int h = hash((String) key);
			if (tabela[h] == null || !tabela[h].getKey().equals(key)) {
				for (int i = reserva; i < tamanho + reserva; i++) {
					if (tabela[i] != null && tabela[i].getKey().equals(key)) {
						int r = tabela[i].getValue();
						tabela[i] = null;
						return r;
					}
				}
			} else {
				int r = tabela[h].getValue();
				tabela[h] = null;
				return r;
			}
		}
		return null;
	}

	@Override
	public int size() {
		int size = 0;
		for (int i = 0; i < tamanho + reserva; i++) {
			if (tabela[i] != null)
				size++;
		}
		return size;
	}

	@Override
	public Collection<Integer> values() {
		Collection<Integer> vals = new ArrayList<>();
		for (int i = 0; i < tamanho + reserva; i++) {
			if (tabela[i] != null)
				vals.add(tabela[i].getValue());
		}
		return vals;
	}

}