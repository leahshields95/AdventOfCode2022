package uk.co.leahshields.day3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day3Test {
  @Test
  public void shouldGetTotalScore15() {
    Day3 day3 = new Day3();
    assertEquals(157, day3.part1("/day3_example.txt"));
  }

  @Test
  public void shouldGetPart1PuzzleAnswer() {
    Day3 day3 = new Day3();
    System.out.println(day3.part1("/day3_actual.txt"));
  }

  @Test
  public void shouldGetTotalScore12() {
    Day3 day3 = new Day3();
    assertEquals(70, day3.part2("/day3_example.txt"));
  }

  @Test
  public void shouldGetPart2PuzzleAnswer() {
    Day3 day3 = new Day3();
    System.out.println(day3.part2("/day3_actual.txt"));
  }
}
