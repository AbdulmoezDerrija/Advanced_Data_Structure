import java.rmi.NotBoundException;
import java.util.*;

public class BST {

    static List<Integer> list = new ArrayList<>();
    static LinkedList<Integer> linkedList = new LinkedList<>();
    static boolean found;
    static int depth;

    //Node Class
    static class Node {
        Node parent, leftNode, rightNode;
        int value;

        //Constructor
        public Node(int value) {
            this.value = value;
            this.parent = null;
            this.leftNode = null;
            this.rightNode = null;
        }
    }
    //recursive version of tree_insert
    public static void insertingBST(Node root, int value) {
        if (root == null)  root = new Node(value);
        if (root.value > value) {
            if (root.leftNode != null) insertingBST(root.leftNode, value);
            else {
//                System.out.println("A new Node has been added with a value of " + root.value);
                root.leftNode = new Node(value);
                root.leftNode.parent = root;
            }
        } else if (root.value < value) {
            if (root.rightNode != null) insertingBST(root.rightNode, value);
            else {
//                System.out.println("A new Node has been added with a value of " + root.value);
                root.rightNode = new Node(value);
                root.rightNode.parent = root;
            }
        }
    }
    public static void tree_insert(Node node, int value) {

    }
    public static Node run() {
        Node root = new Node(list.get(0));
//        System.out.println("Building tree with root value " + root.value);
//        System.out.println("=================================");
        for (int i = 1; i < list.size(); i++) {
            insertingBST(root, list.get(i));
        }
        return root;
    }
    static void inorderBST(Node root){
        if (root == null) return;

        inorderBST(root.leftNode);
        System.out.println(root.value + " ");
        inorderBST(root.rightNode);
    }
    static void postorderBST(Node root){
        if (root == null) return;
        System.out.println(root.value + " ");
        inorderBST(root.leftNode);
        inorderBST(root.rightNode);
    }
    static void preorderBST(Node root){
        if (root == null) return;
        inorderBST(root.leftNode);
        inorderBST(root.rightNode);
        System.out.println(root.value + " ");
    }
    //recursive version of tree_min
    static void  minBST(Node node) {
        if (node == null) return;
        if (node.leftNode !=  null) minBST(node.leftNode);
        else System.out.println(node.value);
    }
    static Node tree_min(Node node) {
        while (node.leftNode != null) node = node.leftNode;
        return node;
    }
    //recursive version of tree_max
    static void maxBST(Node node) {
        if (node == null) return;
        if (node.rightNode !=  null) maxBST(node.rightNode);
        else System.out.println(node.value);
    }
    static Node tree_max(Node node) {
        while (node.rightNode != null) node = node.rightNode;
        return node;
    }
    private static boolean find(Node node, int value) throws Exception {
        found = false;
        if (node == null) throw new Exception("Couldn't find it! ");
        if (node.value == value) {
            found = true;
            System.out.println("Depth is: " + depth);
        }
        depth++;
        if (node.value > value) {
            if (node.leftNode != null) find(node.leftNode, value);
        }
        else if (node.value < value) {
            if (node.rightNode != null) find(node.rightNode, value);
        }
        return found;
    }
    static boolean search(Node node, int value) throws Exception {
        depth = 0;
        return find(node, value);
    }
    static Node iterative_Tree_Search(Node node, int value) {
        while(node != null && node.value != value ) {
            if (node.value > value) node = node.leftNode;
            else node = node.rightNode;
        }
        return node;
    }
    static Node tree_successor(Node node) throws NullPointerException{
        if (node.rightNode != null) {
            return tree_min(node.rightNode);
        }
        Node par = node.parent;
        while (par != null && node == par.rightNode){
            node = par;
            par = par.parent;
        }
        return par;

    }
    static Node tree_predecessor(Node node) throws Exception {
        if (node == null) return null;
        if (node.leftNode != null) {
            return tree_max(node.leftNode);
        }
        Node par = node.parent;
        while (par != null && node == par.leftNode) {
            node = par;
            par = par.parent;
        }
        if (par != null && node == par.rightNode) {
            return par;
        }
        else throw new NotBoundException("Not found");
    }
    static Node deleteNode(Node root, int key) {
        //check if the tree is empty
        if (root == null) return root;

        // Otherwise, recursive down the tree
        if (key < root.value) root.leftNode = deleteNode(root.leftNode, key);
        else if (key > root.value) root.rightNode = deleteNode(root.rightNode, key);

            // if key is sane as root's key, then this is the node to be deleted
        else {
            // node with only one child or no child
            if (root.leftNode == null) return root.rightNode;
            else if (root.rightNode == null) return root.leftNode;

            // node with two children: Get the inorder. successor (smallest in the right subtree)
            root.value = tree_min(root.rightNode).value;

            // Delete the inorder successor
            root.rightNode = deleteNode(root.rightNode, root.value);
        }
        return root;
    }


    public static void main(String[] args) throws Exception {
        list.add(50); list.add(30); list.add(20); list.add(40); list.add(70); list.add(60); list.add(80);// list.add(14); list.add(13); list.add(15);
        System.out.println("Inorder sorting BST: ");
        inorderBST(run());
        System.out.println("Postorder sorting BST: ");
        postorderBST(run());
        System.out.println("Preorder sorting BST: ");
        preorderBST(run());
        System.out.println("min in BST: ");
        minBST(run());
        System.out.println("max in BST: ");
        maxBST(run());
        System.out.println("searching for a specified with finding depth.");
        System.out.println(search(run(), 40));
        System.out.println(iterative_Tree_Search(run(), 80));
        System.out.println(tree_successor(run()));
        System.out.println(tree_predecessor(run()));
        postorderBST(deleteNode(run(), 50));

    }
}
