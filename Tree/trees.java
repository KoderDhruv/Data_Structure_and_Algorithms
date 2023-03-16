import java.util.*;
import java.util.LinkedList;

public class trees {

  static class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
  }

  static class BinaryTree {
    static int idx = -1;

    public Node buildTree(int nodes[]) {
      idx++;
      if (nodes[idx] == -1) {
        return null;
      }
      Node newNode = new Node(nodes[idx]);
      newNode.left = buildTree(nodes);
      newNode.right = buildTree(nodes);

      return newNode;
    }

    public void preorder(Node root) {
      if (root == null) {
        return;
      }
      System.out.print(root.data + " ");
      preorder(root.left);
      preorder(root.right);
    }

    public void inorder(Node root) {
      if (root == null) {
        return;
      }
      inorder(root.left);
      System.out.print(root.left + " ");
      inorder(root.right);
    }

    public void postorder(Node root) {
      if (root == null) {
        return;
      }
      postorder(root.left);
      postorder(root.right);
      System.out.print(root.data + " ");
    }

    public void levelOrder(Node root) {
      if (root == null) {
        return;
      }
      Queue<Node> q = new LinkedList<>();
      q.add(root);
      q.add(null);
      while (!q.isEmpty()) {
        Node currNode = q.remove();
        if (currNode == null) {
          System.out.println();
          if (q.isEmpty()) {
            break;
          } else {
            q.add(null);
          }
        } else {
          System.out.print(currNode.data + " ");
          if (currNode.left != null) {
            q.add(currNode.left);
          }
          if (currNode.right != null) {
            q.add(currNode.right);
          }
        }
      }
    }

    public int height(Node root) {
      if (root == null) {
        return 0;
      }
      int l_height = height(root.left);
      int r_height = height(root.right);
      return (Math.max(l_height, r_height) + 1);
    }

    public int countNodes(Node root) {
      if (root == null) {
        return 0;
      }
      int l_count = countNodes(root.left);
      int r_count = countNodes(root.right);
      return (l_count + r_count + 1);
    }

    public int sumOfNodes(Node root) {
      if (root == null) {
        return 0;
      }
      int l_sum = sumOfNodes(root.left);
      int r_sum = sumOfNodes(root.right);
      return (l_sum + r_sum + root.data);
    }

    public int diameter(Node root) {
      if (root == null) {
        return 0;
      }
      int leftDiam = diameter(root.left);
      int leftHt = height(root.left);
      int rightDiam = diameter(root.right);
      int rightHt = height(root.right);
      int selfDiam = leftHt + rightHt + 1;
      return Math.max(selfDiam, Math.max(leftDiam, rightDiam));
    }

    public class Info {
      int diam;
      int ht;

      Info(int diam, int ht) {
        this.diam = diam;
        this.ht = ht;
      }
    }

    public Info diameter_optimized(Node root) {
      if (root == null) {
        return new Info(0, 0);
      }
      Info leftInfo = diameter_optimized(root.left);
      Info rightInfo = diameter_optimized(root.right);
      int diam = Math.max(Math.max(leftInfo.diam, rightInfo.diam), (leftInfo.ht + rightInfo.ht + 1));
      int ht = Math.max(leftInfo.ht, rightInfo.ht) + 1;
      return new Info(diam, ht);
    }
  }

  public static boolean isIdentical(Node node, Node subroot) {
    if (node == null && subroot == null) {
      return true;
    } else if (node == null || subroot == null || node.data != subroot.data) {
      return false;
    }
    if (!isIdentical(node.left, subroot.left)) {
      return false;
    }
    if (!isIdentical(node.right, subroot.right)) {
      return false;
    }
    return true;
  }

  public static boolean isSubtree(Node root, Node subroot) {
    if (root == null) {
      return false;
    }
    if (root.data == subroot.data) {
      if (isIdentical(root, subroot)) {
        return true;
      }
    }
    return isSubtree(root.left, subroot) || isSubtree(root.right, subroot);
  }

  public static void main(String args[]) {
    // int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
    // BinaryTree tree = new BinaryTree();
    // Node root = tree.buildTree(nodes);
    // tree.preorder(root);
    // tree.postorder(root);
    // tree.levelOrder(root);
    // System.out.println(tree.height(root));
    // System.out.println(tree.countNodes(root));
    // System.out.println(tree.sumOfNodes(root));
    // System.out.println(tree.diameter(root));
    // System.out.println(tree.diameter_optimized(root).diam);

    // subtree of another tree
    /*
                       1                    2
                      /  \                 / \
                     2    3                4   5
                    / \  / \
                   4   5 6  7
     */
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.right.left = new Node(6);
    root.right.right = new Node(7);

    Node subroot = new Node(2);
    subroot.left = new Node(4);
    subroot.right = new Node(5);

    System.out.println(isSubtree(root, subroot));
  }
}
