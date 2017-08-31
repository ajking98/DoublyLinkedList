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
    public void addAtIndex(int index, T data) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (data == null) {
            throw new IllegalArgumentException();
        }

        if (index == 0) {
            LinkedListNode<T> newNode = new LinkedListNode(data);
            newNode.setNext(head);
            head = newNode;
        } else if (index == size) {
            LinkedListNode newNode = new LinkedListNode(data);
            tail.setNext(newNode);
            tail = newNode;
        } else {
            LinkedListNode newNode = new LinkedListNode(data);
            int halfPoint = size / 2;
            if (halfPoint >= index) {
                // Traverse from the tail
                LinkedListNode p = tail;
                for (int i = size - 1; i > index; i--) {
                    p.setPrevious(p);
                }
                p.setPrevious(newNode);
                newNode.setNext(p);
            } else {
                LinkedListNode p = head;
                for (int i = 0; i < index; i++) {
                    p.setPrevious(p);
                }
                p.setPrevious(newNode);
                newNode.setNext(p);
                // Traverse from the head
            }
        }




        incrementSizeVariable();
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        LinkedListNode<T> newFrontNode = new LinkedListNode<T>(data);
        if (size == 0) {
            head = newFrontNode;
            tail = newFrontNode;
        } else {
//            LinkedListNode<T> nodeToPush = head;
//            newFrontNode.setNext(nodeToPush);
//            newFrontNode = head;
            head.setPrevious(newFrontNode);
            newFrontNode.setNext(head);
            head = newFrontNode;
            newFrontNode.setPrevious(null);
        }
        incrementSizeVariable();
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        decrementSizeVariable();

        return null;
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }


        LinkedListNode<T> tmp = head;
        System.out.print(tmp.getData());
        head = head.getNext();
        System.out.println(head.getData());
        head.setPrevious(null);
//        for (int i = 0; i < size ; i++ ) {
//            System.out.println(head.getData());
//            head = head.getNext();
//        }
        decrementSizeVariable();
//        System.out.println(tmp.getData());
        return head.getData();
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        }
        T data = tail.getData();
        LinkedListNode newTail = tail.getPrevious();
        newTail = tail;


        decrementSizeVariable();
        System.out.println(data);
        return data;

    }

    @Override
    public boolean removeFirstOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        return false;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index you used was " +
                    "either a negative number or greater than the size of the" +
                    " list");
        }
        LinkedListNode curr = head;
        if (index == 0) {
            return head.getData();
        } else if (index == (size - 1)) {
            return tail.getData();
        } else {
            for (int i = 0; i <= index ; i++ ) {
                curr = curr.getNext();
            }
        }

        return (T) curr.getData();
    }

    @Override
    public Object[] toArray() {
        Object[] arrayVersion = new Object[size];
        LinkedListNode node = head;
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
