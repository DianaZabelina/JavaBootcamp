import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SelectionSort {

  public static void showParseError() {
    System.out.println("Couldn't parse a number. Please, try again");
  }

  public static int readInt(String prompt, BufferedReader reader) throws IOException {
    while (true) {
      System.out.print(prompt);
      String line = reader.readLine();
      try {
        return Integer.parseInt(line);
      } catch (NumberFormatException e) {
        showParseError();
      }
    }
  }

  public static List<Double> readNumbers(int expectedSize, String prompt, BufferedReader reader)
      throws IOException {
    while (true) {
      System.out.print(prompt);
      String line = reader.readLine();
      if (line == null) {
        showParseError();
        continue;
      }

      String[] parts = line.trim().split("\\s+");
      if (parts.length != expectedSize) {
        showParseError();
        continue;
      }

      List<Double> numbers = new ArrayList<>();
      boolean allParsed = true;

      for (String p : parts) {
        try {
          numbers.add(Double.parseDouble(p));
        } catch (NumberFormatException e) {
          allParsed = false;
          break;
        }
      }

      if (allParsed) return numbers;
      showParseError();
    }
  }

  public static void selectionSort(List<Double> numbers) {
    for (int i = 0; i < numbers.size() - 1; i++) {
      int indexOfMin = i;

      for (int j = i + 1; j < numbers.size(); j++) {
        if (numbers.get(j) < numbers.get(indexOfMin)) {
          indexOfMin = j;
        }
      }

      Double temp = numbers.get(i);
      numbers.set(i, numbers.get(indexOfMin));
      numbers.set(indexOfMin, temp);
    }
  }

  public static void printResult(int size, BufferedReader reader) throws IOException {
    if (size <= 0) {
      System.out.println("Input error. Size <= 0");
      return;
    }

    List<Double> numbers = readNumbers(size, "Enter the numbers separated by spaces: ", reader);
    selectionSort(numbers);

    for (int i = 0; i < numbers.size(); i++) {
      if (i > 0) System.out.print(" ");
      System.out.print(numbers.get(i));
    }
    System.out.println();
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int size = readInt("Enter the number of elements: ", reader);
    printResult(size, reader);
  }
}
