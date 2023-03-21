import java.util.*;

public class BST {
  static class Node {
    int data;
    Node right;
    Node left;

    Node(int data) {
      this.data = data;
      this.right = null;
      this.left = null;
    }
  }

  public static Node insert(Node root, int val) {
    if (root == null) {
      root = new Node(val);
      return root;
    }
    if (val < root.data) {
      root.left = insert(root.left, val);
    } else {
      root.right = insert(root.right, val);
    }
    return root;
  }

  public static void inorder(Node root) {
    if (root == null) {
      return;
    }
    inorder(root.left);
    System.out.print(root.data + " ");
    inorder(root.right);
  }

  public static boolean search(Node root, int key) {
    if (root == null) {
      return false;
    }
    if (root.data == key) {
      return true;
    }
    if (key > root.data) {
      return search(root.right, key);
    } else {
      return search(root.left, key);
    }
  }

  public static Node inOrderSuccessor(Node root) {
    while (root.left != null) {
      root = root.left;
    }
    return root;
  }

  public static Node delete(Node root, int val) {
    if (val > root.data) {
      root.right = delete(root.right, val);
    } else if (val < root.data) {
      root.left = delete(root.left, val);
    } else {
      // leaf node
      if (root.left == null && root.right == null) {
        return null;
      }
      // single child node
      if (root.left == null) {
        return root.right;
      } else if (root.right == null) {
        return root.left;
      }
      // 2 children nodes
      Node IS = inOrderSuccessor(root.right);
      root.data = IS.data;
      root.right = delete(root.right, IS.data);
    }
    return root;
  }

  public static void printInRange(Node root, int k1, int k2) {
    if (root == null) {
      return;
    }
    if (root.data >= k1 && root.data < k2) {
      printInRange(root.left, k1, k2);
      System.out.print(root.data + " ");
      printInRange(root.right, k1, k2);
    } else if (root.data >= k1) {
      printInRange(root.left, k1, k2);
    } else {
      printInRange(root.right, k1, k2);
    }
  }

  public static void printRoot2Leaf(Node root, ArrayList<Integer> path) {
    if (root == null) {
      return;
    }
    path.add(root.data);
    if (root.left == null && root.right == null) {
      for (int i = 0; i < path.size(); i++) {
        System.out.print(path.get(i) + " ");
      }
      System.out.println();
    }
    printRoot2Leaf(root.left, path);
    printRoot2Leaf(root.right, path);
    path.remove(path.size() - 1);
  }

  public static boolean isValidBST(Node root, Node min, Node max) {
    if (root == null) {
      return true;
    }
    if (min != null && root.data <= min.data) {
      return false;
    } else if (max != null && root.data >= max.data) {
      return false;
    }
    return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
  }

  public static Node mirrorBST(Node root) {
    if (root == null) {
      return null;
    }
    Node leftMirror = mirrorBST(root.left);
    Node rightMirror = mirrorBST(root.right);
    root.right = leftMirror;
    root.left = rightMirror;
    return root;
  }

  public static Node createBST(int arr[], int start, int end) {
    if (start > end) {
      return null;
    }
    int mid = start + ((end - start) / 2);
    Node root = new Node(arr[mid]);
    root.left = createBST(arr, start, mid - 1);
    root.right = createBST(arr, mid + 1, end);
    return root;
  }

  static class Info {
    boolean isBST;
    int size;
    int min;
    int max;

    public Info(boolean isBST, int size, int min, int max) {
      this.isBST = isBST;
      this.size = size;
      this.min = min;
      this.max = max;
    }
  }

  public static int maxBST = 0;

  public static Info largestBST(Node root) {
    if (root == null) {
      return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
    Info leftInfo = largestBST(root.left);
    Info rightInfo = largestBST(root.right);
    int size = leftInfo.size + rightInfo.size + 1;
    int min = Math.min(root.data, Math.min(leftInfo.min, rightInfo.min));
    int max = Math.max(root.data, Math.max(leftInfo.max, rightInfo.max));
    if (root.data >= rightInfo.min || root.data <= leftInfo.max) {
      return new Info(false, size, min, max);
    }
    if (leftInfo.isBST && rightInfo.isBST) {
      maxBST = Math.max(maxBST, size);
      return new Info(true, size, min, max);
    }
    return new Info(false, size, min, max);
  }

  public static void main(String args[]) {
    // int values[] = { 8, 5, 3, 1, 4, 6, 10, 11, 14 };
    // Node root = null;
    // for (int i = 0; i < values.length; i++) {
    // root = insert(root, values[i]);
    // }
    // inorder(root);
    // System.out.println(search(root, 1));

    // Delete operation
    // inorder(root);
    // System.out.println();
    // delete(root, 5);
    // inorder(root);

    // printInRange(root, 5, 12);
    // printRoot2Leaf(root, new ArrayList<>());
    // System.out.println(isValidBST(root, null, null));

    // Mirror BST
    // mirrorBST(root);
    // inorder(root);

    // Sorted Array to Balanced BST
    // int arr[] = { 3, 5, 6, 8, 10, 11, 12 };
    // inorder(createBST(arr, 0, arr.length-1));

    // Convert to Balanced BST
    // first convert to array (sorted in-order) then use above function to create
    // Balanced BST

    // Size of larget valid BST
    // Node root = new Node(50);
    // root.left = new Node(30);
    // root.left.left = new Node(5);
    // root.left.right = new Node(20);
    // root.right = new Node(60);
    // root.right.left = new Node(45);
    // root.right.right = new Node(70);
    // root.right.right.left = new Node(65);
    // root.right.right.right = new Node(80);
    // Info info = largestBST(root);
    // System.out.println(maxBST);

    // Merge 2 BST
    
  }
}
