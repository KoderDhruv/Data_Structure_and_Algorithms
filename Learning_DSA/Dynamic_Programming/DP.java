import java.util.*;

public class DP {
  // Matrix Chain Multiplication
  public static void matrixChainMultiplication() {
    
  }

  // Mountain Ranges
  // pair of '/\', also same as Catalan's Number
  // '/\' is base, we add inside and outside and add iteratively

  // Counting BSTs - Count number of BSTs for a specific number of nodes.
  // Answer will be same as Catalan's Number, think about it!!!!
  // Iteratively add left and right

  // Catalan's Number (Easy)
  // C(n)= C(0)C(n-1) + C(1)C(n-2) + C(2)C(n-3)...C(n-1)C(0)
  public static void catalabTabulation(int n) {
    int dp[] = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i < n + 1; i++) {
      for (int j = 0; j < i; j++) {
        dp[i] += dp[j] * dp[i - j - 1];
      }
    }
    // print dp
    for (int i = 0; i < n + 1; i++)
      System.out.print(dp[i] + " ");
  }

  public static int catalanMemoization(int n, int dp[]) {
    if (n == 1 || n == 0)
      return 1;
    if (dp[n] != -1)
      return dp[n];
    int ans = 0;
    for (int i = 0; i < n; i++) {
      ans += catalanMemoization(i, dp) * catalanMemoization(n - i - 1, dp);
    }
    return dp[n] = ans;
  }

  public static int catalanRec(int n) {
    if (n == 0 || n == 1)
      return 1;
    int ans = 0;
    for (int i = 0; i < n; i++) {
      ans += catalanRec(i) * catalanRec(n - i - 1);
    }
    return ans;
  }

  // Wild Card-matching (Hard)
  public static void wildCardMatching(String s, String p) {
    int n = s.length(), m = p.length();
    // Initialization
    boolean dp[][] = new boolean[n + 1][m + 1];
    dp[0][0] = true; // True always as zero string==zero pattern
    for (int i = 1; i < n + 1; i++)
      dp[i][0] = false;
    for (int j = 1; j < m + 1; j++) {
      if (p.charAt(j - 1) == '*') { // will match previous value as it can take empty value
        dp[0][j] = dp[0][j - 1];
      } else {
        dp[0][j] = false;
      }
    }
    // DP
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < m + 1; j++) {
        if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
          dp[i][j] = dp[i - 1][j - 1];
        } else if (p.charAt(j - 1) == '*') {
          dp[i][j] = dp[i - 1][j] || dp[i][j - 1]; // either '*' is taken repeatedly or not at all, and if either works,
                                                   // output true
        } else {
          dp[i][j] = false;
        }
      }
    }
    System.out.println(dp[n][m]);
  }

  // String Comversion
  public static void stringConversion(String str1, String str2) {
    int n = str1.length(), m = str2.length();
    int dp[][] = new int[n + 1][m + 1];
    for (int i = 0; i < n + 1; i++)
      dp[i][0] = i;
    for (int i = 0; i < m + 1; i++)
      dp[0][i] = i;
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < m + 1; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          int add = dp[i][j - 1];
          int del = dp[i - 1][j];
          int replace = dp[i - 1][j - 1];
          dp[i][j] = Math.min(add, Math.min(del, replace)) + 1;
        }
      }
    }
    System.out.println(dp[n][m]);
  }

  // Longest Increasing Subsequence -
  // Here we first create a copy of original array and sort it, then we find
  // longest common subsequence
  public static void longestIncreasingSubsequence(int arr1[]) {
    HashSet<Integer> set = new HashSet<>();
    for (int i = 0; i < arr1.length; i++)
      set.add(arr1[i]);
    int arr2[] = new int[set.size()];
    int i = 0;
    for (int num : set) {
      arr2[i++] = num;
    }
    Arrays.sort(arr2);
    System.out.println(util(arr1, arr2));
  }

  public static int util(int arr1[], int arr2[]) {
    int n = arr1.length, m = arr2.length;
    int dp[][] = new int[n + 1][m + 1];
    for (int i = 0; i < n + 1; i++)
      dp[i][0] = 0;
    for (int i = 0; i < m + 1; i++)
      dp[0][i] = 0;
    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length; j++) {
        if (arr1[i - 1] == arr2[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[n][m];
  }

  // Longest Common Substring
  public static void longestCommonSubstring(String str1, String str2) {
    int n = str1.length(), m = str2.length(), ans = 0;
    int dp[][] = new int[n + 1][m + 1];
    for (int i = 0; i < dp.length; i++)
      dp[i][0] = 0;
    for (int i = 0; i < dp[0].length; i++)
      dp[0][i] = 0;
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < m + 1; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
          ans = Math.max(ans, dp[i][j]);
        } else {
          dp[i][j] = 0;
        }
      }
    }
    System.out.println(ans);
  }

  // Longest Common Subsequence
  public static int lcs(String str1, String str2, int n, int m) {
    if (n == 0 || m == 0) {
      return 0;
    }
    if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
      return lcs(str1, str2, n - 1, m - 1) + 1;
    } else {
      int ans1 = lcs(str1, str2, n - 1, m);
      int ans2 = lcs(str1, str2, n, m - 1);
      return Math.max(ans1, ans2);
    }
  }

  public static int lcsMemoization(String str1, String str2, int n, int m, int dp[][]) {
    if (n == 0 || m == 0)
      return 0;
    if (dp[n][m] != -1)
      return dp[n][m];
    if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
      return dp[n][m] = lcsMemoization(str1, str2, n - 1, m - 1, dp) + 1;
    } else {
      return dp[n][m] = Math.max(lcsMemoization(str1, str2, n - 1, m, dp), lcsMemoization(str1, str2, n, m - 1, dp));
    }
  }

  public static void lcsTabulation(String str1, String str2, int n, int m) {
    int dp[][] = new int[n + 1][m + 1];
    for (int i = 0; i < dp.length; i++)
      dp[i][0] = 0;
    for (int i = 0; i < dp[0].length; i++)
      dp[0][i] = 0;
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < m + 1; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[0].length; j++) {
        System.out.print(dp[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println(dp[n][m]);
  }

  // Rod Cutting
  public static void cutRod(int length[], int price[], int total) {
    int dp[][] = new int[price.length + 1][total + 1];
    for (int i = 0; i < dp.length; i++)
      dp[i][0] = 0;
    for (int i = 0; i < dp[0].length; i++)
      dp[0][i] = 0;
    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length; j++) {
        if (length[i - 1] <= j) {
          dp[i][j] = Math.max(dp[i - 1][j], price[i - 1] + dp[i][j - length[i - 1]]);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    // print dp array
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[0].length; j++) {
        System.out.print(dp[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println(dp[price.length][total]);
  }

  // Coin-Change
  public static void coinChange(int coins[], int sum) {
    int n = coins.length;
    int dp[][] = new int[n + 1][sum + 1];
    for (int i = 0; i < dp.length; i++)
      dp[i][0] = 1;
    for (int i = 1; i < dp[0].length; i++)
      dp[0][i] = 0;
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < sum + 1; j++) {
        int val = coins[i - 1];
        if (val <= j) {
          dp[i][j] = dp[i - 1][j] + dp[i][j - val]; // as we are counting cases and not finding maximum, we will add
                                                    // previous solutions + current
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    // print dp array
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[0].length; j++) {
        System.out.print(dp[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println(dp[n][sum]);
  }

  // Unbounded-knapsack
  public static void unboundedKnapsack(int val[], int wt[], int W) {
    int n = val.length;
    int[][] dp = new int[n + 1][W + 1];
    for (int i = 0; i < dp.length; i++)
      dp[i][0] = 0;
    for (int i = 0; i < dp[0].length; i++)
      dp[0][i] = 0;
    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < W + 1; j++) {
        int v = val[i - 1];
        int w = wt[i - 1];
        if (w <= j) {
          int excProfit = dp[i - 1][j];
          int incProfit = v + dp[i][j - w]; // only difference from 0-1 knapsack, "i" instead of "i-1" as we have to
                                            // include the valye as many times as possible.
          dp[i][j] = Math.max(excProfit, incProfit);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    // print dp array
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[0].length; j++) {
        System.out.print(dp[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println(dp[n][W]);
  }

  // Target Sum Subset
  public static void targetSum(int arr[], int sum) {
    int n = arr.length;
    boolean[][] dp = new boolean[n + 1][sum + 1];
    for (int i = 0; i < n + 1; i++)
      dp[i][0] = true;
    for (int i = 1; i < dp.length; i++) { // "i"
      for (int j = 1; j < dp[0].length; j++) {
        int val = arr[i - 1];
        if (dp[i - 1][j] == true) {
          dp[i][j] = true;
        } else {
          if (val <= j) {
            if (dp[i - 1][j - val] == true) {
              dp[i][j] = true;
            }
          }
        }
      }
    }
    // print dp array
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[0].length; j++) {
        System.out.print(dp[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println(dp[n][sum]);
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
    for (int i = 0; i < n + 1; i++) { // "i" is number of items
      for (int j = 0; j < W + 1; j++) { // "j" is maximum weight
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

  // Climbing Stairs
  // Same as fibonacci if upto 2 jumps, we take sum of previous three if upto 3
  // jumps possible

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
    // int arr[] = { 4, 2, 7, 1, 3 };
    // int sum = 10;
    // targetSum(arr, sum);

    // Unbounded knapsack
    // int val[] = { 15, 14, 10, 45, 30 };
    // int wt[] = { 2, 3, 1, 3, 4 };
    // int W = 7;
    // unboundedKnapsack(val, wt, W);

    // Coin Change
    // int coins[] = { 1, 2, 3 };
    // int sum = 4;
    // coinChange(coins, sum);

    // Rod-Cutting
    // int length[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
    // int price[] = { 1, 5, 8, 9, 10, 17, 17, 20 };
    // int total = 8;
    // cutRod(length, price, total);

    // Longest-Common-Subsequence
    // String str1 = "abcdge";
    // String str2 = "abedg";
    // int n = str1.length(), m = str2.length();
    // System.out.println(lcs(str1, str2, n, m));
    // int dp[][] = new int[n + 1][m + 1];
    // for (int i = 0; i < n + 1; i++) {
    // for (int j = 0; j < m + 1; j++) {
    // dp[i][j] = -1;
    // }
    // }
    // System.out.println(lcsMemoization(str1, str2, n, m, dp));
    // lcsTabulation(str1, str2, n, m);

    // Longest-Common-Substring
    // String str1="abcde";
    // String str2="ajljde";
    // longestCommonSubstring(str1, str2);

    // Longest Increasing Subsequence
    // int arr1[] = { 50, 3, 10, 7, 40, 80 };
    // longestIncreasingSubsequence(arr1);

    // String Conversion
    // String str1 = "intention";
    // String str2 = "execution";
    // stringConversion(str1, str2);

    // Wildcard Matching
    // String s = "baaabab";
    // String p = "*****ba*****ab";
    // wildCardMatching(s, p);

    // Catalan's Number
    // int n = 5;
    // int dp[] = new int[n + 1];
    // Arrays.fill(dp, -1);
    // System.out.println(catalanRec(n));
    // System.out.println(catalanMemoization(n, dp));
    // catalabTabulation(n);
  }
}
