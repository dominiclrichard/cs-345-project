package model;

public class FrequencyTable {
	private final HashMap<Character, Integer> table = new HashMap<>();

	public void add(char character) {
		if (table.containsKey(character)) {
			int count = table.get(character);
			table.put(character, count + 1);
		} else {
			table.put(character, 1);
		}
	}

	public HashMap<Character, Integer> getTable() {
		return table;
	}

	public char[] keySet() {
		return table.keySet();
	}

	public boolean containsKey(char character) {
		for (char key : table.keySet()) {
			if (key == character) {
				return true;
			}
		}
		return false;
	}
}
