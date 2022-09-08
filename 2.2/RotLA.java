import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class RotLA{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        //ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
        //ArrayList<ArrayList<ArrayList<String>>> map = new ArrayList<ArrayList<ArrayList<String>>>();
        //ArrayList<ArrayList<ArrayList<String>>> map = new ArrayList<>();

        //ArrayList<ArrayList<ArrayList<String>>> kms = new ArrayList<ArrayList<ArrayList<String>>>();

        /*ArrayList<ArrayList<ArrayList<String>>> kms = new ArrayList<>(2);

        kms.get(0).get(0).add(0, "0-0-0");*/
        /*kms.get(0).get(0).add("0-0-0");
        kms.get(0).get(0).add("0-1-0");
        kms.get(0).get(0).add("0-2-0");*/
        //kms.add(new ArrayList<ArrayList<String>>(Arrays.asList("0-0-0", "0-1-0", "0-2-0")));

        /*kms.get(0).add(new ArrayList<String>(new ArrayList<String>(Arrays.asList("0-0-0", "0-1-0", "0-2-0"))));
        kms.get(0).add(new ArrayList<String>(Arrays.asList("0-1-1", "0-1-1", "0-2-1")));
        kms.get(0).add(new ArrayList<String>(Arrays.asList("0-1-2", "0-1-2", "0-2-2")));*/

        /*map.get(0).add(0, "000");
        map.get(0).add(0, "010");
        map.get(0).add(0, "020");*/

        //map.get(0).get(0).add("yoo");

        //map.add(new ArrayList<ArrayList<String>>(Arrays.asList("yoo")));

        int x_axis_length = 5;
        int y_axis_length = 3;
        int z_axis_length = 3;	
        ArrayList<ArrayList<ArrayList<String>>> map = new ArrayList<>(x_axis_length);

        for (int i = 0; i < x_axis_length; i++) {
            map.add(new ArrayList<ArrayList<String>>(y_axis_length));
            for (int j = 0; j < y_axis_length; j++) {
                map.get(i).add(new ArrayList<String>(z_axis_length));
            }
        }

        /*map.get(0).get(0).add(0,"Red");
        map.get(0).get(0).add(1,"Red");

        map.get(0).get(1).add(0,"Blue");
        map.get(0).get(1).add(1,"Blue");*/

        map.get(0).set(0, new ArrayList<String>(Arrays.asList("0-0-0", "0-1-0", "0-2-0")));
        map.get(0).set(1, new ArrayList<String>(Arrays.asList("0-0-1", "0-1-1", "0-2-1")));
        map.get(0).set(2, new ArrayList<String>(Arrays.asList("0-1-2", "0-1-2", "0-2-2")));

        map.get(1).set(0, new ArrayList<String>(Arrays.asList("1-0-0", "1-1-0", "1-2-0")));
        map.get(1).set(1, new ArrayList<String>(Arrays.asList("1-0-1", "1-1-1", "1-2-1")));
        map.get(1).set(2, new ArrayList<String>(Arrays.asList("1-1-2", "1-1-2", "1-2-2")));

        map.get(2).set(0, new ArrayList<String>(Arrays.asList("2-0-0", "2-1-0", "2-2-0")));
        map.get(2).set(1, new ArrayList<String>(Arrays.asList("2-0-1", "2-1-1", "2-2-1")));
        map.get(2).set(2, new ArrayList<String>(Arrays.asList("2-1-2", "2-1-2", "2-2-2")));

        map.get(3).set(0, new ArrayList<String>(Arrays.asList("3-0-0", "3-1-0", "3-2-0")));
        map.get(3).set(1, new ArrayList<String>(Arrays.asList("3-0-1", "3-1-1", "3-2-1")));
        map.get(3).set(2, new ArrayList<String>(Arrays.asList("3-1-2", "3-1-2", "3-2-2")));

        map.get(4).set(0, new ArrayList<String>(Arrays.asList("4-0-0", "4-1-0", "4-2-0")));
        map.get(4).set(1, new ArrayList<String>(Arrays.asList("4-0-1", "4-1-1", "4-2-1")));
        map.get(4).set(2, new ArrayList<String>(Arrays.asList("4-1-2", "4-1-2", "4-2-2")));

        //System.out.println(map.get(0).get(0).get(0));

        //System.out.println(map);

        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.size()-2; j++){
                System.out.println(map.get(i).get(j));
            }
            System.out.println();
        }

        System.out.println("u, d, l, r");
        String dir = s.nextLine();
        //System.out.println(dir);

        String p = "it's a me";
        String store = map.get(0).get(1).get(1);

        ArrayList<Integer> pPos = new ArrayList<>();
        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.size(); j++){
                for(int k = 0; k < map.size(); k++){
                    if(map.get(i).get(j).get(k) == p){
                        pPos.add(i);
                        pPos.add(j);
                        pPos.add(k);
                    }
                }
            }
        }

        if(dir.equals("u")){
            System.out.println("up");

            int x = pPos.get(0);
            int y = pPos.get(1);
            int z = pPos.get(2);

            map.get(x).set(y, store);
            store = map.get(x-1).get(y);
            map.get(x-1).set(y, p);
        }
        else if(dir.equals("d")){
            System.out.println("down");

            int x = pPos.get(0);
            int y = pPos.get(1);
            //int z = pPos.get(2);

            map.get(x).set(y, store);
            store = map.get(x+1).get(y);
            map.get(x+1).set(y, p);
        }
        else if(dir.equals("l")){
            System.out.println("left");

            int x = pPos.get(0);
            int y = pPos.get(1);
            //int z = pPos.get(2);

            map.get(x).set(y, store);
            store = map.get(x).get(y-1);
            map.get(x).set(y-1, p);
        }
        else if(dir.equals("r")){
            System.out.println("right");

            int x = pPos.get(0);
            int y = pPos.get(1);
            //int z = pPos.get(2);
            
            map.get(x).set(y, store);
            store = map.get(x).get(y+1);
            map.get(x).set(y+1, p);
        }
        else{
            System.out.println("kys");
        }

        /*map.add(new ArrayList<String>(Arrays.asList("0-0-0", "0-1-0", "0-2-0")));
        map.add(new ArrayList<String>(Arrays.asList("0-0-1", "0-1-1", "0-2-1")));
        map.add(new ArrayList<String>(Arrays.asList("0-1-2", "0-1-2", "0-2-2")));*/

        //System.out.println(map);

        /*for(int i = 0; i < map.size(); i++){
            System.out.println(map.get(i));
        }*/

        /*String p = "it's a me";
        String store = map.get(1).get(1);

        map.get(1).set(1, p);

        for(int i = 0; i < map.size(); i++){
            System.out.println(map.get(i));
        }

        System.out.println("u, d, l, r");
        String dir = s.nextLine();
        //System.out.println(dir);

        ArrayList<Integer> pPos = new ArrayList<>();
        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.size(); j++){
                if(map.get(i).get(j) == p){
                    pPos.add(i);
                    pPos.add(j);
                }
            }
        }

        if(dir.equals("u")){
            System.out.println("up");

            int x = pPos.get(0);
            int y = pPos.get(1);
            //int z = pPos.get(2);

            map.get(x).set(y, store);
            store = map.get(x-1).get(y);
            map.get(x-1).set(y, p);
        }
        else if(dir.equals("d")){
            System.out.println("down");

            int x = pPos.get(0);
            int y = pPos.get(1);
            //int z = pPos.get(2);

            map.get(x).set(y, store);
            store = map.get(x+1).get(y);
            map.get(x+1).set(y, p);
        }
        else if(dir.equals("l")){
            System.out.println("left");

            int x = pPos.get(0);
            int y = pPos.get(1);
            //int z = pPos.get(2);

            map.get(x).set(y, store);
            store = map.get(x).get(y-1);
            map.get(x).set(y-1, p);
        }
        else if(dir.equals("r")){
            System.out.println("right");

            int x = pPos.get(0);
            int y = pPos.get(1);
            //int z = pPos.get(2);
            
            map.get(x).set(y, store);
            store = map.get(x).get(y+1);
            map.get(x).set(y+1, p);
        }
        else{
            System.out.println("kys");
        }

        for(int i = 0; i < map.size(); i++){
            System.out.println(map.get(i));
        }*/

        /*int vc = 3;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(vc);
        
        for(int i = 0; i < vc; i++) {
            graph.add(new ArrayList());
        }
    
        graph.get(0).add(1);
        graph.get(1).add(2);
        graph.get(2).add(0);
    
        graph.get(1).add(0);
        graph.get(2).add(1);
        graph.get(0).add(2);
    
        int vertexCount = graph.size();
        for (int i = 0; i < vertexCount; i++) {
            int edgeCount = graph.get(i).size();
            for (int j = 0; j < edgeCount; j++) {
                Integer startVertex = i;
                Integer endVertex = graph.get(i).get(j);
                System.out.printf("Vertex %d is connected to vertex %d%n", startVertex, endVertex);
            }
        }

        for(int i = 0; i < vc; i++){
            for(int j = 0; j < vc; j++){
                System.out.println(graph.get(i).get(j));
            }
        }*/

        /*int x_axis_length = 2;
        int y_axis_length = 2;
        int z_axis_length = 2;	
        ArrayList<ArrayList<ArrayList<String>>> map = new ArrayList<>(x_axis_length);

        for (int i = 0; i < x_axis_length; i++) {
            map.add(new ArrayList<ArrayList<String>>(y_axis_length));
            for (int j = 0; j < y_axis_length; j++) {
                map.get(i).add(new ArrayList<String>(z_axis_length));
            }
        }

        map.get(0).get(0).add(0,"Red");
        map.get(0).get(0).add(1,"Red");

        map.get(0).get(1).add(0,"Blue");
        map.get(0).get(1).add(1,"Blue");

        System.out.println(map.get(0).get(0).get(0));*/

        /*for(int i = 0; i < x_axis_length; i++){
            for(int j = 0; j < y_axis_length; j++){
                for(int k = 0; j < z_axis_length; k++){
                    System.out.println(map.get(i).get(j).get(k));
                }
            }
        }*/
    }
}