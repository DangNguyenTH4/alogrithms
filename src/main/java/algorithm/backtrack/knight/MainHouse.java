package algorithm.backtrack.knight;

import algorithm.backtrack.knight.cell.Cell;

public class MainHouse {

  public static void main(String[] args) {
    try {
      ImplementBacktrackKnight implementBacktrackKnight = new ImplementBacktrackKnight();
      long time = System.currentTimeMillis();
      implementBacktrackKnight.go(new Cell(1, 1), null, 8);
      System.out.println(System.currentTimeMillis()-time);
    }catch (Exception ex){
      ex.printStackTrace();
    }
  }
}
