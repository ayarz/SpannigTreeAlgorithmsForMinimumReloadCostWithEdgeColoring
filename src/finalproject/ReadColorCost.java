package finalproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ReadColorCost {

    public static LinkedList<ColorCost> colorCostList = new LinkedList<ColorCost>();

    public static void readColorFile(String adress) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader(adress));
        String line = br.readLine();

        String s1 = "";
        String s2 = "";
        int cost = 0;
        while (line != null) {
            StringTokenizer st = new StringTokenizer(line, " ");

            while (st.hasMoreTokens()) {
                s1 = st.nextToken();
                s2 = st.nextToken();
                cost = Integer.parseInt(st.nextToken());
                addColorCost(s1, s2, cost);

            }
            line = br.readLine();
        }
    }

    public static void addColorCost(String s1, String s2, int cost) {
        ColorCost cc = new ColorCost(s1, s2, cost);

        colorCostList.add(cc);
    }

    public void print() {
        for (int i = 0; i < colorCostList.size(); i++) {
            colorCostList.get(i).print();
        }
    }

}
