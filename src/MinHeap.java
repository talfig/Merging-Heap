/**
 * The MinHeap class implements a minimum heap using a doubly linked list.
 * Each heap contains nodes with integer values, and provides various methods to manipulate the heap.
 */
public class MinHeap extends Heap {
    private Node head; // The head (first node) of the doubly linked list representing the heap
    private Node tail; // The tail (last node) of the doubly linked list representing the heap
    private Node min;  // The node with the minimum value in the heap

    /**
     * Constructs an empty MinHeap.
     */
    public MinHeap() {
        head = null;
        tail = null;
        min = null;
    }

    /**
     * Returns the head node of the heap.
     *
     * @return the head node of the heap
     */
    public Node getHead() {
        return head;
    }

    /**
     * Sets the head node of the heap.
     *
     * @param head the new head node
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * Returns the tail node of the heap.
     *
     * @return the tail node of the heap
     */
    public Node getTail() {
        return tail;
    }

    /**
     * Sets the tail node of the heap.
     *
     * @param tail the new tail node
     */
    public void setTail(Node tail) {
        this.tail = tail;
    }

    /**
     * Returns the node with the minimum value in the heap.
     *
     * @return the node with the minimum value
     */
    public Node getMin() {
        return min;
    }

    /**
     * Sets the node with the minimum value in the heap.
     *
     * @param min the new minimum node
     */
    public void setMin(Node min) {
        this.min = min;
    }

    /**
     * Returns a string representation of the heap.
     *
     * @return a string representation of the heap
     */
    public String toString() {
        if (head == null) // If the heap is empty
            return "[]";

        Node ptr = head.getNext(); // Start from the second node
        String str = "[" + head.getValue(); // Initialize string with the value of the head node
        while (ptr != null) { // Traverse the list
            str += ", " + ptr.getValue(); // Append each node's value to the string
            ptr = ptr.getNext();
        }
        str += "]";

        return str; // Return the string representation of the heap
    }

    /**
     * Creates a copy of the current heap.
     *
     * @return a copy of the current heap
     */
    private MinHeap copyHeap() {
        MinHeap heap = new MinHeap(); // Create a new MinHeap object

        Node ptr = head;
        while (ptr != null) { // Traverse the list
            heap.insert(ptr.getValue()); // Insert each node's value into the new heap
            ptr = ptr.getNext();
        }

        return heap; // Return the copied heap
    }

    /**
     * Deletes all nodes in the heap.
     */
    private void delHeap() {
        if (head == null) // If the heap is empty
            return; // Nothing to delete

        Node ptr = head;
        while (ptr.getNext() != null) { // Traverse the list
            Node tmp = ptr.getNext();
            ptr.setNext(null); // Disconnect the current node from the next node
            tmp.setPrev(null); // Disconnect the next node from the current node
            ptr = tmp; // Move to the next node
        }

        head = null;
        tail = null;
        min = null;
    }

    /**
     * Inserts a new value into the heap.
     *
     * @param value the value to be inserted
     */
    @Override
    public void insert(int value) {
        Node node = new Node(value); // Create a new node with the given value

        if (head == null) { // If the heap is empty
            head = node;
            tail = node;
            min = node;
            return;
        }

        tail.setNext(node); // Append the new node to the end of the list
        node.setPrev(tail); // Set the new node's previous pointer to the current tail
        tail = node; // Update the tail to the new node

        if (min.getValue() > node.getValue()) // If the new node's value is less than the current min
            min = node;
    }

    /**
     * Builds a heap from an array of integers.
     *
     * @param arr the array of integers
     */
    @Override
    public void makeHeap(int[] arr) {
        delHeap();

        for (int i = 0; i < arr.length; i++) // Iterate through the array
            insert(arr[i]); // Insert each element into the heap
    }

    /**
     * Returns the minimum value in the heap.
     *
     * @return the minimum value in the heap, or Integer.MAX_VALUE if the heap is empty
     */
    @Override
    public int getMinVal() {
        if (min != null)
            return min.getValue();
        return Integer.MAX_VALUE; // If the heap is empty
    }

    /**
     * Removes and returns the minimum value in the heap.
     *
     * @return the minimum value in the heap, or Integer.MAX_VALUE if the heap is empty
     */
    @Override
    public int extractMin() {
        if (head == null) // If the heap is empty
            return Integer.MAX_VALUE;

        int minVal = min.getValue();
        Node ptr1 = min.getPrev();
        Node ptr2 = min.getNext();
        min.setPrev(null);
        min.setNext(null);

        if (ptr1 != null) // If min is not the head node
            ptr1.setNext(ptr2); // Disconnect min from the previous node
        else
            head = ptr2; // Update head if min was the head node

        if (ptr2 != null) // If min is not the tail node
            ptr2.setPrev(ptr1); // Disconnect min from the next node

        if (head == null) { // If the heap is now empty
            tail = null;
            min = null;
            return minVal;
        }

        Node ptr = head;
        min = head;
        while (ptr != null) { // Traverse the list
            if (ptr.getValue() < min.getValue()) // If a node with a smaller value is found
                min = ptr;
            ptr = ptr.getNext();
        }

        return minVal;
    }

    /**
     * Merges the current heap with another heap into this heap.
     *
     * @param other the other heap to be merged
     */
    @Override
    public void unionHeap(MinHeap other) {
        MinHeap unionHeap = null;
        if (head != null && other.head != null) { // If both heaps are not empty
            tail.setNext(other.head);
            other.head.setPrev(tail);
            unionHeap = copyHeap();
        } else if (head != null) // If only the current heap is not empty
            return;
        else // If only the other heap is not empty
            unionHeap = other.copyHeap();

        // Clearing both heaps
        delHeap();
        other.delHeap();

        // Copy the union heap into this heap
        head = unionHeap.head;
        tail = unionHeap.tail;
        min = unionHeap.min;
    }

    /**
     * Prints the heap in sorted order.
     * At the end, the heap is empty.
     */
    @Override
    public void sortHeap() {
        if (head == null) { // If the heap is empty
            System.out.println("[]");
            return;
        }

        System.out.print("[" + extractMin());
        while (head != null) // While there are still elements in the heap
            System.out.print(", " + extractMin());
        System.out.println("]");
    }
}
