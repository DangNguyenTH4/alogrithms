package algorithm.backtrack.knight;

import algorithm.backtrack.knight.cell.Cell;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImplementBacktrackKnight {
    int n;
    Stack<Cell> steps = new Stack<>();
    Set<Cell> visitedCell = new HashSet<>();
    Map<String, Cell> allCell = new HashMap<>();
    List<Cell> relativeCells = Stream.of(
            new Cell(1, 2),
            new Cell(2, 1),
            new Cell(2, -1),
            new Cell(1, -2),
            new Cell(-1, -2),
            new Cell(-2, -1),
            new Cell(-2, 1),
            new Cell(-1, 2))
            .collect(Collectors.toList());

    public void go(Cell startPoint, Set<Cell> matrix, int n) throws IOException, InterruptedException {

        allCell = this.buildCell(n);
        String key = startPoint.getRow() + "" + startPoint.getCol();
        if (!allCell.containsKey(key)) {
            return;
        }
        startPoint = allCell.get(key);
        File file = new File(n + "-" + System.currentTimeMillis() + ".txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        this.n = n;
        //==> Matrix will start from 1 -> n
        steps.push(startPoint);
        startPoint.setVisited(true);
        visitedCell.add(startPoint);

        while (steps.size() < n * n && !steps.isEmpty()) {
            Cell currentCell = steps.peek();
//      System.out.println("Current cell: "+ currentCell.toString());
            Cell nextCell = this.getNextCell(currentCell);
            //Can not go anywhere.
            if (nextCell == null) {

                currentCell.setVisited(false);
                visitedCell.remove(currentCell);
                currentCell.clearCacheVisited();

//        System.out.println("Back from :" + currentCell.toString());
                if (steps.size() < (n * n)) {
                    System.out.println("All before step now: ");
                    for (Cell cell : steps) {
                        System.out.print(cell.toString());
                    }
                    System.out.println();
//                    WriteStepToFile.write(steps, writer);
                }
                steps.pop();
                if (steps.size() < (n * n)) {
                    System.out.println("All step now: ");
                    System.out.println("All before step now: ");
                    for (Cell cell : steps) {
                        System.out.print(cell.toString());
                    }
                    System.out.println();
//                    WriteStepToFile.write(steps, writer);
                }
                if (steps.size() == 1) {
                    System.out.println("Back to root. Switch to another tree!");
                }
            } else {
                nextCell.setVisited(true);
                visitedCell.add(nextCell);
                currentCell.getCacheVisited().add(nextCell);
                steps.push(nextCell);
                WriteStepToFile.write(steps, writer);
            }
        }
//    System.out.println(steps.toString());
        for (Cell cell : steps) {
            System.out.print(cell.toString());
        }
        System.out.println();
        System.out.println(steps.size());
        while(!WriteStepToFile.isDone()){

        }
      System.out.println("Close file!");
        writer.close();
        System.out.println("Terminate Executor service!");
        WriteStepToFile.terminate();
    }

    private Cell getNextCell(Cell currentCell) {
        Cell nextCellTemp, result = null;
        int r, c;
        int currentVisitedCell = 0;
        int minUnvisitedCellTemp;
        String key;
        int start = new Random().nextInt(relativeCells.size());
        int size = relativeCells.size();
        for(int i = 0;i< size;i++){
            int position = (start+i)%size;
            Cell relativeCell = relativeCells.get(position);
            c = currentCell.getCol() + relativeCell.getCol();
            r = currentCell.getRow() + relativeCell.getRow();
            key = r + "" + c;
            //need inside the table
            if (allCell.containsKey(key)) {
                nextCellTemp = allCell.get(key);
                //cell not be visited before; && not just visited by currentCell
                if (!nextCellTemp.isVisited() && !currentCell.getCacheVisited().contains(nextCellTemp)) {
                    minUnvisitedCellTemp = this.countCellVisited(nextCellTemp);
                    //Check that cell have min unvisited cell
                    if (minUnvisitedCellTemp > currentVisitedCell) {
                        result = nextCellTemp;
                        currentVisitedCell = minUnvisitedCellTemp;
                    }
                }
            }
        }
//        for (Cell relativeCell : relativeCells) {
//            c = currentCell.getCol() + relativeCell.getCol();
//            r = currentCell.getRow() + relativeCell.getRow();
//            key = r + "" + c;
//            //need inside the table
//            if (allCell.containsKey(key)) {
//                nextCellTemp = allCell.get(key);
//                //cell not be visited before; && not just visited by currentCell
//                if (!nextCellTemp.isVisited() && !currentCell.getCacheVisited().contains(nextCellTemp)) {
//                    minUnvisitedCellTemp = this.countCellVisited(nextCellTemp);
//                    //Check that cell have min unvisited cell
//                    if (minUnvisitedCellTemp > currentVisitedCell) {
//                        result = nextCellTemp;
//                        currentVisitedCell = minUnvisitedCellTemp;
//                    }
//                }
//            }
//        }
        return result;
    }

    private Map<String, Cell> buildCell(int n) {
        Map<String, Cell> result = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                result.put(i + "" + j, new Cell(i, j));
            }
        }
        return result;
    }

    private int countCellVisited(Cell cell) {
        int result = 0;
        int r;
        int c;
        String key;
        for (Cell relativeCell : relativeCells) {
            c = cell.getCol() + relativeCell.getCol();
            r = cell.getRow() + relativeCell.getRow();
            key = r + "" + c;
            result += this.getVisited(key);
        }
        return result;
    }

    private int getVisited(String key) {
        int result = 0;
        if (allCell.containsKey(key)) {
            //cell not be visited before; and not just visited by currentCell
            if (allCell.get(key).isVisited()) {
                result++;
            }
        }
        return result;
    }
}

final class WriteStepToFile {
    private static ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    private static AtomicInteger count = new AtomicInteger(0);

    public static boolean isDone() {
        return count.get() == 0;
    }
    public static void terminate() throws InterruptedException {
        threadPoolExecutor.awaitTermination(2, TimeUnit.SECONDS);
        threadPoolExecutor.shutdown();
        System.out.println("Shutdown done!");
    }
    public static void write(Stack<Cell> steps, FileWriter writer) throws IOException {
        List<Cell> serializableSteps = new ArrayList<>();
        for (Cell c : steps) {
            serializableSteps.add(new Cell(c.getRow(), c.getCol()));
        }
        System.out.println("Write line: " + count.incrementAndGet());
        threadPoolExecutor.execute(() -> {
            StringBuilder builder = new StringBuilder();
            for (Cell cell : serializableSteps) {
                builder.append(cell.toString());
            }
            try {
                writer.write(builder.toString() + "\n");
                count.decrementAndGet();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
