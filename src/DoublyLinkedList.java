/**
 * Your implementation of a DoublyLinkedList
 *
 * @author Ahmed Gedi
 * @userid agedi3
 * @GTID 903197142
 * @version 1.44
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {

        checkForIndexOutOfBoundsForAdding(index);

        checkForIllegalArgumentException(data);

        if (isEqualToZero(index)) {
            addToFront(data);
        } else if (isEqualToSize(index)) {
            addToBack(data);
        } else {
            addAtIndexEfficientlyByTraversing(index, data);
            incrementSizeVariable();
        }

    }

    @Override
    public void addToFront(T data) {
        checkForIllegalArgumentException(data);

        LinkedListNode<T> newFrontNode = new LinkedListNode<T>(data);

        if (isEqualToZero(size)) {
            addNodeToEmptyList(newFrontNode);
        } else {
            createNewNodeAsHead(newFrontNode);
        }

        incrementSizeVariable();
    }

    @Override
    public void addToBack(T data) {
        checkForIllegalArgumentException(data);

        LinkedListNode<T> newBackNode = new LinkedListNode<T>(data);

        if (isEqualToZero(size)) {
            addNodeToEmptyList(newBackNode);
        } else {
            createNewNodeAsTail(newBackNode);
        }

        incrementSizeVariable();
    }

    @Override
    public T removeAtIndex(int index) {
        checkForIndexOutOfBounds(index);

        if (isEqualToZero(index)) {
            return removeFromFront();
        }
        if (index == size - 1) {
            return removeFromBack();
        }

        return removeAtIndexEfficientlyByTraversing(index);


    }

    @Override
    public T removeFromFront() {
        if (checkIfEmpty()) return null;

        T headData = deleteFirstNode();

        decrementSizeVariable();

        if (isEqualToZero(size)) clear();

        return headData;
    }

    @Override
    public T removeFromBack() {
        if (checkIfEmpty()) return null;

        T tailData = deleteLastNode();

        decrementSizeVariable();
        
        if (isEqualToZero(size)) clear();

        return tailData;


    }

    private T deleteLastNode() {
        LinkedListNode<T> node = createNodeTail();
        tail = node.getPrevious();
        T tailData = node.getData();
        if (tail != null) {
            tail.setNext(null);
        }
        return tailData;
    }

    private LinkedListNode<T> createNodeTail() {
        return tail;
    }

    @Override
    public boolean removeFirstOccurrence(T data) {
        checkForIllegalArgumentException(data);

        LinkedListNode<T> node = createNodeHead();
        for (int i = 0; i < size; i++) {
            if (node.getData().equals(data)) {
                if (isEqualToZero(i)) {
                    removeFromFront();
                } else if (i == size - 1) {
                    removeFromBack();
                } else {
                    removeAtIndex(i);
                }
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    @Override
    public T get(int index) {
        checkForIndexOutOfBounds(index);

        if (index < size / 2) {
            LinkedListNode<T> node = createNodeHead();
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    return node.getData();
                }
                node = node.getNext();
            }
        } else {
            LinkedListNode<T> node = createNodeTail();
            for (int i = size - 1; i >= 0; i--) {
                if (i == index) {
                    return node.getData();
                }
                node = node.getPrevious();
            }
        }
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] arrayVersion = new Object[size];
        LinkedListNode<T> node = createNodeHead();
        for (int i = 0; i < size(); i++) {
            arrayVersion[i] = node.getData();
            node = node.getNext();
        }

        return arrayVersion;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        addNodeToEmptyList(null);
        size = 0;
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
     * @param index the index to grab for either addAtIndex or get or
     *              removeAtIndex
     * @return a node to place or remove
     */
    private LinkedListNode<T> optimizedTraversal(int index) {
        int distFromFront = index + 1;
        int distFromBack = size - index;
        if (distFromFront < distFromBack) {
            LinkedListNode<T> node = createNodeHead();
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
            return node;
        } else if (distFromBack < distFromFront) {
            LinkedListNode<T> node = createNodeTail();
            for (int i = size - 1; i > index; i--) {
                node = node.getPrevious();
            }
            return node;
        } else {
            LinkedListNode<T> node = createNodeHead();
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
            return node;
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

    /**
     *
     * @param index the index to check for the bounds
     * @param s the custom string message
     */
    private void checkForIndexOutOfBoundsForAdding(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of "
                    + "the possible range. Please request an index within the "
                    + "bounds.");
        }
    }

    /**
     *
     * @param data the data to check for an illegal argument
     * @param s the custom string message
     */
    private void checkForIllegalArgumentException(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null cannot be an argument, "
                    + "please put in another argument.");
        }
    }

    /**
     *
     * @param index
     * @param data
     */
    private void addAtIndexEfficientlyByTraversing(int index, T data) {
        LinkedListNode<T> node = optimizedTraversal(index);
        LinkedListNode<T> newNode = new LinkedListNode<T>(data, node
                .getPrevious(), node);
        node.getPrevious().setNext(newNode);
        node.setPrevious(newNode);
    }

    /**
     *
     * @param index
     * @return
     */
    private boolean isEqualToZero(int index) {
        return index == 0;
    }

    /**
     *
     * @param index
     * @return
     */
    private boolean isEqualToSize(int index) {
        return index == size;
    }

    /**
     *
     * @param newFrontNode
     */
    private void addNodeToEmptyList(LinkedListNode<T> newFrontNode) {
        head = newFrontNode;
        tail = newFrontNode;
    }

    /**
     *
     * @param newFrontNode
     */
    private void createNewNodeAsHead(LinkedListNode<T> newFrontNode) {
        head.setPrevious(newFrontNode);
        newFrontNode.setNext(head);
        head = newFrontNode;
        newFrontNode.setPrevious(null);
    }

    /**
     *
     * @param newBackNode
     */
    private void createNewNodeAsTail(LinkedListNode<T> newBackNode) {
        tail.setNext(newBackNode);
        newBackNode.setPrevious(tail);
        tail = newBackNode;
        newBackNode.setNext(null);
    }

    /**
     *
     * @param index
     */
    private void checkForIndexOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index you placed as the "
                    + "parameter is either negative or greater than the size "
                    + "of the list.");
        }
    }

    /**
     *
     * @param index
     * @return
     */
    private T removeAtIndexEfficientlyByTraversing(int index) {
        LinkedListNode<T> nodeToRemove = optimizedTraversal(index);
        nodeToRemove.getPrevious().setNext(nodeToRemove.getNext());
        nodeToRemove.getNext().setPrevious(nodeToRemove.getPrevious());
        decrementSizeVariable();
        return nodeToRemove.getData();
    }

    private boolean checkIfEmpty() {
        if (isEmpty()) {
            return true;
        }
        return false;
    }

    private T deleteFirstNode() {
        LinkedListNode<T> node = createNodeHead();
        head = node.getNext();
        T headData = node.getData();
        if (head != null) head.setPrevious(null);
        return headData;
    }

    private LinkedListNode<T> createNodeHead() {
        return head;
    }
}
