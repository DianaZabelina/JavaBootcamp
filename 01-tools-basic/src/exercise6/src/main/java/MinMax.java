import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MinMax {

  private static final String RESULT_FILE = "result.txt";

  public static String getFilePath(String prompt) throws IOException {
    System.out.print(prompt);
    BufferedReader streamReader = new BufferedReader(new java.io.InputStreamReader(System.in));
    String path = streamReader.readLine();

    if (path == null || path.trim().isEmpty()) {
      System.out.println("Input error. Path is empty");
      return "";
    }

    return path;
  }

  public static List<String> readFile(String path) {
    File file = new File(path);
    if (!file.exists()) {
      System.out.println("Input error. File doesn't exist");
      return null;
    }

    List<String> lines = new ArrayList<>();
    try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = fileReader.readLine()) != null) {
        lines.add(line);
      }
      return lines;
    } catch (IOException e) {
      return null;
    }
  }

  public static boolean isValidFormat(List<String> lines) {
    if (lines.size() != 2) {
      System.out.println("Input error. File is empty or has invalid format");
      return false;
    }

    return true;
  }

  public static int parseSize(String line) {
    try {
      int size = Integer.parseInt(line);
      return size > 0 ? size : -1;
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  public static boolean isValidSize(Integer size) {
    if (size <= 0) {
      System.out.println("Input error. Size <= 0");
      return false;
    }

    return true;
  }

  public static List<Double> parseDoubles(String line) {
    String[] parts = line.trim().split("\\s+");
    List<Double> numbers = new ArrayList<>();

    for (String part : parts) {
      try {
        numbers.add(Double.parseDouble(part));
      } catch (NumberFormatException ignored) {
      }
    }

    return numbers;
  }

  public static boolean hasEnoughElements(int expectedSize, int actualSize) {
    if (actualSize != expectedSize) {
      System.out.println("Input error. Insufficient number of elements");
      return false;
    }

    return true;
  }

  public static void writeResult(double min, double max) {
    try (FileWriter writer = new FileWriter(RESULT_FILE)) {
      writer.write(min + " " + max);
      System.out.println("Saving min and max values in file");
    } catch (IOException ignored) {
    }
  }

  public static void processFile() throws IOException {
    String path = getFilePath("Enter the file path: ");
    if (path.isEmpty()) return;

    List<String> lines = readFile(path);
    if (lines == null || !isValidFormat(lines)) return;

    int size = parseSize(lines.get(0));
    if (!isValidSize(size)) return;

    List<Double> numbers = parseDoubles(lines.get(1));
    if (!hasEnoughElements(size, numbers.size())) return;

    double min = numbers.get(0);
    double max = numbers.get(0);

    for (int i = 1; i < size; i++) {
      double number = numbers.get(i);
      if (number < min) min = number;
      if (number > max) max = number;
    }

    System.out.println(size);

    for (int i = 0; i < size; i++) {
      if (i > 0) System.out.print(" ");
      System.out.print(numbers.get(i));
    }
    System.out.println();

    writeResult(min, max);
  }

  public static void main(String[] args) throws IOException {
    processFile();
  }
}
