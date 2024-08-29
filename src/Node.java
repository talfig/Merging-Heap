/**
 * The Node class represents a node in a doubly linked list.
 * Each node contains an integer value, a reference to the previous node, and a reference to the next node.
 */
public class Node {
    private int value;
    private Node prev;
    private Node next;

    /**
     * Constructs a new node with the specified value.
     *
     * @param value the integer value to be stored in the node
     */
    public Node(int value) {
        this.value = value;
        prev = null;
        next = null;
    }

    /**
     * Returns the value stored in the node.
     *
     * @return the integer value of the node
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value stored in the node.
     *
     * @param value the new integer value to be stored in the node
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Returns the previous node.
     *
     * @return the previous node, or null if there is no previous node
     */
    public Node getPrev() {
        return prev;
    }

    /**
     * Sets the previous node.
     *
     * @param prev the new previous node
     */
    public void setPrev(Node prev) {
        this.prev = prev;
    }

    /**
     * Returns the next node.
     *
     * @return the next node, or null if there is no next node
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets the next node.
     *
     * @param next the new next node
     */
    public void setNext(Node next) {
        this.next = next;
    }
}