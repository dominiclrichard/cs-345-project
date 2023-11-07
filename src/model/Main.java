package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {


	// CURRENT TESTING
	public static void main(String[] args) {
		String message = "hello, world!";

        Map<Character, Integer> charFrequency = new HashMap<>();
        for (char c : message.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        HuffmanEncoding Encoder = new HuffmanEncoding();

        HuffBaseNode huffmanTree = buildHuffmanTree(charFrequency);
        Map<Character, String> huffmanCodes = buildHuffmanCodes(huffmanTree);
        String encodedMessage = Encoder.encodeMessage(message, huffmanCodes);
        System.out.println("Encoded Message: " + encodedMessage);

        String decodedMessage = Encoder.decodeMessage(encodedMessage, huffmanTree);
        System.out.println("Decoded Message: " + decodedMessage);
	}




    public static HuffBaseNode buildHuffmanTree(Map<Character, Integer> charFrequency) {
        // Create leaf nodes for each character
        List<HuffBaseNode> nodes = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : charFrequency.entrySet()) {
            int weight = (entry.getValue() != null) ? entry.getValue() : 0;
            nodes.add(new HuffLeafNode(entry.getKey(), weight));
        }

        // Build the Huffman tree
        while (nodes.size() > 1) {
            nodes.sort(Comparator.comparingInt(HuffBaseNode::weight));

            HuffBaseNode left = nodes.remove(0);
            HuffBaseNode right = nodes.remove(0);

            int combinedWeight = left.weight() + right.weight();
            nodes.add(new HuffInternalNode(left, right, combinedWeight));
        }

        // Return the root of the Huffman tree
        return nodes.get(0);
    }

    public static Map<Character, String> buildHuffmanCodes(HuffBaseNode root) {
        Map<Character, String> huffmanCodes = new HashMap<>();
        buildHuffmanCodeRecursive(root, "", huffmanCodes);
        return huffmanCodes;
    }

    private static void buildHuffmanCodeRecursive(HuffBaseNode node, String code, Map<Character, String> huffmanCodes) {
        if (node.isLeaf()) {
            huffmanCodes.put(((HuffLeafNode) node).value(), code);
        } else {
            HuffInternalNode internalNode = (HuffInternalNode) node;
            buildHuffmanCodeRecursive(internalNode.left(), code + "0", huffmanCodes);
            buildHuffmanCodeRecursive(internalNode.right(), code + "1", huffmanCodes);
        }
    }
}
