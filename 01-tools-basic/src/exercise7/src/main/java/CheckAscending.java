import java.util.Scanner;

public class CheckAscending {

  public static void processSequence(Scanner sc) {
    System.out.print("Enter the sequence of numbers: ");
    Integer prev = null;
    boolean started = false;
    int index = 0;

    while (sc.hasNext()) {
      String token = sc.next();
      try {
        int num = Integer.parseInt(token);
        if (prev != null && num < prev) {
          System.out.println(
              "The sequence is not ordered from the ordinal number of the number " + index);
          return;
        }
        prev = num;
        started = true;
        index++;
      } catch (NumberFormatException e) {
        if (!started) {
          System.out.println("Input error");
        } else {
          System.out.println("The sequence is ordered in ascending order");
        }
        return;
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    processSequence(sc);
  }
}
