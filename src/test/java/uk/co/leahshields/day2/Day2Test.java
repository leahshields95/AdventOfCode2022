package uk.co.leahshields.day2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day2Test {
  @Test
  public void shouldGetTotalScore15() {
    Day2 day2 = new Day2();
    assertEquals(15, day2.part1("/day2_example.txt"));
  }

  @Test
  public void shouldGetPart1PuzzleAnswer() {
    Day2 day2 = new Day2();
    System.out.println(day2.part1("/day2_actual.txt"));
  }

  @Test
  public void shouldGetTotalScore12() {
    Day2 day2 = new Day2();
    assertEquals(12, day2.part2("/day2_example.txt"));
  }

  @Test
  public void shouldGetPart2PuzzleAnswer() {
    Day2 day2 = new Day2();
    System.out.println(day2.part2("/day2_actual.txt"));
  }
}
