public class tries {
  static class Node {
    Node children[] = new Node[26];
    boolean eow = false;
    int freq;

    Node() {
      for (int i = 0; i < 26; i++) {
        children[i] = null;
      }
      freq = 1; // for prefix problem
    }
  }

  public static Node root = new Node();

  public static void insert(String word) {
    Node curr = root;
    for (int level = 0; level < word.length(); level++) {
      int idx = word.charAt(level) - 'a';
      if (curr.children[idx] == null) {
        curr.children[idx] = new Node();
      } else { // else is for prefix problem
        curr.children[idx].freq++;
      }
      curr = curr.children[idx];
    }
    curr.eow = true;
  }

  public static boolean search(String key) {
    Node curr = root;
    for (int level = 0; level < key.length(); level++) {
      int idx = key.charAt(level) - 'a';
      if (curr.children[idx] == null) {
        return false;
      }
      curr = curr.children[idx];
    }
    return curr.eow == true;
  }

  public static boolean wordBreak(String key) {
    if (key.length() == 0) {
      return true;
    }
    for (int i = 1; i <= key.length(); i++) {
      if (search(key.substring(0, i)) && wordBreak(key.substring(i))) {
        return true;
      }
    }
    return false;
  }

  public static void findPrefix(Node root, String ans) {
    if (root == null) {
      return;
    }
    if (root.freq == 1) {
      System.out.print(ans + " ");
      return;
    }
    for (int i = 0; i < root.children.length; i++) {
      if (root.children[i] != null) {
        findPrefix(root.children[i], ans + (char) (i + 'a'));
      }
    }
  }

  public static boolean startsWith(String prefix) {
    Node curr = root;
    for (int i = 0; i < prefix.length(); i++) {
      int idx = prefix.charAt(i) - 'a';
      if (curr.children[idx] == null) {
        return false;
      }
      curr=curr.children[idx]; 
    }
    return true;
  }

  public static void main(String args[]) {
    // Implementation from Scratch
    // String words[] = { "the", "a", "there", "their", "any", "thee" };
    // for (int i = 0; i < words.length; i++) {
    // insert(words[i]);
    // }
    // System.out.println(search("thee"));

    // Word Break Problem
    // String arr[] = { "i", "like", "sam", "samsung", "mobile", "ice" };
    // for (int i = 0; i < arr.length; i++) {
    // insert(arr[i]);
    // }
    // String key = "isamsunglikesam";
    // System.out.println(wordBreak(key));

    // Prefix Problem
    // String arr[] = { "zebra", "dog", "duck", "dove" };
    // for (int i = 0; i < arr.length; i++) {
    // insert(arr[i]);
    // }
    // root.freq = -1;
    // findPrefix(root, "");

    // startsWith Problem
    // String words[] = { "apple", "app", "mango", "man", "women" };
    // String prefix1 = "app";
    // String prefix2 = "moon";
    // for (int i = 0; i < words.length; i++) {
    //   insert(words[i]);
    // }
    // System.out.println(startsWith(prefix1));
    // System.out.println(startsWith(prefix2));

    // 
  }
}
