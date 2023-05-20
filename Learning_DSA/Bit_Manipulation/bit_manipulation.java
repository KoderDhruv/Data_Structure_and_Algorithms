
public class bit_manipulation {

  public static int fastExpo(int base, int power) {
    int ans = 1;
    while (power > 0) {
      if ((power & 1) != 0) {
        ans = ans * base;
      }
      base = base * base;
      power = power >> 1;
    }
    return ans;
  }

  public static int countSetBits(int n) {
    int count = 0;
    while (n != 0) {
      if ((n & 1) == 1) {
        count++;
      }
      n = n >> 1;
    }
    return count;
  }

  public static boolean isPowerOfTwo(int n) {
    return ((n & (n - 1)) == 0);
  }

  public static int clearBits(int n, int i, int j) {
    int starting_mask = ((~0) << (j + 1));
    int ending_mask = (1 << i) - 1;
    return (n & (starting_mask | ending_mask));
  }

  public static int clearIBits(int n, int i) {
    int bitMask = (~0) << i;
    return (n & bitMask);
  }

  public static int updateIthBit(int n, int i, int newBit) {
    n = clearIthBit(n, i);
    int bitMask = newBit << i;
    return (n | bitMask);
  }

  public static int getIthBit(int n, int i) {
    int bitMask = 1 << i;
    if ((bitMask & n) == 1) {
      return 1;
    } else {
      return 0;
    }
  }

  public static int clearIthBit(int n, int i) {
    int bitMask = ~(1 << i);
    return (n & bitMask);
  }

  public static int setIthBit(int n, int i) {
    int bitMask = 1 << i;
    return (n | bitMask);
  }

  public static void main(String args[]) {
    System.out.println(fastExpo(2, 12));
  }
}
