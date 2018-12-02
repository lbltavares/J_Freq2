package com.jfreq.estruturas;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class BST implements Map<String, Integer> {

	private int comparacoes;
	private Node root;

	public BST() {
		comparacoes = 0;
	}

	public void resetComparacoes() {
		comparacoes = 0;
	}

	public int getComparacoes() {
		return comparacoes;
	}

	private void print(Node no, int tabs) {
		if (no != null) {
			for (int i = 0; i < tabs; i++)
				System.out.print("|  ");
			System.out.println("+" + no.key + " = " + no.val);
			if (no.esq != null) {
				print(no.esq, tabs + 1);
			}
			if (no.dir != null) {
				print(no.dir, tabs + 1);
			}
		}
	}

	public void print() {
		print(root, 0);
	}

	@Override
	public void clear() {
		root = null;
	}

	private boolean containsKey(Node no, String key) {
		if (no == null)
			return false;
		if (no.key.equals(key))
			return true;
		if (no.esq != null)
			containsKey(no.esq, key);
		if (no.dir != null)
			containsKey(no.dir, key);
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		if (key instanceof String)
			return containsKey(root, (String) key);
		return false;
	}

	private boolean containsValue(Node no, Integer val) {
		if (no == null)
			return false;
		if (no.val.equals(val))
			return true;
		if (no.esq != null)
			containsValue(no.esq, val);
		if (no.dir != null)
			containsValue(no.dir, val);
		return false;
	}

	@Override
	public boolean containsValue(Object val) {
		if (val instanceof Integer)
			return containsValue(root, (Integer) val);
		return false;
	}

	private void entrySet(Node no, Set<Entry<String, Integer>> set) {
		if (no == null)
			return;
		set.add(new SimpleEntry<String, Integer>(no.key, no.val));
		entrySet(no.esq, set);
		entrySet(no.dir, set);
	}

	@Override
	public Set<Entry<String, Integer>> entrySet() {
		Set<Entry<String, Integer>> set = new HashSet<>();
		entrySet(root, set);
		return set;
	}

	public Integer get(Node no, String key) {
		if (no == null)
			return null;
		if (no.key.equals(key))
			return no.val;
		if (key.compareTo(no.key) < 0)
			return get(no.esq, key);
		else
			return get(no.dir, key);
	}

	@Override
	public Integer get(Object key) {
		if (key instanceof String) {
			return get(root, (String) key);
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return Objects.isNull(root);
	}

	public void keySet(Node no, Set<String> set) {
		if (no == null)
			return;
		set.add(no.key);
		if (no.esq != null)
			keySet(no.esq, set);
		if (no.dir != null)
			keySet(no.dir, set);
	}

	@Override
	public Set<String> keySet() {
		Set<String> set = new HashSet<>();
		keySet(root, set);
		return set;
	}

	private void add(Node no, String key, Integer amount) {
		comparacoes++;
		if (no == null) {
			root = new Node(key, amount);
			return;
		}
		if (no.key.equals(key)) {
			no.val = no.val != null ? no.val + amount : amount;
			return;
		}
		if (no.key.compareTo(key) > 0) { // No esquerdo
			if (no.esq == null)
				no.esq = new Node(key, amount);
			else
				add(no.esq, key, amount);
		} else if (no.key.compareTo(key) < 0) { // No direito
			if (no.dir == null)
				no.dir = new Node(key, amount);
			else
				add(no.dir, key, amount);
		}
	}

	public void add(String key, Integer amount) {
		add(root, key, amount);
	}

	private Integer put(Node no, String key, Integer val) {
		comparacoes++;
		if (no == null) {
			root = new Node(key, val);
			return val;
		}
		if (no.key.equals(key)) {
			no.val = val;
			return no.val;
		}
		if (no.key.compareTo(key) > 0) { // No esquerdo
			if (no.esq == null)
				return (no.esq = new Node(key, val)).val;
			else
				put(no.esq, key, val);
		} else if (no.key.compareTo(key) < 0) { // No direito
			if (no.dir == null)
				return (no.dir = new Node(key, val)).val;
			else
				put(no.dir, key, val);
		}
		return null;
	}

	@Override
	public Integer put(String key, Integer val) {
		return put(root, key, val);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Integer> map) {
		map.forEach(this::put);
	}

	@Override
	public Integer remove(Object key) {
		return null; // Nao e necessario
	}

	public int size(Node no) {
		if (no != null)
			return 1 + size(no.esq) + size(no.dir);
		return 0;
	}

	@Override
	public int size() {
		return size(root);
	}

	private void values(Node no, Collection<Integer> vals) {
		if (no == null)
			return;
		vals.add(no.val);
		if (no.esq != null)
			values(no.esq, vals);
		if (no.dir != null)
			values(no.dir, vals);
	}

	@Override
	public Collection<Integer> values() {
		List<Integer> values = new ArrayList<>();
		values(root, values);
		return values;
	}

	private class Node {
		public Node esq, dir;
		public String key;
		public Integer val;

		public Node(String key, Integer val) {
			this.key = key;
			this.val = val;
		}
	}
}
