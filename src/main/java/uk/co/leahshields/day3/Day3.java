package uk.co.leahshields.day3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day3 {
  private Map<Character, Integer> priorityMap = new HashMap<>();

  public Day3() {
    for (int i = 0; i < 26; i++) {
      int startingValue = 'a';
      int priority = i + 1;
      char letter = (char) (startingValue + i);
      priorityMap.put(letter, priority);
    }
    for (int i = 0; i < 26; i++) {
      int startingValue = 'A';
      int priority = i + 27;
      char letter = (char) (startingValue + i);
      priorityMap.put(letter, priority);
    }
  }

  private List<String[]> fileToList(String inputLocation) {
    List<String[]> strategyGuide = new ArrayList<>();
    try (
        InputStream inputStream = this.getClass().getResourceAsStream(inputLocation);
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        final int mid = line.length() / 2;
        String[] compartments = { line.substring(0, mid), line.substring(mid) };

        strategyGuide.add(compartments);
      }

      inputStream.close();
      scanner.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
    return strategyGuide;
  }

  private int getNumberOfCommonItems(String[] rucksackContents) {
    char commonItem = 'a';
    for (char item : rucksackContents[0].toCharArray()) {
      for (char item2 : rucksackContents[1].toCharArray()) {
        if (item == item2) {
          commonItem = item;
        }
      }
    }
    return priorityMap.get(commonItem);
  }

  public int part1(String inputLocation) {
    List<String[]> rucksacks = fileToList(inputLocation);
    int total = 0;
    for (String[] rucksack : rucksacks) {
      total += getNumberOfCommonItems(rucksack);
    }

    return total;
  }

  private int findElfBadge(List<String[]> elfGroup) {
    char commonItem = 'a';

    String firstRucksack = elfGroup.get(0)[0] + elfGroup.get(0)[1];
    String secondRucksack = elfGroup.get(1)[0] + elfGroup.get(1)[1];
    String thirdRucksack = elfGroup.get(2)[0] + elfGroup.get(2)[1];
    for (char item : firstRucksack.toCharArray()) {
      for (char item2 : secondRucksack.toCharArray()) {
        if (item == item2) {
          for (char item3 : thirdRucksack.toCharArray()) {
            if (item == item3) {
              commonItem = item;
              break;
            }
          }
        }
      }
    }
    return priorityMap.get(commonItem);
  }

  public int part2(String inputLocation) {
    List<String[]> rucksacks = fileToList(inputLocation);
    List<List<String[]>> groups = new ArrayList<>();
    for (int i = 0; i < rucksacks.size(); i += 3) {
      List<String[]> group = new ArrayList<>();
      group.add(rucksacks.get(i));
      group.add(rucksacks.get(i + 1));
      group.add(rucksacks.get(i + 2));
      groups.add(group);
    }

    int totalScore = 0;
    for (List<String[]> group : groups) {
      totalScore += findElfBadge(group);
    }
    return totalScore;
  }
}
