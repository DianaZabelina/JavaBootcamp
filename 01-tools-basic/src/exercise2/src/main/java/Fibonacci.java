import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

  private static final int MIN_NUM = 0;
  private static final int MAX_NUM = 46;

  public static int readInt(String prompt, BufferedReader reader) throws IOException {
    while (true) {
      System.out.print(prompt);
      String line = reader.readLine();
      try {
        return Integer.parseInt(line);
      } catch (NumberFormatException e) {
        System.out.println("Couldn't parse a number. Please, try again");
      }
    }
  }

  public static int fibonacci(int n) {
    Map<Integer, Integer> memo = new HashMap<>();
    return fib(n, memo);
  }

  private static int fib(int x, Map<Integer, Integer> memo) {
    if (x == 0) return 0;
    if (x == 1) return 1;
    if (memo.containsKey(x)) return memo.get(x);

    int value = fib(x - 1, memo) + fib(x - 2, memo);
    memo.put(x, value);

    return value;
  }

  public static void printResult(int num) {
    if (num < MIN_NUM) {
      System.out.println("Too small n");
    } else if (num > MAX_NUM) {
      System.out.println("Too large n");
    } else {
      System.out.println(fibonacci(num));
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int num = readInt("Enter a number: ", reader);
    printResult(num);
  }
}
