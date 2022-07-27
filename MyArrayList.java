

import java.util.Arrays;

public class MyArrayList<E> {
    private static final Object[] EMPTY_DATA = {};
    private Object[] data;
    private int size = 0;

    public MyArrayList() {
        this.data = EMPTY_DATA;
    }

    public void add(E e) {
        add(e, data.length);
    }

    private void add(E e, int n) {
        if(n == 0) {
            data = Arrays.copyOf(data, n + 1);
            data[n] = e;
            size++;
        } else {
            data = Arrays.copyOf(data, data.length + 1);
            data[n] = e;
            size++;
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException();
        }
        final Object[] rm = data;
        removeOfIndex(rm, index);
    }

    private void removeOfIndex(Object[] es, int index) {
        if (index < data.length - 1) {
            for (int i = index; i < es.length - 1; i++) {
                es[i] = es[i + 1];
            }
            es = Arrays.copyOf(es, es.length - 1);
            size--;
        } else {
            es = Arrays.copyOf(es, es.length - 1);
            size--;
        }
    }


    public E get(int index) {
        if (index < 0 || index > data.length) {
            throw new IndexOutOfBoundsException();
        }
        if (data.length == 0) {
            return null;
        }
        return dataOfIndex(index);
    }

    private E dataOfIndex(int index) {
        return (E) data[index];
    }

    public int size() {
        return size;
    }

    public void clear() {
        data = Arrays.copyOf(data, 0);
        size = 0;
    }

}