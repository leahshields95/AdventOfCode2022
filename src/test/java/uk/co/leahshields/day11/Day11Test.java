package uk.co.leahshields.day11;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day11Test {
  @Test
  public void shouldReturn10605() {
    Day11 day11 = new Day11();
    assertEquals(10605, day11.part1("/day11_example.txt"));
  }

  @Test
  public void shouldGetPart1PuzzleAnswer() {
    Day11 day11 = new Day11();
    System.out.println(day11.part1("/day11_actual.txt"));
  }

  @Test
  public void shouldReturn2713310158() {
    Day11 day11 = new Day11();
    System.out.println(day11.part2("/day11_example.txt"));
  }

  @Test
  public void shouldGetPart2PuzzleAnswer() {
    Day11 day11 = new Day11();
    System.out.println(day11.part2("/day11_actual.txt"));
  }

}
