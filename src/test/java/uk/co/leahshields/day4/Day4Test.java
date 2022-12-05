package uk.co.leahshields.day4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day4Test {
  @Test
  public void shouldGetTotalScore15() {
    Day4 day4 = new Day4();
    assertEquals(2, day4.part1("/day4_example.txt"));
  }

  @Test
  public void shouldGetPart1PuzzleAnswer() {
    Day4 day4 = new Day4();
    System.out.println(day4.part1("/day4_actual.txt"));
  }

  @Test
  public void shouldGetTotalScore12() {
    Day4 day4 = new Day4();
    assertEquals(4, day4.part2("/day4_example.txt"));
  }

  @Test
  public void shouldGetPart2PuzzleAnswer() {
    Day4 day4 = new Day4();
    System.out.println(day4.part2("/day4_actual.txt"));
  }
}
