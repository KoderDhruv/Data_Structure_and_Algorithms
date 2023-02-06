public class bit_manipulation {

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
    System.out.println(clearBits(31,1,3));
  }
}
