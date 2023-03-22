import java.beans.BeanProperty;
import java.util.*;

public class heaps {
  static class Student implements Comparable<Student> {
    String name;
    int rank;

    public Student(String name, int rank) {
      this.name = name;
      this.rank = rank;
    }

    @Override
    public int compareTo(Student s2) {
      return this.rank - s2.rank;
    }
  }

  static class Heap {
    ArrayList<Integer> arr = new ArrayList<>();

    public void add(int data) {
      arr.add(data);
      int child = arr.size() - 1; // because inserted at last position
      int parent = (child - 1) / 2; // child = 2*(parent+1) and 2*(parent+2)
      while (arr.get(parent) > arr.get(child)) {
        int temp = arr.get(child);
        arr.set(child, arr.get(parent));
        arr.set(parent, temp);
        child = parent;
        parent = (child - 1) / 2;
      }
    }

    public int peek() {
      return arr.get(0);
    }

    private void heapify(int i) {
      int left = (2 * i) + 1;
      int right = (2 * i) + 2;
      int minIdx = i;
      if (left < arr.size() && arr.get(minIdx) > arr.get(left)) {
        minIdx = left;
      }
      if (right < arr.size() && arr.get(minIdx) > arr.get(right)) {
        minIdx = right;
      }
      if (minIdx != i) {
        int temp = arr.get(i);
        arr.set(i, arr.get(minIdx));
        arr.set(minIdx, temp);
        heapify(minIdx);
      }
    }

    public int remove() {
      // Deleting first element
      // swap first and last, then delete last, then check minimum between root,
      // leftchild and rightchild and swap it with root
      int data = arr.get(0);
      int temp = arr.get(0);
      arr.set(0, arr.get(arr.size() - 1));
      arr.set(arr.size() - 1, temp);
      arr.remove(arr.size() - 1);
      heapify(0);
      return data;
    }

    public boolean isEmpty() {
      return arr.size() == 0;
    }
  }

  public static void heapify(int arr[], int i, int size) {
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    int maxIdx = i;
    if (left < size && arr[left] > arr[maxIdx]) {
      maxIdx = left;
    }
    if (right < size && arr[right] > arr[maxIdx]) {
      maxIdx = right;
    }
    if (maxIdx != i) {
      int temp = arr[i];
      arr[i] = arr[maxIdx];
      arr[maxIdx] = temp;
      heapify(arr, maxIdx, size);
    }
  }

  public static void heapSort(int arr[]) {
    // Create a maxheap, swap first and last and except last element call heapify
    // and last elements will be in order
    int n = arr.length;
    for (int i = n / 2; i >= 0; i--) {
      heapify(arr, i, n);
    }
    for (int i = n - 1; i > 0; i--) {
      int temp = arr[0];
      arr[0] = arr[i];
      arr[i] = temp;
      heapify(arr, 0, i);
    }
  }

  static class Point implements Comparable<Point> {
    int x;
    int y;
    int distSq;
    int idx;

    public Point(int x, int y, int distSq, int idx) {
      this.x = x;
      this.y = y;
      this.distSq = distSq;
      this.idx = idx;
    }

    @Override
    public int compareTo(Point p2) {
      return this.distSq - p2.distSq;
    }
  }

  public static void kNearestNeighbours(int pts[][], int k) {
    PriorityQueue<Point> pq = new PriorityQueue<>();
    for (int i = 0; i < pts.length; i++) {
      pq.add(new Point(pts[i][0], pts[i][1], ((pts[i][0] * pts[i][0]) + (pts[i][1] * pts[i][1])), i));
    }
    for (int i = 0; i < k; i++) {
      System.out.println(pq.remove().idx);
    }
  }

  public static int minRopeCost(int ropes[]) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < ropes.length; i++) {
      pq.add(ropes[i]);
    }
    int cost = 0;
    while (pq.size() > 1) {
      int min = pq.remove();
      int min2 = pq.remove();
      cost += min + min2;
      pq.add(min + min2);
    }
    return cost;
  }

  static class Row implements Comparable<Row> {
    int soldiers;
    int idx;

    Row(int soldiers, int idx) {
      this.soldiers = soldiers;
      this.idx = idx;
    }

    @Override
    public int compareTo(Row r2) {
      if (this.soldiers == r2.soldiers) {
        return this.idx - r2.idx;
      } else {
        return this.soldiers - r2.soldiers;
      }
    }
  }

  public static void kWeakSoldiers(int army[][], int k) {
    PriorityQueue<Row> pq = new PriorityQueue<>();
    for (int i = 0; i < army.length; i++) {
      int count = 0;
      for (int j = 0; j < army[0].length; j++) {
        count += army[i][j] == 1 ? 1 : 0;
      }
      pq.add(new Row(count, i));
    }
    for (int i = 0; i < k; i++) {
      System.out.print(pq.remove().idx + " ");
    }
  }

  static class Pair implements Comparable<Pair> {
    int val;
    int idx;

    Pair(int val, int idx) {
      this.val = val;
      this.idx = idx;
    }

    @Override
    public int compareTo(Pair p2) {
      return p2.val - this.val; // decsending
    }
  }

  public static void slidingWindowMax(int arr[], int k) {
    
  }

  public static void main(String args[]) {
    // Implementation PriorityQueue

    // PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    // pq.add(3);
    // pq.add(4);
    // pq.add(1);
    // while (!pq.isEmpty()) {
    // System.out.print(pq.peek() + " ");
    // pq.remove();
    // }
    // PriorityQueue<Student> students = new PriorityQueue<>();
    // students.add(new Student("A", 4));
    // students.add(new Student("B", 5));
    // students.add(new Student("C", 2));
    // students.add(new Student("D", 12));
    // while (!students.isEmpty()) {
    // System.out.print(students.peek().name + " ");
    // students.remove();
    // }

    // Implementation Heaps
    // Heap h = new Heap();
    // h.add(3);
    // h.add(4);
    // h.add(1);
    // h.add(5);
    // while (!h.isEmpty()) {
    // System.out.println(h.peek());
    // h.remove();
    // }

    // Heap sort
    // int arr[] = { 1, 2, 4, 5, 3 };
    // heapSort(arr);
    // for (int i = 0; i < arr.length; i++) {
    // System.out.print(arr[i] + " ");
    // }
    // System.out.println();

    // Nearby cars
    // int pts[][] = { { 3, 3 }, { 5, -1 }, { -2, 4 } };
    // int k = 2;
    // kNearestNeighbours(pts, k);

    // Connect N ropes
    // int ropes[] = { 2, 3, 3, 4, 6 };
    // System.out.println(minRopeCost(ropes));

    // K weakest rows
    // int army[][] = {
    // { 1, 0, 0, 0 },
    // { 1, 1, 1, 1 },
    // { 1, 0, 0, 0 },
    // { 1, 0, 0, 0 } };
    // int k = 2;
    // kWeakSoldiers(army, k);

    // Maximum element in Sliding Window
    int arr[] = { 1, 3, -1, -3, 5, 3, 6, 7 };
    int k = 3; // sliding_window
  }
}
