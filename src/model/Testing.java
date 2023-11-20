package model;

import java.util.Random;

public class Testing {

	public static void main(String[] args) {

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
			System.out.println("Input String: " + s);
			Huffman huff = new Huffman();
			String encoded = huff.encode(s);
			System.out.println("Encoded String: " + encoded);
			System.out.println("Decoded String: " + huff.decode(encoded));
			System.out.println();
		}

	}

}
