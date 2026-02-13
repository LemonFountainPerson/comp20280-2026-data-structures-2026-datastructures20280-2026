package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private final E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ)
    {
        if (pred.next != succ || succ.prev != pred)
        {
            throw new IllegalArgumentException("Invalid nodes; they are not beside each other.");
        }
        pred.next = new Node<E>(e, pred, succ);
        succ.prev = pred.next;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0)
        {
            return true;
        }
        return false;
    }

    @Override
    public E get(int i)
    {
        if (i < 0 || i >= size())
        {
            throw new IllegalArgumentException("Invalid position value");
        }

        int k = -1;
        Node<E> cursor = head;
        while (k != i && cursor.next != null)
        {
            cursor = cursor.next;
            k++;
        }

        return cursor.getData();
    }

    @Override
    public void add(int i, E e)
    {
        if (i < 0 || i >= size())
        {
            throw new IllegalArgumentException("Invalid position value");
        }

        int k = 0;
        Node<E> cursor = head;
        while (k != i && cursor.next != null)
        {
            cursor = cursor.next;
            k++;
        }

        if (cursor.next == null)
        {
            return;
        }

        Node<E> newNode = new Node<E>(e, cursor, cursor.next);
        cursor.next.prev = newNode;
        cursor.next = newNode;
        size++;
    }

    @Override
    public E remove(int i)
    {
        if (i < 0 || i >= size())
        {
            throw new IllegalArgumentException("Invalid position value");
        }

        int k = 0;
        Node<E> cursor = head;
        while (k != i && cursor.next != null)
        {
            cursor = cursor.next;
            k++;
        }

        if (cursor.next == null)
        {
            return null;
        }

        Node<E> deleteNode = cursor.next;
        if (deleteNode.next != null)
        {
            deleteNode.next.prev = cursor;
        }
        cursor.next = deleteNode.next;
        size--;

        return deleteNode.getData();
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.next;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n)
    {
        if (n.next == null || n.prev == null)
        {
            throw new IllegalArgumentException("Cannot delete head or tail nodes.");
        }
        Node<E> prev = n.prev;
        prev.next = n.next;
        n.next.prev = prev;
        size--;
        return n.getData();
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.next.getData();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.prev.getData();
    }

    @Override
    public E removeFirst()
    {
        if (isEmpty()) {
            return null;
        }
        return remove(head.next);
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }

        return remove(tail.prev);
    }

    @Override
    public void addLast(E e) {
        addBetween(e, tail.prev, tail);
    }

    @Override
    public void addFirst(E e)
    {
        addBetween(e, head, head.next);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}