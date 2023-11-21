package model;

import java.util.Random;

public class Testing {

	public static void main(String[] args) {
		testRandStr();
	}

	public static void testRandStr() {
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

		int[] sizes = { 10, 50, 100, 500, 1000 };

		for (int i : sizes) {
			System.out.println("Testing with string size " + i);
			String s = "";
			int j = 0;
			Random rand = new Random();

			while (j < i) {
				int randomInd = rand.nextInt(26);
				s += alphabet[randomInd];
				j += 1;
			}
			// Random string passed to Huffman class
			System.out.println("Input String: " + s);
			Huffman huff = new Huffman();
			// Pass random string to Huffman class for encoding
			String encoded = huff.encode(s);
			// Print encoded string
			System.out.println("Encoded String: " + encoded);
			// Decode and print encoded string to ensure equals input
			System.out.println("Decoded String: " + huff.decode(encoded));
			System.out.println();
		}
	}

}
