package uk.co.leahshields.day6;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Day6 {
  String input;

  private void readFromFile(String inputLocation) {
    try (
        InputStream inputStream = this.getClass().getResourceAsStream(inputLocation);
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNext()) {
        input = scanner.nextLine();
      }

      inputStream.close();
      scanner.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
  }

  private int getMarker(int sizeOfMarker) {
    String sequence = "";
    for (int j = 0; j < input.length(); j++) {
      String character = Character.toString(input.charAt(j));
      if (sequence.contains(character)) {
        sequence = sequence.substring(sequence.indexOf(character) + 1);
      }
      sequence += character;
      if (sequence.length() == sizeOfMarker) {
        return j + 1;
      }
    }
    return 0;
  }

  public int part1(String inputLocation) {
    readFromFile(inputLocation);
    return getMarker(4);
  }

  public int part2(String inputLocation) {
    readFromFile(inputLocation);
    return getMarker(14);
  }
}
