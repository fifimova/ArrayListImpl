package org.example.integerlist;

import org.example.exceptions.ElementNotFoundException;
import org.example.exceptions.IllegalIndexException;
import org.example.exceptions.NullItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {

    private IntegerList out = new IntegerListImpl();

    @BeforeEach
    void setUp() {
        out.add(1);
        out.add(2);
        out.add(3);
    }

    @Test
    void shouldAddByItem() {
        Integer actual = out.add(4);
        assertEquals(4, actual);
        assertSame(4, out.size());
    }

    @Test
    void doesExceptionThrowWhenItemNull() {
        assertThrows(NullItemException.class, () -> out.add(null));
    }

    @Test
    void shouldAddItemByIndex() {
        assertEquals(1, out.get(0));
        out.add(0, 0);
        assertEquals(0, out.get(0));
        assertEquals(1, out.get(1));
        assertSame(4, out.size());
    }

    @Test
    void doesExceptionThrowWhenIndexOuyOfBoundOrIllegal() {
        assertThrows(IllegalIndexException.class, () -> out.add(11, 100));
        assertThrows(IllegalIndexException.class, () -> out.add(-1, 100));
    }

    @Test
    void shouldChangeItemByIndex() {
        out.set(0, 10);
        out.set(1, 20);
        assertEquals(10, out.get(0));
        assertEquals(20, out.get(1));
        assertSame(3, out.size());
    }

    @Test
    void shouldRemoveByItem() {
        out.remove(Integer.valueOf("1"));
        assertSame(2, out.get(0));
        assertSame(2, out.size());
    }

    @Test
    void doesExceptionThrowWhenItemNotFound() {
        assertThrows(ElementNotFoundException.class, () -> out.remove(Integer.valueOf("555")));
    }

    @Test
    void shouldRemoveByIndex() {
        out.remove(0);
        assertEquals(2, out.get(0));
        assertSame(2, out.size());
    }

    @Test
    void shouldFindItem() {
        assertTrue(out.contains(1));
        assertFalse(out.contains(555));
    }

    @Test
    void shouldReturnIndexOfItemOrMinusOne() {
        assertSame(0, out.indexOf(1));
        assertSame(-1, out.indexOf(555));
    }

    @Test
    void shouldReturnIndexOfItemOrMinusOneByLast() {
        assertSame(0, out.indexOf(1));
        assertSame(-1, out.indexOf(555));
    }

    @Test
    void shouldReturnItem() {
        assertEquals(1, out.get(0));
    }

    @Test
    void shouldCompareIntegerLists() {
        IntegerList integerList = new IntegerListImpl();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        assertTrue(out.equals(integerList));
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
        Integer[] arr = out.toArray();
        Integer actual = arr[0];
        assertEquals(1, actual);
        int actualLength = arr.length;
        assertEquals(3, actualLength);
    }

    @Test
    void shouldExpandList() {
        for (int i = 0; i < 8; i++) {
            out.add(5);
        }
        assertEquals(11,out.size());
    }
}