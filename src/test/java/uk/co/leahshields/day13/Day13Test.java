package uk.co.leahshields.day13;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day13Test {
  @Test
  public void shouldReturn13() {
    Day13 day13 = new Day13();
    assertEquals(13, day13.part1("/day13_example.txt"));
  }

  @Test
  public void shouldGetPart1PuzzleAnswer() {
    Day13 day13 = new Day13();
    System.out.println(day13.part1("/day13_actual.txt"));
  }

  @Test
  public void shouldReturn140() {
    Day13 day13 = new Day13();
    assertEquals(140, day13.part2("/day13_example.txt"));
  }

  @Test
  public void shouldGetPart2PuzzleAnswer() {
    Day13 day13 = new Day13();
    System.out.println(day13.part2("/day13_actual.txt"));
  }

}
