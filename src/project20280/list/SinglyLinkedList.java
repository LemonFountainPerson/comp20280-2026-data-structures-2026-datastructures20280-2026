package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

import static java.lang.Math.clamp;

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
            size++;
            return;
        }

        Node<E> cursor = head;
        int i = 1;
        while (cursor != null && i < position)
        {
            cursor = cursor.getNext();
            i++;
        }

        if (cursor == null)
        {
            return;
        }

        Node<E> nextNode = cursor.getNext();
        cursor.setNext( new Node<E>(e, nextNode));
        size++;
    }


    @Override
    public void addFirst(E e)
    {
        add(0, e);
    }

    @Override
    public void addLast(E e)
    {
        add(size, e);
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
            Node<E> del = head;
            head = del.getNext();
            return del.getElement();
        }

        Node<E> cursor = head;
        int i = 1;
        while (cursor.next != null && i != position)
        {
            cursor = cursor.next;
            i++;
        }

        Node<E> deleteNode = cursor.next;
        if (deleteNode == null)
        {
            return null;
        }

        cursor.setNext(deleteNode.getNext());
        size--;

        return deleteNode.element;
    }

    @Override
    public E removeFirst()
    {
        return remove(0);
    }

    @Override
    public E removeLast()
    {
        return remove(size - 1);
    }

    public void reverse()
    {
        head = reverseList(head);
    }

    private Node<E> reverseList(Node<E> inputHead)
    {
        if (inputHead == null || inputHead.getNext() == null)
        {
            return inputHead;
        }

        Node<E> reverseHead = reverseList(inputHead.getNext());

        inputHead.getNext().setNext(inputHead);

        inputHead.setNext(null);

        return reverseHead;
    }

    public SinglyLinkedList<E> recursiveCopy()
    {
        SinglyLinkedList<E> list = new SinglyLinkedList<E>();

        recursiveCopyData(head, list);

        return list;
    }

    private void recursiveCopyData(Node<E> inputHead, SinglyLinkedList<E> list)
    {
        if (inputHead == null)
        {
            return;
        }

        recursiveCopyData(inputHead.getNext(), list);

        list.addFirst(inputHead.getElement());

        return;
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
