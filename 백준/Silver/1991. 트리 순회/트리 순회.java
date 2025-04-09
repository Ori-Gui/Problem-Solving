import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        char data;
        Node left;
        Node right;

        Node(char data) {
            this.data = data;
        }
    }

    static Map<Character, Node> tree = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char l = st.nextToken().charAt(0);
            char r = st.nextToken().charAt(0);

            tree.putIfAbsent(root, new Node(root));
            Node rootNode = tree.get(root);

            if (l != '.') {
                tree.putIfAbsent(l, new Node(l));
                rootNode.left = tree.get(l);
            } else {
                rootNode.left = null;
            }

            if (r != '.') {
                tree.putIfAbsent(r, new Node(r));
                rootNode.right = tree.get(r);
            } else {
                rootNode.right = null;
            }
        }

        Node root = tree.get('A');

        System.out.println(preorder(root));
        System.out.println(inorder(root));
        System.out.println(postorder(root));
    }

    static String preorder(Node node) {
        if (node == null) {
            return "";
        }
        return node.data + preorder(node.left) + preorder(node.right);
    }

    static String inorder(Node node) {
        if (node == null) {
            return "";
        }
        return inorder(node.left) + node.data + inorder(node.right);
    }

    static String postorder(Node node) {
        if (node == null) {
            return "";
        }
        return postorder(node.left) + postorder(node.right) + node.data;
    }
}
