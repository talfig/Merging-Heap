/**
 * The SortedHeap class implements a sorted heap using a doubly linked list.
 * Each heap contains nodes with integer values, and provides various methods to manipulate the heap.
 */
public class SortedHeap extends Heap {
    private Node head; // Pointer to the head node of the heap
    private Node tail; // Pointer to the tail node of the heap

    /**
     * Constructs an empty SortedHeap.
     */
    public SortedHeap() {
        head = null;
        tail = null;
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
     * Returns a string representation of the heap.
     *
     * @return a string representation of the heap
     */
    public String toString() {
        if (head == null)
            return "[]";

        Node ptr = head.getNext();
        String str = "[" + head.getValue(); // String representation of the heap
        while (ptr != null) {
            str += ", " + ptr.getValue(); // Appending values to the string
            ptr = ptr.getNext();
        }
        str += "]";

        return str; // Returning the string representation
    }

    /**
     * Creates a copy of the current heap.
     *
     * @return a copy of the current heap
     */
    private SortedHeap copyHeap() {
        SortedHeap heap = new SortedHeap();

        Node ptr = head;
        while (ptr != null) {
            heap.insert(ptr.getValue()); // Inserting each value into the new heap
            ptr = ptr.getNext();
        }

        return heap; // Returning the copy of the heap
    }

    /**
     * Deletes all nodes in the heap.
     */
    private void delHeap() {
        if (head == null)
            return;

        Node ptr = head;
        while (ptr.getNext() != null) {
            Node tmp = ptr.getNext();
            ptr.setNext(null);
            tmp.setPrev(null);
            ptr = tmp;
        }

        head = null;
        tail = null;
    }

    /**
     * Searches for the first node with a value greater than or equal to the specified value.
     *
     * @param value the value to search for
     * @return the node with the specified value or the next higher value, or null if not found
     */
    private Node search(int value) {
        Node ptr = head;
        // Loop until the end of the heap or a value greater than or equal to the specified value is found
        while (ptr != null && ptr.getValue() < value)
            ptr = ptr.getNext();

        return ptr;
    }

    /**
     * Inserts a new value into the heap in a sorted order.
     *
     * @param value the value to be inserted
     */
    @Override
    public void insert(int value) {
        Node node = new Node(value); // Creating a new node with the specified value

        if (head == null) { // If the heap is empty
            head = node;
            tail = node;
            return;
        }

        Node indicator = search(value); // Finding the position to insert the new value
        if (indicator == null) { // If the new value is greater than all existing values
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
            return;
        }

        if (indicator == head) { // If the new value is less than all existing values
            head.setPrev(node);
            node.setNext(head);
            head = node;
            return;
        }

        Node tmp = indicator.getPrev();
        tmp.setNext(node);
        node.setPrev(tmp);
        indicator.setPrev(node);
        node.setNext(indicator);
    }

    /**
     * Builds a heap from an array of integers, sorting the array first.
     *
     * @param arr the array of integers
     */
    @Override
    public void makeHeap(int[] arr) {
        delHeap(); // Clearing the existing heap
        ArrayUtils.mergeSort(arr); // Sorting the array

        head = new Node(arr[0]);
        Node ptr1 = head;
        for (int i = 1; i < arr.length; i++) { // Looping through the array to create nodes and link them
            Node tmp = new Node(arr[i]);
            ptr1.setNext(tmp);
            tmp.setPrev(ptr1);
            ptr1 = tmp;
        }
    }

    /**
     * Returns the minimum value in the heap.
     *
     * @return the minimum value in the heap, or Integer.MAX_VALUE if the heap is empty
     */
    @Override
    public int getMinVal() {
        if (head != null)
            return head.getValue();
        return Integer.MAX_VALUE;
    }

    /**
     * Removes and returns the minimum value in the heap.
     *
     * @return the minimum value in the heap, or Integer.MAX_VALUE if the heap is empty
     */
    @Override
    public int extractMin() {
        if (head == null)
            return Integer.MAX_VALUE;

        int minVal = head.getValue();

        head = head.getNext();
        if (head == null) { // If the heap becomes empty after removing the minimum value
            tail = null;
            return minVal;
        }

        Node tmp = head.getPrev();
        tmp.setNext(null);
        head.setPrev(null);

        return minVal;
    }

    /**
     * Merges the current heap with another heap into this heap.
     *
     * @param other the other heap to be merged
     */
    @Override
    public void unionHeap(SortedHeap other) {
        SortedHeap unionHeap = new SortedHeap();
        Node ptr1 = head; // Pointer to traverse the current heap
        Node ptr2 = other.getHead(); // Pointer to traverse the other heap

        // Loop until reaching the end of either heap
        while (ptr1 != null && ptr2 != null) {
            if (ptr1.getValue() < ptr2.getValue()) { // If value in current heap is smaller
                unionHeap.insert(ptr1.getValue());
                ptr1 = ptr1.getNext();
            } else { // If value in other heap is smaller or equal
                unionHeap.insert(ptr2.getValue());
                ptr2 = ptr2.getNext();
            }
        }

        // Add remaining nodes from current heap
        while (ptr1 != null) {
            unionHeap.insert(ptr1.getValue());
            ptr1 = ptr1.getNext();
        }

        // Add remaining nodes from other heap
        while (ptr2 != null) {
            unionHeap.insert(ptr2.getValue());
            ptr2 = ptr2.getNext();
        }

        // Clearing both heaps
        delHeap();
        other.delHeap();

        // Copy the union heap into this heap
        head = unionHeap.head;
        tail = unionHeap.tail;
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
        while (head != null) // Loop until the heap is empty
            System.out.print(", " + extractMin());
        System.out.println("]");
    }
}
