import java.util.Arrays;

public class MyHashMap<K, V> {
    static final float LOAD_FACTOR = 0.75f;
    static final int DEFAULT_CAPACITY = 16;


    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }


    private Node<K, V>[] table;
    private int size;

    public MyHashMap() {
    }

    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int indexFor(int hash, int capacity) {
        return (capacity - 1) & hash;
    }

    private V addVal(int hash, K key, V value) {
        Node<K, V>[] tab;
        Node<K, V> node;
        int putIndex;
        tab = table;
        if (tab == null) {
            tab = new Node[DEFAULT_CAPACITY];
        }
        putIndex = indexFor(hash, tab.length);

        if (tab[putIndex] == null) {
            tab[putIndex] = new Node<>(hash, key, value, null);
        } else {
            Node<K, V> e;
            K keyTemp;
            node = tab[putIndex];
            if (node.hash == hash && ((keyTemp = node.key) == key || (key != null && key.equals(keyTemp)))) {
                node.value = value;
            } else {
                tab[putIndex] = new Node<>(hash, key, value, node);
            }
        }
        table = Arrays.copyOf(tab, tab.length);
        size++;
        return null;
    }

    public V put(K key, V value) {
        return addVal(hash(key), key, value);
    }

    public V get(Object key) {
        Node<K, V> e;
        return (e = getNode(key)) == null ? null : e.value;
    }

    private Node<K, V> getNode(Object key) {
        Node<K, V>[] tab;
        Node<K, V> firstNode, nextNode;
        int hash = hash((K) key);
        K k;
        tab = table;
        firstNode = tab[indexFor(hash((K) key), tab.length)];
        if (tab != null && tab.length > 0 && firstNode != null) {
            if (firstNode.hash == hash && ((k = firstNode.key) == key || (key != null && key.equals(k)))) {
                return firstNode;
            }
        }
        if ((nextNode = firstNode.next) != null) {
            do {
                if (nextNode.hash == hash && ((k = firstNode.key) == key || key.equals(k))) {
                    return nextNode;
                }
            }
            while ((nextNode = nextNode.next) != null);
        }
        return null;
    }

    public V remove(Object key) {
        Node<K, V> e;
        return (e = removeNode(hash(key), key, null)) == null ?
                null : e.value;
    }

    Node<K, V> removeNode(int hashKey, Object key, Object value) {
        Node<K, V>[] tab;
        Node<K, V> p;
        int index;
        tab = table;
        if (tab != null && tab.length > 0 && (p = tab[index = indexFor(hash(key), tab.length)]) != null) {
            Node<K, V> node = null, e;
            K k;
            if (p.hash == hashKey && ((k = p.key) == key || (key != null && key.equals(k)))) {
                node = p;
            } else if ((e = p.next) != null) {
                do {
                    if (e.hash == hashKey &&
                            ((k = e.key) == key ||
                                    (key != null && key.equals(k)))) {
                        node = e;
                        break;
                    }
                    p = e;
                } while ((e = e.next) != null);
            }
            if (node != null) {
                if (node == p) {
                    tab[index] = node.next;
                } else {
                    p.next = node.next;
                }
                size--;
                return node;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        Node<K, V>[] tab;
        tab = table;
        if (tab != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                tab[i] = null;
        }
    }
}
