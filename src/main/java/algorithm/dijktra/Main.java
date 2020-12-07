package algorithm.dijktra;

import java.util.Map;
import node.Node;

public class Main {

  public static void main(String[] args) {
    BuildRoad buildRoad = new BuildRoad();
    ImplementDijktraAlgorithm implementDijktraAlgorithm = new ImplementDijktraAlgorithm();
//    Map<String, Node> build =buildRoad.buildNodesSimple();
    Map<String, Node> build =buildRoad.buildNodesComplex();
    Integer integer = implementDijktraAlgorithm.getMin(String.valueOf(0), String.valueOf(8), build);
  }
}
