package algorithm.dijktra;

import java.util.HashMap;
import java.util.Map;
import algorithm.dijktra.node.Node;

public class BuildRoad {
  public Map<String, Node> buildNodesSimple(){
    Map<String, Node> result = new HashMap<>();
    for(int i=1;i<=6;i++){
      result.put(String.valueOf(i), new Node(String.valueOf(i)));
    }
    addGraphOneWay(1,2,7,result);
    addGraphOneWay(1,3,9,result);
    addGraphOneWay(1,6,14,result);
    //2
    addGraphOneWay(2,1,7,result);
    addGraphOneWay(2,3,1,result);
//    addGraph(2,3,10,result);
    addGraphOneWay(2,4,15,result);
    //3
    addGraphOneWay(3,1,9,result);
    addGraphOneWay(3,2,1,result);
//    addGraph(2,3,10,result);
    addGraphOneWay(3,4,11,result);
    addGraphOneWay(3,6,2,result);
    //4
    addGraphOneWay(4,2,15,result);
    addGraphOneWay(4,3,11,result);
    addGraphOneWay(4,5,6,result);
    //5
    addGraphOneWay(5,4,6,result);
    addGraphOneWay(5,6,9,result);
    //6
    addGraphOneWay(6,1,14,result);
    addGraphOneWay(6,3,2,result);
    addGraphOneWay(6,5,9,result);
    return result;
  }
  public Map<String, Node> buildNodesComplex() {
    Map<String, Node> result = new HashMap<>();
    for (int i = 0; i <= 8; i++) {
      result.put(String.valueOf(i), new Node(String.valueOf(i)));
    }

    addGraphTwoWay(0, 1, 4, result);
    addGraphTwoWay(0, 7, 8, result);

    addGraphTwoWay(1, 2, 8, result);
    addGraphTwoWay(1, 7, 11, result);


    addGraphTwoWay(2, 3, 7, result);
    addGraphTwoWay(2, 5, 4, result);
    addGraphTwoWay(2, 8, 2, result);

    addGraphTwoWay(3, 4, 9, result);
    addGraphTwoWay(3, 5, 14, result);

    addGraphTwoWay(4, 5, 10, result);

    addGraphTwoWay(5, 6, 2, result);

    addGraphTwoWay(6, 7, 1, result);
    addGraphTwoWay(6, 8, 6, result);

    addGraphTwoWay(7, 8, 7, result);
    return result;
  }



  private void addGraphOneWay(Integer current, Integer next, Integer distance, Map<String, Node> graph){
    Node currentNode = graph.get(String.valueOf(current));
    Node nextNode = graph.get(String.valueOf(next));
    currentNode.getAdjacentNodes().put(nextNode, distance);
//    nextNode.getAdjacentNodes().put(currentNode, distance);
  }
  private void addGraphTwoWay(Integer current, Integer next, Integer distance, Map<String, Node> graph){
    Node currentNode = graph.get(String.valueOf(current));
    Node nextNode = graph.get(String.valueOf(next));
    currentNode.getAdjacentNodes().put(nextNode, distance);
    nextNode.getAdjacentNodes().put(currentNode, distance);
  }
}
