package project20280.exercises;

import project20280.list.SinglyLinkedList;
import project20280.tree.LinkedBinaryTree;

import java.util.Arrays;

public class Wk6 {
    static int iter = 0;

    static public void reset() {
        iter = 0;
    }

    static public void reverseArray(int[] array, int start, int end) {
        if (start > array.length || end < 0 || start >= end) {
            return;
        }

        iter++;
        int current = iter;
        for (int i = 1; i < current; i++) {
            System.out.print("-");
        }
        System.out.println("Recursion " + current + " start:");

        if (array[start] < array[end]) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            reverseArray(array, start + 1, end - 1);
        }

        for (int i = 1; i < current; i++) {
            System.out.print("-");
        }
        System.out.println("Recursion " + current + " end:");
    }

    static public int fibonacci(int n) {
        if (n <= 2) {
            return 1;
        }

        iter++;
        int current = iter;
        for (int i = 1; i < current; i++) {
            System.out.print("-");
        }
        System.out.println("Recursion " + current + " start: " + n);

        int val = fibonacci(n - 1) + fibonacci(n - 2);

        for (int i = 1; i < current; i++) {
            System.out.print("-");
        }
        System.out.println("Recursion " + current + " end: " + val);

        return val;
    }

    static public int tribonacci(int n) {
        if (n <= 2) {
            return 0;
        }

        if (n == 3) {
            return 1;
        }

        iter++;
        int current = iter;
        for (int i = 1; i < current; i++) {
            System.out.print("-");
        }
        System.out.println("Recursion " + current + " start: " + n);

        int val = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);

        for (int i = 1; i < current; i++) {
            System.out.print("-");
        }
        System.out.println("Recursion " + current + " end: " + val);

        return val;
    }

    static public int mcCarthy(int n) {
        iter++;
        int current = iter;
        for (int i = 1; i < current; i++) {
            System.out.print("-");
        }
        System.out.println("Recursion " + current + " start: " + n);


        if (n > 100)
        {
            return n - 10;
        }

        int val = mcCarthy(mcCarthy(n + 11));


        for (int i = 1; i < current; i++) {
            System.out.print("-");
        }
        System.out.println("Recursion " + current + " end: " + val);

        return val;
    }

    public static int foo(int x)
    {
        if (x / 2 == 0)
        {
            System.out.println(x);
            return 0;
        }

        foo(x / 2);

        System.out.println(x % 2);
        return 0;
    }


    public static int mystery (int n, int a , int d)
    {
        iter++;
        int current = iter;
        for (int i = 1; i < current; i++) {
            System.out.print("-");
        }
        System.out.println("Recursion " + current + " start: " + n + " aVal: " + a + " dVal: " + d);

        if(n == 1)
        {
            for (int i = 1; i < current; i++) {
                System.out.print("-");
            }
            System.out.println("Recursion " + current + " end: " + n + " aVal: " + a + " dVal: " + d);

            return a;
        }

        int val = d + mystery (n - 1, a, d);

        for (int i = 1; i < current; i++) {
            System.out.print("-");
        }
        System.out.println("Recursion " + current + " end: " + val + " aVal: " + a + " dVal: " + d);

        return val;
    }


    public static void main()
    {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        list.addFirst(6);
        list.addFirst(7);
        list.addFirst(8);
        list.addFirst(9);
        list.addFirst(10);


        reverseArray(array, 0, 8);

        System.out.println("ReverseArray > " + Arrays.toString(array) + " \n");

        reset();
        System.out.println("Fibonacci > ");
        System.out.println("answer: " + fibonacci(5) + "\n");

        reset();
        System.out.println("Tribonacci > ");
        System.out.println("answer: " + tribonacci(10) + "\n");

        reset();
        System.out.println("McCarthy > ");
        System.out.println("answer: " + mcCarthy(87) + "\n");

        reset();
        System.out.println("Foo > ");
        System.out.println("answer: " + foo(2468) + "\n");


        System.out.println("Before: " + list);
        list.reverse();
        System.out.println("After: " + list);

        SinglyLinkedList<Integer> copy = list.recursiveCopy();
        System.out.println("Copy: " + copy + "\n");

        reset();
        System.out.println("answer: " + mystery(2, 4, 4) + "\n");

        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        tree.createLevelOrder(arr);
        System.out.println(tree.toBinaryTreeString());
        tree.toBinaryTreeLeafString();
    }
}
