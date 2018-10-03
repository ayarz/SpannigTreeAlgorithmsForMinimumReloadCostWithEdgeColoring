package finalproject;

import java.awt.Color;

public class Edges {

    public Nodes d1;
    public Nodes d2;
    public Colors color;
    boolean visited;
    boolean deleted;

    public Edges(Nodes d1, Nodes d2, Colors color) {
        this.d1 = d1;
        this.d2 = d2;
        this.color = new Colors(Color.WHITE, "white");
        visited = false;
        deleted = false;
    }

    public void makeDeleted() {
        deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Nodes getOtherNode(Nodes d) {
        if (d1.equals(d)) {
            return d2;
        } else {
            return d1;
        }
    }

    public boolean isThisEdge(Nodes n1, Nodes n2) {
        if (d1.equals(n1) && d2.equals(n2)) {
            return true;
        }
        if (d2.equals(n1) && d1.equals(n2)) {
            return true;
        }

        return false;
    }

    public boolean isNodeThisEdge(Nodes d) {
        if (d1.equals(d)) {
            return true;
        } else if (d2.equals(d)) {
            return true;
        }
        return false;
    }

    public boolean isThisEdge(String n1, String n2) {
        if (d1.label.equals(n1) && d2.label.equals(n2)) {
            return true;
        }
        if (d2.label.equals(n1) && d1.label.equals(n2)) {
            return true;
        }

        return false;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public void makeVisited() {
        visited = true;
    }

    public String print() {
        String text = "";
        System.out.println(d1.label + " " + color.colorname + " " + d2.label);
        text = d1.label + " " + color.colorname + " " + d2.label + "\n";
        return text;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edges other = (Edges) obj;
        if (this.d1 != other.d1 && (this.d1 == null || !this.d1.equals(other.d1))) {
            return false;
        }
        if (this.d2 != other.d2 && (this.d2 == null || !this.d2.equals(other.d2))) {
            return false;
        }
        return true;
    }

}
