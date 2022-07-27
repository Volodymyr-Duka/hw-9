import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack<E>  {
    private static final Object[] EMPTY_DATA = {};
    private Object[] data;
    private int size = 0;

    public MyStack() {
        this.data = EMPTY_DATA;
    }
    public E push(E item) {
        add(item, data.length);
        return item;
    }
    private void add(E e,int count){
        if(count == 0) {
            data = Arrays.copyOf(data, count + 1);
            data[count] = e;
            size++;
        } else {
            data = Arrays.copyOf(data, data.length + 1);
            data[count] = e;
            size++;
        }
    }
    public E peek(){
        int length = size();
        if(length == 0) {
            throw new EmptyStackException();
        }
        return getDataOfIndex(length-1);
    }
    private  E getDataOfIndex(int index){
        return (E)data[index];
    }
    public E pop(){
        E elementData;
        int length = size();
        if(length == 0){
            return null;
        }
        elementData = peek();
        removeDataLast(length-1);
        return elementData;
    }
    private void removeDataLast(int index){
        data = Arrays.copyOf(data,index);
        size--;
    }
    public void remove(int index){
        int length = size();
        if(length == 0){
            return;
        }
        removeOfIndex(data,index);
    }
    private void removeOfIndex(Object[] es, int index){
        if (index < data.length - 1) {
            for (int i = index; i < es.length - 1; i++) {
                es[i] = es[i + 1];
            }
            Arrays.copyOf(es, es.length - 1);
            size--;
        } else {
            Arrays.copyOf(es, es.length - 1);
            size--;
        }
    }
    public void clear(){
        data = Arrays.copyOf(data, 0);
        size = 0;
    }
    public int size(){
        return size;
    }
}