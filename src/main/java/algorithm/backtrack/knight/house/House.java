package algorithm.backtrack.knight.house;

import algorithm.backtrack.knight.cell.Cell;
import java.util.Stack;

public class House {
  Stack<Cell> steps = new Stack<>();

  public Stack<Cell> getSteps() {
    return steps;
  }

  public void setSteps(Stack<Cell> steps) {
    this.steps = steps;
  }
}
