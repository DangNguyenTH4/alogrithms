package algorithm.dijktra.node;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Node {

  public Node(String name){
    this.name = name;
  }

  private String name;
  private boolean visited = false;
  private Map<Node, Integer> adjacentNodes = new HashMap<>();
  private Integer minValue;
  private Node previousNode;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean visited() {
    return visited;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public Map<Node, Integer> getAdjacentNodes() {
    return adjacentNodes;
  }

  public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
    this.adjacentNodes = adjacentNodes;
  }

  public Integer getMinValue() {
    return minValue;
  }

  public void setMinValue(Integer minValue) {
    this.minValue = minValue;
  }

  public void setPreviousNode(Node previousNode){
    this.previousNode = previousNode;
  }

  public Node getPreviousNode() {
    return previousNode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Node)) {
      return false;
    }
    Node node = (Node) o;
    return Objects.equals(getName(), node.getName());
  }

  public String getPath(String fromNode){
    if(this.name.equals(fromNode)){
      return this.name;
    }
    return this.name + "," + previousNode.getPath(fromNode);
  }
  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
