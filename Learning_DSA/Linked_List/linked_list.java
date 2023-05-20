
public class linked_list {
  public static class Node {
    int data;
    Node next;

    public Node(int data) {
      this.data = data;
      this.next = null;
    }
  }

  static class Linked_List {

    public Node head;
    public Node tail;
    int size;

    public void addFirst(int data) {
      Node newNode = new Node(data);
      if (head == null) {
        head = tail = newNode;
        return;
      }
      newNode.next = head;
      head = newNode;
      size++;
    }

    public void addLast(int data) {
      Node newNode = new Node(data);
      if (head == null) {
        head = tail = newNode;
        return;
      }
      // Node currNode = head;
      // while (currNode.next != null) {
      // currNode = currNode.next;
      // }
      // currNode.next = newNode;
      // size++;
      tail.next = newNode;
      tail = newNode;
      size++;
    }

    public void printList() {
      if (head == null) {
        System.out.println("LL is empty");
        return;
      }
      Node temp = head;
      while (temp != null) {
        System.err.print(temp.data + " -> ");
        temp = temp.next;
      }
      System.out.print("null");
      System.out.println();
    }

    public void add(int idx, int data) {
      if (idx == 0) {
        addFirst(data);
        return;
      }
      Node newNode = new Node(data);
      Node temp = head;
      size++;
      for (int i = 0; i < (idx - 1); i++) {
        temp = temp.next;
      }
      newNode.next = temp.next;
      temp.next = newNode;
    }

    public void removeFirst() {
      if (size == 0) {
        System.out.println("LL is empty");
      } else if (size == 1) {
        head = tail = null;
        size = 0;
      }
      head = head.next;
      size--;
    }

    public void removeLast() {
      if (size == 0) {
        System.out.println("LL is empty");
      } else if (size == 1) {
        head = tail = null;
        size = 0;
      }
      Node prev = head;
      for (int i = 0; i < size - 2; i++) {
        prev = prev.next;
      }
      prev.next = null;
      tail = prev;
      size--;
    }

    public int itrSearch(int key) {
      Node temp = head;
      for (int i = 0; temp != null; i++) {
        if (temp.data == key) {
          return i;
        } else {
          temp = temp.next;
        }
      }
      return -1;
    }

    public int recursiveSearch(Node head, int key, int idx) {
      if (head == null) {
        return -1;
      }
      if (head.data == key) {
        return idx;
      }
      return recursiveSearch(head.next, key, idx + 1);
    }

    public void reverse() {
      // declare next, change curr node pointer then shift prev and curr forward
      Node prev = null;
      Node curr = tail = head;
      Node next;
      while (curr != null) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }
      head = prev;
    }

    public void deleteNfromEnd(int n) {
      int sz = 0;
      Node temp = head;
      while (temp != null) {
        temp = temp.next;
        sz++;
      }
      if (n == sz) {
        head = head.next;
        return;
      }
      int i = 0;
      int iToFind = sz - n - 1;
      Node prev = head;
      while (i < iToFind) {
        prev = prev.next;
        i++;
      }
      prev.next = prev.next.next;
      return;
    }

