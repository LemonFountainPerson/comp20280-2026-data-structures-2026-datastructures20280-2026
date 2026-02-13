package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n)
        {
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement()
        {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext()
        {
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n)
        {
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)

    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList()
    {

    }              // constructs an initially empty list

    //@Override
    public int size()
    {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public E get(int position)
    {
        if (position < 0 || position >= size)
        {
            throw new IllegalArgumentException("Invalid position value");
        }

        Node<E> cursor = head;
        int i = 0;
        while (cursor != null)
        {
            if (i == position)
            {
                return cursor.getElement();
            }
            cursor = cursor.next;
            i++;
        }

        return null;
    }

    @Override
    public void add(int position, E e)
    {
        if (position < 0 || position > size)
        {
            throw new IllegalArgumentException("Invalid position value");
        }

        if (position == 0)
        {
            head = new Node<E>(e, head);
            return;
        }

        Node<E> cursor = head;
        int i = 1;
        while (cursor != null && i < position)
        {
            cursor = cursor.next;
            i++;
        }

        if (cursor == null)
        {
            return;
        }

        Node<E> nextNode = cursor.next;
        cursor.next = new Node<E>(e, nextNode);
    }


    @Override
    public void addFirst(E e)
    {
        head = new Node<E>(e, head);
        size++;
    }

    @Override
    public void addLast(E e)
    {
        if (head == null)
        {
            addFirst(e);
            return;
        }

        Node<E> cursor = head;
        while (cursor.next != null)
        {
            cursor = cursor.next;
        }

        cursor.next = new Node<E>(e, null);
        size++;
    }

    @Override
    public E remove(int position)
    {
        if (position < 0 || position >= size())
        {
            throw new IllegalArgumentException("Invalid position value");
        }
        if (position == 0)
        {
            return removeFirst();
        }

        Node<E> cursor = head;
        int i = 1;
        while (cursor.next != null && i != position)
        {
            cursor = cursor.next;
            i++;
        }

        if (cursor.next == null)
        {
            return null;
        }

        Node<E> deleteNode = cursor.next;
        cursor.next = cursor.next.next;
        size--;

        return deleteNode.element;
    }

    @Override
    public E removeFirst()
    {
        if (head == null)
        {
            return null;
        }

        Node<E> firstElement = head;
        head = head.next;
        size--;

        return firstElement.element;
    }

    @Override
    public E removeLast()
    {
        if (head == null)
        {
            return null;
        }

        Node<E> cursor = head;

        if (cursor.next == null)
        {
            return removeFirst();
        }

        while (cursor.next.next != null)
        {
            cursor = cursor.next;
        }

        Node<E> last = cursor.next;
        cursor.next = null;
        size--;

        return last.element;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

    }
}
