package algorithm.backtrack.knight;

import java.util.concurrent.ThreadLocalRandom;

public class GFG {
  public static final int N = 8;
  // Move pattern on basis of the change of
  // x coordinates and y coordinates respectively
  public static final int cx[] = {1, 1, 2, 2, -1, -1, -2, -2};
  public static final int cy[] = {2, -2, 1, -1, 2, -2, 1, -1};
  // function restricts the knight to remain within
  // the 8x8 chessboard
  boolean limits(int x, int y)
  {
    return ((x >= 0 && y >= 0) &&
        (x < N && y < N));
  }
  /* Checks whether a square is valid and
    empty or not */
  boolean isempty(int a[], int x, int y)
  {
    // a[y*N + x] < 0 mean not visited
    return (limits(x, y)) && (a[y * N + x] < 0);
  }
  /* Returns the number of empty squares
    adjacent to (x, y) */
  // Number of cell not visited from cel(row,col)
  int getDegree(int a[], int row, int col)
  {
    int numberOfInvalidCell = 0;
    for (int i = 0; i < N; ++i)
      if (isempty(a, (row + cx[i]),
          (col + cy[i])))
        numberOfInvalidCell++;

    return numberOfInvalidCell;
  }
  // Picks next point using Warnsdorff's heuristic.
  // Returns false if it is not possible to pick
  // next point.
  Cell nextMove(int a[], Cell cell)
  {
    int cellHaveMinNumberOfInvalidCell = -1,
        tempMinNumberOfInValidCell,
        minNumberOfInValidCell = (N + 1),
        rowOfNextCell, colOfNextCell;

    // Try all N adjacent of (*x, *y) starting
    // from a random adjacent. Find the adjacent
    // with minimum degree.
    int start = ThreadLocalRandom.current().nextInt(1000) % N;
    for (int count = 0; count < N; ++count)
    {
      int i = (start + count) % N;
//      int i = count;
      rowOfNextCell = cell.row + cx[i];
      colOfNextCell = cell.col + cy[i];
      tempMinNumberOfInValidCell = getDegree(a, rowOfNextCell, colOfNextCell);
      if ((isempty(a, rowOfNextCell, colOfNextCell)) &&
          ( tempMinNumberOfInValidCell < minNumberOfInValidCell))
      {
        cellHaveMinNumberOfInvalidCell = i;
        minNumberOfInValidCell = tempMinNumberOfInValidCell;
      }
    }

    // IF we could not find a next cell
    if (cellHaveMinNumberOfInvalidCell == -1)
      return null;

    // Store coordinates of next point
    rowOfNextCell = cell.row + cx[cellHaveMinNumberOfInvalidCell];
    colOfNextCell = cell.col + cy[cellHaveMinNumberOfInvalidCell];

    // Mark next move
    a[colOfNextCell * N + rowOfNextCell] = a[(cell.col) * N +
        (cell.row)] + 1;

    // Update next point
    cell.row = rowOfNextCell;
    cell.col = colOfNextCell;

    return cell;
  }
  /* displays the chessboard with all the
   legal knight's moves */
  void print(int a[])
  {
    for (int i = 0; i < N; ++i)
    {
      for (int j = 0; j < N; ++j)
        System.out.printf("%d\t", a[j * N + i]);
      System.out.printf("\n");
    }
  }
  /* checks its neighbouring sqaures */
    /* If the knight ends on a square that is one
    knight's move from the beginning square,
    then tour is closed */
  boolean neighbour(int endRow, int endCol, int startRow, int startCol)
  {
    for (int i = 0; i < N; ++i)
      if (((endRow + cx[i]) == startRow) &&
          ((endCol + cy[i]) == startCol))
        return true;

    return false;
  }
  /* Generates the legal moves using warnsdorff's
    heuristics. Returns false if not possible */
  boolean findClosedTour()
  {
    // Filling up the chessboard matrix with -1's
    int a[] = new int[N * N];
    for (int i = 0; i < N * N; ++i)
      a[i] = -1;

    // initial position
    int startRow = 2;
    int startCol = 2;

    // Current points are same as initial points
    Cell cell = new Cell(startRow, startCol);

    a[cell.col * N + cell.row] = 1; // Mark first move.

    // Keep picking next points using
    // Warnsdorff's heuristic
    Cell ret = null;
    for (int i = 0; i < N * N - 1; ++i)
    {
      ret = nextMove(a, cell);
      if (ret == null)
        return false;
    }

    // Check if tour is closed (Can end
    // at starting point)
//    if (!neighbour(ret.row, ret.col, startRow, startCol))
//      return false;

    print(a);
    return true;
  }

  // Driver Code
  public static void main(String[] args)
  {
    boolean finded = false;
    // While we don't get a solution
    while (!finded)
    {
      finded = new GFG().findClosedTour();
      System.out.println("Find: " + finded);
    }
  }
}
class Cell
{
  int row;
  int col;

  public Cell(int row, int col)
  {
    this.row = row;
    this.col = col;
  }
}

