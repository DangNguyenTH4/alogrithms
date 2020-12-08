package algorithm.backtrack.knight;

import algorithm.backtrack.knight.cell.Cell;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ImplementBacktrackKnight {
  int n;
  Stack<Cell> steps = new Stack<>();
  Set<Cell> visitedCell = new HashSet<>();

  public  void go(Cell startPoint, Set<Cell> matrix, int n) throws IOException {

    if(startPoint.getCol()< 1 || startPoint.getCol()>n){
      return;
    }
    if(startPoint.getRow() < 1 || startPoint.getRow()>n){
      return;
    }
    File file = new File(n+"-"+System.currentTimeMillis()+".txt");
    file.createNewFile();
    FileWriter writer = new FileWriter(file);
    this.n=n;
    //==> Matrix will start from 1 -> n
    steps.push(startPoint);
    startPoint.setVisited(true);
    visitedCell.add(startPoint);

    while(steps.size()< n*n && !steps.isEmpty()){
      Cell currentCell = steps.peek();
//      System.out.println("Current cell: "+ currentCell.toString());
      Cell nextCell = this.getNextCell(currentCell);
      //Can not go anywhere.
      if(nextCell==null){

        currentCell.setVisited(false);
        visitedCell.remove(currentCell);
        currentCell.clearCacheVisited();

//        System.out.println("Back from :" + currentCell.toString());
        if(steps.size()<(n*n)/2) {
          System.out.println("All before step now: ");
          StringBuilder builder = new StringBuilder();
          for (Cell cell : steps) {
            System.out.print(cell.toString());
            builder.append(cell.toString());
          }
          writer.write(builder.toString()+"\n");
          System.out.println();
        }
        steps.pop();
          if(steps.size()<(n*n)/2) {
            System.out.println("All step now: ");
          StringBuilder builder = new StringBuilder();
          for (Cell cell : steps) {
            System.out.print(cell.toString());
            builder.append(cell.toString());
          }
          writer.write(builder.toString()+"\n");
          System.out.println();
        }
//        try {
//          Thread.sleep(3000);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }

      }
      else{
        nextCell.setVisited(true);
        visitedCell.add(nextCell);
        currentCell.getCacheVisited().add(nextCell);
        steps.push(nextCell);
      }
    }
//    System.out.println(steps.toString());
    for(Cell cell : steps){
      System.out.print(cell.toString());
    }
    System.out.println();
    System.out.println(steps.size());
    writer.close();
  }

  private Cell getNextCell(Cell currentCell){
    Cell nextCell ;
    int r, c;

    //1,2
    c = currentCell.getCol()+1;
    r = currentCell.getRow()+2;
    //need inside the table;
    if(isValidCell(n,r,c)){
      nextCell = new Cell(r,c);
      //cell not be visited before; and not just visited by currentCell
      if(!visitedCell.contains(nextCell) && !currentCell.getCacheVisited().contains(nextCell)){
        return  nextCell;
      }
    }
    //2,1
    c = currentCell.getCol()+2;
    r = currentCell.getRow()+1;
    if(isValidCell(n,r,c)){
      nextCell = new Cell(r,c);
      if(!visitedCell.contains(nextCell) && !currentCell.getCacheVisited().contains(nextCell)){
        return  nextCell;
      }
    }
    //2,-1
    c = currentCell.getCol()+2;
    r = currentCell.getRow()-1;
    if(isValidCell(n,r,c)){
      nextCell = new Cell(r,c);
      if(!visitedCell.contains(nextCell) && !currentCell.getCacheVisited().contains(nextCell)){
        return  nextCell;
      }
    }
    //1,-2
    c = currentCell.getCol()+1;
    r = currentCell.getRow()-2;
    if(isValidCell(n,r,c)){
      nextCell = new Cell(r,c);
      if(!visitedCell.contains(nextCell) && !currentCell.getCacheVisited().contains(nextCell)){
        return  nextCell;
      }
    }
    //-1,-2
    c = currentCell.getCol()-1;
    r = currentCell.getRow()-2;
    if(isValidCell(n,r,c)){
      nextCell = new Cell(r,c);
      if(!visitedCell.contains(nextCell) && !currentCell.getCacheVisited().contains(nextCell)){
        return  nextCell;
      }
    }
    //-2,-1
    c = currentCell.getCol()-2;
    r = currentCell.getRow()-1;
    if(isValidCell(n,r,c)){
      nextCell = new Cell(r,c);
      if(!visitedCell.contains(nextCell) && !currentCell.getCacheVisited().contains(nextCell)){
        return  nextCell;
      }
    }
    //-2,1
    c = currentCell.getCol()-2;
    r = currentCell.getRow()+1;
    if(isValidCell(n,r,c)){
      nextCell = new Cell(r,c);
      if(!visitedCell.contains(nextCell) && !currentCell.getCacheVisited().contains(nextCell)){
        return  nextCell;
      }
    }
    //-1,2
    c = currentCell.getCol()-1;
    r = currentCell.getRow()+2;
    if(isValidCell(n,r,c)){
      nextCell = new Cell(r,c);
      if(!visitedCell.contains(nextCell) && !currentCell.getCacheVisited().contains(nextCell)){
        return  nextCell;
      }
    }
    return null;
  }
  private boolean isValidCell(int n, int r, int c){
    return r >= 1 && c >= 1 && r <= n && c <= n;
  }
}
