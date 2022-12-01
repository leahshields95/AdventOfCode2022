package uk.co.leahshields.day1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day1 {
  private List<Integer> fileToList(String inputLocation) {
    List<Integer> elfCalories = new ArrayList<>();
    try (
        InputStream inputStream = this.getClass().getResourceAsStream(inputLocation);
        Scanner scanner = new Scanner(inputStream)) {
      int totalCalories = 0;
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        if (line != "") {
          totalCalories += Integer.parseInt(line);
        } else {
          elfCalories.add(totalCalories);
          totalCalories = 0;
        }
      }
      elfCalories.add(totalCalories);

      inputStream.close();
      scanner.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
    return elfCalories;
  }

  private int getHighestValue(List<Integer> elfCalories) {
    return Collections.max(elfCalories);
  }

  private int getTopElves(List<Integer> elfCalories, int count) {
    Collections.sort(elfCalories, (o1, o2) -> o2 - o1);
    int totalCalories = 0;
    for (int i = 0; i < count; i++) {
      totalCalories += elfCalories.get(i);
    }
    return totalCalories;
  }

  public int part1(String inputLocation) {
    return getHighestValue(fileToList(inputLocation));
  }

  public int part2(String inputLocation) {
    return getTopElves(fileToList(inputLocation), 3);
  }
}
