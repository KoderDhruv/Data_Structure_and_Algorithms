import java.util.*;

public class Greedy {

  public static void fractionalKnapsack(int weight[], int value[], int W) {
    double ratio[][] = new double[value.length][2];
    for (int i = 0; i < value.length; i++) {
      ratio[i][0] = i;
      ratio[i][1] = value[i] / (double) weight[i];
    }
    Arrays.sort(ratio, Comparator.comparingDouble(o->o[1]));
  }

  public static void maxActivity(int start[], int end[]) {
    ArrayList<Integer> ans = new ArrayList<>();

    // Sorting
    int activities[][] = new int[start.length][3];
    for (int i = 0; i < start.length; i++) {
      activities[i][0] = i;
      activities[i][1] = start[i];
      activities[i][2] = end[i];
    }

    // Sorting 2d arrays according to column
    Arrays.sort(activities, Comparator.comparingInt(o -> o[2]));

    // 1st Activity
    int maxAct = 1;
    ans.add(activities[0][0]);
    int prevEnd = activities[0][2];
    for (int i = 0; i < end.length; i++) {
      if (activities[i][1] >= prevEnd) {
        maxAct++;
        ans.add(activities[i][0]);
        prevEnd = activities[i][2];
      }
    }
    for (int i = 0; i < ans.size(); i++) {
      System.out.print("A" + ans.get(i) + " ");
    }
    System.out.println();
  }

  public static void main(String args[]) {
    // Maximum activity selection
    // int start[] = { 1, 3, 0, 5, 8, 5 };
    // int end[] = { 2, 4, 6, 7, 9, 9 };
    // maxActivity(start, end);

    // Fractional knapsack
    int weight[] = { 10, 20, 30 };
    int value[] = { 60, 100, 120 };
    int W = 50;
    fractionalKnapsack(weight, value, W);
  }
}
