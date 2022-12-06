package uk.co.leahshields.day5;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day5Test {
  @Test
  public void shouldGetCratesCMZ() {
    Day5 day5 = new Day5();
    assertEquals("CMZ", day5.part1("/day5_example.txt"));
  }

  @Test
  public void shouldGetPart1PuzzleAnswer() {
    Day5 day5 = new Day5();
    System.out.println(day5.part1("/day5_actual.txt"));
  }

  @Test
  public void shouldGetCrates() {
    Day5 day5 = new Day5();
    assertEquals("MCD", day5.part2("/day5_example.txt"));
  }

  @Test
  public void shouldGetPart2PuzzleAnswer() {
    Day5 day5 = new Day5();
    System.out.println(day5.part2("/day5_actual.txt"));
  }
}
