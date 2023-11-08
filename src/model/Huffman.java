package model;

public class Huffman {
    public static HuffBaseNode buildHuffmanTree(FrequencyTable frequencyTable) {
        MinHeap minHeap = new MinHeap();

        for (char character : frequencyTable.getTable().keySet()) {
            int frequency = frequencyTable.getTable().get(character);
            minHeap.insert(new HuffLeafNode(character, frequency));
        }

        while (minHeap.size() > 1) {
            HuffBaseNode left = minHeap.removeMin();
            HuffBaseNode right = minHeap.removeMin();
            int totalWeight = left.weight() + right.weight();
            minHeap.insert(new HuffInternalNode(left, right, totalWeight));
        }

        return minHeap.removeMin();
    }

    public static void buildHuffmanCodes(HuffBaseNode root, StringBuilder code, HashMap<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }
        if (root.isLeaf()) {
            huffmanCodes.put(((HuffLeafNode) root).value(), code.toString());
        }
        code.append("0");
        buildHuffmanCodes(root.left(), code, huffmanCodes);
        code.setLength(code.length() - 1);
        code.append("1");
        buildHuffmanCodes(root.right(), code, huffmanCodes);
        code.setLength(code.length() - 1);
    }

    public static String encode(String input, HashMap<Character, String> huffmanCodes) {
        StringBuilder encodedData = new StringBuilder();
        for (char character : input.toCharArray()) {
            encodedData.append(huffmanCodes.get(character));
        }
        return encodedData.toString();
    }

    public static String decode(String encodedData, HuffBaseNode root) {
        StringBuilder decodedData = new StringBuilder();
        HuffBaseNode current = root;
        for (char bit : encodedData.toCharArray()) {
            if (bit == '0') {
                current = current.left();
            } else {
                current = current.right();
            }
            if (current.isLeaf()) {
                decodedData.append(((HuffLeafNode) current).value());
                current = root;
            }
        }
        return decodedData.toString();
    }

    public static void main(String[] args) {
        String input = "this is an example for huffman encoding";

        FrequencyTable frequencyTable = new FrequencyTable();
        for (char character : input.toCharArray()) {
            if (frequencyTable.getTable().containsKey(character)) {
                frequencyTable.add(character);
            } else {
                frequencyTable.add(character);
            }
        }

        HuffBaseNode root = buildHuffmanTree(frequencyTable);
        HashMap<Character, String> huffmanCodes = new HashMap<Character, String>();
        StringBuilder code = new StringBuilder();
        buildHuffmanCodes(root, code, huffmanCodes);

        String encodedData = encode(input, huffmanCodes);
        System.out.println("Encoded data: " + encodedData);

        String decodedData = decode(encodedData, root);
        System.out.println("Decoded data: " + decodedData);
    }
}
