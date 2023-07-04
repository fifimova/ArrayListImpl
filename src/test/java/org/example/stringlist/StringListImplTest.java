package org.example.stringlist;

import org.example.exceptions.ElementNotFoundException;
import org.example.exceptions.IllegalIndexException;
import org.example.exceptions.NullItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {

    private StringList out = new StringListImpl();

    @BeforeEach
    void setUp() {
        out.add("a");
        out.add("b");
        out.add("c");
    }

    @Test
    void shouldAddByItem() {
        String actual = out.add("d");
        assertEquals("d", actual);
        assertSame(4, out.size());

    }

    @Test
    void doesExceptionThrowWhenItemNull() {
        assertThrows(NullItemException.class, () -> out.add(null));
    }

    @Test
    void shouldAddItemByIndex() {
        assertEquals("a", out.get(0));
        out.add(0, "z");
        assertEquals("z", out.get(0));
        assertEquals("a", out.get(1));
        assertSame(4, out.size());
    }

    @Test
    void doesExceptionThrowWhenIndexOuyOfBoundOrIllegal() {
        assertThrows(IllegalIndexException.class, () -> out.add(11, "B"));
        assertThrows(IllegalIndexException.class, () -> out.add(-1, "A"));
    }

    @Test
    void shouldChangeItemByIndex() {
        out.set(0, "A");
        out.set(1, "A");
        assertEquals("A", out.get(0));
        assertEquals("A", out.get(1));
    }

    @Test
    void shouldRemoveByItem() {
        out.remove("a");
        assertSame("b", out.get(0));
        assertSame(2, out.size());
    }

    @Test
    void doesExceptionThrowWhenItemNotFound() {
        assertThrows(ElementNotFoundException.class, () -> out.remove("AAAAA"));
    }

    @Test
    void shouldRemoveByIndex() {
        out.remove(0);
        assertEquals("b", out.get(0));
        assertSame(2, out.size());
    }

    @Test
    void shouldFindItem() {
        assertTrue(out.contains("a"));
        assertFalse(out.contains("AAAAA"));
    }

    @Test
    void shouldReturnIndexOfItemOrMinusOne() {
        assertSame(0, out.indexOf("a"));
        assertSame(-1, out.indexOf("AAAAA"));
    }

    @Test
    void shouldReturnIndexOfItemOrMinusOneByLast() {
        assertSame(0, out.indexOf("a"));
        assertSame(-1, out.indexOf("AAAAA"));
    }

    @Test
    void shouldReturnItem() {
        assertEquals("a", out.get(0));
    }

    @Test
    void shouldCompareStringLists() {
        StringList stringList = new StringListImpl();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        assertTrue(out.equals(stringList));
    }

    @Test
    void shouldReturnAmountOfElements() {
        assertSame(3, out.size());
    }

    @Test
    void shouldCheckListIsEmpty() {
        assertFalse(out.isEmpty());
    }

    @Test
    void shouldDoneListEmpty() {
        out.clear();
        assertTrue(out.isEmpty());
    }

    @Test
    void shouldMadeListToArray() {
        String[] arr = out.toArray();
        String actual = arr[0];
        assertEquals("a", actual);
        int actualLength = arr.length;
        assertEquals(3, actualLength);
    }

}