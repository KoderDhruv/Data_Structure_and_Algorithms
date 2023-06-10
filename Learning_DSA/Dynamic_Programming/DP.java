public class DP {

  public static int fibMemoization(int n) {
    int f[] = new int[n + 1];
    if (n == 0 || n == 1) {
      return n;
    }
    if (f[n] != 0) {
      return f[n];
    }
    f[n] = fibMemoization(n - 1) + fibMemoization(n - 2);
    return f[n];
  }

  public static int fibTabulation(int n) {
    int dp[] = new int[n + 1];
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }

  public static int knapsack(int val[], int wt[], int W, int n) {
    if (W == 0 || n == 0) {
      return 0;
    }
    if (wt[n - 1] <= W) { // ifValid
      // include
      int ans1 = val[n - 1] + knapsack(val, wt, W - wt[n - 1], n - 1);
      // exclude
      int ans2 = knapsack(val, wt, W, n - 1);
      return Math.max(ans1, ans2);
    } else {
      return knapsack(val, wt, W, n - 1);
    }
  }

  public static int knapsackMemoization(int[] val, int[] wt, int W, int n) {
    int dp[][] = new int[val.length + 1][W + 1];
    for (int i = 0; i < val.length + 1; i++) {
      for (int j = 0; j < W + 1; j++) {
        dp[i][j] = -1;
      }
    }
    if (W == 0 || n == 0) {
      return 0;
    }
    if (dp[n][W] != -1) {
      return dp[n][W];
    }
    if (wt[n - 1] <= W) { // ifValid
      // include
      int ans1 = val[n - 1] + knapsackMemoization(val, wt, W - wt[n - 1], n - 1);
      // exclude
      int ans2 = knapsackMemoization(val, wt, W, n - 1);
      dp[n][W] = Math.max(ans1, ans2);
      return dp[n][W];
    } else {
      dp[n][W] = knapsackMemoization(val, wt, W, n - 1);
      return dp[n][W];
    }
  }

  public static int knapsackTabulation(){
    return 0;
  }

  public static void main(String args[]) {
    // Fibonacci
    // int n = 5;
    // System.out.println(fibMemoization(n));
    // System.out.println(fibTabulation(n));

    // 0-1 Knapsack
    int val[] = { 15, 14, 10, 45, 30 };
    int wt[] = { 2, 5, 1, 3, 4 };
    int W = 7;
    System.out.println(knapsack(val, wt, W, val.length));
    System.out.println(knapsackMemoization(val, wt, W, val.length));
    System.out.println();
  }
}
