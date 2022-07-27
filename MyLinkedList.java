
public class MyLinkedList<E> {
    private  int size = 0;
    private Node<E> first;
    private Node<E> last;

    public MyLinkedList() {
    }
    public void add(E e){
        addElement(e);
    }
    private void  addElement(E e){
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l,e,null);
        last = newNode;
        if (l == null) {
            first = newNode;
        }
        else {
            l.nextLink = newNode;
        }
        size++;
    }
    private Node<E> getElement(int index){

        if (index < (size >> 1)) {
            Node<E> f = first;
            for (int i = 0; i < index; i++)
                f = f.nextLink;
            return f;
        } else {
            Node<E> l = last;
            for (int i = size - 1; i > index; i--)
                l = l.prevlink;
            return l;
        }
    }
    public  E get(int index){
        if(size == 0) {
            return null;
        }
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return getElement(index).element;
    }
    public E remove(int index) {
        if(size == 0) {
            return null;
        }
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return removeElement(getElement(index));
    }
    private E removeElement(Node<E> el){
        final E element = el.element;
        final Node<E> nextBuf = el.nextLink;
        final Node<E> prevBuf = el.prevlink;

        if (prevBuf == null) {
            first = nextBuf;
        } else {
            prevBuf.nextLink = nextBuf;
            el.prevlink = null;
        }

        if (nextBuf == null) {
            last = prevBuf;
        } else {
            nextBuf.prevlink = prevBuf;
            el.nextLink = null;
        }

        el.element = null;
        size--;
        return element;
    }
    public int size() {
        return size;
    }
    public void clear() {

        for (Node<E> f = first; f != null; ) {
            Node<E> next = f.nextLink;
            f.element = null;
            f.nextLink = null;
            f.prevlink = null;
            f = next;
        }
        first = last = null;
        size = 0;
    }

    private static class Node<E>{
        E element;
        Node<E> nextLink;
        Node<E> prevlink;

        public Node( Node<E> prevlink,E element, Node<E> nextLink) {
            this.element = element;
            this.nextLink = nextLink;
            this.prevlink = prevlink;
        }
    }
}