class maximum_subarray {
  public static boolean isNegative(int[] nums) {
    boolean allNegative = true;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] >= 0) {
        allNegative = false;
      }
    }
    return allNegative;
  }

  public static int maxSubArray(int[] nums) {
    if (isNegative(nums)) {
      int maxi = Integer.MIN_VALUE;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] > maxi) {
          maxi = nums[i];
        }
      }
      return maxi;
    } else {
      int sum = 0;
      int maxSum = 0;
      for (int i = 0; i < nums.length; i++) {
        sum = sum + nums[i];
        if (sum >= 0 && sum >= maxSum) {
          maxSum = sum;
        } else if (sum < 0) {
          sum = 0;
        }
      }
      return maxSum;
    }
  }
  public static void main(String args[]){
    int arr[]={-1};
    System.out.println(maxSubArray(arr));
  }
}