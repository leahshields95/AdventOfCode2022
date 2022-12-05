package uk.co.leahshields.day4;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {

  public Day4() {

  }

  private List<String[]> fileToList(String inputLocation) {
    List<String[]> strategyGuide = new ArrayList<>();
    try (
        InputStream inputStream = this.getClass().getResourceAsStream(inputLocation);
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNext()) {
        String[] line = scanner.nextLine().split(",");
        strategyGuide.add(line);
      }

      inputStream.close();
      scanner.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
    return strategyGuide;
  }

  private int getStartOfRange(String sectionRange) {
    String[] range = sectionRange.split("-");
    return Integer.parseInt(range[0]);
  }

  private int getEndOfRange(String sectionRange) {
    String[] range = sectionRange.split("-");
    return Integer.parseInt(range[1]);
  }

  private boolean isFullyContained(String range1, String range2) {
    System.out.println("Range being checked: " + range1 );

    System.out.println("Checking against: " + range2);
    if (getStartOfRange(range1) >= getStartOfRange(range2) && getEndOfRange(range1) <= getEndOfRange(range2)) {
      return true;
    }
    if (getStartOfRange(range2) >= getStartOfRange(range1) && getEndOfRange(range2) <= getEndOfRange(range1)) {
      return true;
    }

    return false;
  }

  public int part1(String inputLocation) {
    List<String[]> sectionAssignments = fileToList(inputLocation);
    int count = 0;

    for (String[] section : sectionAssignments) {
      if (isFullyContained(section[0], section[1])) {
        count++;
      }
    }

    return count;
  }

  
  private boolean areOverlapped(String range1, String range2) {
    System.out.println("Range being checked: " + range1 );

    System.out.println("Checking against: " + range2);
    if (getStartOfRange(range1) <= getEndOfRange(range2) && getStartOfRange(range2) <= getEndOfRange(range1)) {
      return true;
    }

    return false;
  }


  public int part2(String inputLocation) {
    List<String[]> sectionAssignments = fileToList(inputLocation);
    int count = 0;

    for (String[] section : sectionAssignments) {
      if (areOverlapped(section[0], section[1])) {
        count++;
        System.out.println("Count: " + count);
      }
    }

    return count;
  }
}
