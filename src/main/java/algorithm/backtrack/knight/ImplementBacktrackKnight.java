package algorithm.backtrack.knight;

import algorithm.backtrack.knight.cell.Cell;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ImplementBacktrackKnight {
  int n;
  Stack<Cell> steps = new Stack<>();
  Set<Cell> visitedCell = new HashSet<>();
  Map<String, Cell> allCell = new HashMap<>();
  public  void go(Cell startPoint, Set<Cell> matrix, int n) throws IOException {

    allCell = this.buildCell(n);
    String key = startPoint.getRow()+""+startPoint.getCol();
    if(!allCell.containsKey(key)){
      return;
    }
    startPoint = allCell.get(key);
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
          if(steps.size()==1){
            System.out.println("Debug!");
          }
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
    Cell nextCellTemp, result = null;
    int r, c;
    int currentVisitedCell = 0;
    int minUnvisitedCellTemp;
    String key;
    //1,2
    c = currentCell.getCol()+1;
    r = currentCell.getRow()+2;
    key = r+""+c;
    //need inside the table;
    if(allCell.containsKey(key)){
      nextCellTemp = allCell.get(key);
      //cell not be visited before; and not just visited by currentCell
      if(!nextCellTemp.isVisited() && !currentCell.getCacheVisited().contains(nextCellTemp) && (minUnvisitedCellTemp = this.countCellVisited(nextCellTemp))>currentVisitedCell){
        result = nextCellTemp;
        currentVisitedCell = minUnvisitedCellTemp;
      }
    }
    //2,1
    c = currentCell.getCol()+2;
    r = currentCell.getRow()+1;
    key = r+""+c;
    //need inside the table;
    if(allCell.containsKey(key)){
      nextCellTemp = allCell.get(key);
      //cell not be visited before; and not just visited by currentCell
      if(!nextCellTemp.isVisited() && !currentCell.getCacheVisited().contains(nextCellTemp) && (minUnvisitedCellTemp = this.countCellVisited(nextCellTemp))>currentVisitedCell){
        result = nextCellTemp;
        currentVisitedCell = minUnvisitedCellTemp;
      }
    }
    //2,-1
    c = currentCell.getCol()+2;
    r = currentCell.getRow()-1;
    key = r+""+c;
    //need inside the table;
    if(allCell.containsKey(key)){
      nextCellTemp = allCell.get(key);
      //cell not be visited before; and not just visited by currentCell
      if(!nextCellTemp.isVisited() && !currentCell.getCacheVisited().contains(nextCellTemp) && (minUnvisitedCellTemp = this.countCellVisited(nextCellTemp))>currentVisitedCell){
        result = nextCellTemp;
        currentVisitedCell = minUnvisitedCellTemp;
      }
    }
    //1,-2
    c = currentCell.getCol()+1;
    r = currentCell.getRow()-2;
    key = r+""+c;
    //need inside the table;
    if(allCell.containsKey(key)){
      nextCellTemp = allCell.get(key);
      //cell not be visited before; and not just visited by currentCell
      if(!nextCellTemp.isVisited() && !currentCell.getCacheVisited().contains(nextCellTemp) && (minUnvisitedCellTemp = this.countCellVisited(nextCellTemp))>currentVisitedCell){
        result = nextCellTemp;
        currentVisitedCell = minUnvisitedCellTemp;
      }
    }
    //-1,-2
    c = currentCell.getCol()-1;
    r = currentCell.getRow()-2;
    key = r+""+c;
    //need inside the table;
    if(allCell.containsKey(key)){
      nextCellTemp = allCell.get(key);
      //cell not be visited before; and not just visited by currentCell
      if(!nextCellTemp.isVisited() && !currentCell.getCacheVisited().contains(nextCellTemp) && (minUnvisitedCellTemp = this.countCellVisited(nextCellTemp))>currentVisitedCell){
        result = nextCellTemp;
        currentVisitedCell = minUnvisitedCellTemp;
      }
    }
    //-2,-1
    c = currentCell.getCol()-2;
    r = currentCell.getRow()-1;
    key = r+""+c;
    //need inside the table;
    if(allCell.containsKey(key)){
      nextCellTemp = allCell.get(key);
      //cell not be visited before; and not just visited by currentCell
      if(!nextCellTemp.isVisited() && !currentCell.getCacheVisited().contains(nextCellTemp) && (minUnvisitedCellTemp = this.countCellVisited(nextCellTemp))>currentVisitedCell){
        result = nextCellTemp;
        currentVisitedCell = minUnvisitedCellTemp;
      }
    }
    //-2,1
    c = currentCell.getCol()-2;
    r = currentCell.getRow()+1;
    key = r+""+c;
    //need inside the table;
    if(allCell.containsKey(key)){
      nextCellTemp = allCell.get(key);
      //cell not be visited before; and not just visited by currentCell
      if(!nextCellTemp.isVisited() && !currentCell.getCacheVisited().contains(nextCellTemp) && (minUnvisitedCellTemp = this.countCellVisited(nextCellTemp))>currentVisitedCell){
        result = nextCellTemp;
        currentVisitedCell = minUnvisitedCellTemp;
      }
    }
    //-1,2
    c = currentCell.getCol()-1;
    r = currentCell.getRow()+2;
    key = r+""+c;
    //need inside the table;
    if(allCell.containsKey(key)){
      nextCellTemp = allCell.get(key);
      //cell not be visited before; and not just visited by currentCell
      if(!nextCellTemp.isVisited() && !currentCell.getCacheVisited().contains(nextCellTemp) && (minUnvisitedCellTemp = this.countCellVisited(nextCellTemp))>currentVisitedCell){
        result = nextCellTemp;
        currentVisitedCell = minUnvisitedCellTemp;
      }
    }
    return result;
  }
  private boolean isValidCell(int n, int r, int c){
    return r >= 1 && c >= 1 && r <= n && c <= n;
  }
  private Map<String, Cell> buildCell(int n){
    Map<String,Cell> result = new HashMap<>();
    for(int i=1;i<=n;i++){
      for(int j=1;j<=n;j++){
        result.put(i+""+j,new Cell(i,j));
      }
    }
    return result;
  }
  private int countCellVisited(Cell cell){
    int result = 0;
    Cell nextCellTemp;
    int r, c;
    String key;
    //1,2
    c = cell.getCol()+1;
    r = cell.getRow()+2;
    key = r+""+c;
    //need inside the table;
    result += this.getVisited(key);
    //2,1
    c = cell.getCol()+2;
    r = cell.getRow()+1;
    key = r+""+c;
    //need inside the table;
    result += this.getVisited(key);
    //2,-1
    c = cell.getCol()+2;
    r = cell.getRow()-1;
    key = r+""+c;
    //need inside the table;
    result += this.getVisited(key);
    //1,-2
    c = cell.getCol()+1;
    r = cell.getRow()-2;
    key = r+""+c;
    result += this.getVisited(key);
    //-1,-2
    c = cell.getCol()-1;
    r = cell.getRow()-2;
    key = r+""+c;
    //need inside the table;
    result += this.getVisited(key);
    //-2,-1
    c = cell.getCol()-2;
    r = cell.getRow()-1;
    key = r+""+c;
    //need inside the table;
    result += this.getVisited(key);
    //-2,1
    c = cell.getCol()-2;
    r = cell.getRow()+1;
    key = r+""+c;
    //need inside the table;
    result += this.getVisited(key);
    //-1,2
    c = cell.getCol()-1;
    r = cell.getRow()+2;
    key = r+""+c;
    //need inside the table;
    result += this.getVisited(key);
    return result;
  }

  private int getVisited(String key){
    int result = 0;
    if(allCell.containsKey(key)){
      //cell not be visited before; and not just visited by currentCell
      if(allCell.get(key).isVisited()){
        result++;
      }
    }
    return result;
  }


}
