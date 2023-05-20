
public class array {

  public static void max_profit_of_stock(int prices[]) {
    int buy_price = Integer.MAX_VALUE;
    int max_profit = 0;

    for (int i = 0; i < prices.length; i++) {
      if (buy_price < prices[i]) {
        int profit = prices[i] - buy_price;
        max_profit = Math.max(profit, max_profit);
      } else {
        buy_price = prices[i];
      }
    }
    System.out.println(max_profit);
  }

  public static void trapped_rainwater(int array[]) {
    int n = array.length;
    int left_max[] = new int[n];
    left_max[0] = array[0];

    for (int i = 1; i < n; i++) {
      left_max[i] = Math.max(array[i], left_max[i - 1]);
    }

    int right_max[] = new int[n];
    right_max[n - 1] = array[n - 1];

    for (int i = n - 2; i >= 0; i--) {
      right_max[i] = Math.max(array[i], right_max[i + 1]);
    }

    int trapped_water = 0;

    for (int i = 0; i < n; i++) {
      trapped_water += Math.min(left_max[i], right_max[i]) - array[i];
    }

    System.out.println(trapped_water);
  }

  public static void max_subarray_sum(int numbers[]) {
    int current_sum = 0;
    int max_sum = Integer.MIN_VALUE;
    int prefix[] = new int[numbers.length];

    prefix[0] = numbers[0];
    for (int i = 1; i < prefix.length; i++) {
      prefix[i] = prefix[i - 1] + numbers[i];
    }
    for (int i = 0; i < numbers.length; i++) {
      for (int j = i; j < numbers.length; j++) {

        // current_sum = (i == 0) ? prefix[j] : prefix[j] - prefix[i - 1];

        if (i == 0) {
          current_sum = prefix[j];
        } else {
          current_sum = prefix[j] - prefix[i - 1];
        }

        if (max_sum < current_sum) {
          max_sum = current_sum;
        }
      }
    }
    System.out.println(max_sum);
  }

  public static void kodanes(int numbers[]) {
    int max_sum = Integer.MIN_VALUE;
    int current_sum = 0;

    for (int i = 0; i < numbers.length; i++) {
      current_sum = current_sum + numbers[i];
      if (current_sum < 0) {
        current_sum = 0;
      }
      max_sum = Math.max(current_sum, max_sum);
    }
    System.out.println(max_sum);
  }

  public static void main(String args[]) {
    // int numbers[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
    // int rainwater_array[] = { 4, 2, 0, 6, 3, 2, 5 };
    // kodanes(numbers);
    // max_subarray_sum(numbers);
    // trapped_rainwater(rainwater_array);
    int prices[] = { 7, 1, 5, 3, 6, 4 };
    max_profit_of_stock(prices);
  }
}