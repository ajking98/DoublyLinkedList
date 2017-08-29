/**
 * Your implementation of a DoublyLinkedList
 *
 * @author Ahmed Gedi=
 * @userid agedi3
 * @GTID 903197142
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) throws
            IndexOutOfBoundsException, IllegalArgumentException {

        checkIndexOutOfBoundException(index);

        checkIllegalArgumentException(data);

        LinkedListNode newNode = new LinkedListNode(data);

        for (int i = 0; i < size; i++) {

        }

    }

    @Override
    public void addToFront(T data) throws IllegalArgumentException {
        checkIllegalArgumentException(data);

        LinkedListNode newFrontNode = new LinkedListNode(data);
        if (size == 0) {
            head = newFrontNode;
            tail = newFrontNode;
        } else {
            newFrontNode.setNext(head);
            newFrontNode.setPrevious(null);
            head = newFrontNode;
        }
        incrementSizeVariable();
    }

    @Override
    public void addToBack(T data) {
        checkIllegalArgumentException(data);

        LinkedListNode newBackNode = new LinkedListNode(data);
        if (size == 0) {
            head = newBackNode;
            tail = newBackNode;
        } else {
            tail.setNext(newBackNode);
            newBackNode.setPrevious(tail);
            tail = newBackNode;
            newBackNode.setNext(null);
        }
        incrementSizeVariable();
    }


    @Override
    public T removeAtIndex(int index) {
        checkIndexOutOfBoundException(index);

        decrementSizeVariable();
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }
        LinkedListNode nodeToRemove = head;
        nodeToRemove.setNext(head);
        head.setPrevious(null);
        decrementSizeVariable();
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        }

        decrementSizeVariable();
    }

    @Override
    public boolean removeFirstOccurrence(T data) {
        checkIllegalArgumentException(data);


    }

    @Override
    public T get(int index) {
        checkIndexOutOfBoundException(index);


    }

    @Override
    public Object[] toArray() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {

    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     *
     * @param index the index to test if it is out of bounds of the linked list
     */
    private void checkIndexOutOfBoundException(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     *
     * @param data the data to check if it is a illegal argument and if so
     *             then it will return null
     */
    private void checkIllegalArgumentException(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * increments the size variable
     */
    private void incrementSizeVariable() {
        size++;
    }

    /**
     * decrements the size variable
     */
    private void decrementSizeVariable() {
        size--;
    }
}
