package finalproject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;


public class Nodes {

    public String label;
    boolean visited;
    boolean added;
    public int maxEdge;
    public int edgeSize;
    LinkedList<Edges> edgeList;

    public Nodes(String label) {
        this.label = label;
        visited = false;
        added = false;
        maxEdge=5;
        edgeSize=0;
        edgeList = new LinkedList<Edges>();
    }
    public void setMaxEdge(int maxEdge){
        if(maxEdge<=10){
          this.maxEdge=maxEdge;  
        }
        
    }
    
   
    

    public void makeAdded() {
        added = true;
    }

    public boolean isAdded() {
        return added;
    }

    public void makeVisited() {
        visited = true;
    }

    public void makeUnVisited() {
        visited = false;
    }

    public boolean isVisited() {
        return visited;
    }

    public int unVisitedEdgeListSize() {
        int c = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            Nodes n = edgeList.get(i).getOtherNode(this);
            if (!n.isVisited()) {
                c++;
            }
        }
        
        return c;
    }

    public void print() {
        System.out.print(label + ": ");
        for (int i = 0; i < edgeList.size(); i++) {
            System.out.print(edgeList.get(i).getOtherNode(this).label);
        }
        System.out.println();

    }

    public void addEdge(Edges edge) {
   
            edgeList.add(edge);
            edgeSize++;
  
    }

    public Nodes unvisitedNeighbor() {
        ListIterator<Edges> iterator = edgeList.listIterator();
        while (iterator.hasNext()) {
            Edges d = iterator.next();
            Nodes n = d.getOtherNode(this);

            if (n.visited == false) {
                return n;
            }
        }
        return null;
    }

    public Edges unvisitedEdge() {
        ListIterator<Edges> iterator = edgeList.listIterator();
        while (iterator.hasNext()) {
            Edges d = iterator.next();
            Nodes n = d.getOtherNode(this);

            if (n.visited == false) {
                return d;
            }
        }
        return null;
    }

    public boolean visitedEdge() {
        ListIterator<Edges> iterator = edgeList.listIterator();
        while (iterator.hasNext()) {
            Edges d = iterator.next();

            if (d.visited == true) {
                return true;
            }
        }
        return false;
    }

    public Edges unvisitedMinChildEdge() {
        int min = 700;
        Edges minChildEdge = null;
        ListIterator<Edges> iterator = edgeList.listIterator();
        while (iterator.hasNext()) {
            Edges d = iterator.next();
            Nodes n = d.getOtherNode(this);

            if (n.visited == false) {

                if (n.unVisitedEdgeListSize() < min) {

                    min = n.edgeList.size();
                    minChildEdge = d;
                }
            }
        }

        return minChildEdge;
    }

}
