package uk.co.leahshields.day1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day1Test {
  @Test
  public void shouldReturnCaloriesFromElfWithMost() {
    Day1 day1 = new Day1();
    assertEquals(24000, day1.part1("/day1_example.txt"));
  }

  @Test
  public void shouldGetPart1PuzzleAnswer() {
    Day1 day1 = new Day1();
    System.out.println(day1.part1("/day1_actual.txt"));
  }

  @Test
  public void shouldReturnCaloriesFromTop3ElvesWithMost() {
    Day1 day1 = new Day1();
    assertEquals(45000, day1.part2("/day1_example.txt"));
  }

  @Test
  public void shouldGetPart2PuzzleAnswer() {
    Day1 day1 = new Day1();
    System.out.println(day1.part2("/day1_actual.txt"));
  }
}
