package com.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class GameMap {
        protected Map<Node, List<Node>> gameMap= new HashMap<>();
    protected List<Node> stickNodes= new ArrayList<>();
        protected List<Node> drawnedNodes= new ArrayList<>();
        public GameMap()
        {
            gameMap= new HashMap<>();
            stickNodes= new ArrayList<>();
            drawnedNodes= new ArrayList<>();
        }
        public void reset()
        {
            gameMap= new HashMap<>();
            stickNodes= new ArrayList<>();
            drawnedNodes= new ArrayList<>();
        }
        public void addToList(Node node , Node neighbour)
        {
            List<Node> neighbours= new ArrayList<>();
            neighbours=gameMap.get(node);
            neighbours.add(neighbour);
        }
        public Node getNode(Node node)
        {
           for(Node tempNode : stickNodes)
           {
               if(tempNode.equals(node))
                   return tempNode;
           }
           return node;
        }
        public void printNeighbours()
        {
            stickNodes.forEach(node -> {
                System.out.println(node+ ": " +  node.neighbours);
            });
        }


}