    public Node findMid(Node head) {
      Node slow = head;
      Node fast = head;
      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }
      return slow;
    }

    public boolean isPalindrome() {
      if (head == null || head.next == null) {
        return true;
      }
      Node midNode = findMid(head);
      // reverse second half
      Node prev = null;
      Node curr = midNode;
      Node next;
      while (curr != null) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }
      // checking right and left half
      Node right = prev;
      Node left = head;
      while (right != null) {
        if (left.data != right.data) {
          return false;
        }
        left = left.next;
        right = right.next;
      }
      return true;
    }

    public boolean isCycle() {
      Node slow = head;
      Node fast = head;
      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
          return true;
        }
      }
      return false;
    }

    public void removeCycle() {
      // first detect cycle, then assign slow to head and then traverse slow and fast
      // by one node, the node where they meet is junction, last node will be previous
      // of last
      Node slow = head;
      Node fast = head;
      boolean cycle = false;
      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
          cycle = true;
          break;
        }
      }
      if (cycle == false) {
        return;
      }
      // finding meeting point and pointing prev to null
      slow = head;
      Node prev = null;
      while (slow != fast) {
        prev = fast;
        slow = slow.next;
        fast = fast.next;
      }
      prev.next = null;
    }

    public Node getMid(Node head) {
      Node slow = head;
      Node fast = head;
      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }
      return slow;
    }

    public Node merge(Node head1, Node head2) {
      Node merged = new Node(-1);
      Node temp = merged;

      while (head1 != null && head2 != null) {
        if (head1.data < head2.data) {
          temp.next = head1;
          head1 = head1.next;
        } else {
          temp.next = head2;
          head2 = head2.next;
        }
        temp = temp.next;
      }
      while (head1 != null) {
        temp.next = head1;
        head1 = head1.next;
        temp = temp.next;
      }
      while (head2 != null) {
        temp.next = head2;
        head2 = head2.next;
        temp = temp.next;
      }
      return merged.next;
    }

    public Node mergeSort(Node head) {
      if (head == null || head.next == null) {
        return head;
      }
      Node mid = getMid(head);
      Node secondHalf = mid.next;
      mid.next = null;
      Node left = mergeSort(head);
      Node right = mergeSort(secondHalf);
      Node sorted = merge(left, right);
      return sorted;
    }

    public void zigZag(Node head) {
      // Find Mid
      Node slow = head;
      Node fast = head;
      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }
      Node mid = slow;

      // Reverse Second half
      Node curr = mid.next;
      mid.next = null;
      Node prev = null;
      Node next;
      while (curr != null) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }
      Node left = head;
      Node right = prev;
      Node nextL, nextR;

      // Alternate Merge
      while (left != null && right != null) {
        nextL = left.next;
        left.next = right;
        nextR = right.next;
        right.next = nextL;
        left = nextL;
        right = nextR;
      }
    }
  }

  public static class NodeDll {
    int data;
    NodeDll next;
    NodeDll prev;

    public NodeDll(int data) {
      this.data = data;
      this.next = null;
      this.prev = null;
    }
  }

  static class DoublyLinked_List {
    public NodeDll headDll;
    public NodeDll tailDll;
    public int sizeDll;

    public void addFirst(int data) {
      NodeDll newNodeDll = new NodeDll(data);
      if (headDll == null) {
        headDll = tailDll = newNodeDll;
        return;
      }
      newNodeDll.next = headDll;
      headDll.prev = newNodeDll;
      headDll = newNodeDll;
      sizeDll++;
    }

    public void printDll() {
      NodeDll temp = headDll;
      while (temp != null) {
        System.out.print(temp.data + " <-> ");
        temp = temp.next;
      }
      System.out.println("null");
    }

    public int removeFirstDll() {
      if (headDll == null) {
        System.out.println("Dll is empty!");
        return Integer.MIN_VALUE;
      }
      if (sizeDll == 1) {
        int val = headDll.data;
        headDll = tailDll = null;
        sizeDll--;
        return val;
      }
      int val = headDll.data;
      headDll = headDll.next;
      headDll.prev = null;
      sizeDll--;
      return val;
    }

    public void reverseDll() {
      NodeDll curr = headDll;
      NodeDll prev = null;
      NodeDll next;
      while (curr != null) {
        next = curr.next;
        curr.next = prev;
        curr.prev = next;
        prev = curr;
        curr = next;
      }
      headDll = prev;
    }
  }

  public static void main(String[] args) {
    // Implementation from scratch
    // Linked_List ll = new Linked_List();
    // ll.addFirst(1);
    // ll.addFirst(2);
    // ll.addFirst(3);
    // ll.addFirst(4);
    // ll.addLast(8);
    // ll.addLast(6);
    // ll.addLast(5);
    // ll.add(2, 2);
    // ll.printList();
    // System.out.println(ll.recursiveSearch(head, 8, 0));
    // ll.reverse();
    // ll.printList();
    // ll.head = ll.mergeSort(ll.head);
    // ll.printList();

    // Zig-Zag Linked list
    // Linked_List ll = new Linked_List();
    // ll.addLast(1);
    // ll.addLast(2);
    // ll.addLast(3);
    // ll.addLast(4);
    // ll.addLast(5);
    // ll.zigZag(ll.head);
    // ll.printList();

    // Implementation DoublyLinkedList
    DoublyLinked_List dll = new DoublyLinked_List();
    dll.addFirst(3);
    dll.addFirst(2);
    dll.addFirst(1);
    dll.printDll();
    dll.removeFirstDll();
    dll.printDll();
    dll.reverseDll();
    dll.printDll();
  }
}