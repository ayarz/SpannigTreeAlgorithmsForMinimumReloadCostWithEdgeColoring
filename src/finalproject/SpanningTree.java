package finalproject;

import java.util.ArrayList;
import java.util.Stack;

public class SpanningTree {

    Graphs g;

    public SpanningTree(Graphs g) {
        this.g = g;
    }

    public Graphs randomSpanningTree(int s) {

        int rootIndex = 0;
        ArrayList<Nodes> stNodes = new ArrayList<Nodes>();
        ArrayList<Edges> stEdges = new ArrayList<Edges>();
        ArrayList<Edges> neighbooredges = new ArrayList<Edges>();

        for (int i = 0; i < g.nodeNumber; i++) {
            if (g.nodeList.get(i).label.equals(g.root.label)) {
                rootIndex = i;
                break;
            }
        }
        Nodes root = g.nodeList.get(rootIndex);
        root.makeAdded();
        stNodes.add(root);
        for (int i = 0; i < root.edgeList.size(); i++) {
            neighbooredges.add(root.edgeList.get(i));
        }

        while (stNodes.size() != g.nodeNumber) {

            Edges random_edge = randomEdge(neighbooredges, s);

            // find new added node 
            // which node of the edge is not added is new node 
            Nodes newNode = random_edge.d1.isAdded() ? random_edge.d2 : random_edge.d1;

            deleteEdgesto_newNode(neighbooredges, newNode);
            addEdgesnewNode(newNode, neighbooredges);

            // add new node to spanning tree node list
            newNode.makeAdded();
            stNodes.add(newNode);

            // add random edge to spanning tree edge list
            stEdges.add(random_edge);
        }

        for (int i = 0; i < g.nodeNumber; i++) {
            g.nodeList.get(i).added = false;
        }
        for (int i = 0; i < g.edgeList.size(); i++) {
            g.edgeList.get(i).deleted = false;
        }
        //  print(stEdges, stNodes);
        return createSpanningTree(stEdges);
    }

    /*
      if s=0, return first edge from neighboor edges, 
      it is like applying prim algorithm by assuming that edge weight is 1 
      if s!=0, return random edge from neighboor edges
     */
    public Edges randomEdge(ArrayList<Edges> neighbooredges, int s) {
        Edges random_edge;

        if (s == 0) {

            random_edge = neighbooredges.get(s);
        } else {

            int r = (int) (Math.random() * neighbooredges.size());

            random_edge = neighbooredges.get(r);
        }

        return random_edge;
    }

    /*
       Delete the edges of the new node from the neighbor list, 
        mark the deleted edges as deleted
     */
    public void deleteEdgesto_newNode(ArrayList<Edges> neighbooredges, Nodes newNode) {
        for (int i = 0; i < neighbooredges.size(); i++) {
            Edges edge = neighbooredges.get(i);
            if (edge.isNodeThisEdge(newNode)) {
                neighbooredges.remove(edge);
                i--;
                edge.makeDeleted();
            }
        }
    }

    /*
     Add the edges of the new node that are not deleted to the neighbor list,
     ie the edges deleted in the previous method will not be added
     */
    public void addEdgesnewNode(Nodes yeniDugum, ArrayList<Edges> komsuKenarlar) {
        for (int i = 0; i < yeniDugum.edgeList.size(); i++) {
            Edges kenar = yeniDugum.edgeList.get(i);
            if (!kenar.isDeleted()) {
                komsuKenarlar.add(kenar);
            }
        }
    }

    public void print(ArrayList<Edges> stEdges, ArrayList<Nodes> stNodes) {
        System.out.println("Spanning Tree Edges:");
        for (int i = 0; i < stEdges.size(); i++) {
            Edges edge = stEdges.get(i);
            System.out.print(edge.d1.label + "" + edge.d2.label + ", ");
        }
        System.out.println();
        System.out.println();

        System.out.println("Spanning Tree Nodes:");
        for (int i = 0; i < stNodes.size(); i++) {
            Nodes node = stNodes.get(i);
            System.out.print(node.label + ", ");
        }
        System.out.println();
        System.out.println();
    }

    public Graphs createSpanningTree(ArrayList<Edges> stEdges) {
        Graphs st = new Graphs(g.root.label);
        String node1 = "";
        String node2 = "";
        for (Edges stEdge : stEdges) {
            node1 = stEdge.d1.label;
            node2 = stEdge.d2.label;
            st.addto_Graph(node1, node2);
        }

        return st;
    }

    public Graphs mst() {
        ArrayList<Edges> mstEdges = new ArrayList<Edges>();
        int rootIndex = 0;
        for (int i = 0; i < g.nodeNumber; i++) {
            if (g.nodeList.get(i).label.equals(g.root.label)) {
                rootIndex = i;
                break;
            }
        }

        Stack<Nodes> stack = new Stack<Nodes>();
        Nodes firstNode = g.nodeList.get(rootIndex);
        firstNode.makeVisited();
        // System.out.println(firstNode.label);
        stack.push(firstNode);

        while (!stack.empty()) {
            Nodes upNode = stack.peek();
            Edges neighbor = upNode.unvisitedMinChildEdge();
            if (neighbor == null) {
                stack.pop();
            } else {
                neighbor.getOtherNode(upNode).makeVisited();
                stack.push(neighbor.getOtherNode(upNode));
                mstEdges.add(neighbor);
            }

        }
        for (int i = 0; i < g.nodeNumber; i++) {
            g.nodeList.get(i).makeUnVisited();
        }
        return createSpanningTree(mstEdges);
    }

}
