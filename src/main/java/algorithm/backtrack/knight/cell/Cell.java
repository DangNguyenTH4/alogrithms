package algorithm.backtrack.knight.cell;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cell {
  public Cell(int row, int col){
    this.row = row;
    this.col = col;
  }
  private int row;
  private int col;
  private boolean visited;
  private int value;
  private Set<Cell> cacheVisited = new HashSet<>();

  public void clearCacheVisited(){
    this.cacheVisited.clear();
  }

  public Set<Cell> getCacheVisited() {
    return cacheVisited;
  }

  public void setCacheVisited(Set<Cell> cacheVisited) {
    this.cacheVisited = cacheVisited;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getCol() {
    return col;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public boolean isVisited() {
    return visited;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Cell)) {
      return false;
    }
    Cell cell = (Cell) o;
    return row == cell.row &&
        col == cell.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }

  @Override
  public String toString() {
    return String.format("(%d,%d),", row,col);
  }
}
