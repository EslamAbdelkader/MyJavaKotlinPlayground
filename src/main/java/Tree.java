import java.util.ArrayList;
import java.util.List;

public class Tree {
    Node root;

    Tree(Node root) {
        this.root = root;
    }

    void insert(Node node) {
        this.root = insert(root, node);
    }

    // Inserts and returns the subtree
    private Node insert(Node root, Node node) {
        if (root == null) return node;

        if (node.key == root.key) {
            node.left = root.left;
            node.right = root.right;
            root = node;
        } else if (node.key <= root.key) {
            root.left = insert(root.left, node);
        } else if (node.key > root.key) {
            root.right = insert(root.right, node);
        }

        root.count = 1 + size(root.left) + size(root.right);
        update(root);

        return balance(root);
    }

    private int size(Node root) {
        if (root == null) return 0;
        return root.count;
    }

    Node search(int key) {
        return search(root, key);
    }

    private Node search(Node root, int key) {
        if (root == null) return null;
        if (root.key == key) return root;
        if (key <= root.key) return search(root.left, key);
        return search(root.right, key);
    }

    public void delete(int key) {
        delete(root, key);
    }

    private Node delete(Node root, int key) {
        if (root == null) return null;
        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {    // if key == root.key
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // if two children
            Node temp = root;
            root = min(root.right);
            root.right = delete(temp.right, root.key);
            root.left = temp.left;
        }
        root.count = 1 + size(root.left) + size(root.right);
        update(root);
        return balance(root);
    }

    private Node balance(Node root) {
        if (root.balanceFactor == 2) {
            if (root.left.balanceFactor == 1) {
                return leftLeftCase(root);
            } else {
                return leftRightCase(root);
            }
        }

        if (root.balanceFactor == -2) {
            if (root.right.balanceFactor == -1) {
                return rightRightCase(root);
            } else {
                return rightLeftCase(root);
            }
        }

        return root;
    }

    private Node rightLeftCase(Node root) {
        root.right = rotateRight(root.right);
        return rightRightCase(root);
    }

    private Node leftRightCase(Node root) {
        root.left = rotateLeft(root.left);
        return leftLeftCase(root);
    }

    private Node rightRightCase(Node root) {
        return rotateLeft(root);
    }

    private Node leftLeftCase(Node root) {
        return rotateRight(root);
    }

    private void update(Node root) {
        root.height = 1 + Math.max(height(root.left), height(root.right));
        root.balanceFactor = height(root.left) - height(root.right);
    }

    private int height(Node root) {
        if (root == null) return -1;
        return root.height;
    }


    // Zero Based
    public int rank(int key) {
        return rank(root, key);
    }

    // Zero Based
    private int rank(Node root, int key) {
        if (root == null) return 0;
        if (key == root.key) return size(root.left);
        if (key < root.key) return rank(root.left, key);
        return 1 + size(root.left) + rank(root.right, key);
    }


    public Node findLCA(int low, int high) {
        return findLCA(root, low, high);
    }

    private Node findLCA(Node root, int low, int high) {
        if (root == null) return null;
        if (root.key >= low && root.key <= high) return root;
        if (root.key < low) return findLCA(root.right, low, high);
        // if (root.key > high)
        return findLCA(root.left, low, high);
    }

    public List<Node> rangeSearch(int low, int high) {
        Node lca = findLCA(low, high);
        List<Node> list = new ArrayList<>();
        rangeSearch(root, low, high, list);
        return list;
    }

    private void rangeSearch(Node root, int low, int high, List<Node> list) {
        if (root == null) return;
        if (root.key >= low && root.key <= high) {
            list.add(root);
        }
        if (root.key >= low) {
            rangeSearch(root.left, low, high, list);
        }
        if (root.key <= high) {
            rangeSearch(root.right, low, high, list);
        }
    }

    public Node min() {
        return min(root);
    }

    private Node min(Node root) {
        if (root.left == null) return root;
        return min(root.left);
    }

    public Node floor(int key) {
        return floor(root, key);
    }

    private Node floor(Node root, int key) {
        if (root == null) return null;
        if (key == root.key) return root;
        if (key < root.key) return floor(root.left, key);
        // if key > root.key
        Node candidate = floor(root.right, key);
        if (candidate != null) {
            return candidate;
        } else {
            return root;
        }
    }

    void inOrder() {
        inOrder(root);
    }

    void preOrder() {
        preOrder(root);
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }


    private void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.key + " ");
        inOrder(root.right);
    }

    private void preOrder(Node root) {
        if (root == null) return;
        System.out.print(root.key + " ");
        preOrder(root.left);
        preOrder(root.right);
    }


    public static class Node {
        int key;
        String data;
        Node left;
        Node right;
        int balanceFactor;
        int count = 1;
        int height = 0;


        Node(int key) {
            this.key = key;
        }

        Node(int key, String data) {
            this.key = key;
            this.data = data;
        }

        public String toString() {
            if (data != null && data.length() > 0) return data;
            return String.valueOf(key);
        }
    }

    public static Node bst(int[] arr, int start, int end) {
        if (start < 0 || end > arr.length - 1 || end < start) return null;
        if (start == end) return new Node(arr[start]);
        // else
        int mid = start + (end - start) / 2;
        Node node = new Node(arr[mid]);
        node.left = bst(arr, start, mid - 1);
        node.right = bst(arr, mid + 1, end);
        return node;
    }

    public List<List<Node>> levelTraverse() {
        List<List<Node>> result = new ArrayList<>();
        List<Node> current = new ArrayList<>();
        current.add(root);
        while (!current.isEmpty()) {
            List<Node> children = new ArrayList<>();
            for (Node node : current) {
                if (node.left != null) children.add(node.left);
                if (node.right != null) children.add(node.right);
            }
            result.add(current);
            current = children;
        }
        return result;
    }

    public static boolean checkBST(Node node, Integer min, Integer max) {
        if (node == null) return true;

        int data = node.key;

        // check self
        boolean minVariant = min == null || data > min;
        boolean maxVariant = max == null || data <= max;

        if (minVariant && maxVariant) {
            // check subTrees
            return checkBST(node.left, min, data) && checkBST(node.right, data, max);
        } else return false;        // Unbalanced
    }

}

