import java.util.*;

public class Greedy {

  public static void chocola(int n, int m, Integer costVer[], Integer costHor[]) {
    Arrays.sort(costHor, Collections.reverseOrder());
    Arrays.sort(costVer, Collections.reverseOrder());
    int h = 0, v = 0;
    int h_pieces = 1, v_pieces = 1;
    int cost = 0;
    while (h < costHor.length && v < costVer.length) {
      if (costVer[v] <= costHor[h]) {
        cost += (costHor[h] * v_pieces);
        h_pieces++;
        h++;
      } else {
        cost += (costVer[v] * h_pieces);
        v_pieces++;
        v++;
      }
    }
    while (h < costHor.length) {
      cost += (costHor[h] * v_pieces);
      h_pieces++;
      h++;
    }
    while (v < costVer.length) {
      cost += (costVer[v] * h_pieces);
      v_pieces++;
      v++;
    }
    System.out.println(cost);
  }

  static class Job {
    int profit;
    int deadline;
    int id;

    public Job(int i, int d, int p) {
      id = i;
      deadline = d;
      profit = p;
    }
  }

  public static void jobSequencing(int jobsInfo[][]) {
    ArrayList<Job> jobs = new ArrayList<>();
    for (int i = 0; i < jobsInfo.length; i++) {
      jobs.add(new Job(i, jobsInfo[i][0], jobsInfo[i][1]));
    }
    Collections.sort(jobs, (obj1, obj2) -> obj2.profit - obj1.profit);
    ArrayList<Integer> seq = new ArrayList<>();
    int time = 0;
    for (int i = 0; i < jobs.size(); i++) {
      Job curr = jobs.get(i);
      if (curr.deadline > time) {
        seq.add(curr.id);
        time++;
      }
    }
    System.out.println(seq);
  }

  public static void indianCoins(Integer coins[]) {
    Arrays.sort(coins, Comparator.reverseOrder());
    int countOfCoins = 0;
    int amount = 590;
    ArrayList<Integer> ans = new ArrayList<>();
    for (int i = 0; i < coins.length; i++) {
      if (coins[i] <= amount) {
        while (coins[i] <= amount) {
          countOfCoins++;
          ans.add(coins[i]);
          amount -= coins[i];
        }
      }
    }
    System.out.println("Total (min) coins used = " + countOfCoins);
    System.out.println(ans);
  }

  public static void minAbsDiff(int A[], int B[]) {
    Arrays.sort(A);
    Arrays.sort(B);
    int minDiff = 0;
    for (int i = 0; i < A.length; i++) {
      minDiff += Math.abs(A[i] - B[i]);
    }
    System.out.println("Minimum Absolute Difference is " + minDiff);
  }

  public static void fractionalKnapsack(int weight[], int value[], int W) {
    double ratio[][] = new double[value.length][2];
    for (int i = 0; i < value.length; i++) {
      ratio[i][0] = i;
      ratio[i][1] = value[i] / (double) weight[i];
    }
    Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));
    int capacity = W;
    int finalVal = 0;
    for (int i = ratio.length - 1; i >= 0; i--) {
      int idx = (int) ratio[i][0];
      if (capacity >= weight[idx]) {
        finalVal += value[idx];
        capacity -= weight[idx];
      } else {
        finalVal += (ratio[i][1] * capacity);
        capacity = 0;
        break;
      }
    }
    System.out.println("Final capacity is " + finalVal);
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
    // int weight[] = { 10, 20, 30 };
    // int value[] = { 60, 100, 120 };
    // int W = 50;
    // fractionalKnapsack(weight, value, W);

    // Minimum Absolute Difference
    // int A[] = { 4, 1, 8, 7 };
    // int B[] = { 2, 3, 6, 5 };
    // minAbsDiff(A, B);

    // Indian coins
    // Integer coins[] = { 1, 2, 5, 10, 20, 50, 100, 500, 2000 };
    // indianCoins(coins);

    // Job Sequencing
    // int jobsInfo[][] = { { 4, 20 }, { 1, 10 }, { 1, 40 }, { 1, 30 } };
    // jobSequencing(jobsInfo);

    // Chocola Problem
    int n = 4;
    int m = 6;
    Integer costVer[] = { 2, 1, 3, 1, 4 };
    Integer costHor[] = { 4, 2, 1 };
    chocola(n, m, costVer, costHor);
  }
}
