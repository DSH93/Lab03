import javax.lang.model.type.NullType;

public class DLinkedList<T> implements List<T> {
    private DNode head;
    private DNode tail;
    private DNode cursor;
    private class DNode {
        private T element;
        private DNode next;
        private DNode prev;
        public DNode(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setNext(DNode next) {
            this.next = next;
        }

        public DNode getNext() {
            return next;
        }

        public void setPrev(DNode prev) {
            this.prev = prev;
        }

        public DNode getPrev() {
            return prev;
        }
    } // d Class

    public DLinkedList() {
        head = new DNode(null);
        tail = new DNode(null);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
        cursor = head;
    } //Constructor

    @Override
    public void insert(T newElement) {
        if (newElement == null) return;
        DNode newNode = new DNode(newElement);
        newNode.next = cursor.next;
        newNode.prev = cursor;
        cursor.next = newNode;
    }

    @Override
    public T remove() {
        T del = cursor.element;
        cursor.next.prev = cursor.prev;
        cursor.prev.next = cursor.next;
        cursor = cursor.next;
        return del;
    }

    @Override
    public T remove(T element) {
        T del = null;
        DNode localCursor = head;
        while (localCursor.element != null) {
            if (localCursor.element == element) {
                cursor = localCursor;
                del = remove();
            }
            localCursor = localCursor.next;
        }
        return del;
    }

    @Override
    public void clear() {
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void replace(T newElement) {
        remove();
        insert(newElement);
    }

    @Override
    public boolean isEmpty() {
        if (head.next == tail && tail.prev == head) return true;
        return false;
    }

    @Override
    public boolean goToBeginning() {
        if (!isEmpty()) {
            cursor = head.next;
            return true;
        }
        return false;
    }

    @Override
    public boolean goToEnd() {
        if (!isEmpty()) {
            cursor = tail.prev;
            return true;
        }
        return false;
    }

    @Override
    public T getNext() {
        if (cursor.next != null && cursor.next != tail) {
            cursor = cursor.next;
            return cursor.element;
        }
        return null;
    }

    @Override
    public T getPrev() {
        if (cursor.prev != null && cursor.prev != head) {
            cursor = cursor.prev;
            return cursor.element;
        }
        return null;
    }

    @Override
    public T getCursor() {
        if (!isEmpty()) {
            return cursor.element;
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (isEmpty()) return false;
        if (cursor.next != null && cursor.next != tail) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPrev() {
        if (isEmpty()) return false;
        if (cursor.prev != null && cursor.prev != head) {
            return true;
        }
        return false;
    }

}
