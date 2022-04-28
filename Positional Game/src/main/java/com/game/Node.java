package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    protected int x,y;
    protected List<Node> neighbours= new ArrayList<Node>();

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void addNeighbour(Node neighbour)
    {
        neighbours.add(neighbour);
    }

    public boolean containsNeighbour(Node node)
    {
        if(neighbours.contains(node))
            return true;
        return false;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return
                "x=" + x +
                ", y=" + y ;
    }
}
