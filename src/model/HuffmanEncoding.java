import java.util.Map;

public class HuffmanEncoding {

    public static String encodeMessage(String message, Map<Character, String> huffmanCodes) {
        StringBuilder encodedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            String code = huffmanCodes.get(c);
            encodedMessage.append(code);
        }
        return encodedMessage.toString();
    }

    public static String decodeMessage(String encodedMessage, HuffBaseNode root) {
        StringBuilder decodedMessage = new StringBuilder();
        HuffBaseNode currentNode = root;
        int index = 0;

        while (index < encodedMessage.length()) {
            if (currentNode.isLeaf()) {
                decodedMessage.append(((HuffLeafNode) currentNode).value());
                currentNode = root;
            } else {
                char bit = encodedMessage.charAt(index);
                if (bit == '0') {
                    currentNode = ((HuffInternalNode) currentNode).left();
                } else if (bit == '1') {
                    currentNode = ((HuffInternalNode) currentNode).right();
                }
                index++;
            }
        }

        decodedMessage.append(((HuffLeafNode) currentNode).value());

        return decodedMessage.toString();
    }

}
