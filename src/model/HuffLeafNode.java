package model;


public class HuffLeafNode implements HuffBaseNode {
    private char element;
    private int weight;

    public HuffLeafNode(char element, int weight) {
        this.element = element;
        this.weight = weight;
    }

    public char value() {
        return element;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public int weight() {
        return weight;
    }

    @Override
    public HuffBaseNode left() {
        return null;
    }

    @Override
    public HuffBaseNode right() {
        return null;
    }
}
