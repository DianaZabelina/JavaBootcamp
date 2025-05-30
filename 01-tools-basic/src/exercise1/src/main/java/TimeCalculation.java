import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TimeCalculation {

  public static int readInt(String prompt, BufferedReader reader) throws IOException {
    while (true) {
      System.out.print(prompt);
      String input = reader.readLine();
      try {
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Couldn't parse a number. Please, try again");
      }
    }
  }

  public static int[] calculateTime(int rawTime) {
    int h = rawTime / 3600;
    int m = (rawTime % 3600) / 60;
    int s = rawTime % 60;

    return new int[] {h, m, s};
  }

  public static String formatTime(int h, int m, int s) {
    return String.format("%02d:%02d:%02d", h, m, s);
  }

  public static void printResult(int rawTime) {
    if (rawTime < 0) {
      System.out.println("Incorrect time");
    } else {
      int[] hms = calculateTime(rawTime);
      System.out.println(formatTime(hms[0], hms[1], hms[2]));
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int rawTime = readInt("Enter time in seconds: ", reader);
    printResult(rawTime);
  }
}
