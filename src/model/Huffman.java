package model;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Huffman {
    // Attribute to keep track of the root node
    private static HuffBaseNode rootNode;
    
    // Method to build the Huffman tree from a frequency table
public static HuffBaseNode buildHuffmanTree(FrequencyTable frequencyTable) {
    // default min heap size will be the size of the alphabet
    MinHeap minHeap = new MinHeap(26);
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
    // Set the root node for future decoding
    rootNode = minHeap.removeMin();
    return rootNode;
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

     // Method to encode a string using Huffman codes
    public static String encode(String input) {
        HashMap<Character, String> huffmanCodes = new HashMap<>();
        StringBuilder encodedData = new StringBuilder();
        FrequencyTable frequencyTable = new FrequencyTable();
        for (char character : input.toCharArray()) {
            frequencyTable.add(character);
        }
        HuffBaseNode root = buildHuffmanTree(frequencyTable);
        StringBuilder code = new StringBuilder();
        buildHuffmanCodes(root, code, huffmanCodes);
        for (char character : input.toCharArray()) {
            encodedData.append(huffmanCodes.get(character));
        }
        return encodedData.toString();
    }

    // Method to decode a string using the root node
    public static String decode(String encodedData) {
        StringBuilder decodedData = new StringBuilder();
        HuffBaseNode current = rootNode;
        for (char bit : encodedData.toCharArray()) {
            if (bit == '0') {
                current = current.left();
            } else {
                current = current.right();
            }
            if (current.isLeaf()) {
                decodedData.append(((HuffLeafNode) current).value());
                current = rootNode;
            }
        }
        return decodedData.toString();
    }

    public static void encodeFile(String inputFileName, String outputFileName) {
        try (FileReader fileReader = new FileReader(inputFileName);
             FileWriter fileWriter = new FileWriter(outputFileName);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {

            // Read the content of the file
            StringBuilder inputContent = new StringBuilder();
            int currentChar;
            while ((currentChar = fileReader.read()) != -1) {
                inputContent.append((char) currentChar);
            }

            // Build the frequency table
            FrequencyTable frequencyTable = new FrequencyTable();
            for (char character : inputContent.toString().toCharArray()) {
                frequencyTable.add(character);
            }

            // Build Huffman tree
            HuffBaseNode root = buildHuffmanTree(frequencyTable);

            // Build Huffman codes
            HashMap<Character, String> huffmanCodes = new HashMap<>();
            buildHuffmanCodes(root, new StringBuilder(), huffmanCodes);

            // Encode the content
            String encodedData = encode(inputContent.toString());

            // Write the encoded data to the output file
            writer.write(encodedData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decodeFile(String inputFileName, String outputFileName) {
        try (
            FileReader fileReader = new FileReader(inputFileName);
            FileWriter fileWriter = new FileWriter(outputFileName);
            BufferedWriter writer = new BufferedWriter(fileWriter)) {
            // Read the encoded content from the file
            StringBuilder encodedContent = new StringBuilder();
            int currentChar;
            while ((currentChar = fileReader.read()) != -1) {
                encodedContent.append((char) currentChar);
            }
            // Decode the content using the provided root
            String decodedData = decode(encodedContent.toString());
            // Write the decoded data to the output file
            writer.write(decodedData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
