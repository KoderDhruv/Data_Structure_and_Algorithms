
public class DP {
  // Target Sum Subset
  public static void targetSum(int arr[], int sum) {
    int n = arr.length;
    boolean[][] dp = new boolean[n + 1][sum + 1];
    for (int i = 0; i < n + 1; i++)
      dp[i][0] = true;

  }

  // 0-1 Knapsack
  public static int knapsack(int val[], int wt[], int W, int n) {
    if (W == 0 || n == 0)
      return 0;
    if (wt[n - 1] <= W) { // ifValid
      // include
      int ans1 = val[n - 1] + knapsack(val, wt, W - wt[n - 1], n - 1);
      // exclude
      int ans2 = knapsack(val, wt, W, n - 1);
      return Math.max(ans1, ans2);
    } else { // not Valid, so exclude
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
    if (W == 0 || n == 0)
      return 0;
    if (dp[n][W] != -1)
      return dp[n][W];
    if (wt[n - 1] <= W) { // ifValid
      // include
      int ans1 = val[n - 1] + knapsackMemoization(val, wt, W - wt[n - 1], n - 1);
      // exclude
      int ans2 = knapsackMemoization(val, wt, W, n - 1);
      dp[n][W] = Math.max(ans1, ans2);
      return dp[n][W];
    } else { // not Valid, so exclude
      dp[n][W] = knapsackMemoization(val, wt, W, n - 1);
      return dp[n][W];
    }
  }

  public static int knapsackTabulation(int[] val, int[] wt, int W, int n) {
    int dp[][] = new int[val.length + 1][W + 1];
    for (int i = 0; i < dp.length; i++)
      dp[i][0] = 0;
    for (int i = 0; i < dp[0].length; i++)
      dp[0][i] = 0;
    for (int i = 1; i < n + 1; i++) { // "i" is number of items
      for (int j = 1; j < W + 1; j++) { // "j" is maximum weight
        int v = val[i - 1];
        int w = wt[i - 1];
        if (w <= j) { // ifValid
          int incProfit = v + dp[i - 1][j - w]; // include
          int excProfit = dp[i - 1][j]; // exclude
          dp[i][j] = Math.max(incProfit, excProfit);
        } else { // if not Valid, so exclude
          int excProfit = dp[i - 1][j];
          dp[i][j] = excProfit;
        }
      }
    }
    // Printing dp array
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[0].length; j++) {
        System.out.print(dp[i][j] + " ");
      }
      System.out.println();
    }
    return dp[n][W];
  }

  // Fibonacci series
  public static int fibMemoization(int n) {
    int f[] = new int[n + 1];
    if (n == 0 || n == 1)
      return n;
    if (f[n] != 0)
      return f[n];
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

  public static void main(String args[]) {
    // Fibonacci
    // int n = 5;
    // System.out.println(fibMemoization(n));
    // System.out.println(fibTabulation(n));

    // 0-1 Knapsack
    // int val[] = { 15, 14, 10, 45, 30 };
    // int wt[] = { 2, 5, 1, 3, 4 };
    // int W = 7;
    // System.out.println(knapsack(val, wt, W, val.length));
    // System.out.println(knapsackMemoization(val, wt, W, val.length));
    // System.out.println(knapsackTabulation(val, wt, W, val.length));

    // Target-Sum
    int arr[] = { 4, 2, 1, 7, 1, 3 };
    int sum = 10;
    targetSum(arr, sum);
  }
}
