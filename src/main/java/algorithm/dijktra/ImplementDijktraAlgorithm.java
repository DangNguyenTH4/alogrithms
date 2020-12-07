package algorithm.dijktra;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import node.Node;

public class ImplementDijktraAlgorithm {

  private Queue<Node> queue = new ArrayDeque<>();


  public Integer getMin(String sourceNode, String targetNode, Map<String, Node> nodes){
    //For start;
    Node startNode = nodes.get(sourceNode);
    startNode.setMinValue(0);
    queue.add(startNode);
    //While queue is not empty. Find min.
    while(!queue.isEmpty()){
      Node currentNode = queue.poll();
      fromANode(currentNode);
      //set node is visited. For ignore.
      currentNode.setVisited(true);
    }

    Integer result = nodes.get(targetNode).getMinValue();
    System.out.println("Min: " +result);
    System.out.println(new StringBuilder("Path: " + nodes.get(targetNode).getPath(sourceNode)).reverse().toString() );
    return result;
  }

  private void fromANode(Node node){
    if(node == null){
      return;
    }
    if(node.visited()){
      return;
    }
    //fill min value to next node.
    for(Entry<Node, Integer> entryNode : node.getAdjacentNodes().entrySet()){
      Node nextNode = entryNode.getKey();
      //if node was visited. Ignore
      if(nextNode.visited()){
        continue;
      }
      Integer calculateMin = node.getMinValue() + entryNode.getValue();
      //if minValue is not fill or calculateMin < currentMin => Set new minValue
      if(nextNode.getMinValue() == null || nextNode.getMinValue() > calculateMin){
        nextNode.setMinValue(calculateMin);
        nextNode.setPreviousNode(node);
        queue.add(nextNode);
      }
    }
  }
}
