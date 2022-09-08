import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class RotLA{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

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

        String p = "it's a me";
        String store = map.get(0).get(1).get(1);
        map.get(0).get(1).set(1, p);

        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.size()-2; j++){
                System.out.println(map.get(i).get(j));
            }
            System.out.println();
        }

        System.out.println("n, s, e, w, u, d");
        String dir = s.nextLine();

        while(dir != null){
            if(dir.isEmpty()){
                break;
            }
    
            ArrayList<Integer> pPos = new ArrayList<>();
            for(int i = 0; i < map.size()-2; i++){
                for(int j = 0; j < map.size()-2; j++){
                    for(int k = 0; k < map.size()-2; k++){
                        //System.out.println(i + " " + j + " " + k);
                        if(map.get(i).get(j).get(k) == p){
                            pPos.add(i);
                            pPos.add(j);
                            pPos.add(k);
                        }
                    }
                }
            }
    
            if(pPos.get(1) == 1 && pPos.get(2) == 1){
                System.out.println("n, s, e, w, u, d");
                if(dir.equals("n")){
                    System.out.println("north");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y-1).get(z);
                    map.get(x).get(y-1).set(z, p);
                }
                else if(dir.equals("s")){
                    System.out.println("south");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y+1).get(z);
                    map.get(x).get(y+1).set(z, p);
                }
                else if(dir.equals("e")){
                    System.out.println("east");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y).get(z+1);
                    map.get(x).get(y).set(z+1, p);
                }
                else if(dir.equals("w")){
                    System.out.println("west");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
                    
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y).get(z-1);
                    map.get(x).get(y).set(z-1, p);
                }
                else if(dir.equals("u")){
                    System.out.println("up");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(z).set(y, store);
                    store = map.get(x+1).get(y).get(z);
                    map.get(x+1).get(z).set(y, p);
                }
                else if(dir.equals("d")){
                    System.out.println("down");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(z).set(y, store);
                    store = map.get(x-1).get(y).get(z);
                    map.get(x-1).get(z).set(y, p);
                }
                else{
                    System.out.println("kys");
                }
            }
            else{
                System.out.println("n, s, e, w");
                if(dir.equals("n")){
                    System.out.println("north");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y-1).get(z);
                    map.get(x).get(y-1).set(z, p);
                }
                else if(dir.equals("s")){
                    System.out.println("south");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y+1).get(z);
                    map.get(x).get(y+1).set(z, p);
                }
                else if(dir.equals("e")){
                    System.out.println("east");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y).get(z+1);
                    map.get(x).get(y).set(z+1, p);
                }
                else if(dir.equals("w")){
                    System.out.println("west");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
                    
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y).get(z-1);
                    map.get(x).get(y).set(z-1, p);
                }
                else{
                    System.out.println("kys");
                }
            }
    
            for(int i = 0; i < map.size(); i++){
                for(int j = 0; j < map.size()-2; j++){
                    System.out.println(map.get(i).get(j));
                }
                System.out.println();
            }
            dir = s.nextLine();
        }
    }
}