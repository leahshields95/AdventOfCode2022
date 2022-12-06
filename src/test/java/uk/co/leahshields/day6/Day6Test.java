package uk.co.leahshields.day6;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day6Test {
  @Test
  public void shouldReturn7() {
    Day6 day6 = new Day6();
    assertEquals(7, day6.part1("/day6_example.txt"));
  }

  @Test
  public void shouldGetPart1PuzzleAnswer() {
    Day6 day6 = new Day6();
    System.out.println(day6.part1("/day6_actual.txt"));
  }

  @Test
  public void shouldReturn19() {
    Day6 day6 = new Day6();
    assertEquals(19, day6.part2("/day6_example.txt"));
  }

  @Test
  public void shouldGetPart2PuzzleAnswer() {
    Day6 day6 = new Day6();
    System.out.println(day6.part2("/day6_actual.txt"));
  }

}
