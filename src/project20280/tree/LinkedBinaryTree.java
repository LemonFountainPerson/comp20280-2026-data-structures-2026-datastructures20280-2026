package project20280.tree;

import project20280.interfaces.Position;
import project20280.list.SinglyLinkedList;

import java.util.ArrayList;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree()
    {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String [] args) {
        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());

        String[] inorder = { "A", "B", "C", "D", "E", "F", "G", "H" };
        String[] preorder = { "F", "B", "A", "D", "C", "E", "G", "H"};
        LinkedBinaryTree <String> bt2 = new LinkedBinaryTree<>();
        bt2.construct(inorder, preorder);
        System.out.println(bt2.toBinaryTreeString ());

        /*

        Integer[] inorder= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        Integer[] preorder= {18, 2, 1, 14, 13, 12, 4, 3, 9, 6, 5, 8, 7, 10, 11, 15, 16, 17, 28, 23, 19, 22, 20, 21, 24, 27, 26, 25, 29, 30};

        LinkedBinaryTree <Integer > bt2 = new LinkedBinaryTree<>();
        bt2.construct(inorder, preorder);
        System.out.println(bt2.toBinaryTreeString ());
        */

    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException
    {
        if (size >= 1 || root() != null)
        {
            throw new IllegalStateException("Already contains a root!");
        }

        root = new Node<E>(e, null, null, null);
        size++;

        return root();
    }

    public Position<E> removeRoot()
    {
        Node<E> prevRoot = root;

        root = null;
        size = 0;

        return prevRoot;
    }

    public void insert(E e)
    {


    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e)
    {

        return null;
    }

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException
    {
        Node<E> nodePos = validate(p);
        if (nodePos.left != null)
        {
            throw new IllegalArgumentException("This node already has a left child.");
        }
        size++;

        nodePos.setLeft(new Node<E>(e, nodePos, null, null));
        return nodePos.getLeft();
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> nodePos = validate(p);
        if (nodePos.right != null)
        {
            throw new IllegalArgumentException("This node already has a right child.");
        }
        size++;
        nodePos.setRight(new Node<E>(e, nodePos, null, null));
        return nodePos.getRight();
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        node.setElement(e);
        return node.getElement();
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException
    {
        Node<E> leaf = validate(p);
        if (leaf.getLeft() != null || leaf.getRight() != null)
        {
            throw new IllegalArgumentException("Node is not a leaf node.");
        }


        Node<E> leftRoot = validate(t1.root());
        leaf.setLeft(leftRoot);
        leftRoot.setParent(leaf);
        t1.removeRoot();

        Node<E> rightRoot = validate(t2.root());
        leaf.setLeft(rightRoot);
        rightRoot.setParent(leaf);
        t2.removeRoot();

    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> delNode = validate(p);

        if (delNode.getRight() != null && delNode.getLeft() != null)
        {
            throw new IllegalArgumentException("This node has two children.");
        }

        Node<E> child = null;
        if (delNode.getLeft() != null)
        {
            child = delNode.getLeft();
        }
        else if (delNode.getRight() != null)
        {
            child = delNode.getRight();
        }

        Node<E> parent = delNode.getParent();

        if (parent == null)
        {
            root = child;
        }
        else {
            if (parent.getLeft() == delNode) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }

        size--;
        return delNode.getElement();
    }

    public String toString()
    {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l)
    {
        if (root() != null)
        {
            return;
        }

        root = createLevelOrderHelper(l, null, 0);
    }

    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i)
    {
        if (i < l.size() && l.get(i) != null)
        {
            Node<E> n = createNode(l.get(i), p, null, null);
            n.left = createLevelOrderHelper(l, null, 2 * i + 1);
            n.right = createLevelOrderHelper(l, null, 2 * i + 2);
            size++;
            return n;
        }
        return p;
    }

    public void createLevelOrder(E[] arr)
    {
        if (root() != null)
        {
            return;
        }

        root = createLevelOrderHelper(arr, null, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i)
    {
        if (i < arr.length && arr[i] != null)
        {
            Node<E> n = createNode(arr[i], p, null, null);
            n.left = createLevelOrderHelper(arr, null, 2 * i + 1);
            if (n.getLeft() != null)
            {
                n.getLeft().setParent(n);
            }

            n.right = createLevelOrderHelper(arr, null, 2 * i + 2);
            if (n.getRight() != null)
            {
                n.getRight().setParent(n);
            }

            size++;
            return n;
        }
        return p;
    }


    public void construct(E[] inorder, E[] preorder)
    {
        addRoot(preorder[0]);

        int nodeIndex = 0;
        while (nodeIndex < inorder.length && !inorder[nodeIndex].equals(preorder[0]))
        {
            nodeIndex++;
        }

        if (nodeIndex >= inorder.length)
        {
            return;
        }

        Node<E> rootNode = validate(root());
        int rootIndex = 0;
        rootIndex += constructHelper(inorder, preorder, rootNode, 0, nodeIndex - 1, rootIndex);

        //constructHelper(inorder, preorder, rootNode, nodeIndex + 1, inorder.length - 1, rootIndex);

    }


    public int constructHelper(E[] inorder, E[] preorder, Node<E> source, int start, int end, int rootIndex)
    {
        if (source == null || start > end || rootIndex < 0 || rootIndex >= preorder.length)
        {
            return 0;
        }

        //System.out.println(source.getElement().toString() + " node " + (rootIndex));

        source.setLeft(new Node<E>(preorder[rootIndex + 1], source, null, null));

        int nodeIndex = 0;
        while (nodeIndex < end && !inorder[nodeIndex].equals(preorder[rootIndex + 1]))
        {
            nodeIndex++;
        }

        System.out.println("Source: " + source +  " start: " + (start) + " end: " + end + " root: " + (rootIndex));

        if (nodeIndex >= end)
        {
            return 1;
        }

        int nodesAdded = constructHelper(inorder, preorder, source.getLeft(),  start, nodeIndex - 1,rootIndex + 1);

        System.out.println(source.getElement().toString() + (rootIndex + nodesAdded));
        if (rootIndex + nodesAdded >= preorder.length)
        {
            return nodesAdded;
        }

       // System.out.println(source.getElement().toString() + " node " + (rootIndex));

        //source.setRight(new Node<E>(inorder[rootIndex + nodesAdded], source, null, null));
        //nodesAdded += constructHelper(inorder, preorder, source.getRight(),  nodeIndex + 1, end, rootIndex + nodesAdded);

        return nodesAdded;
    }


    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    public void toBinaryTreeLeafString()
    {
        SinglyLinkedList<E> leaves = new SinglyLinkedList<E>();

        findLeavesRecursive(leaves, validate(root()));

        System.out.println(leaves);
        return;
    }

    private void findLeavesRecursive(SinglyLinkedList<E> list, Node<E> inputRoot)
    {
        if (inputRoot == null)
        {
            return;
        }

        if (inputRoot.getLeft() == null && inputRoot.getRight() == null)
        {
            list.addLast(inputRoot.getElement());
            return;
        }

        findLeavesRecursive(list, inputRoot.getLeft());

        findLeavesRecursive(list, inputRoot.getRight());
    }

    public int externalNodeCount(Position<E> input)
    {
        int leaves = 0;

        if (left(input) == null && right(input) == null)
        {
            leaves++;
        }

        for (Position<E> child : children(input))
        {
            leaves += externalNodeCount(child);
        }

        return leaves;
    }

    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }
}
