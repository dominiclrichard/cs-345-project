package model;

public class HashMap<K, V> {

	int[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private char[] map;

	public HashMap() {
		map = new char[26];
		for (int i : alphabet) {
		}
	}

}
