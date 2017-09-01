/**
 * Your implementation of a DoublyLinkedList
 *
 * @author Ahmed Gedi=
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

        checkForIndexOutOfBoundsForAdding(index, "Index is out of "
                + "the possible range. Please request an index within the "
                + "bounds.");
        if (data == null) {
            throw new IllegalArgumentException("Null cannot be an argument, "
                    + "please put in another argument.");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> node = optimizedTraversal(index);
            LinkedListNode<T> newNode = new LinkedListNode<T>(data, node
                    .getPrevious(), node);
            node.getPrevious().setNext(newNode);
            node.setPrevious(newNode);
            incrementSizeVariable();
        }

    }

    @Override
    public void addToFront(T data) {
        checkForIllegalArgumentException(data, "The data you inputed was null"
                + " and that is not what is allowed.");

        LinkedListNode<T> newFrontNode = new LinkedListNode<T>(data);
        if (size == 0) {
            head = newFrontNode;
            tail = newFrontNode;
        } else {
            head.setPrevious(newFrontNode);
            newFrontNode.setNext(head);
            head = newFrontNode;
            newFrontNode.setPrevious(null);
        }
        incrementSizeVariable();
    }

    @Override
    public void addToBack(T data) {
        checkForIllegalArgumentException(data, "The data you inputed was null"
                + " and that is not what is allowed.");

        LinkedListNode<T> newBackNode = new LinkedListNode<T>(data);
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index you placed as the "
                    + "parameter is either negative or greater than the size "
                    + "of the list.");
        } else {
            if (index == 0) {
                return removeFromFront();
            } else if (index == size - 1) {
                return removeFromBack();
            } else {
                LinkedListNode<T> nodeToRemove = optimizedTraversal(index);
                nodeToRemove.getPrevious().setNext(nodeToRemove.getNext());
                nodeToRemove.getNext().setPrevious(nodeToRemove.getPrevious());
                decrementSizeVariable();
                return nodeToRemove.getData();
            }
        }

    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        } else {
            LinkedListNode<T> headNode = head;
            head = headNode.getNext();
            T headData = headNode.getData();
            if (head != null) {
                head.setPrevious(null);
            }
            decrementSizeVariable();
            if (size == 0) {
                clear();
            }
            return headData;
        }
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        } else {
            LinkedListNode<T> tailNode = tail;
            tail = tailNode.getPrevious();
            T tailData = tailNode.getData();
            if (tail != null) {
                tail.setNext(null);
            }
            decrementSizeVariable();
            if (size == 0) {
                clear();
            }
            return tailData;
        }

    }

    @Override
    public boolean removeFirstOccurrence(T data) {
        checkForIllegalArgumentException(data, "The data you inputted was null"
                + " and that is not what is allowed.");
        LinkedListNode<T> node = head;
        for (int i = 0; i < size; i++) {
            if (node.getData().equals(data)) {
                if (i == 0) {
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index you used was "
                    + "either a negative number or greater than the size of the"
                    + " list");
        }

        if (index < size / 2) {
            LinkedListNode<T> currentNode = head;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    return currentNode.getData();
                }
                currentNode = currentNode.getNext();
            }
        } else {
            LinkedListNode<T> currentNode = tail;
            for (int i = size - 1; i >= 0; i--) {
                if (i == index) {
                    return currentNode.getData();
                }
                currentNode = currentNode.getPrevious();
            }
        }
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] arrayVersion = new Object[size];
        LinkedListNode<T> node = head;
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
        head = null;
        tail = null;
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
            LinkedListNode<T> node = head;
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
            return node;
        } else if (distFromBack < distFromFront) {
            LinkedListNode<T> node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.getPrevious();
            }
            return node;
        } else {
            LinkedListNode<T> node = head;
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
    private void checkForIndexOutOfBoundsForAdding(int index, String s) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(s);
        }
    }

    /**
     *
     * @param data the data to check for an illegal argument
     * @param s the custom string message
     */
    private void checkForIllegalArgumentException(T data, String s) {
        if (data == null) {
            throw new IllegalArgumentException(s);
        }
    }
}
