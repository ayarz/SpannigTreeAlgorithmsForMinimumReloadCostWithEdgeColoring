package finalproject;

import java.awt.Color;
import java.util.ArrayList;

public class ColorCost {

    Colors c1;
    Colors c2;
    int cost;

    public ColorCost(String s1, String s2, int cost) {
        this.cost = cost;

        switch (s1) {
            case "black":
                c1 = new Colors(Color.BLACK, "black");
                break;
            case "blue":
                c1 = new Colors(Color.BLUE, "blue");
                break;
            case "cyan":
                c1 = new Colors(Color.CYAN, "cyan");
                break;
            case "gray":
                c1 = new Colors(Color.GRAY, "gray");
                break;
            case "green":
                c1 = new Colors(Color.GREEN, "green");
                break;
            case "magenta":
                c1 = new Colors(Color.MAGENTA, "magenta");
                break;
            case "orange":
                c1 = new Colors(Color.ORANGE, "orange");
                break;
            case "pink":
                c1 = new Colors(Color.PINK, "pink");
                break;
            case "red":
                c1 = new Colors(Color.RED, "red");
                break;
            case "yellow":
                c1 = new Colors(Color.YELLOW, "yellow");
                break;

        }

        switch (s2) {
            case "black":
                c2 = new Colors(Color.BLACK, "black");
                break;
            case "blue":
                c2 = new Colors(Color.BLUE, "blue");
                break;
            case "cyan":
                c2 = new Colors(Color.CYAN, "cyan");
                break;
            case "gray":
                c2 = new Colors(Color.GRAY, "gray");
                break;
            case "green":
                c2 = new Colors(Color.GREEN, "green");
                break;
            case "magenta":
                c2 = new Colors(Color.MAGENTA, "magenta");
                break;
            case "orange":
                c2 = new Colors(Color.ORANGE, "orange");
                break;
            case "pink":
                c2 = new Colors(Color.PINK, "pink");
                break;
            case "red":
                c2 = new Colors(Color.RED, "red");
                break;
            case "yellow":
                c2 = new Colors(Color.YELLOW, "yellow");
                break;

        }
    }

    public boolean isThisColorCost(Colors r1, Colors r2) {
        if (c1.color.equals(r1.color) && c2.color.equals(r2.color)) {
            return true;
        } else if (c2.color.equals(r1.color) && c1.color.equals(r2.color)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean contain(Colors c) {
        if (c1.color.equals(c.color)) {
            return true;
        } else if (c2.color.equals(c.color)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean notContains(ArrayList<Colors> list) {
        for (int i = 0; i < list.size(); i++) {
            if (contain(list.get(i))) {
                return false;
            }
        }
        return true;

    }

    public Colors getOtherColor(Colors c) {
        if (c1.color.equals(c.color)) {
            return c2;
        } else {
            return c1;
        }
    }

    public void print() {

        System.out.println(c1.colorname + "-" + c2.colorname + ": " + cost);
    }

}
