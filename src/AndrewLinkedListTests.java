import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * This is my set of tests for the linked list class. I included the javadoc
 * from the interface to remind myself the contract I am testing for.
 *
 * I also included a method to test that the list is valid
 * (node.next.prev == node, size == number of nodes, etc)
 *
 * @author Andrew Wittenmyer
 * @version 1.0
 */
public class AndrewLinkedListTests {
    private LinkedListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new DoublyLinkedList<String>();
    }

    /*
     * Adds the element to the index specified.
     *
     * Adding to indices 0 and {@code size} should be O(1), all other cases are
     * O(n).
     *
     * @param index The requested index for the new element.
     * @param data The data for the new element.
     * @throws java.lang.IndexOutOfBoundsException if index is negative or
     * index > size.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        // check null data error
        try {
            list.addAtIndex(0, null);
            fail();
        } catch (Exception e) {
            assertEquals(java.lang.IllegalArgumentException.class, e.getClass());
        }

        // check add to front
        list.addAtIndex(0, "a1");
        Object[] expected1 = {"a1"};
        checkValidList(expected1);

        list.addAtIndex(0, "a0");
        Object[] expected2 = {"a0", "a1"};
        checkValidList(expected2);

        // check add to back
        list.addAtIndex(2, "a2");
        Object[] expected3 = {"a0", "a1", "a2"};
        checkValidList(expected3);

        list.addAtIndex(3, "a3");
        Object[] expected4 = {"a0", "a1", "a2", "a3"};
        checkValidList(expected4);

        // check add to middle
        list.addAtIndex(2, "foo1");
        Object[] expected5 = {"a0", "a1", "foo1", "a2", "a3"};
        checkValidList(expected5);

        list.addAtIndex(1, "foo2");
        Object[] expected6 = {"a0", "foo2", "a1", "foo1", "a2", "a3"};
        checkValidList(expected6);

        list.addAtIndex(5, "foo3");
        Object[] expected7 = {"a0", "foo2", "a1", "foo1", "a2", "foo3", "a3"};
        checkValidList(expected7);

        list.addAtIndex(3, "foo4");
        Object[] expected8 = {"a0", "foo2", "a1", "foo4", "foo1", "a2", "foo3", "a3"};
        checkValidList(expected8);

        // check index error before and after list size
        try {
            list.removeAtIndex(-1);
            fail();
        } catch (Exception e) {
            assertEquals(java.lang.IndexOutOfBoundsException.class, e.getClass());
        }
        try {
            list.removeAtIndex(9);
            fail();
        } catch (Exception e) {
            assertEquals(java.lang.IndexOutOfBoundsException.class, e.getClass());
        }

    }

    /*
     * Adds the element to the front of the list. Make sure to update head.
     *
     * Must be O(1) for all cases.
     *
     * @param data The data for the new element.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        // check null data error
        try {
            list.addToFront(null);
            fail();
        } catch (Exception e) {
            assertEquals(java.lang.IllegalArgumentException.class, e.getClass());
        }

        // fill list
        for (int i = 0; i < 10; i++) {
            list.addToFront("a" + i);
        }
        Object[] fullExpected = {"a9", "a8", "a7", "a6", "a5", "a4", "a3", "a2", "a1", "a0"};
        checkValidList(fullExpected);
    }

    /*
     * Adds the element to the back of the list. Make sure to update tail.
     *
     * Must be O(1) for all cases.
     *
     * @param data The data for the new element.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        // check null data error
        try {
            list.addToBack(null);
            fail();
        } catch (Exception e) {
            assertEquals(java.lang.IllegalArgumentException.class, e.getClass());
        }

        // fill list
        for (int i = 0; i < 10; i++) {
            list.addToBack("a" + i);
        }
        Object[] fullExpected = {"a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9"};
        checkValidList(fullExpected);
    }

    /*
     * Removes and returns the element from the index specified.
     *
     * Removing from indices 0 and size - 1 should be O(1), all other cases are
     * O(n).
     *
     * @param index The requested index to be removed.
     * @return The object formerly located at index.
     * @throws java.lang.IndexOutOfBoundsException if index is negative or
     * index >= size.
     */
    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        // check error for empty list
        try {
            list.removeAtIndex(0);
            fail();
        } catch (Exception e) {
            assertEquals(java.lang.IndexOutOfBoundsException.class, e.getClass());
        }
        checkValidList(0);

        // check with single data element
        list.addToBack("a0");
        list.removeAtIndex(0);
        checkValidList(new Object[0]);

        // fill list
        for (int i = 0; i < 10; i++) {
            list.addToBack("a" + i);
        }
        checkValidList(10);

        // check remove from front
        list.removeAtIndex(0);
        Object[] expected1 = {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9"};
        checkValidList(expected1);

        // check remove from back
        list.removeAtIndex(8);
        Object[] expected2 = {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8"};
        checkValidList(expected2);

        // check remove from middle
        list.removeAtIndex(2);
        Object[] expected3 = {"a1", "a2", "a4", "a5", "a6", "a7", "a8"};
        checkValidList(expected3);

        // check index error before and after list size
        try {
            list.removeAtIndex(-1);
            fail();
        } catch (Exception e) {
            assertEquals(java.lang.IndexOutOfBoundsException.class, e.getClass());
        }
        try {
            list.removeAtIndex(7);
            fail();
        } catch (Exception e) {
            assertEquals(java.lang.IndexOutOfBoundsException.class, e.getClass());
        }
    }

    /*
     * Removes and returns the element at the front of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(1) for all cases.
     *
     * @return The object formerly located at the front.
     */
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        // check empty list
        assertNull(list.removeFromFront());
        checkValidList(0);

        // fill list
        for (int i = 0; i < 10; i++) {
            list.addToBack("a" + i);
        }
        checkValidList(10);

        Object[] fullExpected = {"a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9"};
        // remove all
        for (int i = 0; i < 10; i++) {
            assertEquals("a" + i, list.removeFromFront());
            checkValidList(Arrays.copyOfRange(fullExpected, i + 1, fullExpected.length));
        }

        // check empty list again
        assertNull(list.removeFromFront());
        checkValidList(0);
    }

    /*
     * Removes and returns the element at the back of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(1) for all cases.
     *
     * @return The object formerly located at the back.
     */
    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        // check empty list
        assertNull(list.removeFromBack());
        checkValidList(0);

        // fill list
        for (int i = 0; i < 10; i++) {
            list.addToFront("a" + i);
        }
        Object[] fullExpected = {"a9", "a8", "a7", "a6", "a5", "a4", "a3", "a2", "a1", "a0"};
        checkValidList(fullExpected);

        // remove all
        for (int i = 0; i < 10; i++) {
            assertEquals("a" + i, list.removeFromBack());
            checkValidList(Arrays.copyOfRange(fullExpected, 0, 9 - i));
        }

        // check empty list again
        assertNull(list.removeFromBack());
        checkValidList(0);
    }


    /*
     * Removes the first copy of the given data from the list.
     *
     * Must be O(n) for all cases.
     *
     * @param data The data to be removed from the list.
     * @return true if something was removed from the list; false otherwise.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    @Test(timeout = TIMEOUT)
    public void testRemoveFirstOccurrence() {
        // check error for null data
        try {
            list.removeFirstOccurrence(null);
            fail();
        } catch (Exception e) {
            assertEquals(java.lang.IllegalArgumentException.class, e.getClass());
        }

        // check on empty list
        assertFalse(list.removeFirstOccurrence("foo"));
        checkValidList(0);

        // fill list
        for (int i = 0; i < 10; i++) {
            list.addToBack("a" + i);
        }
        checkValidList(10);

        // check remove from front
        list.removeFirstOccurrence("a0");
        Object[] expected1 = {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9"};
        checkValidList(expected1);

        // check remove from back
        list.removeFirstOccurrence("a9");
        Object[] expected2 = {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8"};
        checkValidList(expected2);

        // check remove from middle
        list.removeFirstOccurrence("a3");
        Object[] expected3 = {"a1", "a2", "a4", "a5", "a6", "a7", "a8"};
        checkValidList(expected3);

        // check only remove one
        list.addToBack("a4");
        list.addToBack("a4");
        list.removeFirstOccurrence("a4");
        Object[] expected4 = {"a1", "a2", "a5", "a6", "a7", "a8", "a4", "a4"};
        checkValidList(expected4);

        // check removing a non-member of the list
        list.removeFirstOccurrence("foo");
        checkValidList(expected4);

        // check error for null data on a full list
        try {
            list.removeFirstOccurrence(null);
            fail();
        } catch (Exception e) {
            assertEquals(java.lang.IllegalArgumentException.class, e.getClass());
        }
    }

    /*
     * Returns the element at the specified index.
     *
     * Getting indices 0 and size - 1 should be O(1), all other cases are O(n).
     *
     * @param index The index of the requested element.
     * @return The object stored at index.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size.
     */
    @Test(timeout = TIMEOUT)
    public void testGet() {
        // check index error for empty list
        try {
            list.get(0);
            fail();
        } catch (Exception e) {
            assertEquals(java.lang.IndexOutOfBoundsException.class, e.getClass());
        }

        // fill list
        for (int i = 0; i < 10; i++) {
            list.addToBack("item" + i);
        }

        // check get on first, middle, last item cases
        for (int i = 0; i < 10; i++) {
            assertEquals("item" + i, list.get(i));
        }

        // check index error before and after list size
        try {
            list.get(-1);
            fail();
        } catch (Exception e) {
            assertEquals(java.lang.IndexOutOfBoundsException.class, e.getClass());
        }
        try {
            list.get(10);
            fail();
        } catch (Exception e) {
            assertEquals(java.lang.IndexOutOfBoundsException.class, e.getClass());
        }
    }

    /*
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return An array of length {@code size} holding all of the objects in
     * this list in the same order.
     */
    @Test(timeout = TIMEOUT)
    public void testToArray() {
        // check on empty list
        assertArrayEquals(new Object[0], list.toArray());

        // fill list
        for (int i = 0; i < 10; i++) {
            list.addToBack("a" + i);
        }
        checkValidList(10);

        // the full array
        Object[] expected1 = {"a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9"};
        assertArrayEquals(expected1, list.toArray());
        assertEquals(expected1.length, list.size());
    }

    /*
     * Returns a boolean value indicating if the list is empty.
     *
     * Must be O(1) for all cases.
     *
     * @return true if empty; false otherwise.
     */
    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(list.isEmpty());

        for (int i = 0; i < 10; i++) {
            list.addToBack("item" + i);
        }
        assertFalse(list.isEmpty());

        list.clear();
        assertTrue(list.isEmpty());
    }

    /*
     * Returns the number of elements in the list.
     *
     * Must be O(1) for all cases.
     *
     * @return The size of the list.
     */
    @Test(timeout = TIMEOUT)
    public void testSize() {
        checkValidList(0);

        for (int i = 0; i < 10; i++) {
            list.addToBack("item" + i);
        }
        checkValidList(10);

        for (int i = 10; i < 20; i++) {
            list.addToBack("item" + i);
        }
        checkValidList(20);
    }

    /*
     * Clears the list of all data.
     *
     * Must be O(1) for all cases.
     */
    @Test(timeout = TIMEOUT)
    public void testClear() {
        // clear empty list
        list.clear();
        checkValidList(0);

        // clear large list
        for (int i = 0; i < 100; i++) {
            list.addToBack("item" + i);
        }
        checkValidList(100);
        list.clear();
        checkValidList(0);
    }

    /**
     * Tests for valid list structure: (node.next.prev == node,
     * size == number of nodes, etc)
     * called at various times in each test
     *
     * @return size of the list
     */
    public int checkValidList() {
        LinkedListNode<String> head = list.getHead();
        LinkedListNode<String> tail = list.getTail();
        int size = list.size();

        // check going forward
        int checkSize = 0;
        LinkedListNode<String> cur = head;
        while (cur != null) {
            if (cur.getNext() == null) {
                assertEquals(cur, tail);
            } else {
                assertEquals(cur, cur.getNext().getPrevious());
            }
            checkSize++;
            cur = cur.getNext();
        }
        assertEquals(size, checkSize);

        // check going backward
        checkSize = 0;
        cur = tail;
        while (cur != null) {
            if (cur.getPrevious() == null) {
                assertEquals(cur, head);
            } else {
                assertEquals(cur, cur.getPrevious().getNext());
            }
            checkSize++;
            cur = cur.getPrevious();
        }
        assertEquals(size, checkSize);

        return size;
    }

    /**
     * Checks valid list structure with the additional condition of an
     * expected size.
     *
     * @param expectedSize the size that list should be
     */
    public void checkValidList(int expectedSize) {
        assertEquals(expectedSize, checkValidList());
    }

    /**
     * Checks valid list structure and checks that the list contents matches
     * the expected contents.
     *
     * @param expectedContents what the list should contain
     */
    public void checkValidList(Object[] expectedContents) {
        checkValidList(expectedContents.length);
        assertArrayEquals(list.toArray(), expectedContents);
    }
}