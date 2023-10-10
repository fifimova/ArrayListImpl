package org.example.integerlist;

import org.example.exceptions.ElementNotFoundException;
import org.example.exceptions.IllegalIndexException;
import org.example.exceptions.NullItemException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] integers;
    private int size;

    public IntegerListImpl() {
        integers = new Integer[10];
    }

    public IntegerListImpl(int initSize) {
        integers = new Integer[initSize];
    }

    @Override
    public Integer add(Integer item) {
        growIfArrayIsFull();
        validateItem(item);
        integers[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        growIfArrayIsFull();
        validateItem(item);
        validateIndex(index);
        if (index == size) {
            integers[size++] = item;
            return item;
        }
        System.arraycopy(integers, index, integers, index + 1, size - index);
        integers[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        integers[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }
        if (index != size) {
            System.arraycopy(integers, index + 1, integers, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = integers[index];
        if (index != size) {
            System.arraycopy(integers, index + 1, integers, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] copyIntegers = toArray();
        sort(copyIntegers);
        return binarySearch(copyIntegers, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return integers[index];
    }

    @Override
    public boolean equals(IntegerList otherIntList) {
        return Arrays.equals(this.toArray(), otherIntList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(integers, size);
    }

    private void sort(Integer[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }


    private boolean binarySearch(Integer[] arr, Integer item) {
        int minIdx = 0;
        int maxIdx = arr.length - 1;
        while (minIdx <= maxIdx) {
            int mid = (maxIdx + minIdx) / 2;
            if (item.equals(arr[mid])) {
                return true;
            }
            if (item < arr[mid]) {
                maxIdx = mid - 1;
            } else {
                minIdx = mid + 1;
            }
        }
        return false;
    }

    private void grow() {
        integers = Arrays.copyOf(integers, size + size / 2);
    }


    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void growIfArrayIsFull() {
        if (size >= integers.length) {
            grow();
        }
    }

    private void validateIndex(int index) {
        if (index > size || index < 0) {
            throw new IllegalIndexException();
        }
    }
}

