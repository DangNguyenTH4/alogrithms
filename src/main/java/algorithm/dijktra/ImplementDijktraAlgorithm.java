package algorithm.dijktra;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import node.Node;

public class ImplementDijktraAlgorithm {

  private Queue<Node> queue = new ArrayDeque<>();


  public Integer getMin(String sourceNode, String target, Map<String, Node> nodes){
    //For start;
    Node startNode = nodes.get(sourceNode);
    startNode.setMinValue(0);

    Node targetNode = nodes.get(target);
    queue.add(startNode);
    //While queue is not empty. Find min.
    while(!queue.isEmpty()){
      Node currentNode = queue.poll();
      fromANode(currentNode, targetNode );
      //set node is visited. For ignore.
      currentNode.setVisited(true);
    }

    Integer result = nodes.get(target).getMinValue();
    System.out.println("Min: " +result);
    System.out.println("Path: " + new StringBuilder(nodes.get(target).getPath(sourceNode)).reverse().toString() );
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

  private void fromANode(Node node, Node targetNode){
    if(node == null){
      return;
    }
    if(node.visited()){
      return;
    }
    //ignore if currentNode have minValue great than targetNode. Don't need to check anymore.
    if(targetNode.getMinValue() != null && node.getMinValue() > targetNode.getMinValue()){
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
