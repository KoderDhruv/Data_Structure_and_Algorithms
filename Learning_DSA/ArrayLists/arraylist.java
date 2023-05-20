import java.util.*;

public class arraylist {

  public static boolean pairSumRotated(ArrayList<Integer> list, int target) {
    int pivot = -1;
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) > list.get(i + 1)) {
        pivot = i;
        break;
      }
    }
    int lp = pivot + 1;
    int rp = pivot;
    while (lp < rp) {
      if ((list.get(lp) + list.get(rp)) == target) {
        return true;
      }
      if ((list.get(lp) + list.get(rp)) < target) {
        lp = (lp + 1) % list.size();
      } else {
        rp = (rp - 1) % list.size();
      }
    }
    return false;
  }

  public static boolean pairSum(ArrayList<Integer> list, int target) {
    int lp = 0;
    int rp = list.size() - 1;
    while (lp < rp) {
      if ((list.get(lp) + list.get(rp)) == target) {
        return true;
      }
      if ((list.get(lp) + list.get(rp)) > target) {
        lp++;
      } else {
        rp--;
      }
    }
    return false;
  }

  public static int storeWater(ArrayList<Integer> height) {
    int maxWater = 0;
    int lp = 0;
    int rp = height.size() - 1;
    while (lp < rp) {
      int currWater = ((Math.min(height.get(lp), height.get(rp))) * (rp - lp));
      maxWater = Math.max(currWater, maxWater);
      if (height.get(lp) < height.get(rp)) {
        lp++;
      } else {
        rp--;
      }
    }
    return maxWater;
  }

  public static void Array_List2D() {
    ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
    ArrayList<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    mainList.add(list);

    ArrayList<Integer> list2 = new ArrayList<>();
    list2.add(3);
    list2.add(4);
    mainList.add(list2);

    for (int i = 0; i < mainList.size(); i++) {
      ArrayList<Integer> currList = mainList.get(i);
      for (int j = 0; j < currList.size(); j++) {
        System.out.println(currList.get(j) + " ");
      }
      System.out.println();
    }
  }

  public static void swap(ArrayList<Integer> list, int idx1, int idx2) {
    int temp = list.get(idx1);
    list.set(idx1, list.get(idx2));
    list.set(idx2, temp);
  }

  public static void main(String args[]) {
    ArrayList<Integer> list = new ArrayList<>();
    list.add(2);
    list.add(8);
    list.add(5);
    list.add(3);
    swap(list, 2, 3);
    System.out.println(list);
    Collections.sort(list);
    System.out.println(list);
    Collections.sort(list, Collections.reverseOrder());
    System.out.println(list);
  }
}
