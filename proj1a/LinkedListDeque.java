public class LinkedListDeque<T> {

    public class TNode {
        private TNode prev, next;
        private T item;

        public TNode(T t, TNode pre, TNode nex) {
            item = t;
            prev = pre;
            next = nex;
        }
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    public void addFirst(T item) {
        size += 1;
        TNode p = sentinel.next;
        sentinel.next = new TNode(item, sentinel, p);
        p.prev = sentinel.next;
    }

    public void addLast(T item) {
        size += 1;
        TNode p = sentinel.prev;
        sentinel.next = new TNode(item, p, sentinel);
        p.next = sentinel.prev;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TNode p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        TNode p = sentinel.next;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        size -= 1;
        return p.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        TNode p = sentinel.prev;
        sentinel.prev = p.prev;
        p.prev.next = sentinel;
        size -= 1;
        return p.item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        TNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, TNode t) {
        if (index == 0) {
            return t.item;
        }
        return getRecursiveHelper(index - 1, t.next);
    }
}
